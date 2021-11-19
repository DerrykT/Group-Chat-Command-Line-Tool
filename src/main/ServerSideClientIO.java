package main;

import data.ClackData;
import data.MessageClackData;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSideClientIO implements Runnable {
    private Boolean closeConnection;
    private ClackData dataToReceiveFromClient;
    private ClackData dataToSendToClient;
    private ObjectInputStream inFromClient;
    private ObjectOutputStream outToClient;
    private ClackServer server;
    private Socket clientSocket;

    public ServerSideClientIO( ClackServer server, Socket clientSocket ) {
        this.server = server;
        this.clientSocket = clientSocket;
        this.closeConnection = false;
        this.dataToReceiveFromClient = null;
        this.dataToSendToClient = null;
        this.inFromClient = null;
        this.outToClient = null;
    }

    @Override
    public void run() {
        try {
            this.outToClient = new ObjectOutputStream(this.clientSocket.getOutputStream());
            this.inFromClient = new ObjectInputStream(this.clientSocket.getInputStream());
            while(!this.closeConnection) {
                receiveData();
                if(this.dataToReceiveFromClient == null) {
                    this.closeConnection = true;
                    this.server.remove(this);
                } else if (dataToReceiveFromClient.getType() == ClackData.CONSTANT_LOGOUT) {
                    this.closeConnection = true;
                    this.server.remove(this);
                } else if (dataToReceiveFromClient.getType() == ClackData.CONSTANT_LISTUSERS) {
                    this.server.getUsers(this);
                } else if (dataToReceiveFromClient.getType() == ClackData.CONSTANT_SENDUSERNAME) {
                    //Do nothing
                } else {
                    this.server.broadcast(this.dataToReceiveFromClient);
                }
            }
            this.clientSocket.close();
        } catch(IOException ioe) {
            System.err.println("IO Exception in run method: " + ioe.getMessage());
        }
    }

    public void receiveData() {
        try {
            this.dataToReceiveFromClient = (ClackData) inFromClient.readObject();
        } catch (ClassNotFoundException cnfe) {
            System.err.println("Class Not Found: " + cnfe.getMessage() );
            this.server.remove(this);
            this.closeConnection = true;
        } catch (IOException ioe) {
            this.server.remove(this);
            this.closeConnection = true;
        }
    }

    public void sendData() {
        try {
            this.outToClient.writeObject(this.dataToSendToClient);
            outToClient.flush();
            System.out.println("SENT FROM " + this.dataToSendToClient.getUserName() + ": \n " + this.dataToSendToClient.getData("TIME")); //USED FOR DEBUGGING
        } catch (IOException ioe) {
            System.err.println("IO Exception in sendData: " + ioe.getMessage());
            this.closeConnection = true;
        }
    }

    public void sendData(ClackData data) {
        try {
            this.outToClient.writeObject(data);
            outToClient.flush();
            System.out.println("SENT FROM " + data.getUserName() + ":\n " + data.getData("TIME")); //USED FOR DEBUGGING
        } catch (IOException ioe) {
            System.err.println("IO Exception in sendData: " + ioe.getMessage());
            this.closeConnection = true;
        }
    }

    public void setDataToSendToClient( ClackData dataToSendToClient ) {
        this.dataToSendToClient = dataToSendToClient;
    }

    public ClackData getUsernameRequest() {
        return this.dataToReceiveFromClient;
    }


}
