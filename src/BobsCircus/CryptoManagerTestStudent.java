/*
 * Class: CMSC203
 * Instructor: Professor Agyun
 * Description: Tests the bounds check and the three ciphers in CryptoManager.
 * Due: MM/DD/YYYY
 * Platform/compiler: Eclipse
 * Student Name: Yared Hailegebriel
 */

package BobsCircus;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class CryptoManagerTestStudent {

    //======================================================================
    
    //Check that letters, numbers, and allowed symbols are in bounds
    
    @Test
    public void testIsStringInBoundsAcceptsAllowedSymbols() {

        String text = "CODE 203!";

        assertTrue(CryptoManager.isStringInBounds(text));
    }


    //======================================================================
    
    //Encrypt and decrypt a message using the Caesar cipher
     
    @Test
    public void testCaesarEncryptionAndDecryption() {

        String original = "MARS 203";
        int key = 5;

        String encrypted = CryptoManager.caesarEncryption(original, key);
        String decrypted = CryptoManager.caesarDecryption(encrypted, key);

        assertEquals(original, decrypted);
    }


    //======================================================================
    
    //Check that Caesar encryption wraps from underscore to space
     
    @Test
    public void testCaesarWrapsAtUpperBoundary() {

        String text = "_";
        int key = 1;

        String encrypted = CryptoManager.caesarEncryption(text, key);

        assertEquals(" ", encrypted);
    }


    //======================================================================
    
    //Encrypt and decrypt a message using the Vigenere cipher
     
    @Test
    public void testVigenereEncryptionAndDecryption() {

        String original = "BLUE SKY 42";
        String key = "MOON";

        String encrypted = CryptoManager.vigenereEncryption(original, key);
        String decrypted = CryptoManager.vigenereDecryption(encrypted, key);

        assertEquals(original, decrypted);
    }


    //======================================================================
    
    // Encrypt and decrypt a message using the Playfair cipher
    
    @Test
    public void testPlayfairEncryptionAndDecryption() {

        String original = "SAFE CODE!";
        String key = "ORANGE";

        String encrypted = CryptoManager.playfairEncryption(original, key);
        String decrypted = CryptoManager.playfairDecryption(encrypted, key);

        assertEquals(original, decrypted);
    }
}