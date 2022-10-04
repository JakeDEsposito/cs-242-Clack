package test;

import main.ClackServer;

public class TestClackServer {
    public static void main (String[] args) {
        ClackServer s1 = new ClackServer(100);
        ClackServer s1a = new ClackServer(100);
        ClackServer s2 = new ClackServer(-100);
        ClackServer s3 = new ClackServer();

        System.out.println("Should return true: " + s1.equals(s1a));
        System.out.println("Should return false: " + s1.equals(s2));
        System.out.println("main.ClackServer s3 hashCode(): " + s3.hashCode());
        System.out.println("main.ClackServer s3 toString(): " + s3);
    }
}