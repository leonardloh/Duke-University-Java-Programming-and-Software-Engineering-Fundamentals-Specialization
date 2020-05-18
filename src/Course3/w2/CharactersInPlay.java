package Course3.w2;

import edu.duke.FileResource;

import java.util.ArrayList;

public class CharactersInPlay {

    private ArrayList<String> names = new ArrayList<String>();
    private ArrayList<Integer> counts = new ArrayList<Integer>();

    public void update(String person)
    {
        int idx = names.indexOf(person);
        if (idx == -1)
        {
            names.add(person);
            counts.add(1);
        }
        else
        {
            counts.set(idx, counts.get(idx)+1);
        }
    }

    public void findAllCharacters()
    {
        FileResource fr = new FileResource();
        for (String s : fr.lines())
        {
            String name = "";
            char[] chars = s.toCharArray();
            for (int i=0; i< chars.length; i++)
            {
                if (chars[i] == '.')
                {
//                  System.out.println("update name: " + name);
                  update(name);
                  name ="";

                }
                else
                {
                    name += chars[i];
                }
//                System.out.println(name);
            }
        }
    }


    public void tester()
    {
        findAllCharacters();

        for (int i=0; i< names.size(); i++)
        {
            charactersWithNumParts(i, 10);

        }
    }

    private void charactersWithNumParts(int num1, int threshold) {
        if(counts.get(num1) > threshold)
        {
            System.out.println(names.get(num1) + "\t" + counts.get(num1));
        }
    }

    public static void main(String[] args) {
        CharactersInPlay charactersInPlay = new CharactersInPlay();
        charactersInPlay.tester();
    }


}
