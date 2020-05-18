package Course4.w2.demos;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class AirportSearchMethod {


    //to find is a city name
    public static String findAirportCode(String toFind, Airport[] airports)
    {
        int i = 0;
        while (i < airports.length)
        {
            Airport currentAirport = airports[i];
            String cityName = currentAirport.getCity();
            if (cityName.equals(toFind))
            {
                return currentAirport.getCode3();
            }
            i++;
        }
        return null;
    }


    public static String binarySearch(String city ,Airport[] airport)
    {
        int low = 0;
        int high = airport.length;

        while(low < high)
        {
//            int mid = (low + high) / 2;
            int mid = low + ((high - low) / 2);
            int compare = city.compareTo(airport[mid].getCity());
            if (compare < 0 )
            {
                high = mid - 1;
            }
            else if (compare > 0 )
            {
                low = mid + 1;
            }
            else
            {
                return airport[mid].getCode3();
            }
        }
        return null;
    }

}
