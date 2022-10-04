package data;

import java.util.Date;

/**
 * data.ClackData is an abstract class that all subsequent data classes are based on
 * @author Graham Zarriello
 */
public abstract class ClackData {
    private String username;
    private int type;
    private Date date;
    public final int CONSTANT_LISTUSERS=0;
    public final int CONSTANT_LOGOUT=1;
    public final int CONSTANT_SENDMESSAGE=2;
    public final int CONSTANT_SENDFILE=3;

    /**
     * <p>The constructor of the class which automaticaly sets the date of creation.</p>
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
     * <p>The constructor of the class which automaticaly sets the date of creation
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
}