package fileInfo.ws.service;

import fileInfo.ws.response.FileRecord;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Proccess List of files and gathers information for each
 */
@Service
public interface FileService {
    /**
     * Returns Structured information for the passed list of
     * file names
     * @param fileNames
     * @return
     */
    public List<FileRecord> getFileInfoForList(List<String> fileNames);
}
