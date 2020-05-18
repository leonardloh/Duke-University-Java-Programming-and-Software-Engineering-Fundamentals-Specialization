package Course3.w2;

import edu.duke.FileResource;

import java.util.HashMap;

public class CodonCount {

    private HashMap<String, Integer> dnaCountMap;

    public CodonCount(){
        dnaCountMap = new HashMap<String, Integer>();
    }

    public void buildCodonMap(int start, String dna)
    {

        dnaCountMap = new HashMap<String, Integer>();
        for(int i = start; i< dna.length();i+=3)
        {
            if (i+3 <= dna.length())
            {
                String dnaSubSequence = dna.substring(i, i+3);
                if(dnaCountMap.keySet().contains(dnaSubSequence))
                {
                    dnaCountMap.put(dnaSubSequence, dnaCountMap.get(dnaSubSequence)+1);
                }
                else
                {
                    dnaCountMap.put(dnaSubSequence, 1);
                }
            }
        }

        System.out.println("Reading frame starting with " + start + " results in " + dnaCountMap.size() + " unique codons");

//        for (String key : dnaCountMap.keySet())
//        {
//            System.out.println(key + "\t" + dnaCountMap.get(key));
//        }
    }

    public String getMostCommonCodon()
    {
        int max = 0;
        String maxKey = null;
        for (String s : dnaCountMap.keySet())
        {
            if (dnaCountMap.get(s) > max)
            {
                max = dnaCountMap.get(s);
                maxKey = s;
            }
        }

        System.out.println("and most common codon is " + maxKey + " with count " + max);
        return maxKey;
    }

    public void printCodonCounts(int start, int end)
    {
        System.out.println("Codons between " + start + " & " +end + " inclusive are: ");
        for (String s : dnaCountMap.keySet())
        {
            if (dnaCountMap.get(s) >= start && dnaCountMap.get(s) < end)
            {
                System.out.println(s + " " + dnaCountMap.get(s));
            }
        }
    }

    public void testBuildCodonMap()
    {
        System.out.println("=============0==============");
        buildCodonMap(0, "CGTTCAAGTTCAA");
        System.out.println("=============1==============");
        buildCodonMap(1, "CGTTCAAGTTCAA");
        System.out.println("=============2==============");
        buildCodonMap(2, "CGTTCAAGTTCAA");
    }

    public void tester()
    {
        FileResource fr = new FileResource();
        String dna = fr.asString().toUpperCase();
//        String dna = "CGTTCAAGTTCAA";
        System.out.println("=============0==============");
        buildCodonMap(0, dna);
        String commonCodonZero = getMostCommonCodon();
        printCodonCounts(6,8);
        System.out.println("=============1==============");
        buildCodonMap(1, dna);
        String commonCodonOne = getMostCommonCodon();
        printCodonCounts(1,500);
        System.out.println("=============2==============");
        buildCodonMap(2, dna);
        String commonCodonTwo = getMostCommonCodon();
        printCodonCounts(1,500);

    }

    public static void main(String[] args) {
        CodonCount codonCount = new CodonCount();
//        codonCount.testBuildCodonMap();
        codonCount.tester();
    }

}
