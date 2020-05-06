public class CollectionItem {

    private String fileName;
    private long fileSize;
    private int wordsCount;

    public CollectionItem(String fileName, long fileSize, int wordsCount) {
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.wordsCount = wordsCount;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public int getWordsCount() {
        return wordsCount;
    }

    public void setWordsCount(int wordsCount) {
        this.wordsCount = wordsCount;
    }
}