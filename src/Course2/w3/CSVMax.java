package Course2.w3;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;

public class CSVMax {
    public CSVRecord hottestHourInFile(CSVParser csvParser)
    {
        CSVRecord largestSoFar = null;
        for(CSVRecord currentRow : csvParser)
        {
           largestSoFar = getLargestOfTwo(currentRow, largestSoFar);
        }
        return largestSoFar;
    }

    public CSVRecord hottestDayInManyDays()
    {
        CSVRecord largestSoFar = null;
        DirectoryResource dr = new DirectoryResource();

        for (File f : dr.selectedFiles())
        {

            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            CSVRecord currentHottestRow = hottestHourInFile(parser);
            largestSoFar = getLargestOfTwo(currentHottestRow, largestSoFar);
        }

        return largestSoFar;
    }

    public CSVRecord getLargestOfTwo (CSVRecord currentRow, CSVRecord largestSoFar)
    {
        if (largestSoFar == null)
        {
            largestSoFar = currentRow;
        }

        else
        {
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            double largestTemp = Double.parseDouble(largestSoFar.get("TemperatureF"));

            if (currentTemp > largestTemp)
            {
                largestSoFar = currentRow;
            }
        }
        return largestSoFar;
    }

    public static void main(String[] args) {

        CSVMax maxTempFinder = new CSVMax();
        CSVRecord record = maxTempFinder.hottestDayInManyDays();
        System.out.println(record.toString());

    }
}
