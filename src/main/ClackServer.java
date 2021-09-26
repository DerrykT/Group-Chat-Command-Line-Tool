package main;

import data.ClackData;

public class ClackServer {
    private int port;
    private boolean closeConnection;
    private ClackData dataToReceiveFromClient;
    private ClackData dataToSendToClient;

    private static final int DEFAULT_PORT = 7000;

    public ClackServer( int port ) {
        this.port = port;
        this.dataToReceiveFromClient = null;
        this.dataToSendToClient = null;
    }

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
        return this.toString() == obj.toString();
    }

    @Override
    public String toString() {
        return "" + this.port + "," + this.closeConnection + "," + this.dataToReceiveFromClient.toString()
                + "," + this.dataToSendToClient.toString();
    }

}
