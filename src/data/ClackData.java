package data;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * This is an abstract class that all ClackData classes will inherit from.
 *
 * @author Derryk Taylor
 * @author Jay Donahue
 */
public abstract class ClackData implements Serializable {
    private String userName; /**represents name of client user*/
    private int type; /**represents the kind of data exchanged between the client and the server*/
    private Date date; /**represents date when ClackData object was created*/

    public static final int CONSTANT_LISTUSERS = 0; /**give a listing of all users connected to this session*/
    public static final int CONSTANT_LOGOUT = 1; /**close this client's connection*/
    public static final int CONSTANT_SENDMESSAGE = 2; /**send a message*/
    public static final int CONSTANT_SENDFILE = 3; /**send a file*/
    public static final String CONSTANT_LOWERCASE_ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    public static final String CONSTANT_UPPERCASE_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final int CONSTANT_ALPHABET_LENGTH = 26;


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
     * This is an accessor for the username
     *
     * @return Value of the username
     */
    public String getUserName() {
        return userName;
    }

    /**
     * This is an accessor for the type
     *
     * @return Value of the type
     */
    public int getType() {
        return type;
    }

    /**
     * This is an accessor for the date
     *
     * @return Value of date
     */
    public Date getDate() {
        return date;
    }

    /**
     * This is an abstract method that accesses the data of a class
     *
     * @return the data contained in a ClackData class
     */
    public abstract Object getData();

    /**
     * This is an abstract overloaded method that accesses the data of
     * a class and decrypts it using the key parameter
     *
     * @param key
     * @return the decrypted data contained in a ClackData class
     */
    public abstract Object getData(String key);

    /**
     * This method returns an encrypted string using the Vignère cipher
     *
     * @param inputStringToEncrypt the input string that should be encrypted
     * @param key the key value that is used when encrypting the input string
     * @return the encrypted version of inputStringToEncrypt
     */
    protected String encrypt(String inputStringToEncrypt, String key) {
        String encryptedInput = "";
        int keyIteration = 0;
        for(int i = 0; i < inputStringToEncrypt.length(); i++) {
            if(keyIteration >= key.length()) {
                keyIteration = 0;
            }
            if(!(inputStringToEncrypt.charAt(i) == ' ')
                    && (CONSTANT_LOWERCASE_ALPHABET.contains("" + Character.toLowerCase(inputStringToEncrypt.charAt(i)))))
            { //prevents the key from iterating if no encryption was done due to a space
                encryptedInput += findCharWithKey(inputStringToEncrypt.charAt(i), key.charAt(keyIteration), false);
                keyIteration++;
            } else {
                encryptedInput += inputStringToEncrypt.charAt(i);
            }
        }
        return encryptedInput;
    }

    /**
     * This method decrypts an input string that is encrypted using the Vignère cipher
     * and returns the decrypted string
     *
     * @param inputStringToDecrypt an encrypted input string
     * @param key the key used to encrypt the inputStringToDecrypt
     * @return the decrypted version of the inputStringToDecrypt
     */
    protected String decrypt(String inputStringToDecrypt, String key) {
        String decryptedInput = "";
        int keyIteration = 0;
        for(int i = 0; i < inputStringToDecrypt.length(); i++) {
            if(keyIteration >= key.length()) {
                keyIteration = 0;
            }

            if(!(inputStringToDecrypt.charAt(i) == ' ')
                    && (CONSTANT_LOWERCASE_ALPHABET.contains("" + Character.toLowerCase(inputStringToDecrypt.charAt(i)))))
            { //prevents the key from iterating if no decryption was done due to a non encrypted character
                decryptedInput += findCharWithKey(inputStringToDecrypt.charAt(i), key.charAt(keyIteration), true);
                keyIteration++;
            } else {
                decryptedInput += inputStringToDecrypt.charAt(i);
            }
        }
        return  decryptedInput;
    }

/*
This is a function used in the encrypt and decrypt methods. It is given the
character to encrypt/decrypt, the key used, and a boolean that, if true, will
decrypt the input parameter or if false, will encrypt the given parameter
 */
    private char findCharWithKey(char input, char key, boolean decryptOn) {
        char outputChar;
        int pointAtAlphabet;

        if(decryptOn) { //decrypting the character
            if(Character.isUpperCase(input)) { //returning a uppercase letter because input is uppercase
                pointAtAlphabet =
                        CONSTANT_UPPERCASE_ALPHABET.indexOf(input) -
                                CONSTANT_UPPERCASE_ALPHABET.indexOf(Character.toUpperCase(key));
                if(pointAtAlphabet < 0) {
                    pointAtAlphabet = CONSTANT_ALPHABET_LENGTH - (pointAtAlphabet * -1);
                }
                outputChar = CONSTANT_UPPERCASE_ALPHABET.charAt(pointAtAlphabet);
            } else { //returning a lowercase letter because input is uppercase
                pointAtAlphabet =
                        CONSTANT_LOWERCASE_ALPHABET.indexOf(input) -
                                CONSTANT_LOWERCASE_ALPHABET.indexOf(Character.toLowerCase(key));
                if(pointAtAlphabet < 0) {
                    pointAtAlphabet = CONSTANT_ALPHABET_LENGTH - (pointAtAlphabet * -1);
                }
                outputChar = CONSTANT_LOWERCASE_ALPHABET.charAt(pointAtAlphabet);
            }
        } else { //returning a uppercase letter because input is uppercase
            if(Character.isUpperCase(input)) {
                pointAtAlphabet =
                        CONSTANT_UPPERCASE_ALPHABET.indexOf(input) +
                                CONSTANT_UPPERCASE_ALPHABET.indexOf(Character.toUpperCase(key));
                if(pointAtAlphabet >= CONSTANT_ALPHABET_LENGTH) {
                    pointAtAlphabet -= CONSTANT_ALPHABET_LENGTH;
                }
                outputChar = CONSTANT_UPPERCASE_ALPHABET.charAt(pointAtAlphabet);
            } else { //returning a lowercase letter because input is uppercase
                pointAtAlphabet =
                        CONSTANT_LOWERCASE_ALPHABET.indexOf(input) +
                                CONSTANT_LOWERCASE_ALPHABET.indexOf(Character.toLowerCase(key));
                if(pointAtAlphabet >= CONSTANT_ALPHABET_LENGTH) {
                    pointAtAlphabet -= CONSTANT_ALPHABET_LENGTH;
                }
                outputChar = CONSTANT_LOWERCASE_ALPHABET.charAt(pointAtAlphabet);
            }
        }
        return outputChar;
    }

    /**
     * This is an overridden toString() method
     *
     * @return the username, type, and date separated by commas
     */
    @Override
    public String toString() {
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        return this.userName + "," + this.type + "," + formatter.format(this.date);
    }
}