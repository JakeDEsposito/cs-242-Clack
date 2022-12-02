package main;

import data.ClackData;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

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
     * ArrayList of all connected Users
     */
    private ArrayList<ServerSideClientIO> serverSideClientIOList;

    /**
     * String Array of every connected user's name
     */
    private ArrayList<String> userList;

    /**
     * Constructor that sets port number, should set dataToReceiveFromClient and dataToSendToClient as null.
     * @param port is for the port
     */
    public ClackServer (int port) throws IllegalArgumentException {
        if (port < 1024)
            throw new IllegalArgumentException("port is less than 1024");

        this.port = port;
        this.serverSideClientIOList=new ArrayList<ServerSideClientIO>();
        this.userList=new ArrayList<String>();
    }

    /**
     * Default constructor that sets port to default port number 7000, default port number should be set up as constant, this constructor should call another constructor.
     */
    public ClackServer () {
        this(DEFAULT_PORT);
    }

    /**
     * Does not return anything
     */
    public void start () {
        try {
            ServerSocket ss = new ServerSocket(port);

            while (!closeConnection) {
                Socket s = ss.accept();

                if (s.isConnected()){
                    serverSideClientIOList.add(new ServerSideClientIO(this, s));
                    serverSideClientIOList.get(serverSideClientIOList.size()-1).run();
                }
                else {
                    closeConnection = true;
                }
            }

            ss.close();
        } catch (IOException ioe) {
            System.err.println(ioe);
        }

    }

    /**
     *
     * @param dataToBroadcastToClients
     */
    public void broadcast(ClackData dataToBroadcastToClients){
        for(ServerSideClientIO user : serverSideClientIOList){
            user.setDataToSendToClient(dataToBroadcastToClients);
            user.sendData();
        }
    }

    /**
     *
     * @param serverSideClientToRemove
     */
    public void remove(ServerSideClientIO serverSideClientToRemove){
        int temp=serverSideClientIOList.indexOf(serverSideClientToRemove);
        userList.remove(temp);
        serverSideClientIOList.remove(temp);
    }

    /**
     *
     * @return
     */
    public String getUserList(){
        String temp="";
        for(String u:userList){
            temp.concat(u.concat("\n"));
        }
        return temp;
    }

    /**
     *
     * @param n
     */
    public void addUsername(String n){
        userList.add(n);
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