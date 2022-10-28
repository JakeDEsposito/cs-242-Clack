package test;

import data.*;

import java.io.IOException;

public class TestClackData {
    public static void main(String[] args) {
        //test cases of FileClackData
        FileClackData ft1=new FileClackData("user1","file1",3);
        FileClackData ft2=new FileClackData("user2","file2",1);
        FileClackData ft3=new FileClackData("user1","file2",3);
        FileClackData ft4=new FileClackData("user2","file1",3);
        FileClackData ft5=new FileClackData("user2","file2",2);
        FileClackData ft6=new FileClackData("user2","file2",1);
        FileClackData ft8=new FileClackData(null,null,2);
        FileClackData ft9=new FileClackData();

        //test cases of MessageClackData
        MessageClackData mt1=new MessageClackData("user1","message1",3);
        MessageClackData mt2=new MessageClackData("user2","message2",1);
        MessageClackData mt3=new MessageClackData("user1","message2",3);
        MessageClackData mt4=new MessageClackData("user2","message1",3);
        MessageClackData mt5=new MessageClackData("user2","message2",2);
        MessageClackData mt6=new MessageClackData("user2","message2",1);
        MessageClackData mt8=new MessageClackData(null,null,2);
        MessageClackData mt9=new MessageClackData();
        MessageClackData mt10=new MessageClackData("user", "message", "key", 3);


        //testing of FileClackData
        System.out.println("FileClackData testing:");
        System.out.println("\tDate output:\t"+ft1.getDate());
        System.out.println("\tData output:\t"+ft1.getData());
        System.out.println("\tType output:\t"+ft1.getType());
        System.out.println("\tUsername output:\t"+ft1.getUsername());
        System.out.println("\tFilename output:\t"+ft1.getFileName());
        ft1.setFileName("file2");
        System.out.println("\tFilename output:\t"+ft1.getFileName());
        System.out.println("\tHashcode output:\t"+ft1.hashCode());
        System.out.println("\ttoString output:\t"+ft1.toString());
        ft1.setFileName("file1");
        System.out.println("\tequals output:\t"+ft1.equals(ft2));
        System.out.println("\tequals output:\t"+ft1.equals(ft3));
        System.out.println("\tequals output:\t"+ft1.equals(ft4));
        System.out.println("\tequals output:\t"+ft1.equals(ft5));
        System.out.println("\tequals output:\t"+ft1.equals(ft6));
        System.out.println("\tequals output:\t"+ft2.equals(ft6));
        System.out.println("\tequals output:\t"+ft1.equals(ft8));
        System.out.println("\tequals output:\t"+ft5.equals(ft8));
        System.out.println("\tUsername output:\t"+ft9.getUsername()
                +"\n\tType output:\t"+ft9.getType());

        //testing of MessageClackData
        System.out.println("MessageClackData testing:");
        System.out.println("\tDate output:\t"+mt1.getDate());
        System.out.println("\tData output:\t"+mt1.getData());
        System.out.println("\tType output:\t"+mt1.getType());
        System.out.println("\tUsername output:\t"+mt1.getUsername());
        System.out.println("\tHashcode output:\t"+mt1.hashCode());
        System.out.println("\ttoString output:\t"+mt1.toString());
        System.out.println("\tequals output:\t"+mt1.equals(mt2));
        System.out.println("\tequals output:\t"+mt1.equals(mt3));
        System.out.println("\tequals output:\t"+mt1.equals(mt4));
        System.out.println("\tequals output:\t"+mt1.equals(mt5));
        System.out.println("\tequals output:\t"+mt1.equals(mt6));
        System.out.println("\tequals output:\t"+mt2.equals(mt6));
        System.out.println("\tequals output:\t"+mt1.equals(mt8));
        System.out.println("\tequals output:\t"+mt5.equals(mt8));
        System.out.println("\tUsername output:\t"+mt9.getUsername()
                +"\n\tType output:\t"+mt9.getType());
        System.out.println("\tMessage output:\t"+mt10.getData("key"));

        //testing of interoperability between FileClackData and MessageClackData
        System.out.println("Data interoperability testing");
        FileClackData ft7=new FileClackData("name","data",2);
        MessageClackData mt7=new MessageClackData("name","data",2);
        System.out.println("\tIf FileClackData==MessageClackData:\t"+ft7.equals(mt7)+"\t"+mt7.equals(ft7));

        //testing of read and write methods
        ft1.setFileName("testing.test");
        try{
            String test;
            ft1.readFileContents();
            ft1.setFileName("testing2.test");
            test=ft1.getData();
            ft1.writeFileContents();
            ft1.readFileContents("key");
            ft1.setFileName("testing.test");
            ft1.writeFileContents("key");
            test=ft1.getData("key");
        } catch (IOException ioe) {
            System.err.println("IOE exception");
        }

    }
}