package Course3.w2;

import edu.duke.FileResource;
import edu.duke.URLResource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class GladLibMap {


    private HashMap<String, ArrayList> myMap;
    private ArrayList<String> seenList;
    private ArrayList<String> categoryUsed;
    private int count;

    private Random myRandom;

    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "datalong";

    public GladLibMap(){
        myMap = new HashMap<String, ArrayList>();
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
    }

    public GladLibMap(String source){
        myMap = new HashMap<String, ArrayList>();
        initializeFromSource(source);
        myRandom = new Random();
    }

    private void initializeFromSource(String source) {
        String[] categories = {"adjective", "noun", "color", "country", "name", "animal", "timeframe", "verb", "fruit"};
        for (String category : categories)
        {
            myMap.put(category, readIt(source + "/" + category + ".txt"));
        }
    }

    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }

    private String getSubstitute(String label) {
        if (categoryUsed.indexOf(label) == -1)
        {
            categoryUsed.add(label);
        }
        if (label.equals("number"))
        {
            String randomNum = "" + myRandom.nextInt(50)+5;
            if (myMap.containsKey(label))
            {
                myMap.get(label).add(randomNum);
                return randomNum;
            }
            else
            {
                myMap.put(label, new ArrayList());
                myMap.get(label).add(randomNum);
                return randomNum;
            }
        }

        if (myMap.containsKey(label))
        {
            return randomFrom(myMap.get(label));
        }

        return "**UNKNOWN**";
    }

    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String sub = getSubstitute(w.substring(first+1,last));
//		System.out.println("prefix+sub+suffix: " + prefix+sub+suffix);
        String wordChosen = prefix+sub+suffix;
        if (seenList.indexOf(wordChosen) == -1)
        {
            seenList.add(wordChosen);
        }
        else
        {
            sub = getSubstitute(w.substring(first+1,last));
            wordChosen = prefix+sub+suffix;
            count ++;
        }
        return wordChosen;
    }

    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }

    private String fromTemplate(String source){
        String story = "";
        count =0;
        categoryUsed = new ArrayList<>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        System.out.println("Total word changed: " + count);
        return story;
    }

    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }

    public void totalWordsInMap()
    {
        int sum=0;
        for (String category : myMap.keySet())
        {
            sum += myMap.get(category).size();
        }
        System.out.println("Total num of words to pick from = " + sum);
    }

    public void totalWordConsidered()
    {
        int sum =0;
        System.out.println("Category used: ");
        for (int i=0; i<categoryUsed.size(); i++)
        {
            System.out.println(categoryUsed.get(i));
        }
        for (String category: categoryUsed)
        {
//            System.out.println("curr category: " + category);
            int sizeOfArray = myMap.get(category).size();
            sum += sizeOfArray;
        }
        System.out.println("Total words in category used " + sum);
    }

    public void printMyMap(){
        System.out.println("\n");
        System.out.println("========== My Map =============");
        for (String key : myMap.keySet())
        {
            System.out.println(key + " : " + myMap.get(key));
        }
        System.out.println("========== My Map End =============");
    }

    public void makeStory(){
        seenList = new ArrayList<String>();
        System.out.println("\n");
        String story = fromTemplate("datalong/madtemplate2.txt");
        printOut(story, 60);
//        printMyMap();
        totalWordsInMap();
        totalWordConsidered();
    }

    public static void main(String[] args) {
        GladLibMap gladLibMap = new GladLibMap();
        gladLibMap.makeStory();
    }
}
