package Course3.w2;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class WordsInFiles {
    private HashMap<String, ArrayList> wordmap;

    public WordsInFiles()
    {
        wordmap = new HashMap<String, ArrayList>();
    }

    private void addWordsFromFile(File f)
    {
        FileResource fr = new FileResource(f);
        for (String word : fr.words())
        {
            if (wordmap.containsKey(word))
            {
                String filename = f.getName();
                if (!wordmap.get(word).contains(filename))
                {
                    wordmap.get(word).add(f.getName());
                }
            }
            else
            {
                wordmap.put(word, new ArrayList());
                wordmap.get(word).add(f.getName());
            }
        }

    }

    public void  buildWordFileMap()
    {
        wordmap = new HashMap<String, ArrayList>();

        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles())
        {
            addWordsFromFile(f);
        }
        for (String key : wordmap.keySet())
        {
            System.out.println(key + " "  + wordmap.get(key));
        }
        System.out.println("Total num of words in all files: " + wordmap.keySet().size());

    }

    public void maxNumber()
    {
        int maxLength = 0;
        for (String key : wordmap.keySet())
        {
            if (wordmap.get(key).size() > maxLength)
            {
                maxLength = wordmap.get(key).size();
            }
        }

        System.out.println("Max number = " + maxLength);
    }

    public ArrayList<String> wordsInNumFiles(int number)
    {
        ArrayList<String> wordList = new ArrayList<String>();
        for (String key : wordmap.keySet())
        {
            int arrayLength = wordmap.get(key).size();
            if (arrayLength == number)
            {
                wordList.add(key);
            }
        }

        return wordList;
    }

    public void printFilesIn (String word)
    {
        System.out.println("Print files in " + word);
        for (String key : wordmap.keySet())
        {
            if (key.equals(word))
            {
                ArrayList fileNames = wordmap.get(key);
                for (int i=0; i < fileNames.size(); i++)
                {
                    System.out.println(fileNames.get(i));
                }
            }
        }
    }

    public void testAddWordsFromFile()
    {
        File f = new File("D:\\Coursera Java Course\\ProgrammingImprovingGladLibsData\\brief1.txt");
        addWordsFromFile(f);
    }

    public void testBuildWordFileMap()
    {
        buildWordFileMap();
        maxNumber();
        ArrayList<String> wordLists = wordsInNumFiles(2);
        System.out.println(wordLists);
        printFilesIn("cats");

    }

    public void tester()
    {
        buildWordFileMap();
        maxNumber();
        ArrayList<String> wordLists = wordsInNumFiles(4);
        System.out.println(wordLists.size());
        printFilesIn("tree");
    }

    public static void main(String[] args) {
        WordsInFiles wordsInFiles = new WordsInFiles();
        wordsInFiles.tester();
    }
}
