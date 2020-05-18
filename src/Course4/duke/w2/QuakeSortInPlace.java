package Course4.duke.w2;
/**
 * Write a description of class QuakeSortInPlace here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class QuakeSortInPlace {
    public QuakeSortInPlace() {
        // TODO Auto-generated constructor stub
    }
   
    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        for (int i=from+1; i< quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }

    
    public void sortByMagnitude(ArrayList<QuakeEntry> in) {
       
       for (int i=0; i< in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
           System.out.println("The ith iter: " + i);

//           if(i == 50)
//           {
//               for(QuakeEntry qe : in)
//               {
//                   System.out.println(qe);
//               }
//               break;
//           }
        }
        
    }

    public int getLargestDepth(ArrayList<QuakeEntry> quakes, int from)
    {
        int maxIdx = from;
        for (int i=from+1; i< quakes.size(); i++)
        {
            if(quakes.get(i).getDepth() > quakes.get(maxIdx).getDepth())
            {
                maxIdx = i;
            }
        }
        return maxIdx;
    }

    public void sortByLargestDepth(ArrayList<QuakeEntry> in)
    {
        for (int i=0; i<in.size(); i++)
        {
            int maxIdx = getLargestDepth(in, i);
            QuakeEntry quake = in.get(i);
            QuakeEntry maxQuake = in.get(maxIdx);
            in.set(i, maxQuake);
            in.set(maxIdx, quake);
        }
    }

    public void onePassBubbleSort(ArrayList<QuakeEntry> quakeData, int numSorted)
    {
        for (int i = 0; i < numSorted; i++)
        {
            for (int j =0; j< quakeData.size()-1; j++){
                double currQuake = quakeData.get(j).getMagnitude();
                double nextQuake = quakeData.get(j+1).getMagnitude();

                if (nextQuake < currQuake) {
                    quakeData.set(j, quakeData.get(j + 1));
                    quakeData.set(j + 1, quakeData.get(j));
                }
            }

            System.out.println("iter: " + numSorted);
            for(QuakeEntry qe : quakeData)
               {
                   System.out.println(qe);
               }
        }
    }

    public void sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> qe)
    {
        onePassBubbleSort(qe, qe.size()-1);
    }

    public void testSort() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/earthQuakeDataWeekDec6sample2.atom";
//        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);  
       
        System.out.println("read data for "+list.size()+" quakes");    
//        sortByMagnitude(list);
//        sortByLargestDepth(list);
//        sortByMagnitude(list);
        sortByMagnitudeWithBubbleSort(list);
        for (QuakeEntry qe: list) {
            System.out.println(qe);
        } 
        
    }
    
    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
//        String source = "data/nov20quakedatasmall.atom";
        String source = "data/earthquakeDataSampleSix2.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
    }
    
    public void dumpCSV(ArrayList<QuakeEntry> list) {
		System.out.println("Latitude,Longitude,Magnitude,Info");
		for(QuakeEntry qe : list){
			System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
			                  qe.getLocation().getLatitude(),
			                  qe.getLocation().getLongitude(),
			                  qe.getMagnitude(),
			                  qe.getInfo());
	    }
		
	}


    public static void main(String[] args) {
        QuakeSortInPlace qip = new QuakeSortInPlace();
        qip.testSort();
    }
}
