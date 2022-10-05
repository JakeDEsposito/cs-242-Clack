package data;

import java.util.Objects;

/**
 * data.FileClackData is an abstract class that all subsequent data classes are based on
 * @author Graham Zarriello
 */
public class FileClackData extends ClackData {
    private String fileName;
    private String fileContents;

    /**
     * <p>The constructor of the class which automatically sets the date of creation.</p>
     * public data.FileClackData(String u, String fN, int t)
     * @param u is for the username
     * @param fN is for the file name
     * @param t is for the type
     *          <ul>
     *              <li>1 is for listing all users connected to this session</li>
     *              <li>2 is for logout/close this client's connection</li>
     *              <li>3 is for send a message</li>
     *              <li>4 is for send a file</li>
     *          </ul>
     */
    public FileClackData(String u, String fN, int t) {
        super(u, t);
        fileName=fN;
        fileContents=null;
    }

    /**
     *<p>default constructor which defaults to username anon and type to 2  logout</p>
     * public data.FileClackData()
     */
    public FileClackData() { super(); }

    /**
     * <p>is for changing the file name</p>
     * void setFileName(String fN)
     * @param fN is the new file name that will be used
     */
    public void setFileName(String fN) { fileName=fN; }

    /**
     * <p>is for getting the file name that is being used</p>
     * String getFileName()
     * @return String
     */
    public String getFileName() { return fileName; }

    /**
     * <p>is for getting the file contents of the object</p>
     * String getData()
     * @return String
     */
    @Override
    public String getData() { return fileContents; }

    /**
     * <p>TBD</p>
     * void readFileContents()
     */
    public void readFileContents() {}

    /**
     * <p>TBD</p>
     * void writeFileContents()
     */
    public void writeFileContents() {}

    /**
     * <p>is for generating the hashCode of the object</p>
     * int hashCode()
     * @return int
     */
    @Override
    public int hashCode() {
        return Objects.hash(fileName, fileContents, getType(), getUsername());
    }

    /**
     * <p>is for checking to see if another instance of FileClackData is equal to this instance</p>
     * boolean equals(Object o)
     * @param o is for getting the other instance of FileClackData
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FileClackData)) return false;
        FileClackData that = (FileClackData) o;
        return (getFileName().equals(that.getFileName()) && Objects.equals(fileContents, that.fileContents)&&getUsername().equals(((FileClackData) o).getUsername())&&getType()==((FileClackData) o).getType());
    }

    /**
     * <p>is for generating a string that represents the entire object</p>
     * String toString()
     * @return String
     */
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