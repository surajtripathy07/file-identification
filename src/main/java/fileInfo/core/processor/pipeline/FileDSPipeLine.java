package fileInfo.core.processor.pipeline;

import fileInfo.core.models.FileModel;
import org.springframework.stereotype.Service;

/**
 * Represents a pipe-line Executor which is in-charge
 * of allowing each {@link fileInfo.core.filesource.FileSource FileSource}
 * an opportunity to process the request.
 *
 * @author suraj
 */
@Service
public interface FileDSPipeLine {

    /**
     * This method will pass the file-model through the pipeline
     * and allow each {@link fileInfo.core.filesource.FileSource FileSource}
     * to return {@link fileInfo.core.models.FileInfo FileInfo} for the model.
     *
     * @param model the File for which information is to be fetched
     * @return Updated FileModel
     */
    public FileModel fetchInfoForFile(FileModel model);
}
