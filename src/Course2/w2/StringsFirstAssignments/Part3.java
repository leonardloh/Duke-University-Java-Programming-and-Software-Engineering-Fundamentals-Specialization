package Course2.w2.StringsFirstAssignments;

public class Part3 {

    public boolean twoOccurrences(String stringa, String stringb)
    {
        int index = stringb.indexOf(stringa, stringb.indexOf(stringa) + 1);
        if (index == -1 ){
            return false;
        }
        return true;
    }

    public String lastPart(String stringa, String stringb)
    {
        if (twoOccurrences(stringa, stringb))
        {
            int index = stringb.indexOf(stringa, stringb.indexOf(stringa) + 1);
            return stringb.substring(index, index+stringa.length());
        }
        return stringb;
    }

    public void testtwoOccurrences()
    {
        System.out.println(twoOccurrences("atg", "ctgtatgta"));
        System.out.println(twoOccurrences("by", "A story by Abby Long"));
        System.out.println(twoOccurrences("a", "banana"));

        System.out.println(lastPart("atg", "ctgtatgta"));
        System.out.println(lastPart("by", "A story by Abby Long"));
        System.out.println(lastPart("a", "banana"));
    }

    public static void main(String[] args) {
        Part3 p3 = new Part3();
        p3.testtwoOccurrences();
    }
}
