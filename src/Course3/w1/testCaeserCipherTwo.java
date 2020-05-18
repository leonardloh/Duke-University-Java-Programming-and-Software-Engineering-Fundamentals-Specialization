package Course3.w1;

import edu.duke.FileResource;

public class testCaeserCipherTwo {
    public String halfOfString(String message, int start)
    {
        String resultedString = "";
        for (int i=start; i<message.length(); i+=2)
        {
            char currChar = message.charAt(i);
            resultedString += currChar;
        }
        return resultedString;
    }

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

    /**
     * Find most common length in the array
     * @param values
     * @return
     */
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

    private int getkey(String encrypted) {
        int[] freqs = countLetters(encrypted);
        int maxDex = indexOfMax(freqs);
        int dkey = maxDex-4;
        if (maxDex < 4)
        {
            dkey = 26 - (4-maxDex);
        }
        return dkey;
    }

    public String breakCaesarCipher(String input)
    {
        String firstChars = halfOfString(input,0);
        String secondChars = halfOfString(input, 1);
        int keyFirstChars = getkey(firstChars);
        int keySecondChars = getkey(secondChars);
        System.out.println("key 1 found: " + keyFirstChars);
        System.out.println("key 2 found: " + keySecondChars);
        CaesarCipherTwo cct = new CaesarCipherTwo(keyFirstChars, keySecondChars);
        return cct.decrypt(input);
    }

    public void simpleTests ()
    {
        FileResource fr = new FileResource();
        CaesarCipherTwo cct = new CaesarCipherTwo(17,3);
        String encryptedMsg= cct.encrypt(fr.asString());
        String decryptedMsg = cct.decrypt(encryptedMsg);

        System.out.println("Encrypted: " + encryptedMsg);
        System.out.println("Decrypted: " + decryptedMsg);

//        String msg = "Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!";
//        System.out.println("Automatically cracked: " + breakCaesarCipher(msg));
        System.out.println("Automatically cracked: " + breakCaesarCipher(fr.asString()));
    }


    public static void main(String[] args) {
        testCaeserCipherTwo testCaeserCipherTwo = new testCaeserCipherTwo();
        testCaeserCipherTwo.simpleTests();
    }
}
