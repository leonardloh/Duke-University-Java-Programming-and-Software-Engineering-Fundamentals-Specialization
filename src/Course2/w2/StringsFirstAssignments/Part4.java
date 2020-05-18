package Course2.w2.StringsFirstAssignments;

import edu.duke.URLResource;

public class Part4 {
    public static String pattern = "youtube.com";
    public static String quote = "\"";
    public void findOccurence()
    {
        URLResource ur = new URLResource("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
        for(String word: ur.words())
        {
            int patternIndex = word.indexOf(pattern);
            if (patternIndex != -1 )
            {
                int indexQuote1 = word.indexOf(quote);
                int indexQuote2 = word.indexOf(quote, word.indexOf(quote)+1);
                System.out.println(word.substring(indexQuote1, indexQuote2+1));
            }
        }
    }

    public static void main(String[] args) {
        Part4 p4 = new Part4();
        p4.findOccurence();
    }
}
