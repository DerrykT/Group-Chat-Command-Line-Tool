package main;

import data.ClackData;

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

    private static final int DEFAULT_PORT = 7000; /**the default port number*/

    /**
     * This ClackServer constructor initializes port to a user-provided value and initializes
     * dataToReceiveFromClient and dataToSendToClient to null values.
     *
     * @param port
     */
    public ClackServer( int port ) {
        this.port = port;
        this.dataToReceiveFromClient = null;
        this.dataToSendToClient = null;
    }

    /**
     * This ClackServer Default constructor initializes port to the DEFAULT_PORT value and initializes
     * dataToReceiveFromClient and dataToSendToClient to null values.
     */
    public ClackServer() {
        this( DEFAULT_PORT );
    }

    public void start() {
        //NO CODE FOR PART 1
    }

    public void receiveData() {
        //NO CODE FOR PART 1
    }

    public void sendData() {
        //NO CODE FOR PART 1
    }

    public int getPort() {
        return port;
    }

    //FINISH OVERRIDE METHODS
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this.toString().equals(obj.toString());
    }

    @Override
    public String toString() {
        return "" + this.port + "," + this.closeConnection + "," + this.dataToReceiveFromClient.toString()
                + "," + this.dataToSendToClient.toString();
    }

}
