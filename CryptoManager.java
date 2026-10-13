/* Class: CMSC203 CRN XXXX
 * Program: Assignment 3
 * Instructor: Prof.Agyun
 * Summary of Description: (Give a brief description for each Program) 
 * Due Date: MM/DD/YYYY (<09/27/2026>)
 * Integrity Pledge: I pledge that I have completed the programming assignment independently.
 * I have not copied the code from a student or any source.
 * Student Name: Yared Hailegebriel
 */



package BobsCircus;


public class CryptoManager {

	//Variables
    private static final char LOWER_RANGE = ' ';
    private static final char UPPER_RANGE = '_';
    private static final int RANGE = UPPER_RANGE - LOWER_RANGE + 1;
    private static final String ALPHABET64 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789 !\"#$%&'()*+,-./:;<=>?@[\\]^_";

    public static boolean isStringInBounds(String plainText) {
        for (int i = 0; i < plainText.length(); i++) {
            if (!(plainText.charAt(i) >= LOWER_RANGE
                    && plainText.charAt(i) <= UPPER_RANGE)) {
                return false;
            }
        }

        return true;
    }

    
    //===================================================================================================
    
    /*
     * Vigenere Cipher is a method of encrypting alphabetic text
     * based on the letters of a keyword. It works as below:
     *      Choose a keyword (e.g., KEY).
     *      Repeat the keyword to match the length of the plaintext.
     *      Each letter in the plaintext is shifted by the position of the
     *      corresponding letter in the keyword (A = 0, B = 1, ..., Z = 25).
     */
    
    
    
    
    
    
    
    
    
    //Vigenere Encryption
    public static String vigenereEncryption(String plainText, String key) {
        if (!isStringInBounds(plainText)
                || !isStringInBounds(key)
                || key.isEmpty()) {
            return "The selected string is not in bounds, Try again.";
        }
        
        for (int i = 0; i < key.length(); i++) {
            if (key.charAt(i) < 'A' || key.charAt(i) > 'Z') {
                return "The selected string is not in bounds, Try again.";
            }
        }

        String result = "";

        for (int i = 0; i < plainText.length(); i++) {
            char plainCharacter = plainText.charAt(i);
            char keyCharacter = key.charAt(i % key.length());

            int plainPosition = plainCharacter - LOWER_RANGE;
            int keyShift = keyCharacter - 'A';
            int encryptedPosition = (plainPosition + keyShift) % RANGE;

            result += (char) (LOWER_RANGE + encryptedPosition);
        }

        return result;
    }

    
    
    
    
    
    
    
    
    //Vigenere Decryption
    public static String vigenereDecryption(String encryptedText, String key) {
        if (!isStringInBounds(encryptedText)
                || !isStringInBounds(key)
                || key.isEmpty()) {
            return "The selected string is not in bounds, Try again.";
        }
        
        for (int i = 0; i < key.length(); i++) {
            if (key.charAt(i) < 'A' || key.charAt(i) > 'Z') {
                return "The selected string is not in bounds, Try again.";
            }
        }

        String result = "";

        for (int i = 0; i < encryptedText.length(); i++) {
            char encryptedCharacter = encryptedText.charAt(i);
            char keyCharacter = key.charAt(i % key.length());

            int encryptedPosition = encryptedCharacter - LOWER_RANGE;
            int keyShift = keyCharacter - 'A';
            int decryptedPosition =
                    ((encryptedPosition - keyShift) % RANGE + RANGE) % RANGE;

            result += (char) (LOWER_RANGE + decryptedPosition);
        }

        return result;
    }

    //===================================================================================================
    
    /*
     * Playfair Cipher encrypts two letters at a time instead of just one.
     * It works as follows:
     * A matrix (8X8 in our case) is built using a keyword
     * Plaintext is split into letter pairs (e.g., ME ET YO UR).
     * Encryption rules depend on the positions of the letters in the matrix:
     *     Same row: replace each letter with the one to its right.
     *     Same column: replace each with the one below.
     *     Rectangle: replace each letter with the one in its own row but in the column of the other letter in the pair.
     */

    
    
    
    
    
    
    
    //Playfair Encryption
    public static String playfairEncryption(String plainText, String key) {
        if (!isStringInBounds(plainText)
                || !isStringInBounds(key)
                || key.isEmpty()) {
            return "The selected string is not in bounds, Try again.";
        }

        String characters = "";

        for (int i = 0; i < key.length(); i++) {
            char character = key.charAt(i);

            if (ALPHABET64.indexOf(character) >= 0
                    && characters.indexOf(String.valueOf(character)) < 0) {
                characters += character;
            }
        }

        for (int i = 0; i < ALPHABET64.length(); i++) {
            char character = ALPHABET64.charAt(i);

            if (characters.indexOf(String.valueOf(character)) < 0) {
                characters += character;
            }
        }

        char[][] matrix = new char[8][8];

        for (int i = 0; i < 64; i++) {
            matrix[i / 8][i % 8] = characters.charAt(i);
        }

        String result = "";

        for (int i = 0; i < plainText.length(); i += 2) {
            char first = plainText.charAt(i);
            char second = (i + 1 < plainText.length())
                    ? plainText.charAt(i + 1)
                    : 'X';

            int firstRow = -1;
            int firstColumn = -1;
            int secondRow = -1;
            int secondColumn = -1;

            for (int row = 0; row < 8; row++) {
                for (int column = 0; column < 8; column++) {
                    if (matrix[row][column] == first) {
                        firstRow = row;
                        firstColumn = column;
                    }

                    if (matrix[row][column] == second) {
                        secondRow = row;
                        secondColumn = column;
                    }
                }
            }

            if (firstRow == secondRow) {
                result += matrix[firstRow][(firstColumn + 1) % 8];
                result += matrix[secondRow][(secondColumn + 1) % 8];
            } else if (firstColumn == secondColumn) {
                result += matrix[(firstRow + 1) % 8][firstColumn];
                result += matrix[(secondRow + 1) % 8][secondColumn];
            } else {
                result += matrix[firstRow][secondColumn];
                result += matrix[secondRow][firstColumn];
            }
        }

        return result;
    }

    
    
    
    
    
    
    
    
    
    //Playfair Decryption
    public static String playfairDecryption(String encryptedText, String key) {
        if (!isStringInBounds(encryptedText)
                || !isStringInBounds(key)
                || key.isEmpty()) {
            return "The selected string is not in bounds, Try again.";
        }

        if (encryptedText.length() % 2 != 0) {
            return "The encrypted text must contain an even number of characters.";
        }

        String characters = "";

        for (int i = 0; i < key.length(); i++) {
            char character = key.charAt(i);

            if (ALPHABET64.indexOf(character) >= 0
                    && characters.indexOf(String.valueOf(character)) < 0) {
                characters += character;
            }
        }

        for (int i = 0; i < ALPHABET64.length(); i++) {
            char character = ALPHABET64.charAt(i);

            if (characters.indexOf(String.valueOf(character)) < 0) {
                characters += character;
            }
        }

        char[][] matrix = new char[8][8];

        for (int i = 0; i < 64; i++) {
            matrix[i / 8][i % 8] = characters.charAt(i);
        }

        String result = "";

        for (int i = 0; i < encryptedText.length(); i += 2) {
            char first = encryptedText.charAt(i);
            char second = encryptedText.charAt(i + 1);

            int firstRow = -1;
            int firstColumn = -1;
            int secondRow = -1;
            int secondColumn = -1;

            for (int row = 0; row < 8; row++) {
                for (int column = 0; column < 8; column++) {
                    if (matrix[row][column] == first) {
                        firstRow = row;
                        firstColumn = column;
                    }

                    if (matrix[row][column] == second) {
                        secondRow = row;
                        secondColumn = column;
                    }
                }
            }

            if (firstRow == secondRow) {
                result += matrix[firstRow][(firstColumn + 7) % 8];
                result += matrix[secondRow][(secondColumn + 7) % 8];
            } else if (firstColumn == secondColumn) {
                result += matrix[(firstRow + 7) % 8][firstColumn];
                result += matrix[(secondRow + 7) % 8][secondColumn];
            } else {
                result += matrix[firstRow][secondColumn];
                result += matrix[secondRow][firstColumn];
            }
        }

        if (result.length() > 0
                && result.charAt(result.length() - 1) == 'X') {
            result = result.substring(0, result.length() - 1);
        }

        return result;
    }

    //===================================================================================================
    
    /*
     * Caesar Cipher is a simple substitution cipher that replaces each letter in a message
     * with a letter some fixed number of positions down the alphabet.
     * For example, with a shift of 3, 'A' would become 'D', 'B' would become 'E', and so on.
     */
    
    
    
    
    
    
    

    //Caesar Encryption
    public static String caesarEncryption(String plainText, int key) {
        if (!isStringInBounds(plainText)) {
            return "The selected string is not in bounds, Try again.";
        }

        String result = "";

        for (int i = 0; i < plainText.length(); i++) {
            char current = plainText.charAt(i);
            int shiftedPosition =
                    ((current - LOWER_RANGE + key) % RANGE + RANGE) % RANGE;

            result += (char) (LOWER_RANGE + shiftedPosition);
        }

        return result;
    }
    
    
    
    
    
    
    

    //Caesar Decryption
    public static String caesarDecryption(String encryptedText, int key) {
        if (!isStringInBounds(encryptedText)) {
            return "The selected string is not in bounds, Try again.";
        }

        String result = "";

        for (int i = 0; i < encryptedText.length(); i++) {
            char current = encryptedText.charAt(i);
            int position = current - LOWER_RANGE;
            int shiftedPosition =
                    ((position - key) % RANGE + RANGE) % RANGE;

            result += (char) (LOWER_RANGE + shiftedPosition);
        }

        return result;
    }
}