class ClackServer {
    /**
     * integer representing port number on server connected to
     */
    private int port;

    /**
     * boolean representing whether connection is closed or not
     */
    private boolean closeConnection;

    /**
     * ClackData object representing data received from the client
     */
    private ClackData dataToReceiveFromClient;

    /**
     * ClackData object representing data sent to client
     */
    private ClackData dataToSendToClient;

    /**
     * Constructor that sets port number, should set dataToReceiveFromClient and dataToSendToClient as null.
     */
    ClackServer (int port) {
        this.port = port;
        dataToReceiveFromClient = null;
        dataToSendToClient = null;
    }

    /**
     * Default constructor that sets port to default port number 7000, default port number should be set up as constant, this constructor should call another constructor.
     */
    ClackServer () {
        this(7000);
    }

    /**
     * Does not return anything, for now it should have no code, just a declaration
     */
    public void start () {

    }

    /**
     * Receives data from client, does not return anything for now it should have no code, just a declaration
     */
    public void receiveData () {

    }

    /**
     * Sends data to client, does not return anything, for now it should have no code, just a declaration
     */
    public void sendData () {

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
        if (other instanceof ClackServer) {
            ClackServer otherClackServer = (ClackServer) other;

            if (otherClackServer.getPort() != port)
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
        return "Port: " + port;
    }

}