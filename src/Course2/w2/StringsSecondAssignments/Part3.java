package Course2.w2.StringsSecondAssignments;

import java.util.ArrayList;
import java.util.List;

public class Part3 {

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

    public List findAllGenes(String dna)
    {
        int startIdx = 0;
        List allGenes = new ArrayList();
        while(true){
            String currGene = findGene(dna, startIdx);
            if (currGene.isEmpty()) break;
            allGenes.add(currGene);
            startIdx = dna.indexOf(currGene,startIdx) + currGene.length();
        }
        return allGenes;
    }

    public int countGenes(String dna){
        List allGenes = findAllGenes(dna);
        return allGenes.size();
    }

    public void testCountGenes(){
        String dna1 = "ATGTAAGATGCCCTAGT";
        System.out.println(countGenes(dna1));
        String dna2 = "ATGTAAGATGCCCTAGTzATGTAAGATGCCCTAGTzATGTAAGATGCCCTAGT";
        System.out.println(countGenes(dna2));
    }

    public static void main(String[] args) {
        Part3 p3 = new Part3();
        p3.testCountGenes();

    }
}
