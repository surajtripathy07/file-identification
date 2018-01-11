package fileInfo.ws.service.impl;

import fileInfo.ws.response.FileRecord;
import fileInfo.ws.service.AsyncService;
import fileInfo.ws.service.FileService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Service
public class FileServiceImpl implements FileService {

    Logger logger = Logger.getLogger(FileServiceImpl.class);
    @Autowired
    private AsyncService asyncService;

    @Override
    public List<FileRecord> getFileInfoForList(List<String> fileNames) {

        Collections.sort(fileNames);
        List<Future<FileRecord>> futureReturns = new ArrayList<>();

        for(String fileName : fileNames){
            logger.info("Passing fileName for processing: "+fileName);
            futureReturns.add(asyncService.processFile(fileName));
        }

        List<FileRecord> fileRecordList = new ArrayList<>();
        for(Future<FileRecord> fileRecordFuture : futureReturns){
            try {
                fileRecordList.add(fileRecordFuture.get());
            } catch (InterruptedException | ExecutionException e){
                logger.error("Error while processing: ", e);
            }
        }
        return fileRecordList;
    }
}
