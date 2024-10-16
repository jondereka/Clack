package CSDUO.clack.endpoint;

import java.lang.*;

public class CaesarCipher
{
    public static final String DEFAULT_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private int key;
    private String alphabet;
    private String encrypted;
    private String decrypted;

    /**
     * @param key tells how much spaces to shift by
     * Has a default alphabet from A-Z
     */
    public CaesarCipher(int key)
    {
        if (key == 0){
            throw new IllegalArgumentException("key of zero not allowed");
        }
        this.key = key;
        this.alphabet = DEFAULT_ALPHABET;

    }

    /**
     * @param key tells how much spaces to shift by
     * @param alphabet which creates its own set of
     * alphabets in which you can shift
     */
    public CaesarCipher(int key, String alphabet)
    {
        if (key == 0){throw new IllegalArgumentException("key of zero not allowed");}
        if (alphabet == null || alphabet.isEmpty()) {throw new IllegalArgumentException("empty alphabet not allowed");}
        this.key = key;
        this.alphabet = alphabet;
    }


    /**
     * @return the alphabet that the user inputs
     */
    public String getAlphabet()
    {
        return this.alphabet;
    }

    /**
     *
     * @param cleartext contains plain text
     * @return the encrypted version of the plain text
     */
    public String encrypt(String cleartext)
    {
        encrypted = "";
        for (int i = 0; i < cleartext.length(); i++) {
            char ch = cleartext.charAt(i);
            int index = alphabet.indexOf(ch);

            if (index >= 0) {
                int newIndex = (index + key) % alphabet.length();
                char replace = alphabet.charAt(newIndex);
                encrypted += replace;
            } else {
                encrypted += ch;
            }
        }
        return encrypted;
    }

    /**
     *
     * @param cipherText contains an already encrypted text
     * @return the original text or what it's suppose to be
     */
    public String decrypt(String cipherText)
    {
        decrypted = "";
        for (int i = 0; i < cipherText.length(); i++) {
            char ch = cipherText.charAt(i);
            int index = alphabet.indexOf(ch);

            if (index >= 0) {
                int newIndex = (index - key) % alphabet.length();
                // The line below makes sure that the newIndex for each ch isn't negative by wrapping
                if (newIndex < 0) {newIndex += alphabet.length();}
                char replace = alphabet.charAt(newIndex);
                decrypted += replace;
            } else {
                decrypted += ch;
            }
        }
        return decrypted;
    }

}
