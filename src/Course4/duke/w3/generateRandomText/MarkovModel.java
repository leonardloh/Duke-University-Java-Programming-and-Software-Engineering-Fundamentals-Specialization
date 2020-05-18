package Course4.duke.w3.generateRandomText;

import java.util.ArrayList;
import java.util.Random;

public class MarkovModel {
    private int n;
    private String myText;
    private Random myRandom;

    public MarkovModel(int n) {
        this.n = n;
    }

    public void setRandom(int seed){
        myRandom = new Random(seed);
    }

    public void setTraining(String s){
        myText = s.trim();
    }

    public String getRandomText(int numChars){

        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length()-n);
        String key = myText.substring(index, index+n);
        System.out.println("first key:" + key);
        sb.append(key);

        for (int k=0; k < numChars-n; k++){
            ArrayList<String> follows = getFollows(key);
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

    public ArrayList<String> getFollows(String key)
    {
        ArrayList<String> follows = new ArrayList<String>();
        StringBuilder sb = new StringBuilder();
        int next = 0;
        for(int i=0; i <myText.length(); i++){
            int foundKeyIndex = myText.indexOf(key, next);
            if (foundKeyIndex == -1)
            {
                break;
            }
            int nextCharIdx = foundKeyIndex+ (key.length()-1) +1;
            if (nextCharIdx > myText.length()-1)
            {
                break;
            }
//           System.out.println(myText.charAt(nextCharIdx) + ":" + nextCharIdx);
//            System.out.println(myText.length()-1);
            sb.append(myText.charAt(nextCharIdx));
            next = nextCharIdx;
        }
        String sbString = sb.toString();
        for (char c : sbString.toCharArray())
        {
            follows.add(""+c);
        }

        return follows;
    }
}
