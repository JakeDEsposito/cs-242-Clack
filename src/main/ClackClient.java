package main;

import data.ClackData;
import data.FileClackData;
import data.MessageClackData;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * main.ClackClient is a class for the client data
 * @author Jake D'Esposito
 */
public class ClackClient {
    /**
     * Default Port for Clack Client
     */
    private static final int DEFAULT_PORT = 7000;

    /**
     * String representing name of the client
     */
    private final String userName;

    /**
     * String representing name of the computer representing the server
     */
    private final String hostName;

    /**
     * integer representing port number on server connected to
     */
    private final int port;

    /**
     * boolean representing whether connection is closed or not
     */
    private boolean closeConnection;

    /**
     * data.ClackData object representing data sent to server
     */
    private ClackData dataToSendToServer;

    /**
     * data.ClackData object representing data received from the server
     */
    private ClackData dataToReceiveFromServer;

    /**
     * Scanner object representing standard input
     */
    private Scanner inFromStd;

    /**
     * ObjectInputStream for incoming data
     */
    private ObjectInputStream inFromServer;

    /**
     * ObjectOutputStream for outgoing data
     */
    private ObjectOutputStream outToServer;

    /**
     * Constructor for username, host name, and port, connection should be set to be open. Should set dataToSendToServer and dataToReceiveFromServer as null.
     * @param userName is for the username
     * @param hostName is for the hostname
     * @param port is for the port
     */
    public ClackClient (String userName, String hostName, int port) throws IllegalArgumentException {
        if (userName == null)
            throw new IllegalArgumentException("userName is null");

        if (hostName == null)
            throw new IllegalArgumentException("hostName is null");

        if (port < 1024)
            throw new IllegalArgumentException("port is less than 1024");

        this.userName = userName;
        this.hostName = hostName;
        this.port = port;
        closeConnection = false;
        dataToSendToServer = null;
        dataToReceiveFromServer = null;
        inFromServer = null;
        outToServer = null;
    }

    /**
     * Constructor to set up port to default port number 7000, default port number should be set up as constant, this constructor should call another constructor.
     * @param userName is for the username
     * @param hostName is for the hostname
     */
    public ClackClient (String userName, String hostName) throws IllegalArgumentException {
        this(userName, hostName, DEFAULT_PORT);
    }

    /**
     * Constructor that sets host name to be localhost (i.e., the server and client programs run on the same computer)
     * @param userName is for the username
     */
    public ClackClient (String userName) throws IllegalArgumentException {
        this(userName, "localhost");
    }

    /**
     * Default constructor that sets anonymous user, should call another constructor.
     */
    public ClackClient () {
        this("anonymous");
    }

    /**
     * Does not return anything, for now it should have no code, just a declaration
     */
    public void start () {
        inFromStd = new Scanner(System.in);

        try {
            Socket s = new Socket(hostName, port);

            outToServer = new ObjectOutputStream(s.getOutputStream());
            inFromServer = new ObjectInputStream(s.getInputStream());

            closeConnection = false;

            while (!closeConnection) {
                readClientData();

                sendData();

                receiveData();

                printData();
            }

            outToServer.close();
            inFromServer.close();
            s.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Reads the data from the client, does not return anything, for now it should have no code, just a declaration
     */
    public void readClientData () {
        String nextString = inFromStd.next();

        switch (nextString) {
            case "DONE":
                closeConnection = true;
                break;
            case "SENDFILE":
                if (!inFromStd.hasNext()) {
                    System.out.println("No file provided");
                    break;
                }
                dataToSendToServer = new FileClackData(userName, inFromStd.next(), ClackData.CONSTANT_SENDFILE);
                try {
                    ((FileClackData)dataToSendToServer).readFileContents();
                } catch (IOException e) {
                    dataToSendToServer = null;
                }
                break;
            case "LISTUSERS":
                // TODO: Will be implemented in next part
                break;
            default:
                // TODO: What is type variable set to CONSTANT_SENDMESSAGE
                dataToSendToServer = new MessageClackData(userName, nextString, ClackData.CONSTANT_SENDMESSAGE);
        }
    }

    /**
     * Sends data to server, does not return anything, for now it should have no code, just a declaration
     */
    public void sendData () {
        try {
            outToServer.writeObject(dataToSendToServer);
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }
    }

    /**
     * Receives data from the server, does not return anything for now it should have no code, just a declaration
     */
    public void receiveData () {
        try {
            dataToReceiveFromServer = (ClackData)inFromServer.readObject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Prints the received data to standard output, for now it should have no code, just a declaration
     */
    public void printData () {
        ClackData cData = dataToReceiveFromServer;

        String data = cData.getData();
        String user = cData.getUsername();
        System.out.println(user + " sent " + data);
    }

    /**
     * Returns the username
     * @return String
     */
    public String getUserName () {
        return userName;
    }

    /**
     * Returns the host name
     * @return String
     */
    public String getHostName () {
        return hostName;
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
        if (other instanceof ClackClient) {
            ClackClient otherClackClient = (ClackClient) other;

            if (!otherClackClient.getUserName().equals(userName))
                return false;
            else if (!otherClackClient.getHostName().equals(hostName))
                return false;
            else if (!otherClackClient.getPort().equals(port))
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
        return "Username: " + userName + ", Hostname: " + hostName + ", Port: " + port + ", closeConnection: " + closeConnection;
    }

    public static void main(String[] args) {
        ClackClient client;
        if(args.length==0) {
            client=new ClackClient();
        }
        else {
            int indA=args[0].indexOf('@');
            if(indA!=-1) {
                String u=args[0].substring(0,indA-1);
                int indC=args[0].indexOf(':');
                if(indC!=-1) {
                    String h=args[0].substring(indA+1,indC);
                    String ps=args[0].substring(indC+1);
                    int p=-1;
                    try {
                        p=Integer.parseInt(ps);
                    } catch (NumberFormatException e) {
                        System.err.println("Invalid Port");
                    }
                    if(p==-1) {
                        client=new ClackClient(u,h);
                    }
                    else {
                        client = new ClackClient(u, h, p);
                    }
                }
                else {
                    client=new ClackClient(u,args[0].substring(indA));
                }
            }
            else {
                client=new ClackClient(args[0]);
            }
        }
        client.start();
    }

}