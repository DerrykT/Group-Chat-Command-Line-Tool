package data;

import java.io.*;
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

    private final static String EOS = null; /**Constant used to determine end of file*/

    /**
     * This FileClackData constructor initializes the userName and type to user provided
     * values using the ClackData constructor. It initializes the fileName to a user
     * provided value and fileContents to null.
     *
     * @param userName new userName value
     * @param fileName new fileName value
     * @param type new type value
     */
    public FileClackData( String userName, String fileName, int type ) {
        super( userName, type );
        this.fileName = fileName;
        this.fileContents = "";
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
     * @param fileName new fileName value
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

    /**
     * This method overrides the getData(String key) abstract method
     * of the abstract class ClackData.
     *
     * @param key
     * @return decrypted value of fileContent
     */
    public String getData(String key) {
        return decrypt(this.fileContents, key);
    }

    /**
     * This method does a non-secure file read of the fileName instance variable and sets
     * the instance variable fileContents to the the contents read from the file.
     *
     * @throws IOException
     */
    public void readFileContents() throws IOException {
        try {
            File fileName = new File(this.fileName);
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            try {
                String fileData = "";
                String line = "";

                while((line = br.readLine()) != EOS) {
                    fileData += line;
                }

                br.close();
                this.fileContents = fileData;
            } catch (IOException ioe) {
                br.close();
                throw new IOException("IO Exception occurred: " + ioe.getMessage());
            }
        } catch (FileNotFoundException fnfe) {
            throw new FileNotFoundException("The file " + this.fileName + " is not available: " + fnfe.getMessage());
        } catch (NullPointerException npe) {
            throw new NullPointerException("Null file given: " + npe.getMessage());
        }
    }

    /**
     * This overloaded method does a secure file read of the instance variable fileName using
     * the key parameter to decrypt the file contents and sets the instance variable fileContents
     * to the decrypted file content.
     *
     * @param key the key used to decrypt file content
     * @throws IOException
     */
    public void readFileContents(String key) throws IOException {
        try {
            File fileName = new File(this.fileName);
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            try {
                String fileData = "";
                String line;

                while((line = br.readLine()) != EOS) {
                    fileData += encrypt(line, key);
                }

                br.close();
                this.fileContents = fileData;
            } catch (IOException ioe) {
                br.close();
                throw new IOException("IO Exception occurred: " + ioe.getMessage());
            }
        } catch (FileNotFoundException fnfe) {
            throw new FileNotFoundException("The file" + this.fileName + " is not available: " + fnfe.getMessage());
        } catch (NullPointerException npe) {
            throw new NullPointerException("Null file given: " + npe.getMessage());
        }
    }

    /**
     * This method does a non-secure file write of the fileContent instance variable
     * to the instance variable fileName.
     *
     * @throws IOException
     */
    public void writeFileContents() throws IOException {
        try {
            File fileName = new File(this.fileName);
            BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
            try {
                bw.write(this.fileContents);
                bw.close();
            } catch (IOException ioe) {
                bw.close();
                throw new IOException("IO Exception occurred: " + ioe.getMessage());
            }
        } catch (FileNotFoundException fnfe) {
            throw new FileNotFoundException("The file" + this.fileName + " is not available: " + fnfe.getMessage());
        } catch (NullPointerException npe) {
            throw new NullPointerException("Null file given: " + npe.getMessage());
        }
    }

    /**
     * This method does a secure file write of the fileContent instance variable
     * to the instance variable fileName
     *
     * @param key the key used to encrypt the file contents
     * @throws IOException
     */
    public void writeFileContents(String key) throws IOException {
        try {
            File fileName = new File(this.fileName);
            BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
            try {
                bw.write(decrypt(this.fileContents, key));
                bw.close();
            } catch (IOException ioe) {
                bw.close();
                throw new IOException("IO Exception occurred: " + ioe.getMessage());
            }
        } catch (FileNotFoundException fnfe) {
            throw new FileNotFoundException("The file" + this.fileName + " is not available: " + fnfe.getMessage());
        } catch (NullPointerException npe) {
            throw new NullPointerException("Null file given: " + npe.getMessage());
        }
    }


    /**
     * This method overrides the hashCode() method in the Object class
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