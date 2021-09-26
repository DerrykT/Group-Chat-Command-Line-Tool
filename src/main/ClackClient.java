package main;

import data.ClackData;

public class ClackClient {
    private String userName;
    private String hostName;
    private int port;
    private boolean closeConnection;
    private ClackData dataToSendToServer;
    private ClackData dataToReceiveFromServer;

    private static final int DEFAULT_PORT = 7000;
    private static final String DEFAULT_HOST = "localhost";

    public ClackClient( String userName, String hostName, int port ) {
        this.userName = userName;
        this.hostName = hostName;
        this.port = port;
        this.closeConnection = false;
        this.dataToSendToServer = null;
        this.dataToReceiveFromServer = null;
    }

    public ClackClient( String userName, String hostName ) {
        this( userName, hostName, DEFAULT_PORT );
    }

    public ClackClient( String userName ) {
        this( userName, DEFAULT_HOST );
    }

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
        return this.toString() == obj.toString();
    }

    @Override
    public String toString() {
        return this.userName + "," + this.hostName + "," + this.port + "," + this.closeConnection + ","
                + this.dataToSendToServer.toString() + "," + this.dataToReceiveFromServer.toString();
    }

}
