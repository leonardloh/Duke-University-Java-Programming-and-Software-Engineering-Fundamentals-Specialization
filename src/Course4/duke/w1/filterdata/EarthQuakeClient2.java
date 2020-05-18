package Course4.duke.w1.filterdata;

import java.util.*;
import edu.duke.*;

public class EarthQuakeClient2 {
    public EarthQuakeClient2() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) { 
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) { 
            if (f.satisfies(qe)) { 
                answer.add(qe); 
            } 
        } 
        
        return answer;
    } 

    public void quakesWithFilter() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        Filter f = new MagnitudeFilter(4.0, 5.0);
        ArrayList<QuakeEntry> m4  = filter(list, f);

        Filter f2 = new DepthFilter(-35000.0, -12000.0);
        ArrayList<QuakeEntry> d = filter(m4, f2);

//        Filter f = new DistanceFilter(new Location(35.42, 139.43), 10000000);
//        ArrayList<QuakeEntry> d  = filter(list, f);
//
//        Filter f2 = new PhraseFilter("end", "Japan");
//        ArrayList<QuakeEntry> p = filter(d, f2);

        for (QuakeEntry qe: d) {
            System.out.println(qe);
        }

        System.out.println("Num of quake found: " + d.size());

    }


    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "../data/nov20quakedata.atom";
//        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: "+list.size());
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

    public void testMatchAllFilter ()
    {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        MatchAllFilters maf = new MatchAllFilters();
        maf.addFilter(new MagnitudeFilter(0.0, 2.0));
        maf.addFilter(new DepthFilter( -100000.0 , -10000.0));
        maf.addFilter(new PhraseFilter("any", "a"));

        ArrayList<QuakeEntry> p = filter(list, maf);
        for (QuakeEntry qe : p)
        {
            System.out.println(qe);
        }

        System.out.println("Total quakes found: " + p.size());

        System.out.println("Filters used are: ");
        System.out.println(maf.getName());
    }


    public void testMatchAllFilter2() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "../data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("# quakes read: "+list.size());

        MatchAllFilters maf = new MatchAllFilters();
        maf.addFilter(new MagnitudeFilter(0.0, 3.0));
        maf.addFilter(new DistanceFilter(new Location(36.1314, -95.9372), 10000000 ));
        maf.addFilter(new PhraseFilter("any", "Ca"));

        ArrayList<QuakeEntry> p = filter(list, maf);
        for (QuakeEntry qe : p)
        {
            System.out.println(qe);
        }

        System.out.println("Total quakes found: " + p.size());

        System.out.println("Filters used are: ");
        System.out.println(maf.getName());
    }

    public static void main(String[] args) {
        EarthQuakeClient2 eqc2= new EarthQuakeClient2();
        eqc2.testMatchAllFilter2();

    }

}
