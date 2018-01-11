package fileInfo.core.filesource;

import fileInfo.core.constants.Constants;
import fileInfo.core.models.FileInfo;
import fileInfo.core.models.FileModel;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * File Source corresponding to MIME-Type/Media Type
 * corresponding to the file Extension.
 *
 */
@Service
public class MimeTypeFS implements FileSource {

    private Map<String, String> mimeMap;
    private String mimeHTML;

    Logger logger = Logger.getLogger(MimeTypeFS.class);

    public MimeTypeFS(){
        mimeMap = new HashMap<>();
    }

    /**
     *
     * @param fileModel
     * @return Mime type info
     */
    @Override
    public FileInfo getFileInfoFromSource(FileModel fileModel) {
        String info = getMimeTypeOfExtension(fileModel.getFileExtension());
        return new FileInfo(info);
    }

    private String getMimeTypeOfExtension(String extension){

        if (mimeHTML == null) {
            try {
                mimeHTML = Constants.getCall(Constants.MIME_TYPE_URL);
            } catch (IOException io){
                logger.error("Error while getting MIME-Type for extension: "+extension, io);
                return Constants.NOT_FOUND;
            }
            mimeHTML = mimeHTML.replaceAll("#.*", "");
            parseHTMLandCache(mimeHTML);
        }
        extension = extension.toLowerCase();

        return mimeMap.containsKey(extension) ? mimeMap.get(extension) : Constants.NOT_FOUND;
    }

    private void parseHTMLandCache(String html){
        String[] lines = html.split("\n");
        for (String line: lines) {
            if (line != null && !line.isEmpty()){
                String[] splitLine = line.split("\\s");
                for (int i = 1; i < splitLine.length; i++) {
                    if (!splitLine[i].isEmpty()) {
                        mimeMap.put(splitLine[i], splitLine[0]);
                    }
                }
            }
        }
    }
}
