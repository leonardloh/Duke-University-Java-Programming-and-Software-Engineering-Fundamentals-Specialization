package Course4.duke.w2sortscale;

import java.util.Comparator;

public class TitleAndDepthComparator implements Comparator<QuakeEntry> {

    @Override
    public int compare(QuakeEntry q1, QuakeEntry q2) {
        String title1 = q1.getInfo();
        String title2 = q2.getInfo();
        int compare = title1.compareTo(title2);
        if (compare != 0) return compare;
        double depth1 = q1.getDepth();
        double depth2 = q2.getDepth();
        return Double.compare(depth1, depth2);
    }
}
