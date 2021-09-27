package data;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This is an abstract class that all ClackData classes will inherit from.
 *
 * @author Derryk Taylor
 * @author Jay Donahue
 */
public abstract class ClackData {
    private String userName; /**represents name of client user*/
    private int type; /**represents the kind of data exchanged between the client and the server*/
    private Date date; /**represents date when ClackData object was created*/

    /**
     * This ClackData constructor initializes the username and type to
     * user-provided values and initializes the date to the date it is
     * initialized on.
     *
     * @param userName
     * @param type
     */
    public ClackData( String userName, int type ) {
        this.userName = userName;
        this.type = type;
        this.date = new Date(System.currentTimeMillis());
    }

    /**
     * This ClackData constructor initializes the username to ANON
     * and the date to the date it is initialized on. It initializes
     * the type to a user-provided value.
     *
     * @param type
     */
    public ClackData( int type ) {
        this( "ANON", type );
    }

    /**
     * This ClackData Default constructor initializes the username to
     * ANON and the type to 0. It initializes the date to the date
     * the constructor is called.
     */
    public ClackData() {
        this( 0 );
    }

    /**
     * This is a accessor for the username
     *
     * @return Value of the username
     */
    public String getUserName() {
        return userName;
    }

    /**
     * This is a accessor for the type
     *
     * @return Value of the type
     */
    public int getType() {
        return type;
    }

    /**
     * This is a accessor for the date
     *
     * @return Value of date
     */
    public Date getDate() {
        return date;
    }

    /**
     * This is a abstract method that accesses the data of a class
     *
     * @return the data contained in a ClackData class
     */
    public abstract Object getData();

    /**
     * This is a overridden toString() method
     *
     * @return the username, type, and date separated by commas
     */
    @Override
    public String toString() {
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        return "" + this.userName + "," + this.type + "," + formatter.format(this.date);
    }
}
