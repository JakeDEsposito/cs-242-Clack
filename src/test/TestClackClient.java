package test;

import main.*;

public class TestClackClient {
    public static void main (String[] args) {
        ClackClient c1 = new ClackClient("User1", "Host1", 100);
        ClackClient c1a = new ClackClient("User1", "Host1", 100);
        ClackClient c2 = new ClackClient("User2", "Host2", -100);
        ClackClient c3 = new ClackClient();

        System.out.println("Should return true: " + c1.equals(c1a));
        System.out.println("Should return false: " + c1.equals(c2));
        System.out.println("main.ClackServer s3 hashCode(): " + c3.hashCode());
        System.out.println("main.ClackServer s3 toString(): " + c3);
    }
}