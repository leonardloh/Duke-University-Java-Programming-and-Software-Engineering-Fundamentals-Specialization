package Course4.duke.w3.interfaceAndAbstractClass;

import java.util.ArrayList;
import java.util.Random;

public class MarkovFour extends AbstractMarkovModel {

    public MarkovFour() {
        myRandom = new Random();
    }

//    public void setRandom(int seed){
//        myRandom = new Random(seed);
//    }

    public void setTraining(String s){
        myText = s.trim();
    }

    public String getRandomText(int numChars){

        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length()-4);
        String key = myText.substring(index, index+4);
//        System.out.println("first key:" + key);
        sb.append(key);

        for (int k=0; k < numChars-4; k++){
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

    @Override
    public String toString() {
        return "MarkovModel of order Four";
    }

}
