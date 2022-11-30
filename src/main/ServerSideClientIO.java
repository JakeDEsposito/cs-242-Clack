package main;

import data.ClackData;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerSideClientIO implements Runnable{
    private boolean closeConnection;
    private ClackData dataToReceiveFromClient;
    private ClackData dataToSendToClient;
    private ObjectInputStream inFromClient;
    private ObjectOutputStream outToClient;
    private ClackServer server;
    private Socket clientSocket;

    public ServerSideClientIO(ClackServer server, Socket clientSocket){
        this.server=server;
        this.clientSocket=clientSocket;
        this.closeConnection=false;
    }

    @Override
    public void run(){
        try {
            outToClient = new ObjectOutputStream(clientSocket.getOutputStream());
            inFromClient = new ObjectInputStream(clientSocket.getInputStream());

            while(!closeConnection){
                receiveData();
                this.server.broadcast();
            }
        } catch(IOException ioe) {
            System.err.println(ioe);
        }
    }

    public void receiveData(){
        try {
            dataToReceiveFromClient = (ClackData)inFromClient.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println(e);
            closeConnection = true;
        }
    }

    public void sendData(){
        try {
            outToClient.writeObject(dataToSendToClient);
        } catch (IOException ioe) {
            System.err.println(ioe);
            closeConnection = true;
        }
    }

    public void setDataToSendToClient(ClackData dataToSendToClient) {
        this.dataToSendToClient = dataToSendToClient;
    }
}
