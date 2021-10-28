package main;

import data.ClackData;
import data.FileClackData;
import data.MessageClackData;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * The ClackClient class represents the client user.
 *
 * @author Derryk Taylor
 * @author Jay Donahue
 */
public class ClackClient implements Serializable {
    private String userName; /**username of the client*/
    private String hostName; /**host name of the server connected to*/
    private int port; /**port number connected to*/
    private boolean closeConnection; /**whether the connection is open or not*/
    private ClackData dataToSendToServer; /**data sent to the server*/
    private ClackData dataToReceiveFromServer; /**data received from the server*/
    private Scanner inFromStd = null; /**Scanner object to read in from standard input*/
    private ObjectInputStream inFromServer; /**object used to receive data packets*/
    private ObjectOutputStream outToServer; /**object used to send data packets*/

    private static final int DEFAULT_PORT = 7000; /**default port number*/
    private static final String DEFAULT_HOST = "localhost"; /** the server and client programs run on the same computer*/

    /**
     * This ClackClient constructor initializes the userName, hostName, and port to user-provided values.
     * Initializes the closeConnection to false, and the dataToSendToServer and dataToReceiveFromServer to null values
     *
     * @param userName new userName value
     * @param hostName new hostName value
     * @param port new port value
     */
    public ClackClient( String userName, String hostName, int port ) throws IllegalArgumentException {
        if(userName == null || hostName == null || port < 1024) {
            throw new IllegalArgumentException("Invalid port number, username, and/or hostname being used");
        }
        this.userName = userName;
        this.hostName = hostName;
        this.port = port;
        this.inFromServer = null;
        this.outToServer = null;
    }

    /**
     * This ClackClient constructor initializes the userName and hostName to user-provided values.
     * Initializes the port to the DEFAULT_PORT value, the closeConnection to false, and the
     * dataToSendToServer and dataToReceiveFromServer to null values
     *
     * @param userName new userName value
     * @param hostName new hostName value
     */
    public ClackClient( String userName, String hostName ) {
        this( userName, hostName, DEFAULT_PORT );
    }

    /**
     * This ClackClient constructor initializes userName to a user-provided value. Initializes
     * hostName to the DEFAULT_HOST value and port to the DEFAULT_PORT value. Initializes
     * the closeConnection to false, and the dataToSendToServer and dataToReceiveFromServer to null values
     *
     * @param userName new userName value
     */
    public ClackClient( String userName ) {
        this( userName, DEFAULT_HOST );
    }

    /**
     * This ClackClient Default constructor intiializes username to "ANON", hostName to the DEFAULT_HOST value,
     * port to the DEFAULT_PORT value, the closeConnection to false, and the dataToSendToServer and
     * dataToReceiveFromServer to null values
     */
    public ClackClient() {
        this( "ANON" );
    }

    /**
     * This method starts this client's communication with the server
     */
    public void start() {
        try {
            this.inFromStd = new Scanner(System.in);
            readClientData();
            dataToReceiveFromServer = dataToSendToServer; //Temporary code to aid debugging for Part 2
            printData();
        } catch (NullPointerException e) {
            //used to catch cases where LISTUSERS or DONE is called, dataToReceiveFromServer is null if those options are called
//            System.out.println("The connection is closed: " + this.closeConnection);
        }
    }

    /**
     * This method gets an input from the user through standard input and initializes
     * the dataToSendToServer instance variable based on the input
     */
    public void readClientData() {
        try {
            String input = inFromStd.next();
            if (input.equals("DONE")) {
                this.closeConnection = true;
            } else if (input.equals("SENDFILE")) {
                String filename = inFromStd.next();
                this.dataToSendToServer = new FileClackData(this.userName, filename, ClackData.CONSTANT_SENDFILE);
                try {
                    ((FileClackData)this.dataToSendToServer).readFileContents("TIME");
                } catch (IOException e) {
                    dataToSendToServer = null;
                    System.err.println("Failed to read " + filename);
                    System.err.println(e.getMessage());
                    inFromStd.close();
                }
            } else if (input.equals("LISTUSERS")) {
                System.out.println("list users called"); //used only for debugging purposes, will be deleted
                //Does Nothing For Part 2 of Project
            } else {
                String message = input;
                while(!input.contains("DONE")) {
                    input = inFromStd.nextLine();
                    message = message + input + "\n";
                }
                this.dataToSendToServer = new MessageClackData(this.userName, message, "TIME",ClackData.CONSTANT_SENDMESSAGE);
            }
        } catch (NoSuchElementException e) {
            System.err.println(e.getMessage());
            inFromStd.close();
        }
        inFromStd.close();
    }

    public void sendData() {
        //NO CODE FOR PART 2
    }

    public void receiveData() {
        //NO CODE FOR PART 2
    }

    /**
     * This method prints out the contents in the dataToReceiveFromServer to the client using the
     * standard output and the getData() abstract method
     */
    public void printData() {
        System.out.println(this.dataToReceiveFromServer.getData("TIME"));
    }

    /**
     * This is the accessor for userName
     *
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * This is the accessor for hostName
     *
     * @return the hostName
     */
    public String getHostName() {
        return hostName;
    }

    /**
     * This is the accessor for port
     *
     * @return the port
     */
    public int getPort() {
        return port;
    }

    /**
     * This method overrides the hashCode() method in the Object class
     *
     * @return a hashcode of the object
     */
    @Override
    public int hashCode() {
        int result = 17;
        result = 37*result + this.userName.hashCode();
        result = 37*result + this.hostName.hashCode();
        result = 37*result + this.port;
        if (this.closeConnection)
            result = 37*result + 1;
        else
            result = 37*result;
        if(this.dataToSendToServer == null)
            result = 37*result + 100;
        else
            result = 37*result + this.dataToSendToServer.hashCode();
        if(this.dataToReceiveFromServer == null)
            result = 37*result + 105;
        else
            result = 37*result + this.dataToReceiveFromServer.hashCode();
        return result;
    }
    /**
     * This method overrides the equals() method from the Object class
     *
     * @param obj provides obj to compare to another object
     * @return a boolean which is true if two instances are equal and false if they are not
     */
    @Override
    public boolean equals(Object obj) {
        return this.toString().equals(obj.toString());
    }
    /**
     * This method overrides the toString() method from the ClackData class and the Object class
     *
     * @return the username, hostname, port, connection, data to send to server and the data to receive from server,
     * separated by commas
     */
    @Override
    public String toString() {
        String output = "" + this.userName + "," + this.hostName + "," + this.port + "," + this.closeConnection + ",";
        if(this.dataToSendToServer == null && this.dataToReceiveFromServer == null)
            return output + "null,null";
        if(this.dataToSendToServer == null)
            return output + "null," + this.dataToReceiveFromServer.toString();
        if(this.dataToReceiveFromServer == null)
            return output + this.dataToSendToServer.toString() + ",null";
        return output + this.dataToSendToServer.toString() + "," + this.dataToReceiveFromServer.toString();
    }

}