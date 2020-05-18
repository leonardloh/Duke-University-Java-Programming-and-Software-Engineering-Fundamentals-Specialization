package Course4.duke.w4.WordGramClassStarterProgram;

import java.util.ArrayList;
import java.util.Random;

public class MarkovWord implements IMarkovModel{
    private String[] myText;
    private Random myRandom;
    private int order;

    public MarkovWord(int order) {

        myRandom = new Random();
        this.order = order;
    }

    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }

    public void setTraining(String text){
        myText = text.split("\\s+");
    }

    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length - order);
        WordGram key = new WordGram(myText, index, order);
        sb.append(key);
        sb.append(" ");
        for(int i = 0; i < numWords - order; i++) {
            ArrayList<String> follows = getFollows(key);
            if(follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            key = key.shiftAdd(next);
        }
        return sb.toString().trim();
    }

    private int indexOf(String[] words, WordGram target, int start)
    {
        ArrayList<WordGram> list = new ArrayList<WordGram>();
        for(int j = 0; j < words.length - order; j++) {
            WordGram wg = new WordGram(words, j, order);
            list.add(wg);
        }
        for(int i = start; i < words.length; i++) {
            if(i >= list.size()) {
                return -1;
            }
            if(target.equals(list.get(i))) {
                return i;
            }
        }
        return -1;
    }

    private ArrayList<String> getFollows(WordGram kGram) {
        ArrayList<String> follows = new ArrayList<String>();
        int pos = 0;
        while(pos < myText.length) {
            int start = indexOf(myText, kGram, pos);
            if(start == -1) {
                break;
            }
            if(start + kGram.length() >= myText.length - order) {
                break;
            }
            String next = myText[start + order];
            follows.add(next);
            pos = start + order;
        }
        return follows;
    }

}
