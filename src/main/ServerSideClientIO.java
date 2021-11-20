package main;

import data.ClackData;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * The ServerSideClientIO class implements Runnable and is used to listen to individual client requests
 *
 * @author Derryk Taylor
 * @author Jay Donahue
 */
public class ServerSideClientIO implements Runnable {
    private Boolean closeConnection; /**whether there is a connection between the server and client*/
    private ClackData dataToReceiveFromClient; /**the ClackData being received from the client*/
    private ClackData dataToSendToClient; /**the ClackData being sent to the client*/
    private ObjectInputStream inFromClient; /**object used to receive data packets*/
    private ObjectOutputStream outToClient; /**object used to send data packets*/
    private final ClackServer server; /**the server the client is communicating to*/
    private final Socket clientSocket; /**socket used to communicate with client*/

    /**
     * This ServerSideClientIO constructor takes in a ClackServer and Socket variable and sets the server and clientSocket values to
     * those parameters. Sets closeConnection to be false and all other variables to be null.
     *
     * @param server the new server value
     * @param clientSocket the new clientSocket value
     */
    public ServerSideClientIO( ClackServer server, Socket clientSocket ) {
        this.server = server;
        this.clientSocket = clientSocket;
        this.closeConnection = false;
        this.dataToReceiveFromClient = null;
        this.dataToSendToClient = null;
        this.inFromClient = null;
        this.outToClient = null;
    }

    /**
     * initializes the outToClient and inFromClient values used the clientSocket value. Then listens to the client for requests and
     * fulfills those requests
     */
    @Override
    public void run() {
        try {
            this.outToClient = new ObjectOutputStream(this.clientSocket.getOutputStream());
            this.inFromClient = new ObjectInputStream(this.clientSocket.getInputStream());
            while(!this.closeConnection) {
                receiveData();
                if (dataToReceiveFromClient.getType() == ClackData.CONSTANT_LOGOUT) {
                    this.closeConnection = true;
                    this.server.remove(this);
                } else if (dataToReceiveFromClient.getType() == ClackData.CONSTANT_LISTUSERS) {
                    this.server.getUsers(this);
                } else if (dataToReceiveFromClient.getType() == ClackData.CONSTANT_SENDUSERNAME) {
                    System.out.println(dataToReceiveFromClient.getUserName() + " connected to server"); //used for debugging
                } else {
                    this.server.broadcast(this.dataToReceiveFromClient);
                }
            }
            this.clientSocket.close();
        } catch(IOException ioe) {
            System.err.println("IO Exception in run method: " + ioe.getMessage());
        }
    }

    /**
     * method used to receive data from the client using inFromClient.readObject() and sets the dataToReceiveFromClient
     * value to the ClackData object received
     */
    public void receiveData() {
        try {
            this.dataToReceiveFromClient = (ClackData) inFromClient.readObject();
        } catch (ClassNotFoundException cnfe) {
            System.err.println("Class Not Found: " + cnfe.getMessage() );
        } catch (IOException ioe) {
            System.out.println("IOException: " + ioe.getMessage());
        }
    }

    /**
     * method used to send the dataToSendToClient value to the client using the outToClient.writeObject() method
     */
    public void sendData() {
        try {
            this.outToClient.writeObject(this.dataToSendToClient);
            outToClient.flush();
            System.out.println("SENT FROM " + this.dataToSendToClient.getUserName() + ": \n" + this.dataToSendToClient.getData("TIME")); //USED FOR DEBUGGING
        } catch (IOException ioe) {
            System.err.println("IO Exception in sendData: " + ioe.getMessage());
        }
    }

    /**
     * Overridden sendData method that takes in a ClackData instance to send to the client
     *
     * @param data ClackData instance to send to client
     */
    public void sendData(ClackData data) {
        try {
            this.outToClient.writeObject(data);
            outToClient.flush();
            System.out.println("SENT FROM " + data.getUserName() + ":\n" + data.getData("TIME")); //USED FOR DEBUGGING
        } catch (IOException ioe) {
            System.err.println("IO Exception in sendData: " + ioe.getMessage());
        }
    }

    /**
     * This is the mutator for dataToSendToClient
     *
     * @param dataToSendToClient new dataToSendToClient value
     */
    public void setDataToSendToClient( ClackData dataToSendToClient ) {
        this.dataToSendToClient = dataToSendToClient;
    }

    /**
     * This is the accessor for the dataToReceiveFromClient
     *
     * @return value of dataToReceiveFromClient
     */
    public ClackData getDataToReceiveFromClient() {
        return this.dataToReceiveFromClient;
    }
}
