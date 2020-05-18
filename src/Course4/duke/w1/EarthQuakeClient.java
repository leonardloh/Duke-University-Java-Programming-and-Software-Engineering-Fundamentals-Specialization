package Course4.duke.w1;

import java.util.*;
import edu.duke.*;

public class EarthQuakeClient {
    public EarthQuakeClient() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData,
    double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // TODO
        for (QuakeEntry currentQuake : quakeData) {
            if (currentQuake.getMagnitude() > magMin) {
                answer.add(currentQuake);
            }
        }
        return answer;
    }

    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData,
    double distMax,
    Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // TODO
        for (QuakeEntry quake : quakeData) {
            if(from.distanceTo(quake.getLocation())/1000 < distMax) {
                answer.add(quake);
            }
        }
        return answer;
    }

    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }

    }

    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
//        String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        ArrayList<QuakeEntry> filteredList = filterByMagnitude(list, 5.0);
        for (QuakeEntry quakeEntry : filteredList)
        {
            System.out.println(quakeEntry);
        }
        System.out.println("Found " + filteredList.size() + " quakes that match that criteria");
    }

    public void closeToMe(){
        EarthQuakeParser parser = new EarthQuakeParser();
//        String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        // This location is Durham, NC
//        Location city = new Location(35.988, -78.907);

        // This location is Bridgeport, CA
         Location city =  new Location(38.17, -118.82);

        // TODO
        ArrayList<QuakeEntry> quakeWithinDistance = filterByDistanceFrom(list, 1000.00, city);
        for(QuakeEntry quakeEntry : quakeWithinDistance)
        {
            System.out.println(city.distanceTo(quakeEntry.getLocation())/1000 + " " + quakeEntry.getInfo());
        }

        System.out.println("Found " + quakeWithinDistance.size() + " quakes that match that criteria.");

    }

    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }


    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData, double minDepth, double maxDepth)
    {
        ArrayList<QuakeEntry> ans = new ArrayList<QuakeEntry>();
        for (QuakeEntry qe : quakeData)
        {
            double depth = qe.getDepth();
            if (depth > minDepth && depth < maxDepth)
            {
                ans.add(qe);
            }
        }
        return ans;
    }


    public void quakesOfDepth()
    {
        EarthQuakeParser parser = new EarthQuakeParser();
//        String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        double minDepth = -10000.00;
        double maxDepth = -8000.00;
        ArrayList<QuakeEntry> filteredList = filterByDepth(list, minDepth, maxDepth);
        System.out.println("Find quakes with depth between " + minDepth + " and " + maxDepth);
        for (QuakeEntry quakeEntry : filteredList)
        {
            System.out.println(quakeEntry);
        }
        System.out.println("Found " + filteredList.size() + " quakes that match that criteria.");
    }

    public ArrayList<QuakeEntry> filterByPhase(ArrayList<QuakeEntry> quakeData, String where, String phrase)
    {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for (QuakeEntry quake : quakeData)
        {
            String title = quake.getInfo();
            String[] splited = title.split("\\b+");
            if (where.toLowerCase().equals("start"))
            {
                if (splited[0].contains(phrase))
                {
                    answer.add(quake);
                }
            }
            if (where.toLowerCase().equals("end"))
            {
                if (splited[splited.length - 1].contains(phrase))
                {
                    answer.add(quake);
                }
            }
            if (where.toLowerCase().equals("any"))
            {

                if (title.indexOf(phrase) != -1)
                {
                    answer.add(quake);
                }

            }
        }
        return answer;
    }

    public void filterByPhase()
    {
        EarthQuakeParser parser = new EarthQuakeParser();
//        String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        ArrayList<QuakeEntry> result = filterByPhase(list, "any", "Creek");
        for (QuakeEntry quake : result)
        {
            System.out.println(quake);
        }
        System.out.println("Found " + result.size() + " quakes that match that criteria.");
    }

    public static void main(String[] args) {
        EarthQuakeClient eqc = new EarthQuakeClient();
//        eqc.quakesOfDepth();
        eqc.filterByPhase();
    }
    
}
