package data;

/**
 * This class inherits from the ClackData class and contains
 * the message value of a Clack message
 *
 * @author Derryk Taylor
 * @author Jay Donahue
 */
public class MessageClackData extends ClackData{
    private String message; /**represents instant message content*/

    /**
     * This MessageClackData constructor initializes userName and type using the
     * super constructor of the ClackData abstract class and initializes message
     * to a user-provided value.
     *
     * @param userName
     * @param message
     * @param type
     */
    public MessageClackData( String userName, String message, int type ) {
        super ( userName, type );
        this.message = message;
    }

    /**
     * This MessageClackData Default constructor called the super Default Constructor
     * of the ClackData class.
     */
    public MessageClackData () {
        super();
    }

    /**
     * This method overrides the getData() abstract method of
     * the abstract class ClackData.
     *
     * @return value of the message
     */
    public String getData() {
        return this.message;
    }


    //hasCode, equals, and toString are not finished
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
        return super.toString() + "," + this.message;
    }


}
