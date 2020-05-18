package Course2.w3;
import edu.duke.*;
import org.apache.commons.csv.*;

public class WhichCountriesExport {
    public void listExporter(CSVParser parser, String exportOfInterest)
    {
        //for each row in CSV
        for (CSVRecord record : parser)
        {
            //look at the export column
            String export = record.get("Exports");
            if(export.contains(exportOfInterest))
            {
                String country = record.get("Country");
                System.out.println(country);
            }
        }
    }

    public String countryInfo (CSVParser parser, String country)
    {
        for(CSVRecord record : parser)
        {
            String countryColumnData = record.get("Country");
            if (countryColumnData.contains(country))
            {
                return country + ": " + record.get("Exports") + ": " + record.get("Value (dollars)");
            }
        }
        return "NOT FOUND";
    }

    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2)
    {
        for (CSVRecord record:parser)
        {
            String export = record.get("Exports");
            if (export.contains(exportItem1) && export.contains(exportItem2))
            {
                System.out.println(record.get("Country"));
            }
        }
    }

    public int numberofExporters(CSVParser parser, String exportItem )
    {
        int sum = 0;
        for (CSVRecord record : parser)
        {
            if (record.get("Exports").contains(exportItem)) sum++;
        }
        return sum;
    }

    public void bigExporters(CSVParser parser, String amount)
    {
        for (CSVRecord record: parser)
        {
            String exportAmount = record.get("Value (dollars)");
            if (exportAmount.length() > amount.length())
            {
                System.out.println(record.get("Country") + " " + exportAmount);
            }
        }
    }

    public void whoExportsCoffee(){
        FileResource fr = new FileResource();
        CSVParser csvParser = fr.getCSVParser();
        listExporter(csvParser, "coffee");
    }

    public void tester()
    {
        FileResource fr = new FileResource("C:\\Users\\LohJZ\\Downloads\\exports\\exports\\exportdata.csv");
        CSVParser parser = fr.getCSVParser();

//        String result = countryInfo(parser, "Nauru");
//        System.out.println(result);

//        listExportersTwoProducts(parser, "cotton", "flowers");

//        int result = numberofExporters(parser, "cocoa");
//        System.out.println(result);

        bigExporters(parser, "$999,999,999,999");
    }

    public static void main(String[] args) {
        WhichCountriesExport export = new WhichCountriesExport();
        export.tester();
    }
}
