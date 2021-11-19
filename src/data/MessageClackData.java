package data;

import java.io.Serializable;

/**
 * This class inherits from the ClackData class and contains
 * the message value of a Clack message
 *
 * @author Derryk Taylor
 * @author Jay Donahue
 */
public class MessageClackData extends ClackData implements Serializable {

    private String message; /**represents instant message content*/

    /**
     * This MessageClackData constructor initializes userName and type using the
     * super constructor of the ClackData abstract class and initializes message
     * to a user-provided value.
     *
     * @param userName new userName value
     * @param message new message value
     * @param type new type value
     */
    public MessageClackData( String userName, String message, int type ) {
        super ( userName, type );
        this.message = message;
    }

    /**
     * This MessageClackData constructor initializes the userName and type using the
     * super constructor of the ClackData abstract class and initializes message
     * to a encrypted user-provided string using the ClackData encrypt method and the
     * user given encryption key
     *
     * @param userName new userName value
     * @param message new message value
     * @param key new key value
     * @param type new type value
     */
    public MessageClackData( String userName, String message, String key, int type) {
        super(userName, type);
        this.message = encrypt(message, key);
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

    /**
     * This method overrides the getData(String key) abstract overloaded method of
     * the abstract class ClackData
     *
     * @param key the key used the decrypt the message
     * @return
     */
    public String getData(String key) {
        return decrypt(this.message, key);
    }


    /**
     * This method overrides the hashCode() method from the Object class
     *
     * @return returns the resulting hashcode given to the class instance
     */
    @Override
    public int hashCode() {
        int result = 17;
        result = 37*result + super.toString().hashCode();
        if(this.message == null)
            result = 37*result + 300;
        else
            result = 37*result + this.message.hashCode();
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
     * @return the username, type, date, and message separated by commas
     */
    @Override
    public String toString() {
        return super.toString() + "," + this.message;
    }


}