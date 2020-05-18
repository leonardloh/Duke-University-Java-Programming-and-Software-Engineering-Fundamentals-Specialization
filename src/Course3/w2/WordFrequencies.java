package Course3.w2;

import edu.duke.FileResource;

import java.util.ArrayList;

public class WordFrequencies {

    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;

    public WordFrequencies()
    {
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }

    public void findUnique()
    {
        FileResource fr =  new FileResource();
        for (String word : fr.words())
        {
            word = word.toLowerCase();
            int idx = myWords.indexOf(word);
            if (idx == -1)
            {
                myWords.add(word);
                myFreqs.add(1);
            }
            else
            {
                myFreqs.set(idx, myFreqs.get(idx)+1);
            }
        }

    }

    public int findIndexOfMax()
    {
        int maxIdx = 0;
        int max = 0;
        for (int i =0; i< myFreqs.size(); i++)
        {
            int currFreq = myFreqs.get(i);
            if (currFreq > max)
            {
                max = currFreq;
                maxIdx = i;
            }
        }

        return maxIdx;
    }

    public void testFindUnique()
    {
        findUnique();
        System.out.println("Number of unique words: " + myWords.size());
        for (int i =0; i< myWords.size(); i++)
        {
            System.out.println(myFreqs.get(i) + "\t" + myWords.get(i));
        }
        int maxIdx = findIndexOfMax();
        System.out.println("all num words: " + myWords.size());
        System.out.println("The word that occurs most often and its count are: " + myWords.get(maxIdx) + " " + myFreqs.get(maxIdx));
    }

    public static void main(String[] args) {
        WordFrequencies wf = new WordFrequencies();
        wf.testFindUnique();
    }
}
