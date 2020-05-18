package Course2.w2.StringsThirdAssignments;

import edu.duke.FileResource;
import edu.duke.StorageResource;

public class Part1 {
    public int findStopCodon(String dna, int startIndex, String stopCodon)
    {
        int stopIndex = dna.indexOf(stopCodon,startIndex);
        int result = -1;
        if (stopIndex == -1)
        {
//            System.out.println("No stop codon was found return length: " + dna.length());
            return dna.length(); // no gene was found
        }

        if ((stopIndex - startIndex)%3 ==0)
        {
            result = stopIndex;
        }
        else
        {
            //the length between startIndex and stopIndex is not divisible by 3
            //return -1
//            System.out.println("Gene length not divisible by 3");
            result = dna.length();
        }

        return result;
    }

    public String findGene(String dna, int where){
        int startCodonIndex = dna.indexOf("ATG", where);
        if (startCodonIndex == -1) return ""; //"No ATG found"
        int endCodonIndexTAA = findStopCodon(dna,startCodonIndex, "TAA");
        int endCodonIndexTAG = findStopCodon(dna,startCodonIndex, "TAG");
        int endCodonIndexTGA = findStopCodon(dna,startCodonIndex, "TGA");
//        if (endCodonIndexTAA == dna.length() && endCodonIndexTAG == dna.length() && endCodonIndexTGA == dna.length()) return ""; //No gene found
        int minIndex = Math.min(endCodonIndexTAA, Math.min(endCodonIndexTAG, endCodonIndexTGA));
        if ((minIndex+3) > dna.length()) {
            return dna.substring(startCodonIndex, dna.length());
        }
        return dna.substring(startCodonIndex, minIndex+3);
    }

    public String findGene(String dna){
        int startCodonIndex = dna.indexOf("ATG", 0);
        if (startCodonIndex == -1){
            System.out.println("No ATG found");
            return ""; //"No ATG found"
        }
        int endCodonIndexTAA = findStopCodon(dna,startCodonIndex, "TAA");
        int endCodonIndexTAG = findStopCodon(dna,startCodonIndex, "TAG");
        int endCodonIndexTGA = findStopCodon(dna,startCodonIndex, "TGA");

        if (endCodonIndexTAA == dna.length() && endCodonIndexTAG == dna.length() && endCodonIndexTGA == dna.length()){
            System.out.println("No gene found");
            return "no gene"; //No gene found
        }
        int minIndex = Math.min(endCodonIndexTAA, Math.min(endCodonIndexTAG, endCodonIndexTGA));
        return dna.substring(startCodonIndex, minIndex+3);
    }

    public void printAllGenes(String dna)
    {
        int startIdx = 0;
        while(true){
            String currGene = findGene(dna, startIdx);
            if (currGene.isEmpty()) break;
            System.out.println(currGene);
            startIdx = dna.indexOf(currGene,startIdx) + currGene.length();
        }
    }

    public StorageResource getAllGenes(String dna)
    {
        int startIdx = 0;
        StorageResource geneLists = new StorageResource();
        while(true) {
            String currGene = findGene(dna, startIdx);
            if (currGene.isEmpty()) break;
            geneLists.add(currGene);
            startIdx = dna.indexOf(currGene, startIdx) + currGene.length();
        }
        return geneLists;
    }

    public float findNumChar(char c, String dna)
    {
        int num = 0;
        for(int i=0; i<dna.length(); i++)
        {
            if(dna.charAt(i) == c)
            {
                num++;
            }
        }
        return (float) num;
    }

    public float cgRatio(String dna)
    {
//        System.out.println("C num: " + findNumChar('C', dna) );
//        System.out.println("G num: " + findNumChar('G', dna) );
        float ratio = findNumChar('C', dna)/findNumChar('G', dna);
        return ratio;
    }

    public void processGenes(StorageResource sr)
    {
        //num of string longer than 9 char
        int numString = 0;

        int numGenes = sr.size();
        System.out.println("Number of genes: " + numGenes);

        System.out.println("num of string longer than 60 char: ");
        for(String s: sr.data())
        {
            if (s.length() > 60) {
                System.out.println(s);
                numString++;
            }
        }
        System.out.println("num of string longer than 60 char: " + numString);

//        print the Strings in sr whose C-G-ratio is higher than 0.35
        System.out.println("the Strings in sr whose C-G-ratio is higher than 0.35: ");
        int sum = 0;
        for(String s: sr.data())
        {
            if (cgRatio(s)>0.35)
            {
                System.out.println(s);
                sum++;
            }
        }
        System.out.println("print the number of strings in sr whose C-G-ratio is higher than 0.35: " + sum);

//        print the length of the longest gene in sr
        int longestLength = 0;
        for(String s: sr.data())
        {
            if (s.length() > longestLength)
            {
                longestLength = s.length();
            }
        }
        System.out.println("print the length of the longest gene in sr: " + longestLength);
    }

    public void testProcessGene(){

        //genes longer than 9 characters
        String dna1 = "ATGAAAAAAAAATAAATGAAAAAAAAATAA";
        processGenes(getAllGenes(dna1));

        //no genes longer than 9 characters
        String dna2 = "ATGAAATAA";
        processGenes(getAllGenes(dna2));

        //one DNA string that has some genes whose C-G-ratio is higher than 0.35
        String dna3 = "ATGCCCCCCCCCGGGTAG";
        processGenes(getAllGenes(dna3));

        //one DNA string that has some genes whose C-G-ratio is lower than 0.35
        String dna4 = "ATGACCGGGGGGGGTAGA";
        processGenes(getAllGenes(dna4));

    }

    public void testRealDNA(){

        //Modify your processGenes method so that it prints all the Strings that are longer than 60 characters and prints the number of Strings that are longer than 60 characters
        // (you do not need to make changes to the rest of the method).
        FileResource fr = new FileResource("C:\\Users\\LohJZ\\Downloads\\dna\\dna\\GRch38dnapart.fa");
        String dna = fr.asString();
        processGenes(getAllGenes(dna.toUpperCase()));
    }

    public void testcgRatio(){
        String dna = "CCCGGGGGGGGG";
        System.out.println(cgRatio(dna));
    }

    public void testFindStopCondon()
    {
        String dna = "hflajfjafahfZZZncjsn";
        String stopCodon = "ZZZ";
        System.out.println(findStopCodon(dna,0, stopCodon));
    }

    public void testFindGene(){
        // no ATG
        String dna1 = "fhcnahffhfhajTAAfjjfafaefaehTGA";
        System.out.println(findGene(dna1));

        // ATG with TAA
        String dna2 = "hfjancajdhafhlhfATGzzzzzzTGA";
        System.out.println(findGene(dna2));

        //ATG with multiple valid stop codon
        String dna3 = "hfjancajdhafhlhfATGzzzzzzTGAzzzzzzzzzTAAnnnnnnnnnnnnTAG";
        System.out.println(findGene(dna3));

        //ATG with no valid stop codon
        String dna4 = "hfjancajdhafhlhfATGzzzzzzz";
        System.out.println(findGene(dna4));

    }
    public void testPrintAllGenes(){
        String dna = "ATGATCTAATTTATGCTGCTGCTAATGTAGCTAGATGC";
        printAllGenes(dna);
    }

    public void testGetAllGenes()
    {
        String dna = "ATGATCTAATTTATGCTGCTGCTAATGTAGCTAGATGC";
        StorageResource allGenes = getAllGenes(dna);
        for(String gene: allGenes.data())
        {
            System.out.println(gene);
        }
    }

    public static void main(String[] args) {
        Course2.w2.StringsThirdAssignments.Part1 p1 = new Course2.w2.StringsThirdAssignments.Part1();
//        p1.testcgRatio();
//        p1.testProcessGene();
        p1.testRealDNA();
    }
}
