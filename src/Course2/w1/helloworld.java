package Course2.w1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class helloworld {
    public List<String> runHelloWorld() throws FileNotFoundException {
        File myFile = new File("C:\\Users\\LohJZ\\Downloads\\hello\\hello_unicode.txt");
        Scanner sc = new Scanner(myFile);
        List<String> stringData = new ArrayList<>();
        while (sc.hasNextLine()){
            String data = sc.nextLine();
            stringData.add(data);
        }
        sc.close();
        return stringData;
    }

    public static void main(String[] args) throws FileNotFoundException {
        helloworld hw = new helloworld();
        List<String> textLists = hw.runHelloWorld();
        for (String text: textLists){
            System.out.println(text);
        }
    }
}
