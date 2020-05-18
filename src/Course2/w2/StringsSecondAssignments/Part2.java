package Course2.w2.StringsSecondAssignments;

public class Part2 {
    public int howMany(String pattern, String dna)
    {
        int startIdx = 0;
        int sum = 0;
        while(true){
            int currPatternIdx = dna.indexOf(pattern, startIdx);
            if (currPatternIdx == -1)
            {
                break;
            }
            sum++;
            startIdx = currPatternIdx + pattern.length();
        }
        return sum;
    }

    public void testHowMany(){
        String stringa = "GAA";
        String stringb = "ATGAACGAATTGAATC";
        System.out.println("Total occurences of " + stringa + " in " + stringb + " = " + howMany(stringa, stringb));

        stringa = "AA";
        stringb = "ATAAAA";
        System.out.println("Total occurences of " + stringa + " in " + stringb + " = " + howMany(stringa, stringb));
    }

    public static void main(String[] args) {
        Part2 p2 = new Part2();
        p2.testHowMany();
    }
}
