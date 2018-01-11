package fileInfo.core.processor;

import fileInfo.core.filesource.FileExtensionFS;
import fileInfo.core.filesource.MimeTypeFS;
import fileInfo.core.models.FileModel;
import fileInfo.core.processor.pipeline.FileDSPipeLine;
import fileInfo.core.processor.pipeline.FileSourcePipeLine;
import org.springframework.stereotype.Service;

/**
 * @author suraj
 * This class is the entry point for Gathering information
 * for a passed String
 */
@Service
public class CollectInfoForFile {

    private FileDSPipeLine fileDSPipeLine;

    public CollectInfoForFile(){
     this.fileDSPipeLine = new FileSourcePipeLine(new FileExtensionFS(), new MimeTypeFS());
    }

    /**
     * Collects FileName Info from all the available DataSources
     *
     * @throws RuntimeException if fileName is improper.
     * @param fileName for which information needs to be collected
     * @return {@link FileModel FileModel} which will contain all the information from
     * the data-sources.
     */
    public FileModel collectInfoForFile(String fileName){
        if (fileName == null || fileName.isEmpty()){
            throw new RuntimeException("Empty File Name");
        }
        String[] nameExtension = fileName.split("\\.");
        if (nameExtension.length < 2){
            throw new RuntimeException("Improper fileName");
        }

        // For multiple extension document, the last extension will be considered
        // eg abc.tar.gz will become name = abc.tar, extension = gz
        StringBuilder name = new StringBuilder(nameExtension[0].trim());
        for (int i = 1; i < nameExtension.length - 1; i++){
            name.append(".").append(nameExtension[i].trim());
        }

        return fileDSPipeLine.fetchInfoForFile(new FileModel(name.toString(), nameExtension[nameExtension.length - 1]));
    }
}
