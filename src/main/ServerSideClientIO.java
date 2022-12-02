package main;

import data.ClackData;
import data.MessageClackData;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerSideClientIO implements Runnable{
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
     * ObjectInputStream for receiving data from client
     */
    private ObjectInputStream inFromClient;

    /**
     * ObjectOutputStream for sending data from client
     */
    private ObjectOutputStream outToClient;

    /**
     *
     */
    private ClackServer server;

    /**
     *
     */
    private Socket clientSocket;

    /**
     *
     * @param server
     * @param clientSocket
     */
    public ServerSideClientIO(ClackServer server, Socket clientSocket){
        this.server=server;
        this.clientSocket=clientSocket;
        this.closeConnection=false;
        this.dataToReceiveFromClient = null;
        this.dataToSendToClient = null;
        this.outToClient = null;
        this.inFromClient = null;
    }

    /**
     *
     */
    @Override
    public void run(){
        try {
            outToClient = new ObjectOutputStream(clientSocket.getOutputStream());
            inFromClient = new ObjectInputStream(clientSocket.getInputStream());

            receiveData();
            server.addUsername(dataToReceiveFromClient.getUsername());

            while(!closeConnection){
                receiveData();
                if(this.dataToReceiveFromClient.getType()==ClackData.CONSTANT_LISTUSERS){
                    this.dataToSendToClient=new MessageClackData("Server",server.getUserList(),ClackData.CONSTANT_SENDMESSAGE);
                    this.sendData();
                } else if (this.dataToReceiveFromClient.getType()==ClackData.CONSTANT_LOGOUT) {
                    this.closeConnection=true;
                } else {
                    this.dataToSendToClient=this.dataToReceiveFromClient;
                    this.server.broadcast(this.dataToSendToClient);
                }
            }
        } catch(IOException ioe) {
            System.err.println(ioe);
        }
    }

    /**
     * Receives data from client
     */
    public void receiveData(){
        try {
            dataToReceiveFromClient = (ClackData)inFromClient.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println(e);
            closeConnection = true;
        }
    }

    /**
     * Sends data to client, does not return anything
     */
    public void sendData(){
        try {
            outToClient.writeObject(dataToSendToClient);
        } catch (IOException ioe) {
            System.err.println(ioe);
            closeConnection = true;
        }
    }

    /**
     *
     * @param dataToSendToClient
     */
    public void setDataToSendToClient(ClackData dataToSendToClient) {
        this.dataToSendToClient = dataToSendToClient;
    }
}
