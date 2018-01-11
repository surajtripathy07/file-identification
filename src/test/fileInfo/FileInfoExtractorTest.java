package fileInfo;


import fileInfo.core.constants.Constants;
import fileInfo.core.models.FileModel;
import fileInfo.core.processor.CollectInfoForFile;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Basic test cases to test the core logic of file info extraction
 */
public class FileInfoExtractorTest{
    private CollectInfoForFile collectInfoForFile;

    @Before
    public void setUp(){
        collectInfoForFile = new CollectInfoForFile();
    }

    @Test(expected = RuntimeException.class)
    public void testFetchNull(){
        collectInfoForFile.collectInfoForFile(null);
    }

    @Test(expected = RuntimeException.class)
    public void testFetchEmpty(){
        collectInfoForFile.collectInfoForFile("");
    }

    @Test(expected = RuntimeException.class)
    public void testInvalidFileType(){
        collectInfoForFile.collectInfoForFile("a");
    }

    @Test
    public void testValidData(){
        FileModel fileModel = collectInfoForFile.collectInfoForFile("abc.png");
        Assert.assertNotNull(fileModel);
        Assert.assertNotNull(fileModel.getFileInfo());
        System.out.println(fileModel);
        Assert.assertEquals(fileModel.getFileInfo().size(), 2);
    }

    @Test
    public void testExtensionOnlyData(){
        FileModel fileModel = collectInfoForFile.collectInfoForFile(".dpkg");
        Assert.assertNotNull(fileModel);
        Assert.assertNotNull(fileModel.getFileInfo());
        System.out.println(fileModel);
        Assert.assertEquals(fileModel.getFileInfo().size(), 2);
    }

    @Test
    public void testBadlyFormatedData1(){
        FileModel fileModel = collectInfoForFile.collectInfoForFile(".                png     ");
        Assert.assertNotNull(fileModel);
        Assert.assertNotNull(fileModel.getFileInfo());
        System.out.println(fileModel);
        Assert.assertEquals(fileModel.getFileInfo().size(), 2);
    }

    @Test
    public void testBadlyFormatedData2(){
        FileModel fileModel = collectInfoForFile.collectInfoForFile("@And then there were none. To be or !not to be is the question$");
        Assert.assertNotNull(fileModel);
        Assert.assertNotNull(fileModel.getFileInfo());
        System.out.println(fileModel);
        Assert.assertEquals(fileModel.getFileInfo().size(), 2);
    }

    @Test
    public void testSpecialCharacterData(){
        FileModel fileModel = collectInfoForFile.collectInfoForFile("$$$$$$$$$$$$$$$$$$$$$$$.$$$$$$$$$$$");
        Assert.assertNotNull(fileModel);
        Assert.assertNotNull(fileModel.getFileInfo());
        System.out.println(fileModel);
        Assert.assertEquals(fileModel.getFileInfo().size(), 2);
        Assert.assertEquals(fileModel.getFileInfo().get(0).getInfo(), Constants.NOT_FOUND);
        Assert.assertEquals(fileModel.getFileInfo().get(1).getInfo(), Constants.NOT_FOUND);
    }
}
