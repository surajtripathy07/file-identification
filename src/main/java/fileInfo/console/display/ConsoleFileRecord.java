package fileInfo.console.display;

public class ConsoleFileRecord {

    private String fileName;
    private String shortDescription;
    private String mimeType;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }


    @Override
    public String toString() {
        return "{" +
                "fileName='" + fileName + '\'' +
                ", shortDescription='" + shortDescription + '\'' +
                ", mimeType='" + mimeType + '\'' +
                '}';
    }
}
