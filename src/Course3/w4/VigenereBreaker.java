package Course3.w4;

import java.util.*;
import edu.duke.*;

public class VigenereBreaker {

    public HashSet<String> readDictionary(FileResource fr)
    {
        HashSet<String> set = new HashSet<String>();
        for(String word : fr.words())
        {
            set.add(word.toLowerCase());
        }
        return set;
    }

    public int countWords(String message, HashSet<String> dictionary)
    {
        int count = 0;
        message = message.toLowerCase();
        for(String word : message.split("\\W+"))
        {
            if(dictionary.contains(word)) count++;
        }
        return count;
    }

    public String breakForLanguage(String encrypted, HashSet<String> dictionary)
    {
        int maxValidCount = 0;
        int bestKeyLength = 0;
        char c = mostCommonCharIn(dictionary);
        for (int i = 1; i <100; i++) {
            int[] keyArray = tryKeyLength(encrypted, i, c);
            VigenereCipher vc = new VigenereCipher(keyArray);
            String decryptMessage = vc.decrypt(encrypted);
            int validCount = countWords(decryptMessage, dictionary);
            if (validCount > maxValidCount)
            {
                maxValidCount = validCount;
                bestKeyLength = i;
            }
        }

        int[] keyArray = tryKeyLength(encrypted, bestKeyLength, c);
        System.out.println("Keys choosen are: ");
        for(int i=0; i<keyArray.length; i++)
        {
            System.out.println(keyArray[i]);
        }
        System.out.println("Total number of keys: " + keyArray.length);
        System.out.println("Number of valid words count: " + maxValidCount);
        VigenereCipher vc = new VigenereCipher(keyArray);
        String decryptMessage = vc.decrypt(encrypted);

        return decryptMessage;
    }

    public char mostCommonCharIn(HashSet<String> dictionary)
    {
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        for (String word : dictionary)
        {
            char[] charArray = word.toLowerCase().toCharArray();
            for(char c: charArray)
            {
                if (!map.containsKey(c))
                {
                    map.put(c, 1);
                }
                else
                {
                    map.put(c, map.get(c) + 1);
                }
            }
        }
        int maxCharCount = 0;
        char commonChar = ' ';
        for (char c : map.keySet())
        {
            int currCount = map.get(c);
            if (currCount > maxCharCount)
            {
                maxCharCount = currCount;
                commonChar = c;
            }
        }
        return commonChar;
    }

    public void breakForAlllangs (String encrypted, HashMap<String,HashSet<String>> languages)
    {
        int maxWordCount = 0;
        String languageUsed = null;
        for (String lang : languages.keySet())
        {
            HashSet<String> wordSet = languages.get(lang);
            String decryptMessage = breakForLanguage(encrypted, wordSet);
            int wordCount = countWords(decryptMessage, wordSet);
            if (wordCount > maxWordCount)
            {
                maxWordCount = wordCount;
                languageUsed = lang;
            }
        }

        HashSet<String> wordSet = languages.get(languageUsed);
        String decryptMessage = breakForLanguage(encrypted, wordSet);
        int wordCount = countWords(decryptMessage, wordSet);
        System.out.println("Total valid word count: " + wordCount);
        System.out.println("Language used: " + languageUsed);
        System.out.println("Decrypted message: \n" + decryptMessage);

    }

    public String sliceString(String message, int whichSlice, int totalSlices) {
        //REPLACE WITH YOUR CODE
        char[] charArray = message.toCharArray();
        String stringToReturn = "";
        for(int i = whichSlice; i < charArray.length; i+=totalSlices)
        {
            stringToReturn += charArray[i];
        }
        return stringToReturn;
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        CaesarCracker cc = new CaesarCracker(mostCommon);

        //WRITE YOUR CODE HERE
        for (int i = 0; i <klength; i++)
        {
            String slice = sliceString(encrypted, i, klength);
            key[i] = cc.getKey(slice);
        }
        return key;
    }

    public HashMap<String, HashSet<String>> mapLanguageDictionary()
    {
        HashMap<String, HashSet<String>> languageMap = new HashMap<String, HashSet<String>>();
        String[] languages = new String[] {"Danish", "Dutch", "English", "French", "German", "Italian", "Portuguese", "Spanish"};
        for (String language : languages)
        {
            System.out.println("Loading dictionary of " + language);
            FileResource fr = new FileResource("D:\\Coursera Java Course\\dictionaries\\" + language);
            languageMap.put(language, readDictionary(fr));
        }
        System.out.println("Loading completed!");
        return languageMap;
    }

    public void breakVigenere () {
        FileResource fr = new FileResource();
        String encryptedMessage = fr.asString();
        HashMap<String, HashSet<String>> languageMap = mapLanguageDictionary();
        breakForAlllangs(encryptedMessage, languageMap);

//        FileResource dictionaryFileResource = new FileResource();
//        HashSet<String> dictionary = readDictionary(dictionaryFileResource);
//        char c =mostCommonCharIn(dictionary);
//        System.out.println(c);

        //Automated breaking method old
//        System.out.println("Files to decrypt: ");
//        FileResource fr = new FileResource();
//        String encrypted = fr.asString();
//
//        System.out.println("Dictionary: ");
//        FileResource dictionaryFileResource = new FileResource();
//        HashSet<String> dictionary = readDictionary(dictionaryFileResource);
//
//        String decryptMessage = breakForLanguage(encrypted, dictionary);
//        System.out.println("Decrypted message: " + decryptMessage);

        //WRITE YOUR CODE HERE OLD
//        FileResource fr= new FileResource();
//        String message = fr.asString();
//        int[] key = tryKeyLength(message, 38, 'e');
//        System.out.println("Keys used are: ");
//        for(int i=0; i<key.length; i++)
//        {
//            System.out.println(key[i]);
//        }
//        VigenereCipher vc = new VigenereCipher(key);
//        System.out.println("Decrypted message: " + vc.decrypt(message));
//        System.out.println("Choose Dictionary... ");
//        FileResource dictionaryFileResource = new FileResource();
//        HashSet<String> dictionary = readDictionary(dictionaryFileResource);
//        System.out.println("Number of valid words: " + countWords(vc.decrypt(message), dictionary));
    }
    
}
