package fileInfo.console;

import fileInfo.console.display.ConsoleFileRecord;
import fileInfo.core.constants.Constants;
import fileInfo.core.models.FileModel;
import fileInfo.core.processor.CollectInfoForFile;
import org.apache.log4j.Logger;

import java.text.MessageFormat;
import java.util.Scanner;

/**
 *
 * This is a Multi Threaded Console app
 * This app allows the test of FileInfo Extraction through
 * a console environment.
 *
 */
public class MainApplication extends Thread {
    private CollectInfoForFile collectInfoForFile;
    private String fileName;
    Logger logger = Logger.getLogger(MainApplication.class);

    MainApplication(CollectInfoForFile collectInfoForFile, String fileName){
        this.collectInfoForFile = collectInfoForFile;
        this.fileName = fileName;
    }

    @Override
    public void run(){
        try {
            FileModel m = collectInfoForFile.collectInfoForFile(fileName);
            logger.info("Processed FileModel is: "+getConsoleRecordFromFileMode(m));
        } catch (Exception e){
            logger.error("Error while collecting file Info for fileName: "+fileName, e);
        }

    }

    public static void main(String args[]){
        System.out.println("Pass comma separated list of fileNames: ");
        Scanner sc = new Scanner(System.in);
        String fileNames = sc.nextLine();

        String[] listOfFileNames = fileNames.split(",");
        CollectInfoForFile f = new CollectInfoForFile();

        for (String fName : listOfFileNames) {
            Thread t = new MainApplication(f, fName);
            t.start();
        }
    }

    private ConsoleFileRecord getConsoleRecordFromFileMode(FileModel fileModel){
        ConsoleFileRecord consoleFileRecord = new ConsoleFileRecord();
        consoleFileRecord.setFileName(MessageFormat.format(Constants.FILE_NAME, fileModel.getFileName(), fileModel.getFileExtension()));
        consoleFileRecord.setShortDescription(fileModel.getFileInfo().get(0).getInfo());
        consoleFileRecord.setMimeType(fileModel.getFileInfo().get(1).getInfo());

        return consoleFileRecord;
    }
}
