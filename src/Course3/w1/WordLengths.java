package Course3.w1;

import edu.duke.FileResource;

import java.io.File;
import java.lang.reflect.Array;

public class WordLengths {

    public void countWordLengths(FileResource resource, int[] counts)
    {
        for (String word : resource.words())
        {
            int length = 0;
            for (int i=0; i< word.length(); i++)
            {
                char currChar = word.charAt(i);
                if (Character.isLetter(currChar))
                {
                    length ++;
                }
            }
            counts[length] ++;
        }

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


    public void testCountWordLengths()
    {
        FileResource fr = new FileResource();
        int[] counts = new int[31];
        countWordLengths(fr, counts);
        for (int i = 0; i< counts.length; i++)
        {
            System.out.println(i + ": " + counts[i]);
        }
        System.out.println("Most common length: " + indexOfMax(counts));
    }

    public static void main(String[] args) {
        WordLengths wl = new WordLengths();
        wl.testCountWordLengths();
    }

}
