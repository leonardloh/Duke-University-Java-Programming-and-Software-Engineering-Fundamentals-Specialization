package Course3.w1;

public class CaesarCipherTwo {
    private String alphabet;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;
    private int key1;
    private int key2;

    public CaesarCipherTwo(int key1, int key2)
    {
        alphabet = "abcdefghijklmnopqrstuvwxyz";
        //computed shifted alphabet
        shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0, key1);
        shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0, key2);
        this.key1 = key1;
        this.key2 = key2;
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

    public String encrypt(String input)
    {
        StringBuilder encrypted = new StringBuilder(input);

//        System.out.println(alphabet);
//        System.out.println(shiftedAlphabetKey1);
//        System.out.println(shiftedAlphabetKey2);

        for(int i=0; i< encrypted.length()-1; i=i+2)
        {
            char currChar = encrypted.charAt(i);
            char enCurrChar = encryptCharacter(currChar, alphabet, shiftedAlphabet1);
            encrypted.setCharAt(i, enCurrChar);
            char nextChar = encrypted.charAt(i+1);
            char enNextChar = encryptCharacter(nextChar, alphabet, shiftedAlphabet2);
            encrypted.setCharAt(i+1, enNextChar);

        }
        return encrypted.toString();
    }

    public String decrypt(String input)
    {
        CaeserCipher cc= new CaeserCipher();
        return cc.encryptTwoKeys(input, 26-key1, 26-key2);
    }

}
