class ClackClient {
    /**
     * String representing name of the client
     */
    private String userName;

    /**
     * String representing name of the computer representing the server
     */
    private String hostName;

    /**
     * integer representing port number on server connected to
     */
    private int port;

    /**
     * boolean representing whether connection is closed or not
     */
    private boolean closeConnection;

    /**
     * ClackData object representing data sent to server
     */
    private ClackData dataToSendToServer;

    /**
     * ClackData object representing data received from the server
     */
    private ClackData dataToReceiveFromServer;

    /**
     * Constructor for username, host name, and port, connection should be set to be open. Should set dataToSendToServer and dataToReceiveFromServer as null.
     */
    ClackClient (String userName, String hostName, int port) {
        this.userName = userName;
        this.hostName = hostName;
        this.port = port;
        closeConnection = false;
        dataToSendToServer = null;
        dataToReceiveFromServer = null;
    }

    /**
     * Constructor to set up port to default port number 7000, default port number should be set up as constant, this constructor should call another constructor.
     */
    ClackClient (String userName, String hostName) {
        this(userName, hostName, 7000);
    }

    /**
     * Constructor that sets host name to be “localhost” (i.e., the server and client programs run on the same computer)
     */
    ClackClient (String userName) {
        this(userName, "localhost");
    }

    /**
     * Default constructor that sets anonymous user, should call another constructor.
     */
    ClackClient () {
        this("anonymous");
    }

    /**
     * Does not return anything, for now it should have no code, just a declaration
     */
    public void start () {

    }

    /**
     * Reads the data from the client, does not return anything, for now it should have no code, just a declaration
     */
    public void readClientData () {

    }

    /**
     * Sends data to server, does not return anything, for now it should have no code, just a declaration
     */
    public void sendData () {

    }

    /**
     * Receives data from the server, does not return anything for now it should have no code, just a declaration
     */
    public void receiveData () {

    }

    /**
     * Prints the received data to standard output, for now it should have no code, just a declaration
     */
    public void printData () {

    }

    /**
     * Returns the user name
     */
    public String getUserName () {
        return userName;
    }

    /**
     * Returns the host name
     */
    public String getHostName () {
        return hostName;
    }

    /**
     * Returns the port
     */
    public Integer getPort() {
        return port;
    }

    /**
     * Should be correctly overridden
     */
    @Override
    public int hashCode () {
        return toString().hashCode();
    }

    /**
     * Should be correctly overridden
     */
    @Override
    public boolean equals (Object other) {
        if (other instanceof ClackClient) {
            ClackClient otherClackClient = (ClackClient) other;

            if (otherClackClient.getUserName() != userName)
                return false;
            else if (otherClackClient.getHostName() != hostName)
                return false;
            else if (otherClackClient.getPort() != port)
                return false;
            else
                return true;
        }
        else
            return false;
    }

    /**
     * Should be overridden to return a full description of the class with all instance variables
     */
    @Override
    public String toString () {
        return "Username: " + userName + ", Hostname: " + hostName + ", Port: " + port;
    }

}