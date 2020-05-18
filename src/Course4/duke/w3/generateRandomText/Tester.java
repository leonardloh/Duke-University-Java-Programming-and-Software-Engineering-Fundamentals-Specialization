package Course4.duke.w3.generateRandomText;

import edu.duke.FileResource;

import java.util.ArrayList;

public class Tester {

    public void testGetFollows() {
        FileResource fr = new FileResource();
        String myText = fr.asString();
        MarkovOne mo = new MarkovOne();
//        String myText = "this is a test yes this is a test.";
        mo.setTraining(myText);
        ArrayList<String> output = mo.getFollows("th");
        System.out.println(output.size());
    }

    public static void main(String[] args) {
        Tester t = new Tester();
        t.testGetFollows();
    }

}
