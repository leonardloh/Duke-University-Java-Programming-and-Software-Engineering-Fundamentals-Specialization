package Course4.duke.w1;

import java.util.ArrayList;
import java.util.Collections;

public class LargestQuake {

    public static int indexOfLargest(ArrayList<QuakeEntry> data){
        int maxIdx = 0;
        for (int i = 0; i < data.size(); i++)
        {
            double currMagnitude = data.get(i).getMagnitude();
            if (currMagnitude > data.get(maxIdx).getMagnitude())
            {
                maxIdx = i;
            }
        }

        return maxIdx;
    }

    public static ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData, int howMany)
    {
        ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(quakeData);
        ArrayList<QuakeEntry> result = new ArrayList<QuakeEntry>();
        for (int i = 0; i < howMany; i++)
        {
            int indexLargest = indexOfLargest(copy);
            result.add(copy.get(indexLargest));
            copy.remove(indexLargest);
        }
        return result;
    }


    public static void findLargestQuake()
    {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
//        String source = "data/nov20quakedatasmall.atom";
//        String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size());
        int indexLargest = indexOfLargest(list);
        ArrayList<QuakeEntry> topNlargest = getLargest(list, 5);
        for (int i = 0; i < topNlargest.size(); i++)
        {
            System.out.println(topNlargest.get(i));
        }
    }

    public static void main(String[] args) {
        LargestQuake lq = new LargestQuake();
        lq.findLargestQuake();
    }
}
