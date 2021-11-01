package main;

import data.ClackData;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * The ClackServer class is a blueprint for a ClackServer object that contains information about the
 * port number that clients connect to, a boolean representing whether the server needs to be
 * closed or not, and ClackData objects representing data sent to and received from the client.
 *
 * @author Derryk Taylor
 * @author Jay Donahue
 */
public class ClackServer {
    private int port; /**port number that clients connect to*/
    private boolean closeConnection; /**representing whether the server needs to be closed or not*/
    private ClackData dataToReceiveFromClient; /**data sent to the client*/
    private ClackData dataToSendToClient; /**data received from the client*/
    private ObjectInputStream inFromClient; /**object used to receive data packets*/
    private ObjectOutputStream outToClient; /**object used to send data packets*/

    private static final int DEFAULT_PORT = 7000; /**the default port number*/

    /**
     * This ClackServer constructor initializes port to a user-provided value and initializes
     * dataToReceiveFromClient and dataToSendToClient to null values.
     *
     * @param port new port value
     * @throws IllegalArgumentException
     */
    public ClackServer( int port ) throws IllegalArgumentException{
        if (port < 1024) {
            throw new IllegalArgumentException("port number is less than 1024");
        } else {
            this.port = port;
            this.dataToReceiveFromClient = null;
            this.dataToSendToClient = null;
            this.inFromClient = null;
            this.outToClient = null;
        }
    }

    /**
     * This ClackServer Default constructor initializes port to the DEFAULT_PORT value and initializes
     * dataToReceiveFromClient and dataToSendToClient to null values.
     */
    public ClackServer() {
        this( DEFAULT_PORT );
    }

    /**
     * This method initializes the ServerSocket and starts the communication with the ClackClient class.
     */
    public void start() {
        try {
            ServerSocket sskt = new ServerSocket(this.port);
            Socket clientSkt = sskt.accept();
            outToClient = new ObjectOutputStream(clientSkt.getOutputStream());
            inFromClient = new ObjectInputStream(clientSkt.getInputStream());
            closeConnection = false;
            while(!closeConnection) {
                receiveData();
                if(dataToReceiveFromClient != null) {
                    dataToSendToClient = dataToReceiveFromClient; //echoing back to client for PART 3
                    sendData();
                } else {
                    closeConnection = true;
                }
            }
            sskt.close();
            clientSkt.close();
        } catch(IOException ioe) {
            System.err.println("IO Exception in start method: " + ioe.getMessage());
        }
    }

    /**
     * This method is used to receive ClackData objects from the ClackClient class using the inFromClient ObjectInputStream
     * object. It then sets the value of dataToReceiveFromClient to the read in object.
     */
    public void receiveData() {
        try {
            dataToReceiveFromClient = (ClackData) inFromClient.readObject();
            if(dataToReceiveFromClient == null) {
                this.closeConnection = true;
            }
        } catch (ClassNotFoundException cnfe) {
            System.err.println("Class Not Found: " + cnfe.getMessage() );
            this.closeConnection = true;
        } catch (IOException ioe) {
            this.closeConnection = true;
        }
    }

    /**
     * This method is used to send data to the ClackClient class by using the outToClient ObjectOutputStream object.
     */
    public void sendData() {
        try {
            outToClient.writeObject(dataToSendToClient);
            System.out.println(dataToSendToClient.getData("TIME")); //USED FOR DEBUGGING IN PART 3
        } catch (IOException ioe) {
            System.err.println("IO Exception in sendData: " + ioe.getMessage());
            this.closeConnection = true;
        }
    }

    /**
     * This is the accessor for the port
     *
     * @return value of port
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
        result = 37*result + this.port;
        if (this.closeConnection)
            result = 37*result + 1;
        else
            result = 37*result;
        if(this.dataToReceiveFromClient == null)
            result = 37*result + 100;
        else
            result = 37*result + this.dataToReceiveFromClient.hashCode();
        if(this.dataToSendToClient == null)
            result = 37*result + 105;
        else
            result = 37*result + this.dataToSendToClient.hashCode();
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
     * @return the port, connection, data to receive from client and the data to send to client,
     * separated by commas
     */
    @Override
    public String toString() {
        String output = "" + this.port + "," + this.closeConnection + ",";
        if(this.dataToReceiveFromClient == null && this.dataToSendToClient == null)
            return output + "null,null";
        if(this.dataToReceiveFromClient == null)
            return output + this.dataToSendToClient.toString();
        if(this.dataToSendToClient == null)
            return output + this.dataToReceiveFromClient.toString();
        return output + this.dataToReceiveFromClient.toString() + "," + this.dataToSendToClient.toString();
    }

    public static void main(String[] args) {
        ClackServer server;
        String portAsString;
        int portNumber = 0;
        if(args.length > 0) {
            portAsString = args[0];
            try {
                portNumber = Integer.parseInt(portAsString);
                server = new ClackServer(portNumber);
                server.start();
            } catch (NumberFormatException nfe) {
                System.err.println("Port number given is not an integer: " + nfe.getMessage());
            }
        } else {
            server = new ClackServer();
            server.start();
        }
    }





}