package data;

import java.text.SimpleDateFormat;

/**
 * This class inherits from the ClackData class and contains the
 * fileName and fileContent.
 *
 * @author Derryk Taylor
 * @author Jay Donahue
 */
public class FileClackData extends ClackData {
    private String fileName; /**represents name of file*/
    private String fileContents; /**represents contents of file*/

    /**
     * This FileClackData constructor initializes the userName and type to user provided
     * values using the ClackData constructor. It initializes the fileName to a user
     * provided value and fileContents to null.
     *
     * @param userName
     * @param fileName
     * @param type
     */
    public FileClackData( String userName, String fileName, int type ) {
        super( userName, type );
        this.fileName = fileName;
        this.fileContents = null;
    }

    /**
     * This FileClackData Default constructor calls upon the ClackData default constructor
     */
    public FileClackData() {
        super();
    }

    /**
     * This is the mutator for fileName
     *
     * @param fileName   new fileName value
     */
    public void setFileName( String fileName ) {
        this.fileName = fileName;
    }

    /**
     * This is the accessor for the fileName
     *
     * @return value of the fileName
     */
    public String getFileName() {
        return this.fileName;
    }

    /**
     * This method overrides the getData() abstract method of
     * the abstract class ClackData.
     *
     * @return value of the fileContent
     */
    @Override
    public String getData() {
        return this.fileContents;
    }

    public void readFileContents() {
        //No Code for Part 1
    }

    public void writeFileContents() {
        //No Code for Part 1
    }


    /**
     * This method overrides the hashCode() method in the Object class
     *
     *
     * @return a hashcode of the object
     */
    @Override
    public int hashCode() {
        int result = 17;
        result = 37*result + super.toString().hashCode();
        result = 37*result + this.fileName.hashCode();
        if(this.fileContents == null)
            result = 37*result + 200;
        else
            result = 37*result + this.fileContents.hashCode();
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
     *
     * @return the username, type, date, fileName, and fileContents separated by commas
     */
    @Override
    public String toString() {
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        String output = super.toString() + "," + this.fileName + ",";
        //cannot return null values so toString checks if fileContents is null
        if(this.fileContents == null) {
            return output += "null";
        }
        return output + this.fileContents;
    }
}
