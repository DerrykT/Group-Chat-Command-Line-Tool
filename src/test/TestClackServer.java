package test;

import main.ClackServer;

public class TestClackServer {
    public static void main(String []args) {
        ClackServer cs1 = new ClackServer(3000);
        ClackServer cs2 = new ClackServer();

        //calls the getPort() function for each ClackServer object
        System.out.println("Testing getPort method:");
        System.out.println(cs1.getPort());
        System.out.println(cs2.getPort());

        //calls the hashCode() function for each ClackServer object
        System.out.println("Testing hashCode method:");
        System.out.println(cs1.hashCode());
        System.out.println(cs2.hashCode());

        //calls the equals function with false and true outputs
        System.out.println("Testing equals method:");
        System.out.println(cs1.equals(cs2));
        System.out.println(cs1.equals(cs1));

        //automatically calls the toString function for each ClackServer object
        System.out.println("Testing toString method:");
        System.out.println(cs1);
        System.out.println(cs2);

    }
}
