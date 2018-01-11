package fileInfo.ws.service.impl;

import fileInfo.core.constants.Constants;
import fileInfo.core.models.FileModel;
import fileInfo.core.processor.CollectInfoForFile;
import fileInfo.ws.response.FileRecord;
import fileInfo.ws.service.AsyncService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.concurrent.Future;

@Service
public class AsyncServiceImpl implements AsyncService {
    @Autowired
    private CollectInfoForFile collectInfoForFile;

    Logger logger = Logger.getLogger(AsyncService.class);

    @Async
    @Override
    public Future<FileRecord> processFile(String fileName){
        logger.debug("Piked up fileName ["+fileName+"] for processing");
        FileModel fileModel = collectInfoForFile.collectInfoForFile(fileName);
        logger.debug("Finished processing fileName ["+fileName+"]");
        return new AsyncResult<FileRecord>(getFileRecordFromFileModel(fileModel));
    }

    private FileRecord getFileRecordFromFileModel(FileModel fileModel){
        FileRecord record = new FileRecord();
        record.setFileName(MessageFormat.format(Constants.FILE_NAME, fileModel.getFileName(),fileModel.getFileExtension()));
        record.setShortDescription(fileModel.getFileInfo().get(0).getInfo());
        record.setMediaType(fileModel.getFileInfo().get(1).getInfo());

        return record;
    }
}
