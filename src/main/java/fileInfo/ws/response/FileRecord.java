package fileInfo.ws.response;

/**
 * Corresponds to a single display merged record
 * for the file.
 *
 */
public class FileRecord {
    private String fileName;
    private String shortDescription;

    private String mediaType;

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

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }
}
