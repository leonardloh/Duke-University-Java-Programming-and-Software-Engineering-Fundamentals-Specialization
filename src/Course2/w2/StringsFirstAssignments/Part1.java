package Course2.w2.StringsFirstAssignments;

public class Part1 {
    public String FindSimpleGene(String gene)
    {
        String finalGene ="";
        int firstIndex = gene.indexOf("ATG");
        if (firstIndex == -1 ) return "NO GENE FOUND";
        int lastIndex = gene.indexOf("TAA",firstIndex+3);
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
        String gene1 = "cjadshahAAAAGSTGATAA"; //no ATG
        System.out.println(FindSimpleGene(gene1));

        String gene2 = "cjadshahAAGTAGSTGA"; //no TAA
        System.out.println(FindSimpleGene(gene2));

        String gene3 = "cjadshahAAAGTAGTA"; //no ATG TAA
        System.out.println(FindSimpleGene(gene3));

        String gene4 = "cjadshahAATGAGSTGATAA"; //with ATG TAA
        System.out.println(FindSimpleGene(gene4));

        String gene5 = "AAATGCCCTAACTAGATTAAGAAACC"; //with ATG TAA
        System.out.println(FindSimpleGene(gene5));
    }
    public static void main(String[] args) {
        Part1 p1 = new Part1();
        p1.testFindSimpleGene();
    }
}
