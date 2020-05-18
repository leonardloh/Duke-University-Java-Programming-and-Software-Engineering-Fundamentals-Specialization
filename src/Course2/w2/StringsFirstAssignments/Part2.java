package Course2.w2.StringsFirstAssignments;

public class Part2 {
    public String FindSimpleGene(String gene, String startCodon, String stopCodon)
    {
        String finalGene ="";
        int firstIndex = gene.toLowerCase().indexOf(startCodon.toLowerCase());
        if (firstIndex == -1 ) return "NO GENE FOUND";
        int lastIndex = gene.toLowerCase().indexOf(stopCodon.toLowerCase(),firstIndex+3);
        if((firstIndex+3 - lastIndex)%3 ==0)
        {
            finalGene = gene.substring(firstIndex, lastIndex+3);
        }
        else
        {
            return "NO GENE FOUND";
        }
        return finalGene;
    }

    public void testFindSimpleGene(){
        String startCodon = "ATG";
        String endCodon = "TAA";

        String gene1 = "cjadshahAAAAGSTGATAA"; //no ATG
        System.out.println(FindSimpleGene(gene1, startCodon, endCodon));

        String gene2 = "cjadshahAAGTAGSTGA"; //no TAA
        System.out.println(FindSimpleGene(gene2, startCodon, endCodon));

        String gene3 = "cjadshahAAAGTAGTA"; //no ATG TAA
        System.out.println(FindSimpleGene(gene3, startCodon, endCodon));

        String gene4 = "cjadshahAATGAGSTGATAA"; //with ATG TAA
        System.out.println(FindSimpleGene(gene4, startCodon, endCodon));

        String gene5 = "cjadshahAATGAGSTGATAAATG111TAA".toLowerCase(); //lower case
        System.out.println(FindSimpleGene(gene5, startCodon, endCodon));
    }

    public static void main(String[] args) {
        Part2 p2 = new Part2();
        p2.testFindSimpleGene();
    }
}
