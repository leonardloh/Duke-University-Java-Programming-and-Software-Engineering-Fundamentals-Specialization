package Course4.duke.w4.WordngramStarterProgram;
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class MarkovRunner {
    public void runModel(IMarkovModel markov, String text, int size){ 
        markov.setTraining(text); 
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runModel(IMarkovModel markov, String text, int size, int seed){ 
        markov.setTraining(text); 
        markov.setRandom(seed);
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runMarkov() {
        int seed = 549;
        int size = 200;
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' ');
        MarkovWordTwo markovWord = new MarkovWordTwo();
        runModel(markovWord, st, size, seed);
    }


    public void testMarkov()
    {
        String st = "this is a test yes this is just a simple test";
        st = st.replace('\n', ' ');
        MarkovWordTwo markovWord = new MarkovWordTwo();
        runModel(markovWord, st, 200);
    }

    private void printOut(String s){
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------------------------");
        for(int k=0; k < words.length; k++){
            System.out.print(words[k]+ " ");
            psize += words[k].length() + 1;
            if (psize > 60) {
                System.out.println(); 
                psize = 0;
            } 
        } 
        System.out.println("\n----------------------------------");
    }

    public static void main(String[] args) {
        MarkovRunner runner = new MarkovRunner();
        runner.runMarkov();
    }

}