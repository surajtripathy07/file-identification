package fileInfo.core.filesource;

import fileInfo.core.models.FileInfo;
import fileInfo.core.models.FileModel;
import org.springframework.stereotype.Service;

/**
 *
 * Represents a single Data Source.
 * Each FileSource encapsulates the logic
 * to collect information about the {@link FileModel#fileName
 * fileName}, {@link FileModel#fileExtension extension}
 *
 * @author suraj
 */
@Service
public interface FileSource {

    /**
     * Given a File Model, this method will fetch DataSource
     * specific information for that File Model and return
     * the specific File Info.
     *
     * @param fileModel
     * @return {@link FileInfo FileInfo} with appropriate information or
     *         {@link fileInfo.core.constants.Constants#NOT_FOUND NOT FOUND} when no information corresponding to the {@link FileModel FileMode} is found.
     */
    public FileInfo getFileInfoFromSource(FileModel fileModel);

}
