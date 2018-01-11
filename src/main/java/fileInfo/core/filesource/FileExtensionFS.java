package fileInfo.core.filesource;

import fileInfo.core.constants.Constants;
import fileInfo.core.models.FileInfo;
import fileInfo.core.models.FileModel;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * File Source implementation corresponding to
 * Short Description of File Extension.
 *
 * @author suraj
 */
@Service
public class FileExtensionFS implements FileSource {

    Logger logger = Logger.getLogger(FileExtensionFS.class);
    private Map<String, String> pages;

    public FileExtensionFS(){
        pages = new HashMap<>();
    }

    /**
     *
     * @param fileModel
     * @return Short description of FileExtension
     */
    @Override
    public FileInfo getFileInfoFromSource(FileModel fileModel) {
        String info = getExtensionInfo(fileModel.getFileExtension());
        return new FileInfo(info);
    }

    public String getExtensionInfo(String extension){

        String extInfo = Constants.NOT_FOUND;
        try {
            String html = getHTMLPAGE(Constants.FILE_EXTENSION_URL, extension.substring(0, 1));
            extension = extension.toUpperCase();
            String regEx = "target=\"_blank\">"+extension+"<\\/a><\\/td>\\s+<td>(.*?)<\\/td>";
            Pattern pattern = Pattern.compile(regEx);
            Matcher matcher = pattern.matcher(html);
            if (matcher.find()) {
                extInfo = matcher.group(1);
            }
        }
        catch(IOException e){
            logger.error("Error while getting FileExtension Info for extension: "+extension, e);
        }
        return extInfo;
    }

    public String getHTMLPAGE(String url, String startIndex) throws IOException{
        url = MessageFormat.format(url, startIndex);

        if (pages.containsKey(startIndex)){
            return pages.get(startIndex);
        }
        String response = Constants.getCall(url);
        if (response != null) {
            pages.put(startIndex, response.toString());
        }
        return response;
    }


}
