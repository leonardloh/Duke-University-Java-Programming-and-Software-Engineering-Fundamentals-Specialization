package Course4.w2.module6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.data.ShapeFeature;
import de.fhpotsdam.unfolding.marker.SimpleLinesMarker;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PGraphics;

/** 
 * A class to represent AirportMarkers on a world map.
 *   
 * @author Adam Setters and the UC San Diego Intermediate Software Development
 * MOOC team
 *
 */
public class AirportMarker extends CommonMarker {
	public static List<SimpleLinesMarker> routes;
	
	public AirportMarker(Feature city){
		super(((PointFeature)city).getLocation(), city.getProperties());
	}


	@Override
	public void drawMarker(PGraphics pg, float x, float y) {
		pg.fill(11);
		pg.ellipse(x, y, 5, 5);
		
		
	}


	@Override
	public void showTitle(PGraphics pg, float x, float y) {

		String country = (String) this.getProperty("country");
		String code = (String) this.getProperty("code");
		String city = (String) this.getProperty("city");

		String text = country + " " + code + " " + city;

		// show rectangle with title
		pg.pushStyle();
		pg.rectMode(PConstants.CORNER);
		pg.stroke(110);
		pg.fill(255,255,255);
		pg.rect(x, y + 15, pg.textWidth(text) +6, 18, 5);
		pg.textAlign(PConstants.LEFT, PConstants.TOP);
		pg.fill(0);
		pg.text(text, x + 3 , y +18);

		//show routes

		pg.popStyle();
	}

	public void addRoute()
	{

	}
	
}
