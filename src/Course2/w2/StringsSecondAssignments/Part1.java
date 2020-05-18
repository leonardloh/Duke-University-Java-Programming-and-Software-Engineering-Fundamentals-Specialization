package Course2.w2.StringsSecondAssignments;

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
        if (endCodonIndexTAA == dna.length() && endCodonIndexTAG == dna.length() && endCodonIndexTGA == dna.length()) return ""; //No gene found
        int minIndex = Math.min(endCodonIndexTAA, Math.min(endCodonIndexTAG, endCodonIndexTGA));
        return dna.substring(startCodonIndex, minIndex+3);
    }

    public String findGene(String dna){
        int startCodonIndex = dna.indexOf("ATG", 0);
        if (startCodonIndex == -1) return ""; //"No ATG found"
        int endCodonIndexTAA = findStopCodon(dna,startCodonIndex, "TAA");
        int endCodonIndexTAG = findStopCodon(dna,startCodonIndex, "TAG");
        int endCodonIndexTGA = findStopCodon(dna,startCodonIndex, "TGA");
        if (endCodonIndexTAA == dna.length() && endCodonIndexTAG == dna.length() && endCodonIndexTGA == dna.length()) return ""; //No gene found
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

    public static void main(String[] args) {
        Part1 p1 = new Part1();
//        p1.testFindStopCondon();
//        p1.testFindGene();
        p1.testPrintAllGenes();
    }

}
