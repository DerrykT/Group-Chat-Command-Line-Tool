package main;

/**
 * The ClientSideServerListener class implements Runnable and is used to listen to the server for ClackData objects
 *
 * @author Derryk Taylor
 * @author Jay Donahue
 */
public class ClientSideServerListener implements Runnable {
    private ClackClient client; /**client that is connected to the server*/

    /**
     * This ClientSideServerListener constructor sets the client value to the given ClackClient value in the parameter.
     *
     * @param client new client value
     */
    ClientSideServerListener( ClackClient client ) {
        this.client = client;
    }

    /**
     * This method will wait for data to be received from the server and print out that data while the client closeConnection value is false
     */
    @Override
    public void run() {
        while(!client.getCloseConnection()) {
            client.receiveData();
            client.printData();
        }
    }
}
