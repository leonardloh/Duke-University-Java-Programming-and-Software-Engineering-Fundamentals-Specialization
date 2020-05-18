package Course2.w3;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;

public class CSVFindColdest {

    public CSVRecord coldestHourInFile(CSVParser parser){
        CSVRecord lowestTempRow = null;
        for (CSVRecord currentRow : parser)
        {
            if (lowestTempRow == null)
            {
                lowestTempRow = currentRow;
            }
            else
            {
               double currentRowTemp = Double.parseDouble(currentRow.get("TemperatureF"));
               double lowestRowTemp = Double.parseDouble(lowestTempRow.get("TemperatureF"));

               if(currentRowTemp < lowestRowTemp)
               {
                   lowestTempRow = currentRow;
               }
            }
        }
        return lowestTempRow;
    }

    public String fileWithColdestTemperature()
    {
        String coldestFileName = null;
        DirectoryResource dr = new DirectoryResource();
        CSVRecord coldestRow = null;
        for (File f : dr.selectedFiles())
        {
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            CSVRecord currentColdest = coldestHourInFile(parser);
            if (coldestRow == null)
            {
                coldestRow = currentColdest;
            }
            else
            {
                double currentColdestTemp = Double.parseDouble(currentColdest.get("TemperatureF"));
                double coldestRowTemp = Double.parseDouble(coldestRow.get("TemperatureF"));
                if (currentColdestTemp < coldestRowTemp)
                {
                    coldestFileName = f.getAbsolutePath();
                }
            }
        }

        System.out.println("All temperatures on the coldest day were: ");
        FileResource coldestFile = new FileResource(coldestFileName);
        CSVParser coldestFileParser = coldestFile.getCSVParser();

        for (CSVRecord coldestFileRow : coldestFileParser)
        {
            System.out.println(coldestFileRow.get("DateUTC") + ": " +  coldestFileRow.get("TemperatureF"));
        }
        return coldestFileName;
    }


    public CSVRecord lowestHumidityInFile( CSVParser parser)
    {
        CSVRecord lowestHumidityRow = null;
        for (CSVRecord currentRow : parser)
        {
            if (lowestHumidityRow == null)
            {
                lowestHumidityRow = currentRow;
            }
            else
            {
                if(!currentRow.get("Humidity").equalsIgnoreCase("N/A") && !lowestHumidityRow.get("Humidity").equalsIgnoreCase("N/A"))
                {
                    double currentRowHumidity = Double.parseDouble(currentRow.get("Humidity"));
                    double lowestRowHumidity = Double.parseDouble(lowestHumidityRow.get("Humidity"));
                    if(currentRowHumidity < lowestRowHumidity)
                    {
                        lowestHumidityRow = currentRow;
                    }
                }
            }
        }
        return lowestHumidityRow;
    }

    public CSVRecord lowestHumidityInManyFiles ()
    {
        DirectoryResource dr = new DirectoryResource();
        CSVRecord lowestHumidRow = null;
        for (File f : dr.selectedFiles())
        {
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            CSVRecord currentLowestHumidFile = lowestHumidityInFile(parser);
            if (lowestHumidRow == null)
            {
                lowestHumidRow = currentLowestHumidFile;
            }
            else
            {
                if(!currentLowestHumidFile.get("Humidity").equalsIgnoreCase("N/A") && !lowestHumidRow.get("Humidity").equalsIgnoreCase("N/A"))
                {
                    double currentLowestHumid = Double.parseDouble(currentLowestHumidFile.get("Humidity"));
                    double lowestRowHumid = Double.parseDouble(lowestHumidRow.get("Humidity"));
                    if (currentLowestHumid < lowestRowHumid) {
                        lowestHumidRow = currentLowestHumidFile;
                    }
                }
            }
        }

        return lowestHumidRow;
    }

    public double averageTemperatureInFile(CSVParser parser)
    {
        double sum = 0;
        for (CSVRecord record : parser)
        {
            double currentTemperature = Double.parseDouble(record.get("TemperatureF"));
            sum += currentTemperature;
        }
        return sum/(double) parser.getRecordNumber();
    }

    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value)
    {
        double sum = 0.0;
        double average = 0.0;
        int counter = 0;
        for(CSVRecord currentRow : parser) {
            String humidityStr = currentRow.get("Humidity");
            if(!humidityStr.equalsIgnoreCase("N/A")) {
                double humidity = Double.parseDouble(humidityStr);

                if(humidity >= value) {
                    double temp = Double.parseDouble(currentRow.get("TemperatureF"));
//                    System.out.println(humidity + " " + temp);
                    sum += temp;
                    counter ++;
                    average = sum / counter;
                }
            }
        }
        return average;
    }

    public void testColdestHourInFile()
    {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord coldestHourRow = coldestHourInFile(parser);
        System.out.println("Coldest temperature is " + coldestHourRow.get("TemperatureF") );
    }

    public void testFileWithColdestTemperature()
    {
        String coldestFileName = fileWithColdestTemperature();
        System.out.println("Coldest day was found in the file " + coldestFileName.substring(coldestFileName.indexOf("weather-"), coldestFileName.indexOf("csv")+3));
        FileResource fr = new FileResource(coldestFileName);
        CSVParser parser = fr.getCSVParser();
        CSVRecord coldestHourRow = coldestHourInFile(parser);
        System.out.println("Coldest temperature is " + coldestHourRow.get("TemperatureF") + " at time " + coldestHourRow.get("TimeEST"));
    }

    public void testLowestHumidityInFile()
    {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        System.out.println("Lowest Humidity was " + csv.get("Humidity") + " at " + csv.get("DateUTC"));
    }

    public void testLowestHumidityInManyFiles()
    {
        CSVRecord record = lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was " + record.get("Humidity") + " at " + record.get("DateUTC"));
    }

    public void testAverageTemperatureInFile()
    {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        Double average = averageTemperatureInFile(parser);
        System.out.println("Average temperature in file is " + average);
    }

    public void testAverageTemperatureWithHighHumidityInFile()
    {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        Double average = averageTemperatureWithHighHumidityInFile(parser, 80);
        if (average == -1.0)
        {
            System.out.println("No temperatures with that humidity");
        }
        else
        {
            System.out.println("Average temperature when high Humidity is " + average);
        }
    }

    public static void main(String[] args) {
        CSVFindColdest coldest = new CSVFindColdest();
//        coldest.testColdestHourInFile();
        coldest.testFileWithColdestTemperature();
//        coldest.testLowestHumidityInFile();
//        coldest.testLowestHumidityInManyFiles();
//        coldest.testAverageTemperatureInFile();
//        coldest.testAverageTemperatureWithHighHumidityInFile();
    }
}
