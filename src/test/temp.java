package test;

public class temp {
    public static final String CONSTANT_LOWERCASE_ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    public static final String CONSTANT_UPPERCASE_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final int CONSTANT_ALPHABET_LENGTH = 26;

    public static void main(String[] args) {
        System.out.println(CONSTANT_UPPERCASE_ALPHABET.indexOf('N'));
        System.out.println(CONSTANT_UPPERCASE_ALPHABET.indexOf('Z'));
        System.out.println(CONSTANT_UPPERCASE_ALPHABET.indexOf('E'));
        System.out.println(CONSTANT_UPPERCASE_ALPHABET.indexOf('I'));
        System.out.println(CONSTANT_UPPERCASE_ALPHABET.indexOf('Q'));
        String input = "BRAVE NEW WORLD";
        String key = "TIME";
        System.out.println(decrypt(encrypt(input, key),key));
    }

    public static String encrypt(String inputStringToEncrypt, String key) {
        String encryptedInput = "";
        int keyIteration = 0;
        for(int i = 0; i < inputStringToEncrypt.length(); i++) {
            if(keyIteration >= key.length()) {
                keyIteration = 0;
            }
            encryptedInput += findCharWithKey(inputStringToEncrypt.charAt(i), key.charAt(keyIteration), false);
            if(!(inputStringToEncrypt.charAt(i) == ' ')) { //prevents the key from iterating if no encryption was done due to a space
                keyIteration++;
            }
        }
        return encryptedInput;
    }

    public static String decrypt(String inputStringToDecrypt, String key) {
        String decryptedInput = "";
        int keyIteration = 0;
        for(int i = 0; i < inputStringToDecrypt.length(); i++) {
            if(keyIteration >= key.length()) {
                keyIteration = 0;
            }
            decryptedInput += findCharWithKey(inputStringToDecrypt.charAt(i), key.charAt(keyIteration), true);
            if(!(inputStringToDecrypt.charAt(i) == ' ')) { //prevents the key from iterating if no decryption was done due to a space
                keyIteration++;
            }
        }
        return  decryptedInput;
    }

    public static char findCharWithKey(char input, char key, boolean decryptOn) {
        char outputChar;
        int pointAtAlphabet;
        if(input == ' ') {
            return ' ';
        }
        if(decryptOn) { //decrypting the character
            if(Character.isUpperCase(input)) { //returning a uppercase letter because input is uppercase
                pointAtAlphabet =
                        CONSTANT_UPPERCASE_ALPHABET.indexOf(input) -
                                CONSTANT_UPPERCASE_ALPHABET.indexOf(key);
                if(pointAtAlphabet < 0) {
                    pointAtAlphabet = CONSTANT_ALPHABET_LENGTH - (pointAtAlphabet * -1);
                }
                outputChar = CONSTANT_UPPERCASE_ALPHABET.charAt(pointAtAlphabet);
            } else { //returning a lowercase letter because input is uppercase
                pointAtAlphabet =
                        CONSTANT_LOWERCASE_ALPHABET.indexOf(input) -
                                CONSTANT_LOWERCASE_ALPHABET.indexOf(key);
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
                                CONSTANT_LOWERCASE_ALPHABET.indexOf(Character.toUpperCase(key));
                if(pointAtAlphabet >= CONSTANT_ALPHABET_LENGTH) {
                    pointAtAlphabet -= CONSTANT_ALPHABET_LENGTH;
                }
                outputChar = CONSTANT_LOWERCASE_ALPHABET.charAt(pointAtAlphabet);
            }
        }
        return outputChar;
    }
}
