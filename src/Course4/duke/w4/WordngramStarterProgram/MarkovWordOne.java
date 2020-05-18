package Course4.duke.w4.WordngramStarterProgram;
/**
 * Write a description of class MarkovWordOne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MarkovWordOne implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    
    public MarkovWordOne() {
        myRandom = new Random();
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
		myText = text.split("\\s+");
	}
	
	public String getRandomText(int numWords){
		StringBuilder sb = new StringBuilder();
		int index = myRandom.nextInt(myText.length-1);  // random word to start with
		String key = myText[index];
		sb.append(key);
		sb.append(" ");
		for(int k=0; k < numWords-1; k++){
		    ArrayList<String> follows = getFollows(key);
//			System.out.println(key + ": " + follows); //print key and follows for debug
		    if (follows.size() == 0) {
		        break;
		    }
			index = myRandom.nextInt(follows.size());
			String next = follows.get(index);
			sb.append(next);
			sb.append(" ");
			key = next;
		}
		
		return sb.toString().trim();
	}

	private int indexOf(String[] words, String target, int start)
	{
		for (int i = start; i < words.length; i++)
		{
			if (words[i].equals(target))
			{
				return i;
			}
		}
		return -1;
	}
	
	private ArrayList<String> getFollows(String key) {
	    ArrayList<String> follows = new ArrayList<String>();
		StringBuilder sb = new StringBuilder();
		int next = 0;
		for(int i=0; i <myText.length; i++){
			int foundKeyIndex = indexOf(myText, key, next);
			if (foundKeyIndex == -1)
			{
				break;
			}
			int nextWordIdx = foundKeyIndex +1;
			if (nextWordIdx > myText.length-1)
			{
				break;
			}
			sb.append(myText[nextWordIdx] + " ");
			next = nextWordIdx;
		}
		String sbString = sb.toString();
		String[] stringArray = sbString.split("\\s+");
		for (String word : stringArray)
		{
			follows.add(word);
		}
		return follows;
    }

    public void testIndexOf(){
    	String text = "this is just a test yes this is a simple test";
    	String[] wordsArray = text.split("\\s+");
		System.out.println(indexOf(wordsArray, "test", 5));
	}

}
