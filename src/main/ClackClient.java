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
        this.port = port;
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
        return this.userName + "," + this.hostName + "," + this.port + "," + this.closeConnection + ","
                + this.dataToSendToServer.toString() + "," + this.dataToReceiveFromServer.toString();
    }

}
