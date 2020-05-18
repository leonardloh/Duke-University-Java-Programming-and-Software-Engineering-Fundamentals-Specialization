package Course4.duke.w3.interfaceAndAbstractClass;
/**
 * Abstract class AbstractMarkovModel - write a description of the class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */

import java.util.*;

public abstract class AbstractMarkovModel implements IMarkovModel {
    protected String myText;
    protected Random myRandom;
    
    public AbstractMarkovModel() {
        myRandom = new Random();
    }
    
    public void setTraining(String s) {
        myText = s.trim();
    }
 
    abstract public String getRandomText(int numChars);

    public void setRandom(int seed)
    {
        myRandom = new Random(seed);
    }

    protected ArrayList<String> getFollows(String key)
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

    abstract public String toString();

}
