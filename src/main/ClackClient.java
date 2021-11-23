package main;

import data.ClackData;
import data.FileClackData;
import data.MessageClackData;

import java.io.*;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * The ClackClient class represents the client user.
 *
 * @author Derryk Taylor
 * @author Jay Donahue
 */
public class ClackClient {
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
     * @throws IllegalArgumentException thrown when userName or hostName is null or port is less than 1024
     */
    public ClackClient( String userName, String hostName, int port ) throws IllegalArgumentException {
        if(userName == null || hostName == null || port < 1024) {
            throw new IllegalArgumentException("Invalid port number, username, and/or hostname being used");
        }
        this.closeConnection = false;
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
     * This method starts this client's communication with the server and creates a ClientSideServerListener thread.
     */
    public void start() {
        try {
            Socket skt = new Socket(this.hostName, this.port);
            outToServer = new ObjectOutputStream(skt.getOutputStream());
            inFromServer = new ObjectInputStream(skt.getInputStream());
            Thread thread = new Thread(new ClientSideServerListener(this));
            this.inFromStd = new Scanner(System.in);
            closeConnection = false;
            thread.start();
            this.dataToSendToServer = new MessageClackData(this.userName, null, ClackData.CONSTANT_SENDUSERNAME);
            sendData(); //sets the dataToReceive variable in ServerSideClientIO in the case "LISTUSERS" is called with no previous comunication
            while(!closeConnection) {
                readClientData();
                sendData();
            }
            inFromStd.close();
            this.outToServer.close();
            this.inFromServer.close();
            skt.close();
        } catch (NullPointerException e) {
            System.err.println("Null Pointer Exception: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("IO Exception: " + e.getMessage());
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
                this.dataToSendToServer = new MessageClackData(this.userName, "", ClackData.CONSTANT_LOGOUT);
            } else if (input.equals("SENDFILE")) {
                String filename = inFromStd.next();
                this.dataToSendToServer = new FileClackData(this.userName, filename, ClackData.CONSTANT_SENDFILE);
                ((FileClackData)this.dataToSendToServer).readFileContents("TIME");
                if(this.dataToSendToServer.getData() == "") {
                    this.dataToSendToServer = null;
                }
            } else if (input.equals("LISTUSERS")) {
                this.dataToSendToServer = new MessageClackData(this.userName, "", ClackData.CONSTANT_LISTUSERS);
            } else {
                String message = input;
                while(!input.contains("DONE")) {
                    input = inFromStd.nextLine();
                    message = message + input + "\n";
                }
                message = message.substring(0,message.length()-5);
                this.dataToSendToServer = new MessageClackData(this.userName, message, "TIME",ClackData.CONSTANT_SENDMESSAGE);
            }
        } catch (NoSuchElementException e) {
            System.err.println(e.getMessage());
            this.closeConnection = true;
            inFromStd.close();
        }
    }

    /**
     * This method writes the object dataToSendToServer out to the server using the outToServer ObjectOutputStream object
     */
    public void sendData() {
        try {
            if(dataToSendToServer != null) {
                outToServer.writeObject(dataToSendToServer);
                outToServer.flush();
            }
        } catch (IOException e) {
            System.err.println("IO Exception in sendData: " + e.getMessage());
        }
    }

    /**
     * This method receives a ClackData object from the server using the inFromServer ObjectInputStream object and sets
     * the value of dataToReceiveFromServer to the received ClackData object.
     */
    public void receiveData() {
        try {
            if(!closeConnection) {
                dataToReceiveFromServer = (ClackData) inFromServer.readObject();
            }
        } catch (FileNotFoundException fne) {
            System.err.println("dataToSendToServer not found: " + fne.getMessage());
        } catch (IOException ioe) {
//            System.err.println("IO Exception in receiveData: " + ioe.getMessage());
        } catch (ClassNotFoundException cnfe) {
            System.err.println("Class not found: " + cnfe.getMessage());
        }
    }

    /**
     * This method prints out the contents in the dataToReceiveFromServer to the client using the
     * standard output and the getData() abstract method
     */
    public void printData() {
        if(closeConnection) {
            //do nothing because connection is closed
        } else if((this.dataToReceiveFromServer.getType() == ClackData.CONSTANT_SENDMESSAGE) || (this.dataToReceiveFromServer.getType() == ClackData.CONSTANT_SENDFILE)) {
            System.out.println(this.dataToReceiveFromServer.getUserName() + ":  " + this.dataToReceiveFromServer.getData("TIME"));
        } else if(this.dataToReceiveFromServer.getType() == ClackData.CONSTANT_LISTUSERS) {
            System.out.println("{USER-LIST}\n" + this.dataToReceiveFromServer.getData("TIME"));
        }
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
     * This is the accessor for userName
     *
     * @return the closeConnection boolean
     */
    public Boolean getCloseConnection() {
        return this.closeConnection;
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

    /**
     * creates a new ClackClient object with values read in from the command line and starts the communication to the
     * server
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        String userName = "";
        String hostName = "";
        String portAsString = "" ;
        int portNumber = -1;
        String command;
        int index1 = 0;
        int index2 = 0;
        ClackClient client;

        if(args.length > 0) {
            command = args[0];
            if(command.contains("@")) {
                index1 = command.indexOf('@');
                userName = command.substring(0,index1);
                if(command.contains(":")) {
                    index2 = command.indexOf(':');
                    hostName = command.substring(index1 + 1,index2);
                    portAsString = command.substring(index2 + 1, command.length());
                    try {
                        portNumber = Integer.parseInt(portAsString);
                    } catch (NumberFormatException nfe) {
                        System.err.println("portAsString does not contain only numbers: " + nfe.getMessage());
                    }
                } else {
                    hostName = command.substring(index1,command.length());
                }
            }
            if(hostName == "") {
                client = new ClackClient(userName);
            } else if (portNumber == -1) {
                client = new ClackClient(userName, hostName);
            } else {
                client = new ClackClient(userName, hostName, portNumber);
            }
        } else {
            client = new ClackClient();
        }
        client.start();
    }





}