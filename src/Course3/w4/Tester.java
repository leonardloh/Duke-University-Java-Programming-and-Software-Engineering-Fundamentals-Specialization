package Course3.w4;

import Course3.w1.CaesarBreaker;
import edu.duke.FileResource;

public class Tester {

    public void testCaesarCipherEncrypt()
    {
        CaesarCipher cipher = new CaesarCipher(3);
        FileResource fr = new FileResource();
        String messageToEncrypt = fr.asString();
        String ecryptedMessage = cipher.encrypt(messageToEncrypt);
        System.out.println("Original message: " + messageToEncrypt);
        System.out.println("Encrypted message: " + ecryptedMessage);
        System.out.println("Decrypted message: " + cipher.decrypt(ecryptedMessage));
    }

    public void testCaesarBreaker()
    {
        CaesarBreaker cb = new CaesarBreaker();
        FileResource fr = new FileResource();
        String messageToEncrypt = fr.asString();
        System.out.println("Encrypted message: " + messageToEncrypt);
        int key = cb.getkey(messageToEncrypt);
        System.out.println("The key is: " + key);
    }

    public void testVigeneCipher()
    {
        VigenereCipher vc = new VigenereCipher(new int[] {17,14,12,4});
        FileResource fr = new FileResource();
        String messageToEncrypt = fr.asString();
        String encryptedMessage = vc.encrypt(messageToEncrypt);
        String decryptedMessage = vc.decrypt(encryptedMessage);
        System.out.println("Original message: " + messageToEncrypt);
        System.out.println("Encrypted message: " + encryptedMessage);
        System.out.println("Decrypted message: " + decryptedMessage);
    }

    public void testVigenereBreakerSliceString()
    {
        VigenereBreaker vb = new VigenereBreaker();
        System.out.println(vb.sliceString("abcdefghijklm",0,3));
        System.out.println(vb.sliceString("abcdefghijklm",1,3));
        System.out.println(vb.sliceString("abcdefghijklm",2,3));
        System.out.println(vb.sliceString("abcdefghijklm",0,4));
        System.out.println(vb.sliceString("abcdefghijklm",1,4));
        System.out.println(vb.sliceString("abcdefghijklm",2,4));
        System.out.println(vb.sliceString("abcdefghijklm",3,4));
        System.out.println(vb.sliceString("abcdefghijklm",0,5));
        System.out.println(vb.sliceString("abcdefghijklm",1,5));
        System.out.println(vb.sliceString("abcdefghijklm",2,5));
        System.out.println(vb.sliceString("abcdefghijklm",3,5));
        System.out.println(vb.sliceString("abcdefghijklm",4,5));
    }

    public void testVigeneCipherTryKeyLength()
    {
        VigenereBreaker vb = new VigenereBreaker();
        FileResource fr = new FileResource();
        String message = fr.asString();
        int[] key = vb.tryKeyLength(message, 5, 'e');
        for(int i: key)
        {
            System.out.println(i);
        }
    }

    public void testVigenereBreaker()
    {
        VigenereBreaker vb = new VigenereBreaker();
        vb.breakVigenere();
    }


    public static void main(String[] args) {
        Tester tester = new Tester();
//        tester.testCaesarCipherEncrypt();
//        tester.testCaesarBreaker();
//        tester.testVigeneCipher();
//        tester.testVigenereBreakerSliceString();
//        tester.testVigeneCipherTryKeyLength();
        tester.testVigenereBreaker();
    }
}
