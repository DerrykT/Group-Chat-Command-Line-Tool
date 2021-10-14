package test;

import main.ClackClient;

import java.util.Scanner;

public class TestClackClient {
    public static void main(String []args) {
        ClackClient c1 = new ClackClient("My Username1", "My Hostname1", 6000);

        c1.start();
//
//        Scanner scanner = new Scanner(System.in);
//        String text = scanner.next();
//        if(text.equals("hi")) {
//            System.out.println("bye");
//        } else {
//            System.out.println(text);
//        }

//        ClackClient c2 = new ClackClient("My Username2", "My Hostname2");
//        ClackClient c3 = new ClackClient("My Username3");
//        ClackClient c4 = new ClackClient();
//
//
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
//
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
