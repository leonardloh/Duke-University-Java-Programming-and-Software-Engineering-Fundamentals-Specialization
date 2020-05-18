package Course4.duke.w2sortscale;

import java.util.Comparator;

public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry> {
    @Override
    public int compare(QuakeEntry q1, QuakeEntry q2) {
        String[] lastWordArray1 = q1.getInfo().split("\\b+");
        String lastWord1 = lastWordArray1[lastWordArray1.length - 1];

        String[] lastWordArray2 = q2.getInfo().split("\\b+");
        String lastWord2 = lastWordArray2[lastWordArray2.length - 1];

        int compareText = lastWord1.compareTo(lastWord2);

//        System.out.println(lastWord1 + " " + lastWord2);
//        System.out.println("compare txt: " + compareText);
        if (compareText != 0){return compareText;}

        double magnitude1 = q1.getMagnitude();
        double magnitude2 = q2.getMagnitude();

        return Double.compare(magnitude1, magnitude2);
    }
}
