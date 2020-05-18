package Course3.w1;

import edu.duke.FileResource;

import java.io.File;

public class CaesarBreaker {
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

    public String decrypt (String encrypted)
    {
        CaeserCipher cc= new CaeserCipher();
        int dkey = getkey(encrypted);
        return cc.encrypt(encrypted, 26 - dkey);
    }

    public int getkey(String encrypted) {
        int[] freqs = countLetters(encrypted);
        int maxDex = indexOfMax(freqs);
        int dkey = maxDex-4;
        if (maxDex < 4)
        {
            dkey = 26 - (4-maxDex);
        }
        return dkey;
    }

    public String decryptTwoKeys(String encrypted)
    {
        CaeserCipher cc= new CaeserCipher();
        String firstChars = halfOfString(encrypted,0);
        String secondChars = halfOfString(encrypted, 1);
        int keyFirstChars = getkey(firstChars);
        int keySecondChars = getkey(secondChars);
        System.out.println("key 1 found: " + keyFirstChars);
        System.out.println("key 2 found: " + keySecondChars);
        return cc.encryptTwoKeys(encrypted, 26-keyFirstChars, 26-keySecondChars);
    }

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



    public void testDecrypt()
    {
//        FileResource fr = new FileResource();
//        CaeserCipher cc = new CaeserCipher();
//
//        String message = fr.asString();
//        String encryptedmsg = cc.encrypt(message, 20);
//        System.out.println("Encrypted: " + encryptedmsg);
//        System.out.println("Decrypted: " + decrypt(encryptedmsg));

//        System.out.println(halfOfString("Qbkm Zgis",1));

//        String message = fr.asString();
//        String encryptedmsg = cc.encryptTwoKeys(message, 20, 12);
//        System.out.println("Encrypted: " + encryptedmsg);
//        System.out.println("Decrypted: " + decryptTwoKeys("Akag tjw Xibhr awoa aoee xakex znxag xwko"));
        CaeserCipher cc= new CaeserCipher();
        System.out.println(cc.encryptTwoKeys("Hfs cpwewloj loks cd Hoto kyg Cyy", 26-14, 26-24));


    }


    public static void main(String[] args) {
        CaesarBreaker cb = new CaesarBreaker();
        cb.testDecrypt();
    }
}
