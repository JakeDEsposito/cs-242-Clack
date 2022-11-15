package main;

import data.ClackData;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ClackServer {
    /**
     * Default Port for Clark Client
     */
    private static final int DEFAULT_PORT = 7000;

    /**
     * integer representing port number on server connected to
     */
    private final int port;

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
     * ObjectOutputStream for sending data from client
     */
    private ObjectOutputStream outToClient;

    /**
     * ObjectInputStream for receiving data from client
     */
    private ObjectInputStream inFromClient;

    /**
     * Constructor that sets port number, should set dataToReceiveFromClient and dataToSendToClient as null.
     * @param port is for the port
     */
    public ClackServer (int port) throws IllegalArgumentException {
        if (port < 1024)
            throw new IllegalArgumentException("port is less than 1024");

        this.port = port;
        dataToReceiveFromClient = null;
        dataToSendToClient = null;
        outToClient = null;
        inFromClient = null;
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
        try {
            ServerSocket ss = new ServerSocket(port);
            Socket s = ss.accept();

            outToClient = new ObjectOutputStream(s.getOutputStream());
            inFromClient = new ObjectInputStream(s.getInputStream());

            closeConnection = false;

            while (!closeConnection) {
                receiveData();

                dataToSendToClient = dataToReceiveFromClient;

                if (s.isConnected())
                    sendData();
                else
                    closeConnection = true;
            }

            outToClient.close();
            inFromClient.close();
            s.close();
            ss.close();
        } catch (IOException ioe) {
            System.err.println(ioe);
        }

    }

    /**
     * Receives data from client, does not return anything for now it should have no code, just a declaration
     */
    public void receiveData () {
        try {
            dataToReceiveFromClient = (ClackData)inFromClient.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println(e);
            closeConnection = true;
        }
    }

    /**
     * Sends data to client, does not return anything, for now it should have no code, just a declaration
     */
    public void sendData () {
        try {
            outToClient.writeObject(dataToSendToClient);
        } catch (IOException ioe) {
            System.err.println(ioe);
            closeConnection = true;
        }
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

    public static void main(String[] args) {
        ClackServer server;
        if(args.length==0) {
            server=new ClackServer();
        }
        else {
            int p=-1;
            try {
                p=Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.err.println("Invalid Port");
            }
            if(p==-1) {
                server=new ClackServer();
            }
            else {
                server=new ClackServer(p);
            }
        }
        server.start();
    }

}