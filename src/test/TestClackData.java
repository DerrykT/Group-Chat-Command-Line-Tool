package test;

import data.ClackData;
import data.FileClackData;
import data.MessageClackData;

import java.io.IOException;

public class TestClackData {
    public static void main(String []args) {

        //objects are created through the ClackData class
        FileClackData fcd1 = new FileClackData("Username1", "src\\test\\Part2_document.txt", 1);

        System.out.println("====>FileClackData Testing<====\n");
        try {
            //reading the Part2_document.txt file and encrypting the file and printing the encrypted String.
            //then decrypts file and prints decrypted version of file using overloading getData method taking in a key
            fcd1.readFileContents("TIME"); //testing readFileContents method with a key
            System.out.println("Encrypted File Content: ");
            System.out.println(fcd1.getData() + "\n");
            System.out.println("Decrypted File Content: ");
            System.out.println(fcd1.getData("TIME") + "\n"); //testing overloaded getData method

            //testing the readFileContents method with no parameters given.
            System.out.println("Unsecure file read test: ");
            fcd1.readFileContents();
            System.out.println(fcd1.getData());

            //testing the writeFileContents methods
            fcd1.readFileContents("TIME"); //getting encrypted version of file contents
            fcd1.setFileName("src\\test\\nonencrypted_file.txt");
            fcd1.writeFileContents();

            fcd1.setFileName("src\\test\\decrypted_file.txt");
            fcd1.writeFileContents("TIME");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        System.out.println("\n\n====>MessageClackData Testing<====\n");

        //testing new constructor and MessageClackData encryption/decryption of message instance variable
        //objects are created through the ClackData class
        MessageClackData mcd = new MessageClackData("Username3","BRAVE NEW WORLD","TIME",1);

        System.out.println("Encrypted Message: " + mcd.getData());
        System.out.println("Decrypted Message: " + mcd.getData("TIME"));



        //Testing for part1, not needed

//        //prints out the username of each FileClackData and MessageClackData objects through the ClackData class
//        System.out.println("Testing getUsername method:");
//        System.out.println(fcd1.getUserName());
//        System.out.println(fcd2.getUserName());
//
//        System.out.println(mcd1.getUserName());
//        System.out.println(mcd2.getUserName());
//
//        //calls the set method for filename in both FileClackData and MessageClackData
//        //and prints the current Filename before and after doing so
//        System.out.println("Testing getFileName and setFileName methods:");
//        System.out.println(fcd1.getFileName());
//        fcd1.setFileName("New Filename1");
//        System.out.println(fcd1.getFileName());
//
//        System.out.println(fcd2.getFileName());
//        fcd2.setFileName("New Filename2");
//        System.out.println(fcd2.getFileName());
//
//        //prints out the type of each FileClackData and MessageClackData objects through the ClackData class
//        System.out.println("Testing getType method:");
//        System.out.println(fcd1.getType());
//        System.out.println(fcd2.getType());
//
//        System.out.println(mcd1.getType());
//        System.out.println(mcd2.getType());
//
//        //prints out the Date of each FileClackData and MessageClackData objects through the ClackData class
//        System.out.println("Testing getDate method:");
//        System.out.println(fcd1.getDate());
//        System.out.println(fcd2.getDate());
//
//        System.out.println(mcd1.getDate());
//        System.out.println(mcd2.getDate());
//
//        //prints out the Data of each FileClackData and MessageClackData objects through the ClackData class
//        System.out.println("Testing getData method:");
//        System.out.println(fcd1.getData());
//        System.out.println(fcd2.getData());
//
//        System.out.println(mcd1.getData());
//        System.out.println(mcd2.getData());

//        //prints out the hashcode of each FileClackData and MessageClackData objects
//        System.out.println("Testing hashCode method:");
//        System.out.println(fcd1.hashCode());
//        System.out.println(fcd2.hashCode());
//
//        System.out.println(mcd1.hashCode());
//        System.out.println(mcd2.hashCode());
//
//        //calls the equals function from FileClackData and MessageClackData
//        System.out.println("Testing equals method:");
//        System.out.println(fcd1.equals(fcd2));
//        System.out.println(fcd1.equals(fcd1));
//
//        System.out.println(mcd1.equals(mcd2));
//        System.out.println(mcd1.equals(mcd1));
//
//        //automatically calls the toString() method for FileClackData and MessageClackData
//        System.out.println("Testing toString method:");
//        System.out.println(fcd1);
//        System.out.println(fcd2);
//
//        System.out.println(mcd1);
//        System.out.println(mcd2);
//
//        System.out.println(fcd1);
    }
}
