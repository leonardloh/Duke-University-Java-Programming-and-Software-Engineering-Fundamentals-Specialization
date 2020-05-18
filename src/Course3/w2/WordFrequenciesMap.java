package Course3.w2;

import edu.duke.FileResource;

import java.util.HashMap;

public class WordFrequenciesMap {

    public void countWords()
    {
        FileResource fr = new FileResource();
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        int total =0;
        for(String w: fr.words())
        {
            w = w.toLowerCase();
            if(map.keySet().contains(w))
            {
                map.put(w, map.get(w)+1);
            }
            else
            {
                map.put(w, 1);
            }
        }
        for (String w: map.keySet())
        {
            int occurence = map.get(w);
            if (occurence > 200)
            {
                System.out.println(occurence + "\t" + w);
            }
        }
    }

    public static void main(String[] args) {
        WordFrequenciesMap wordFrequenciesMap = new WordFrequenciesMap();
        wordFrequenciesMap.countWords();
    }
}
