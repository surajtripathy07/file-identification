package fileInfo.core.processor.pipeline;

import fileInfo.core.filesource.FileSource;
import fileInfo.core.models.FileModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * This class is incharge of taking a FileModel and fetching information
 * from all the dataSource for that FileModel and returning that Information.
 *
 */
@Service
public class FileSourcePipeLine implements FileDSPipeLine {

    private final List<FileSource> dataSources;

    @Autowired
    public FileSourcePipeLine(FileSource... fileSource){
        dataSources = Arrays.asList(fileSource);
    }

    @Override
    public FileModel fetchInfoForFile(FileModel model) {
        for (FileSource fs : dataSources) {
            model.addFileInfo(fs.getFileInfoFromSource(model));
        }

        return model;
    }
}
