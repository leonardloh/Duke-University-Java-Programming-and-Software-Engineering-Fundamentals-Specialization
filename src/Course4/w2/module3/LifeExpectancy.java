package Course4.w2.module3;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.GeoJSONReader;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.utils.MapUtils;
import processing.core.PApplet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LifeExpectancy  extends PApplet {
    private UnfoldingMap map;
    Map<String, Float> lifeExpByCountry;
    List<Feature> countries;
    List<Marker> countryMarkers;

    public void setup() {
        size(800,600, OPENGL);
        map = new UnfoldingMap(this, 50, 50, 700, 500, new Google.GoogleMapProvider());
        MapUtils.createDefaultEventDispatcher(this, map);
        lifeExpByCountry = loadLifeExpByCSV("data\\LifeExpectancyWorldBank.csv");
        countries = GeoJSONReader.loadDataFromJSON(this, "data/countries.geo.json");
        countryMarkers = MapUtils.createSimpleMarkers(countries);
        map.addMarkers(countryMarkers);
        shadeCountries();
    }

    private void shadeCountries() {

        for (Marker marker : countryMarkers)
        {
            String countryID = marker.getId();
            if (lifeExpByCountry.containsKey(countryID))
            {
                float lifeExp = lifeExpByCountry.get(countryID);
                int colourLevel = (int) map(lifeExp, 40, 90, 10, 255);
                marker.setColor(color(255-colourLevel, 100, colourLevel));
            }
            else
            {
                marker.setColor(color(150,150,150));
            }
        }

    }

    private Map<String, Float> loadLifeExpByCSV(String s) {
        Map<String, Float> lifeExpByCountry = new HashMap<String, Float>();
        String[] rows = loadStrings(s);
        for (String row : rows) {
            String[] cols = row.split(",");
            if (containsDigit(cols[5]))
            {
                float value = Float.parseFloat(cols[5]);
                lifeExpByCountry.put(cols[4], value);
            }
        }
        return lifeExpByCountry;
    }

    private final boolean containsDigit(String s) {
        boolean containsDigit = false;
        if (s != null && !s.isEmpty()) {
            for (char c : s.toCharArray()) {
                if (containsDigit = Character.isDigit(c)) {
                    break;
                }
            }
        }
        return containsDigit;
    }

    public void draw(){
        map.draw();
    }

    public static void main(String[] args) {
        PApplet.main("Course4.w2.module3.LifeExpectancy");
    }
}
