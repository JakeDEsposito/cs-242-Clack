package main;

import data.ClackData;

public class ClackServer {
    /**
     * Default Port for Clark Client
     */
    private static final int DEFAULT_PORT = 7000;

    /**
     * integer representing port number on server connected to
     */
    private int port;

    /**
     * boolean representing whether connection is closed or not
     */
    private boolean closeConnection;

    /**
     * data.ClackData object representing data received from the client
     */
    private ClackData dataToReceiveFromClient;

    /**
     * data.ClackData object representing data sent to client
     */
    private ClackData dataToSendToClient;

    /**
     * Constructor that sets port number, should set dataToReceiveFromClient and dataToSendToClient as null.
     * @param port is for the port
     */
    public ClackServer (int port) {
        this.port = port;
        dataToReceiveFromClient = null;
        dataToSendToClient = null;
    }

    /**
     * Default constructor that sets port to default port number 7000, default port number should be set up as constant, this constructor should call another constructor.
     */
    public ClackServer () {
        this(DEFAULT_PORT);
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
     * @return Integer
     */
    public Integer getPort() {
        return port;
    }

    /**
     * Should be correctly overridden
     * @return int
     */
    @Override
    public int hashCode () {
        return toString().hashCode();
    }

    /**
     * Should be correctly overridden
     * @return boolean
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
     * @return String
     */
    @Override
    public String toString () {
        return "Port: " + port;
    }

}