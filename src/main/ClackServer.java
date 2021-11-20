package main;

import data.ClackData;
import data.MessageClackData;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

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
    List<ServerSideClientIO> serverSideClientIOList; /**list containing all ServerSideClientIO threads*/

    private static final int DEFAULT_PORT = 7000; /**the default port number*/

    /**
     * This ClackServer constructor initializes port to a user-provided value, serverSideClientIOList to new ArrayList and initializes
     * dataToReceiveFromClient and dataToSendToClient to null values.
     *
     * @param port new port value
     * @throws IllegalArgumentException thrown when port is less than 1024
     */
    public ClackServer( int port ) throws IllegalArgumentException{
        if (port < 1024) {
            throw new IllegalArgumentException("port number is less than 1024");
        } else {
            this.port = port;
            serverSideClientIOList = new ArrayList<ServerSideClientIO>();
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
     * This method initializes the ServerSocket and starts the communication with the ClackClient class and creates a ServerSideClientIO
     * thread for each client.
     */
    public void start() {
        try {
            ServerSocket sskt = new ServerSocket(this.port);
            Socket clientSkt;
            closeConnection = false;
            ServerSideClientIO ssc;
            Thread thread;
            while(!closeConnection) {
                clientSkt = sskt.accept();
                ssc = new ServerSideClientIO(this, clientSkt);
                thread = new Thread(ssc);
                thread.start();
                serverSideClientIOList.add(ssc);
            }
            sskt.close();
        } catch(IOException ioe) {
            System.err.println("IO Exception in start method: " + ioe.getMessage());
        }
    }

    /**
     * This method removes a ServerSideClientIO instance from the serverSideClientIOList ArrayList.
     *
     * @param client ServerSideClientIO instance to be removed
     */
    public synchronized void remove(ServerSideClientIO client) {
        System.out.println(client.getDataToReceiveFromClient().getUserName() + " has disconnected");
        this.serverSideClientIOList.remove(client);
    }

    /**
     * This method sends data to all clients inside the serverSideClientIOList using the sendData() method.
     *
     * @param data ClackData object to send to all clients
     */
    public synchronized void broadcast(ClackData data) {
        for(ServerSideClientIO client : this.serverSideClientIOList) {
            client.setDataToSendToClient(data);
            client.sendData();
        }
    }

    /**
     * This method returns a string containing all client userNames currently connected to the server
     *
     * @return string containing all client usernames
     */
    public synchronized String printUserList() {
        String out = "";
        int i = 1;
        for(ServerSideClientIO clientIO : this.serverSideClientIOList) {
            out += "User [" + i + "] : " + clientIO.getDataToReceiveFromClient().getUserName() + "\n";
            i++;
        }
        return out;
    }

    /**
     * This method finds the user that requested a list of all users and sends that list to the client
     *
     * @param ssc client requesting list of all users
     */
    public synchronized void getUsers(ServerSideClientIO ssc) {
        for(ServerSideClientIO c : this.serverSideClientIOList) {
            if(c.equals(ssc)) {
                String userList = printUserList();
                ssc.sendData(new MessageClackData("server", userList, "TIME",ClackData.CONSTANT_LISTUSERS));
            }
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
        String output = "" + this.port + "," + this.closeConnection;
        return output;
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