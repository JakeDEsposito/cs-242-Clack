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

    /**
     * The constructor of the class which automaticaly sets the date & tie of creation
     * public data.ClackData(String u, int t)
     * @param u is for the username
     * @param t is for the type
     *          1 is for listing all useres conencted to this session
     *          2 is for logout/close this client's connection
     *          3 is for send a message
     *          4 is for send a file
     */
    public ClackData(String u, int t) {
        this.username=u;
        this.type=t;
        this.date=new Date();
    }

    /**
     * The constructor of the class which automaticaly sets the date & tie of creation
     * and sets the username to anon
     * public data.ClackData(int t)
     * @param t is for the type
     *          1 is for listing all useres conencted to this session
     *          2 is for logout/close this client's connection
     *          3 is for send a message
     *          4 is for send a file
     */
    public ClackData(int t) { this("Anon", t); }

    /**
     *default constructor which defaults to username anon & type2  logout
     * public data.ClackData()
     */
    public ClackData() { this(2); }

    /**
     *is for checking what type this instance is
     * int getType()
     * @return int
     *          1 is for listing all useres conencted to this session
     *          2 is for logout/close this client's connection
     *          3 is for send a message
     *          4 is for send a file
     */
    public int getType() { return type; }

    /**
     *is for getting the username
     * String getUsername()
     * @return String
     */
    public String getUsername() {
        return username;
    }

    /**
     *is for getting when the instance was created
     * Date getDate()
     * @return Date
     */
    public Date getDate(){
        return date;
    }

    /**
     *is an abstract for getting the data of the object
     * abstract String getData()
     * @return n/a
     */
    public abstract String getData();
}