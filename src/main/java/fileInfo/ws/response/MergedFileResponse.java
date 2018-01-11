package fileInfo.ws.response;

import java.util.List;

/**
 * Response for the List of passed FileName
 */
public class MergedFileResponse {
    private String message;

    private List<FileRecord> files;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public List<FileRecord> getFiles() {
        return files;
    }

    public void setFiles(List<FileRecord> files) {
        this.files = files;
    }
}
