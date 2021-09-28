package main;

import data.ClackData;

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

    private static final int DEFAULT_PORT = 7000; /**default port number*/
    private static final String DEFAULT_HOST = "localhost"; /** the server and client programs run on the same computer*/

    /**
     * This ClackClient constructor initializes the userName, hostName, and port to user-provided values.
     * Initializes the closeConnection to false, and the dataToSendToServer and dataToReceiveFromServer to null values.
     *
     * @param userName
     * @param hostName
     * @param port
     */
    public ClackClient( String userName, String hostName, int port ) {
        this.userName = userName;
        this.hostName = hostName;
        if (port < 0)
            this.port = port;
        else
            this.port = DEFAULT_PORT;
        this.closeConnection = false;
        this.dataToSendToServer = null;
        this.dataToReceiveFromServer = null;
    }

    /**
     * This ClackClient constructor initializes the userName and hostName to user-provided values.
     * Initializes the port to the DEFAULT_PORT value, the closeConnection to false, and the
     * dataToSendToServer and dataToReceiveFromServer to null values.
     *
     * @param userName
     * @param hostName
     */
    public ClackClient( String userName, String hostName ) {
        this( userName, hostName, DEFAULT_PORT );
    }

    /**
     * This ClackClient constructor initializes userName to a user-provided value. Initializes
     * hostName to the DEFAULT_HOST value and port to the DEFAULT_PORT value. Initializes
     * the closeConnection to false, and the dataToSendToServer and dataToReceiveFromServer to null values.
     *
     * @param userName
     */
    public ClackClient( String userName ) {
        this( userName, DEFAULT_HOST );
    }

    /**
     * This ClackClient Default constructor intiializes username to "ANON", hostName to the DEFAULT_HOST value,
     * port to the DEFAULT_PORT value, the closeConnection to false, and the dataToSendToServer and
     * dataToReceiveFromServer to null values.
     */
    public ClackClient() {
        this( "ANON" );
    }

    public void start() {
        //NO CODE FOR PART 1
    }

    public void readClientData() {
        //NO CODE FOR PART 1
    }

    public void sendData() {
        //NO CODE FOR PART 1
    }

    public void receiveData() {
        //NO CODE FOR PART 1
    }

    public void printData() {
        //NO CODE FOR PART 1
    }

    public String getUserName() {
        return userName;
    }

    public String getHostName() {
        return hostName;
    }

    public int getPort() {
        return port;
    }


    //NEED TO FINISH OVERRIDE METHODS

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
        String output = this.userName + "," + this.hostName + "," + this.port + "," + this.closeConnection + ",";
        if(this.dataToSendToServer == null && this.dataToReceiveFromServer == null)
            return output + "null,null";
        if(this.dataToSendToServer == null)
            return output + "null, " + this.dataToReceiveFromServer.toString();
        if(this.dataToReceiveFromServer == null)
            return output + this.dataToSendToServer.toString() + ", null";
        return output + this.dataToSendToServer.toString() + "," + this.dataToReceiveFromServer.toString();
    }

}
