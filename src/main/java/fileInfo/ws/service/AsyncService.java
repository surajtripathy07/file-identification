package fileInfo.ws.service;

import fileInfo.ws.response.FileRecord;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

/**
 * Asynchronous processing Service
 */
@Service
public interface AsyncService {

    public Future<FileRecord> processFile(String fileName);
}
