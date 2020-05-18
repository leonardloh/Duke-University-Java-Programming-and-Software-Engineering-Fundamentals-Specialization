package Course4.duke.w3.interfaceAndAbstractClass;
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import edu.duke.*; 

public class MarkovRunnerWithInterface {
    public void runModel(IMarkovModel markov, String text, int size, int seed) {
    	markov.setRandom(seed);
        markov.setTraining(text);
        System.out.println("running with " + markov);
        for(int k=0; k < 3; k++){
			String st= markov.getRandomText(size);
			printOut(st);
		}
    }
    
    public void runMarkov() {
        FileResource fr = new FileResource();
		String st = fr.asString();
		st = st.replace('\n', ' ');
		int size = 200;
		int seed = 123;
		
        MarkovZero mz = new MarkovZero();
        runModel(mz, st, size, seed);
    
        MarkovOne mOne = new MarkovOne();
        runModel(mOne, st, size, seed);
        
        MarkovModel mThree = new MarkovModel(3);
        runModel(mThree, st, size, seed);
        
        MarkovFour mFour = new MarkovFour();
        runModel(mFour, st, size, seed);

    }

    public void compareMethods()
	{
		FileResource fr = new FileResource();
		String st = fr.asString();
		st = st.replace('\n', ' ');
		int order = 2;
		int seed = 42;
		int textLength = 1000;

		MarkovModel ml = new MarkovModel(order);
		EfficientMarkovModel eml = new EfficientMarkovModel(order);

		long startTime = System.nanoTime();
		for (int i = 0; i <3; i++)
		{
			runModel(ml, st, textLength, seed);
		}
		long stopTime = System.nanoTime();


		long startTime2 = System.nanoTime();
		for (int i = 0; i <3; i++)
		{
			runModel(eml, st, textLength, seed);
		}
		long stopTime2 = System.nanoTime();

		System.out.println("Total time taken for Markov Model: " + (stopTime - startTime));
		System.out.println("Total time taken for Efficient Markov Model: " + (stopTime2 - startTime2));
	}

    public void testHashMap() {
    	int seed = 42;
    	String st = "yes-this-is-a-thin-pretty-pink-thistle";
    	int size = 50;

		EfficientMarkovModel model = new EfficientMarkovModel(2);
		model.setTraining(st);
//		model.printHasMapInfo();
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
		MarkovRunnerWithInterface runner = new MarkovRunnerWithInterface();
//		runner.runMarkov();
//		runner.testHashMap();
		runner.compareMethods();
	}
	
}
