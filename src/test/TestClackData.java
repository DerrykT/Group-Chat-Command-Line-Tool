package test;

import data.ClackData;
import data.FileClackData;
import data.MessageClackData;

public class TestClackData {
    public static void main(String []args) {
        //objects are created through the ClackData class
        FileClackData fcd1 = new FileClackData("Username1", "Filename1", 1);
        FileClackData fcd2 = new FileClackData();
        MessageClackData mcd1 = new MessageClackData("Username2", "Filename2", 1);
        MessageClackData mcd2 = new MessageClackData();

        //prints out the username of each FileClackData and MessageClackData objects through the ClackData class
        System.out.println("Testing getUsername method:");
        System.out.println(fcd1.getUserName());
        System.out.println(fcd2.getUserName());

        System.out.println(mcd1.getUserName());
        System.out.println(mcd2.getUserName());

        //calls the set method for filename in both FileClackData and MessageClackData
        //and prints the current Filename before and after doing so
        System.out.println("Testing getFileName and setFileName methods:");
        System.out.println(fcd1.getFileName());
        fcd1.setFileName("New Filename1");
        System.out.println(fcd1.getFileName());

        System.out.println(fcd2.getFileName());
        fcd2.setFileName("New Filename2");
        System.out.println(fcd2.getFileName());

        //prints out the type of each FileClackData and MessageClackData objects through the ClackData class
        System.out.println("Testing getType method:");
        System.out.println(fcd1.getType());
        System.out.println(fcd2.getType());

        System.out.println(mcd1.getType());
        System.out.println(mcd2.getType());

        //prints out the Date of each FileClackData and MessageClackData objects through the ClackData class
        System.out.println("Testing getDate method:");
        System.out.println(fcd1.getDate());
        System.out.println(fcd2.getDate());

        System.out.println(mcd1.getDate());
        System.out.println(mcd2.getDate());

        //prints out the Data of each FileClackData and MessageClackData objects through the ClackData class
        System.out.println("Testing getData method:");
        System.out.println(fcd1.getData());
        System.out.println(fcd2.getData());

        System.out.println(mcd1.getData());
        System.out.println(mcd2.getData());

        //prints out the hashcode of each FileClackData and MessageClackData objects
        System.out.println("Testing hashCode method:");
        System.out.println(fcd1.hashCode());
        System.out.println(fcd2.hashCode());

        System.out.println(mcd1.hashCode());
        System.out.println(mcd2.hashCode());

        //calls the equals function from FileClackData and MessageClackData
        System.out.println("Testing equals method:");
        System.out.println(fcd1.equals(fcd2));
        System.out.println(fcd1.equals(fcd1));

        System.out.println(mcd1.equals(mcd2));
        System.out.println(mcd1.equals(mcd1));

        //automatically calls the toString() method for FileClackData and MessageClackData
        System.out.println("Testing toString method:");
        System.out.println(fcd1);
        System.out.println(fcd2);

        System.out.println(mcd1);
        System.out.println(mcd2);

        System.out.println(fcd1);
    }
}
