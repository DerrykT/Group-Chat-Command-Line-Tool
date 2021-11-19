package test;

import main.ClackClient;

import java.util.Scanner;

public class TestClackClient {
    public static void main(String []args) {

        ClackClient clientTest = new ClackClient("My Username1", "My Hostname1", 6000);

        clientTest.start();
        /*
            Below includes the test cases used in standard input to test the functionality of the ClackClient
            class, specifically the start() method.

            1.  INPUT: DONE
                OUTPUT: The connection is closed: true
                (NOTE:normally no output will be given, only done for debugging)

            2.  INPUT:SENDFILE src\test\Part2_document.txt
                OUTPUT: A digital computer can usually be regarded as consisting of three parts: (i) Store. (ii) Executive unit. (iii) Control. ...The executive unit is the part which carries out the various individual operations involved in a calculation. ...It is the duty of the control to see that...[the table of] instructions are obeyed correctly and in the right order. ...A typical instruction might say—"Add the number stored in position 6809 to that in 4302 and put the result back into the latter storage position." Needless to say it would not occur in the machine expressed in English. It would more likely be coded in a form such as 6809430217. Here 17 says which of various possible operations [add] is to be performed on the two numbers. ...It will be noticed that the instruction takes up 10 digits and so forms one packet of information...
                (NOTE: The output is the decrypted contents of the file)

            3.  INPUT: LISTUSERS
                OUTPUT: list users called

            4.  INPUT: this is a message DONE
                OUTPUT: this is a message DONE


            RUN THIS COMMAND FOR SERVER
            -java –jar ClackServer.jar 12415
            RUN THIS COMMAND FOR CLIENT
            -java -jar ClackClient.jar Sherlock@localhost:12415
        */












//        extra testing for part 1 of project
//        System.out.println("Data for c1: ");//prints out data for c1
//        System.out.println(c1.getUserName());
//        System.out.println(c1.getHostName());
//        System.out.println(c1.getPort());
//        System.out.println("Data for c2: ");//prints out data for c2
//        System.out.println(c2.getUserName());
//        System.out.println(c2.getHostName());
//        System.out.println(c2.getPort());
//        System.out.println("Data for c3: ");//prints out data for c3
//        System.out.println(c3.getUserName());
//        System.out.println(c3.getHostName());
//        System.out.println(c3.getPort());
//        System.out.println("Data for c4: ");//prints out data for c4
//        System.out.println(c4.getUserName());
//        System.out.println(c4.getHostName());
//        System.out.println(c4.getPort());

//        //calls the hashcode method for each ClackClient object
//        System.out.println("Testing hashCode() method:");
//        System.out.println(c1.hashCode());
//        System.out.println(c2.hashCode());
//        System.out.println(c3.hashCode());
//        System.out.println(c4.hashCode());
//
//        //calls the equals function providing false and true outputs
//        System.out.println("Testing equals() method:");
//        System.out.println(c1.equals(c2));
//        System.out.println(c3.equals(c4));
//        System.out.println(c4.equals(c4));
//
//        //automatically calls the toString method for each ClackClient object
//        System.out.println("Testing toString method:");
//        System.out.println(c1);
//        System.out.println(c2);
//        System.out.println(c3);
//        System.out.println(c4);


    }
}
