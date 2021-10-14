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
        if (port < 0)
            this.port = port;
        else
            this.port = DEFAULT_PORT;
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

}