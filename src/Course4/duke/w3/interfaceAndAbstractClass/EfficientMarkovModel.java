package Course4.duke.w3.interfaceAndAbstractClass;

import java.util.ArrayList;
import java.util.HashMap;

public class EfficientMarkovModel extends AbstractMarkovModel{
    private HashMap<String, ArrayList<String>> wordFollowsMap;
    private int n;

    public EfficientMarkovModel(int order)
    {
        wordFollowsMap = new HashMap<String, ArrayList<String>>();
        n = order;
    }

    @Override
    public String getRandomText(int numChars) {

        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length()-n);
        String key = myText.substring(index, index+n);
        sb.append(key);
        buildHashMap();
        for (int k=0; k < numChars-n; k++){
            ArrayList<String> follows = wordFollowsMap.get(key);
//            System.out.println("key " + key + follows);
            if (follows.size() == 0)
            {
                break;
            }
            int randomChoiceFromPossibleChar = myRandom.nextInt(follows.size());

            String next = follows.get(randomChoiceFromPossibleChar);
            sb.append(next);
            key = key.substring(1) + next;
        }

        return sb.toString();
    }

    private void buildHashMap() {

        ArrayList<String> follows;
        for (int i = 0; i < myText.length(); i++) {
            String currWord = myText.substring(i, i + n);
            if (i + n + 1 > myText.length())
            {
                if (!wordFollowsMap.containsKey(currWord))
                {
                    follows = new ArrayList<String>();
                    wordFollowsMap.put(currWord, follows);
                }
                break;
            }
            String nextWord = myText.substring(i+ n, i+ n +1);

            if (!wordFollowsMap.containsKey(currWord))
            {
                follows = new ArrayList<String>();
                follows.add(nextWord);
                wordFollowsMap.put(currWord, follows);
            }
            else
            {
                wordFollowsMap.get(currWord).add(nextWord);
            }

        }

    }

//    public void printHasMapInfo()
//    {
//        buildHashMap();
//        int largestSize = -1;
//        ArrayList<String> wordListLargestSize = new ArrayList<String>();
//        for (String word : wordFollowsMap.keySet())
//        {
//            System.out.println("Key: " + word + ", Value: " + wordFollowsMap.get(word));
//            int currSize = wordFollowsMap.get(word).size();
//            if(currSize > largestSize) {largestSize = currSize;}
//        }
//
//        System.out.println("Number of keys: " + wordFollowsMap.size());
//        System.out.println("Largest Value Size: " + largestSize);
//
//        System.out.println("Word with largest value size:");
//        for (String word : wordFollowsMap.keySet())
//        {
//            if (wordFollowsMap.get(word).size() == largestSize)
//            {
//                System.out.println(word);
//            }
//        }
//
//    }

    @Override
    public String toString() {
        return "Efficient Markov Model";
    }

}
