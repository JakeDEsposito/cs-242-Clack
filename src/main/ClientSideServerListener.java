package main;

import java.io.IOException;
import java.net.Socket;

public class ClientSideServerListener implements Runnable {
    /**
     * ClackClient instance variable representing a client that calls this class to make a threaded Runnable object.
     */
    private ClackClient client;

    /**
     * Constructor that takes in a ClackClient object ‘client’ as parameter.
     * @param client The client
     */
    ClientSideServerListener (ClackClient client) {
        this.client = client;
    }

    /**
     * Method from overridden from Runnable interface
     */
    @Override
    public synchronized void run () {
//        try {
            //Socket s = new Socket(client.getHostName(), client.getPort());

            while (!client.getCloseConnection()) {
                try {
                    wait();

                    client.receiveData();

                    client.printData();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            //s.close();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }
}
