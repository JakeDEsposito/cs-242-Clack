package test;

import main.*;

public class TestClackClient {
    public static void main (String[] args) {
        ClackClient c1 = new ClackClient("User1", "Host1", 3000);
        ClackClient c1a = new ClackClient("User1", "Host1", 3000);
        ClackClient c2 = new ClackClient();

        System.out.println("Should return true: " + c1.equals(c1a));
        System.out.println("Should return false: " + c1.equals(c2));
        System.out.println("main.ClackServer s3 hashCode(): " + c2.hashCode());
        System.out.println("main.ClackServer s3 toString(): " + c2);

        System.out.println("User name should be User1: " + c1.getUserName());
        System.out.println("Host name should be Host1: " + c1.getHostName());
        System.out.println("Port number should be 3000: " + c1.getPort());

        c1.start();
    }
}