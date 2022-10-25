package data;

import java.util.Objects;

/**
 * data.MessageClackData is an abstract class that all subsequent data classes are based on
 * @author Graham Zarriello
 */
public class MessageClackData extends ClackData {
    private String message;

    /**
     * <p>The constructor of the class which automatically sets the date of creation.</p>
     * public data.MessageClackData(String u, String m, int t)
     * @param u is for the username
     * @param m is for the instant message contents
     * @param t is for the type
     *          <ul>
     *              <li>1 is for listing all users connected to this session</li>
     *              <li>2 is for logout/close this client's connection</li>
     *              <li>3 is for send a message</li>
     *              <li>4 is for send a file</li>
     *          </ul>
     */
    public MessageClackData(String u, String m, int t) {
        super(u, t);
        this.message=m;
    }

    /**
     * <p>The constructor of the class which automatically sets the date of creation
     * and encrypts the message</p>
     * public data.MessageClackData(String u, String m, String k, int t)
     * @param u is for the username
     * @param m is for the instant message contents
     * @param k is for the encryption key
     * @param t is for the type
     *          <ul>
     *              <li>1 is for listing all users connected to this session</li>
     *              <li>2 is for logout/close this client's connection</li>
     *              <li>3 is for send a message</li>
     *              <li>4 is for send a file</li>
     *          </ul>
     */
    public MessageClackData(String u, String m, String k, int t){
        super(u, t);
        this.message=encrypt(m, k);
    }

    /**
     *<p>default constructor which defaults to username anon and type 2  logout</p>
     * public data.MessageClackData()
     */
    public MessageClackData() { super(); }

    /**
     * <p>Is for retrieving the contents of the message</p>
     * String getData()
     * @return String
     */
    @Override
    public String getData() { return message; }

    /**
     *<p>default constructor which defaults to username anon and type 2  logout</p>
     * public data.MessageClackData(String k)
     * @param k is the key for decrypting the message
     * @return returns the decrypted message
     */
    public String getData(String k) { return decrypt(message, k); }

    /**
     * <p>is for generating and returning the hash code of the object</p>
     * int hashCode()
     * @return int
     */
    @Override
    public int hashCode() {
        return Objects.hash(message, getType(), getUsername());
    }

    /**
     * <p>is for checking if different MessageClackData objects are equal to each other</p>
     * boolean equals(Object o)
     * @param o is for the other MessageClackData object
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MessageClackData)) return false;
        MessageClackData that = (MessageClackData) o;
        return (message.equals(that.message)&&getUsername().equals(that.getUsername())&&(getType()==that.getType()));
    }

    /**
     * <p>is for converting the MessageClackData object to a string;
     * will contain the message, type, username, and date</p>
     * String toString()
     * @return String
     */
    @Override
    public String toString() {
        return "data.MessageClackData{" +
                "message='" + message + '\'' +
                ", type=" + getType() +
                ", username='" + getUsername() + '\'' +
                ", date=" + getDate() +
                '}';
    }
}