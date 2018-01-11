package fileInfo.ws.controller;

import fileInfo.ws.constant.ResponseMessages;
import fileInfo.ws.constant.URLConstants;
import fileInfo.ws.response.FileRecord;
import fileInfo.ws.response.MergedFileResponse;
import fileInfo.ws.service.FileService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.MessageFormat;
import java.util.List;

/**
 * Rest Controller which is entry point for File Processing API
 *
 * @author suraj
 */
@RestController
public class FileController {

    @Autowired
    private FileService fileService;
    Logger logger = Logger.getLogger(FileController.class);

    @RequestMapping(value = URLConstants.PING, method = RequestMethod.GET)
    public String ping() {
        return "Ok";
    }

    @RequestMapping(value = URLConstants.FILE_INFO_PROCESS, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MergedFileResponse> processFiles(@RequestBody List<String> fileNames){
        // validation of names
        MergedFileResponse response = new MergedFileResponse();
        // if empty list then return as processed
        if (fileNames == null || fileNames.isEmpty()){
            logger.error("Empty List passed for processing");
            response.setMessage(ResponseMessages.SUCCESS_MSG);
            return new ResponseEntity<MergedFileResponse>(response, HttpStatus.OK);
        }

        for(String name : fileNames) {
            if (name == null || name.isEmpty()){
                logger.error("Empty/ Null file name passed for processing");
                response.setMessage(ResponseMessages.INVALID_FILE_NAME);
                return new ResponseEntity<MergedFileResponse>(response, HttpStatus.BAD_REQUEST);
            }

            // check if fileName is a valid file Name of type name.extension
            if (name.split("\\.").length < 2){
                String error = MessageFormat.format(ResponseMessages.FILE_NAME_FORMAT_ERROR, name);
                logger.error("Invalid file format "+ name);
                response.setMessage(error);
                return new ResponseEntity<MergedFileResponse>(response, HttpStatus.BAD_REQUEST);
            }
        }


        List<FileRecord> fileRecordList = fileService.getFileInfoForList(fileNames);
        response.setFiles(fileRecordList);
        response.setMessage(ResponseMessages.SUCCESS_MSG);

        return new ResponseEntity<MergedFileResponse>(response, HttpStatus.OK);


    }

}
