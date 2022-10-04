package data;

import java.util.Objects;

public class FileClackData extends ClackData {
    private String fileName;
    private String fileContents;

    public FileClackData(String u, String fN, int t) {
        super(u, t);
        fileName=fN;
        fileContents=null;
    }

    public FileClackData() { super(); }

    public void setFileName(String fN) { fileName=fN; }

    public String getFileName() { return fileName; }

    @Override
    public String getData() { return fileContents; }

    public void readFileContents() {}

    public void writeFileContents() {}

    @Override
    public int hashCode() {
        return Objects.hash(fileName, fileContents, getDate(), getType(), getUsername());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FileClackData)) return false;
        FileClackData that = (FileClackData) o;
        return getFileName().equals(that.getFileName()) && Objects.equals(fileContents, that.fileContents);
    }

    @Override
    public String toString() {
        return "data.FileClackData{" +
                "fileName='" + fileName + '\'' +
                ", fileContents='" + fileContents + '\'' +
                ", type=" + getType() +
                ", username='" + getUsername() + '\'' +
                ", date=" + getDate() +
                '}';
    }
}