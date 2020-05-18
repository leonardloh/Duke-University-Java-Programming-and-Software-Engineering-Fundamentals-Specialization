package Course3.w1;

import edu.duke.FileResource;

public class CaeserCipher {

    private String alphabet;
    private String shiftedAlphabet;
    private int mainKey;

    public CaeserCipher (int key)
    {
        alphabet = "abcdefghijklmnopqrstuvwxyz";
        //computed shifted alphabet
        shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
        mainKey = key;
    }

    public CaeserCipher ()
    {
        alphabet = "abcdefghijklmnopqrstuvwxyz";
        //computed shifted alphabet
    }


    public String encrypt(String input)
    {
        StringBuilder encrypted = new StringBuilder(input);
        for(int i=0; i< encrypted.length(); i++)
        {
            char currChar = encrypted.charAt(i);
                //check if the currChar lower or upper case
                //if lower case
                if (Character.isUpperCase(currChar))
                {
                    int idx = alphabet.toUpperCase().indexOf(currChar);
                    if (idx != -1)
                    {
                        char newChar = Character.toUpperCase(shiftedAlphabet.charAt(idx));
                        encrypted.setCharAt(i, newChar);
                    }
                }
                //if upper case
                else if (Character.isLowerCase(currChar))
                {
                    int idx = alphabet.indexOf(currChar);
                    if (idx != -1)
                    {
                        char newChar = shiftedAlphabet.charAt(idx);
                        encrypted.setCharAt(i, newChar);
                    }
                }
            }
        return encrypted.toString();
    }

    public String encrypt(String input, int key)
    {
        alphabet = "abcdefghijklmnopqrstuvwxyz";
        //computed shifted alphabet
        shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
        StringBuilder encrypted = new StringBuilder(input);
        for(int i=0; i< encrypted.length(); i++)
        {
            char currChar = encrypted.charAt(i);
            //check if the currChar lower or upper case
            //if lower case
            if (Character.isUpperCase(currChar))
            {
                int idx = alphabet.toUpperCase().indexOf(currChar);
                if (idx != -1)
                {
                    char newChar = Character.toUpperCase(shiftedAlphabet.charAt(idx));
                    encrypted.setCharAt(i, newChar);
                }
            }
            //if upper case
            else if (Character.isLowerCase(currChar))
            {
                int idx = alphabet.indexOf(currChar);
                if (idx != -1)
                {
                    char newChar = shiftedAlphabet.charAt(idx);
                    encrypted.setCharAt(i, newChar);
                }
            }
        }
        return encrypted.toString();
    }

    private char encryptCharacter (char currChar, String alphabet, String shiftedAlphabet)
    {
        char result = ' ';
        if (Character.isUpperCase(currChar))
        {
            int idx = alphabet.toUpperCase().indexOf(currChar);
            if (idx != -1)
            {
                char newChar = Character.toUpperCase(shiftedAlphabet.charAt(idx));
                result = newChar;
            }
        }
        //if upper case
        else if (Character.isLowerCase(currChar))
        {
            int idx = alphabet.indexOf(currChar);
            if (idx != -1)
            {
                char newChar = shiftedAlphabet.charAt(idx);
                result = newChar;
            }
        }

        return result;
    }

    public String encryptTwoKeys (String input, int key1, int key2)
    {
        StringBuilder encrypted = new StringBuilder(input);
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        //computed shifted alphabet
        String shiftedAlphabetKey1 = alphabet.substring(key1) + alphabet.substring(0, key1);
        String shiftedAlphabetKey2 = alphabet.substring(key2) + alphabet.substring(0, key2);

        System.out.println(alphabet);
        System.out.println(shiftedAlphabetKey1);
        System.out.println(shiftedAlphabetKey2);

        for(int i=0; i< encrypted.length()-1; i=i+2)
        {
            char currChar = encrypted.charAt(i);
            char enCurrChar = encryptCharacter(currChar, alphabet, shiftedAlphabetKey1);
            encrypted.setCharAt(i, enCurrChar);
            char nextChar = encrypted.charAt(i+1);
            char enNextChar = encryptCharacter(nextChar, alphabet, shiftedAlphabetKey2);
            encrypted.setCharAt(i+1, enNextChar);

        }
        return encrypted.toString();

    }

    public String decrypt(String input)
    {
        CaeserCipher cc = new CaeserCipher(26 - mainKey);
        return cc.encrypt(input);
    }

    public void testCaesar()
    {
//        int key = 23;
//        FileResource fr = new FileResource();
//        String message = fr.asString();
//        String encrypted = encryptCase(message, key);
//        System.out.println("key is " + key + "\n" + encrypted);

        //tester
//        System.out.println(encrypt("Can you imagine life WITHOUT the internet AND computers in your pocket?", 15));

        //tester
        System.out.println(encryptTwoKeys("Can you imagine life WITHOUT the internet AND computers in your pocket?", 21, 8));
    }


    public static void main(String[] args) {
        CaeserCipher cc = new CaeserCipher(23);
        cc.testCaesar();
    }
}
