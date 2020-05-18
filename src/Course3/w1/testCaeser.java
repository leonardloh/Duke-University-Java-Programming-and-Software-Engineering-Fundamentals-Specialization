package Course3.w1;

import edu.duke.FileResource;

public class testCaeser {

    private int[] countLetters(String message)
    {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        int[] countLetters = new int[26];
        for(int i=0; i< message.length(); i++)
        {
            char currChar = Character.toLowerCase(message.charAt(i));
            int idx = alphabet.indexOf(currChar);
            if (idx != -1)
            {
                countLetters[idx]++;
            }
        }
        return countLetters;
    }

    public int indexOfMax(int[] values)
    {
        int max = 0;
        int maxIdx = 0;
        for (int i=0; i < values.length; i++)
        {
            if (values[i] > max)
            {
                max = values[i];
                maxIdx = i;
            }
        }
        return maxIdx;

    }

    public void simpleTests()
    {
        FileResource fr = new FileResource();
        String message = fr.asString();
        CaeserCipher cc = new CaeserCipher(18);
        String encryptedMsg = cc.encrypt(message);
        String decryptedMsg = cc.decrypt(encryptedMsg);
        System.out.println("Encrypt: " + encryptedMsg);
        System.out.println("Decrypt: " + decryptedMsg);

        CaesarBreaker cb = new CaesarBreaker();
        System.out.println("Cracked: " + cb.decrypt(encryptedMsg));
    }

    public static void main(String[] args) {
        testCaeser tc = new testCaeser();
        tc.simpleTests();
    }
}
