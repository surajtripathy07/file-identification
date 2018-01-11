package fileInfo.core.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents One file Object, and will eventually contain
 * the fetched information for that file
 */
public class FileModel {

    private String fileName;
    private String fileExtension;
    private List<FileInfo> fileInfo;

    public FileModel(String fileName, String fileExtension){
        this.fileName = fileName;
        this.fileExtension = fileExtension;
        this.fileInfo = new ArrayList<>();
    }

    public List<FileInfo> getFileInfo() {
        return fileInfo;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    /**
     * Adds new acquired information for this file
     * @param newInfo
     */
    public void addFileInfo(FileInfo newInfo){
        this.fileInfo.add(newInfo);
    }

    @Override
    public String toString() {
        return "FileModel{" +
                "fileName='" + fileName + '\'' +
                ", fileExtension='" + fileExtension + '\'' +
                ", fileInfo=" + fileInfo +
                '}';
    }
}
