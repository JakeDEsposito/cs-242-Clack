package data;

import java.io.*;
import java.io.IOException;
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
     * <p>is for getting the file contents of the object</p>
     * String getData(String k)
     * @param k is the key for decrypting the message
     * @return returns the decrypted fileContents
     */
    public String getData(String k){ return decrypt(fileContents, k); }

    /* is for opening a file reading its contents closing the file and returning the contents
     */
    private String readFile() throws IOException{
        String contents="";
        File file=new File(fileName);
        try{
            BufferedReader buffer=new BufferedReader(new FileReader(file));
            String nextLine;
            if((nextLine= buffer.readLine())!=null){
                contents=contents.concat(nextLine);
            }
            while ((nextLine=buffer.readLine())!=null){
                contents=contents.concat("\n").concat(nextLine);
            }
            buffer.close();
        } catch (FileNotFoundException fife) {
            System.err.println("The file "+fileName+" can not be found.");
        } catch (IOException ioe) {
            System.err.println("Error in opening, writing to, or closing the file.");
        }
        return contents;
    }

    /**
     * <p>method to read the file contents in the file specified by fileName parameter</p>
     * void readFileContents()
     * @throws IOException if there is an error in opening, writing to, or closing the file
     */
    public void readFileContents() throws IOException {
        fileContents=readFile();
    }

    /**
     * <p>method to read the file contents in the file specified by fileName parameter and
     * encrypt the contents of the file</p>
     * void readFileContents(String k)
     * @param k is the key for encrypting the file contents
     * @throws IOException if there is an error in opening, writing to, or closing the file
     */
    public void readFileContents(String k) throws IOException {
        fileContents=encrypt(readFile(), k);
    }

    /* is for opening/creating a file writing its contents closing the file
     */
    private void writeFile(String contents) throws IOException{
        File file=new File(fileName);
        try{
            BufferedWriter buffer=new BufferedWriter(new FileWriter(file));
            buffer.write(contents);
            buffer.close();
        } catch (FileNotFoundException fife) {
            System.err.println("File does not exist.");
        } catch (IOException ioe) {
            System.err.println("Error in opening, writing to, or closing the file.");
        }
    }

    /**
     * <p>method to write the file contents in the file specified by fileName parameter after
     * decrypting the contents of the file</p>
     * void writeFileContents()
     * @throws IOException if there is an error in opening, writing to, or closing the file
     */
    public void writeFileContents() throws IOException{
        writeFile(fileContents);
    }

    /**
     * <p>method to write the file contents in the file specified by fileName parameter after
     * decrypting the contents of the file</p>
     * void writeFileContents(String k)
     * @param k is the key for decrypting the file contents
     * @throws IOException if there is an error in opening, writing to, or closing the file
     */
    public void writeFileContents(String k) throws IOException{
        writeFile(decrypt(fileContents, k));
    }

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