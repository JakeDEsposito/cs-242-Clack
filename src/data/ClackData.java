package data;

import java.io.Serializable;
import java.util.Date;

/**
 * data.ClackData is an abstract class that all subsequent data classes are based on
 * @author Graham Zarriello
 */
public abstract class ClackData implements Serializable {
    private String username;
    private int type;
    private Date date;
    public final static int CONSTANT_LISTUSERS=0;
    public final static int CONSTANT_LOGOUT=1;
    public final static int CONSTANT_SENDMESSAGE=2;
    public final static int CONSTANT_SENDFILE=3;

    /**
     * <p>The constructor of the class which automatically sets the date of creation.</p>
     * public data.ClackData(String u, int t)
     * @param u is for the username
     * @param t is for the type
     *          <ul>
     *              <li>1 is for listing all users connected to this session</li>
     *              <li>2 is for logout/close this client's connection</li>
     *              <li>3 is for send a message</li>
     *              <li>4 is for send a file</li>
     *          </ul>
     */
    public ClackData(String u, int t) {
        this.username=u;
        this.type=t;
        this.date=new Date();
    }

    /**
     * <p>The constructor of the class which automatically sets the date of creation
     * and sets the username to anon</p>
     * public data.ClackData(int t)
     * @param t is for the type
     *          <ul>
     *              <li>1 is for listing all users connected to this session</li>
     *              <li>2 is for logout/close this client's connection</li>
     *              <li>3 is for send a message</li>
     *              <li>4 is for send a file</li>
     *          </ul>
     */
    public ClackData(int t) { this("Anon", t); }

    /**
     *<p>default constructor which defaults to username anon and type to 2  logout</p>
     * public data.ClackData()
     */
    public ClackData() { this(2); }

    /**
     *<p>is for checking what type this instance is</p>
     * int getType()
     * @return int
     *          <ul>
     *              <li>1 is for listing all users connected to this session</li>
     *              <li>2 is for logout/close this client's connection</li>
     *              <li>3 is for send a message</li>
     *              <li>4 is for send a file</li>
     *          </ul>
     */
    public int getType() { return type; }

    /**
     *<p>is for getting the username</p>
     * String getUsername()
     * @return String
     */
    public String getUsername() {
        return username;
    }

    /**
     *<p>is for getting when the instance was created</p>
     * Date getDate()
     * @return Date
     */
    public Date getDate(){
        return date;
    }

    /**
     *<p>is an abstract for getting the data of the object</p>
     * abstract String getData()
     * @return n/a
     */
    public abstract String getData();

    /**
     * <p>is an abstract for getting the data of the object</p>
     * abstract String getData(String k)
     * @param k is the key for decrypting the message
     * @return n/a
     */
    public abstract String getData(String k);

    /**
     * Encrypts a string with the given key using Vigenère cipher
     * @param inputStringToEncrypt is the input string that will be encrypted
     * @param key is the key that will be used to encrypt the input string
     *
     * @return The encrypted string
     */
    protected String encrypt (String inputStringToEncrypt, String key) {
        String encrypted = "";

        for (int i = 0, j = 0; i < inputStringToEncrypt.length(); ++i) {
            char c = inputStringToEncrypt.charAt(i);
            if (c >= 'a' && c <= 'z')
                c += 'A' - 'a';
            else if (c < 'A' || c > 'Z')
                continue;
            encrypted += (c + key.charAt(j) - 2 * 'A') % 26 + 'A';
            j = (j + 1) % key.length();
        }

        return encrypted;
    }

    /**
     * Decrypts a string with the given key using Vigenère cipher
     *
     * @param inputStringToDecrypt is the encrypted string that will be decrypted
     * @param key is the key that will be used to decrypt the string
     *
     * @return The decrypted string
     */
    protected String decrypt (String inputStringToDecrypt, String key) {
        String decrypted = "";

        for (int i = 0, j = 0; i < inputStringToDecrypt.length(); ++i) {
            char c = inputStringToDecrypt.charAt(i);
            if (c >= 'a' && c <= 'z')
                c += 'A' - 'a';
            else if (c < 'A' || c > 'Z')
                continue;
            decrypted += (c - key.charAt(j) + 26) % 26 + 'A';//added 'A' to bring it in range of ASCII alphabet [ 65-90 | A-Z ]
            j = (j + 1) % key.length();
        }

        return decrypted;
    }
}