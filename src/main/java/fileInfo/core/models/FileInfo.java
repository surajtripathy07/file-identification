package fileInfo.core.models;

/**
 * @author suraj
 * Represents the Info corresponding to the FileObject
 * which was collected from the source
 */
public class FileInfo {
    private String info;

    public FileInfo(String info){
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "FileInfo{" +
                "info='" + info + '\'' +
                '}';
    }
}
