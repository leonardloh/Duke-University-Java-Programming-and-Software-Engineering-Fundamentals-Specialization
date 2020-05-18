package Course2.w4;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;
import edu.duke.StorageResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class BabyNames {
    public void totalBirths(FileResource fr)
    {
        int totalBoy = 0;
        int totalGirl = 0;
        int totalNames = 0;
        for (CSVRecord record : fr.getCSVParser(false))
        {
            String gender = record.get(1);
            int number = Integer.parseInt(record.get(2));

            if (gender.equals("F"))
            {
                totalGirl += 1;
            }

            if (gender.equals("M"))
            {
                totalBoy += 1;
            }

            totalNames += number;
        }

        System.out.println("Total Boys: " + totalBoy + " Total Girl: " + totalGirl + " Total Names: " + totalNames);
    }

    /**
     * return the rank of the name in the file given gender
     * @param year
     * @param name
     * @param gender
     * @return
     */
    public int getRank(int year, String name, String gender) {
        String yearString = String.valueOf(year);
        String fileName = "yob"+ yearString +".csv";
        FileResource fr = new FileResource("C:\\Users\\LohJZ\\Downloads\\us_babynames\\us_babynames_by_year\\" + fileName);

//        tester
//        String yearString = String.valueOf(year);
//        String fileName = "yob"+ yearString +"short.csv";
//        FileResource fr = new FileResource("C:\\Users\\LohJZ\\Downloads\\us_babynames\\us_babynames_small\\testing\\" + fileName);

        int rank = -1;
        int count = 0;
        for (CSVRecord record : fr.getCSVParser(false)) {
            String currentName = record.get(0);
            String currentGender = record.get(1);
            int currentBirths = Integer.parseInt(record.get(2));

            if (currentGender.equals(gender)) {
                count++;
                if (currentName.equals(name)) {
                    rank = count;
                }
            }
        }
        return rank;
    }

    public String getName(int year, int rank, String gender)
    {
        String yearString = String.valueOf(year);
        String fileName = "yob"+ yearString +".csv";
        FileResource fr = new FileResource("C:\\Users\\LohJZ\\Downloads\\us_babynames\\us_babynames_by_year\\" + fileName);

        //tester
//        String yearString = String.valueOf(year);
//        String fileName = "yob"+ yearString +"short.csv";
//        FileResource fr = new FileResource("C:\\Users\\LohJZ\\Downloads\\us_babynames\\us_babynames_small\\testing\\" + fileName);

        int counter = 0;
        String name = null;
        for (CSVRecord record : fr.getCSVParser(false))
        {
            String currentName = record.get(0);
            String currentGender = record.get(1);
            int currentBirths = Integer.parseInt(record.get(2));

            if (currentGender.equals(gender))
            {
                counter ++;
                if (counter == rank)
                {
                    return currentName;
                }
            }
        }
        return "NO NAME";
    }


    public void whatIsNameInYear (String name, int year, int newYear, String gender)
    {
        int rank;
        String newName = null;
        if (gender.equalsIgnoreCase("M"))
        {
            rank = getRank(year, name,gender);
            newName = getName(newYear,rank,gender);
            System.out.println(name + " born in " + year + " would be " + newName + " if he was born in " + newYear);
        }
        else
        {
            if (gender.equalsIgnoreCase("F"))
            {
                rank = getRank(year, name,gender);
                newName = getName(newYear,rank,gender);
                System.out.println(name + " born in " + year + " would be " + newName + " if she was born in " + newYear);
            }
        }
    }

    public int yearOfHighestRank(String name, String gender)
    {
        DirectoryResource dr = new DirectoryResource();
        int highestRankInYears = 0;
        int highestRankYear = -1;
        for( File f : dr.selectedFiles())
        {
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser(false);

            int localRank = 0;
            for (CSVRecord record : parser)
            {
                String currentName = record.get(0);
                String currentGender = record.get(1);
                int count = 0;
                if (currentGender.equals(gender)) {
                    count++;
                    if (currentName.equals(name)) {
                        localRank = count;
                    }
                }

            }

            if (localRank > highestRankInYears)
            {
                highestRankInYears = localRank;
                highestRankYear = Integer.parseInt(f.getName().substring(f.getName().indexOf("yob")+3, f.getName().indexOf(".csv")));
                System.out.println("highest rank year: " + highestRankYear);
                //tester
//                highestRankYear = Integer.parseInt(f.getName().substring(f.getName().indexOf("yob")+3, f.getName().indexOf("short.csv")));
            };
        }
        return highestRankYear;
    }

    public double getAverageRank (String name, String gender)
    {
        DirectoryResource dr = new DirectoryResource();
        double sumRank = 0.0;
        double totNumRankAppears=0;
        double averageRank = -1.0;
        for( File f : dr.selectedFiles())
        {
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser(false);
            int rank = -1;
            int count = 0;

            for (CSVRecord record : parser)
            {
                String currentName = record.get(0);
                String currentGender = record.get(1);
                int currentBirths = Integer.parseInt(record.get(2));

                if (currentGender.equals(gender)) {
                    count++;
                    if (currentName.equals(name)) {
                        rank = count;
                        sumRank += rank;
                        totNumRankAppears++;
                    }
                }
            }
        }

        if (sumRank == 0.0)
        {
            return -1.0;
        }
        return sumRank / totNumRankAppears;
    }

    public int getTotalBirthsRankedHigher(int year, String name, String gender)
    {
        String yearString = String.valueOf(year);
        String fileName = "yob"+ yearString +".csv";
        FileResource fr = new FileResource("C:\\Users\\LohJZ\\Downloads\\us_babynames\\us_babynames_by_year\\" + fileName);

//        //tester
//        String yearString = String.valueOf(year);
//        String fileName = "yob"+ yearString +"short.csv";
//        FileResource fr = new FileResource("C:\\Users\\LohJZ\\Downloads\\us_babynames\\us_babynames_small\\testing\\" + fileName);

        CSVParser parser = fr.getCSVParser(false);
        int rank = 0;
        int count = 0;
        int sumBirths = 0;
        int totalBirthToReturn = 0;

        for (CSVRecord record : parser)
        {
            String currentName = record.get(0);
            String currentGender = record.get(1);
            int currentBirths = Integer.parseInt(record.get(2));

            if (currentGender.equals(gender)) {
                count++;
                sumBirths += currentBirths;
//                System.out.println("Current Name: " + currentName);
//                System.out.println("SumBirths: " + sumBirths);
                if (currentName.equals(name)) {
                    rank = count;
                    totalBirthToReturn = sumBirths - currentBirths;
                }
            }
        }

        return totalBirthToReturn;
    }


    public void testTotalBirth()
    {
        FileResource resource = new FileResource();
        totalBirths(resource);
    }

    public static void main(String[] args) {
        BabyNames bn = new BabyNames();

        bn.testTotalBirth();

//        int rank = bn.getRank(1971, "Frank", "M");
//        System.out.println(rank);

//        System.out.println(bn.getName(1982, 450, "M"));

//        bn.whatIsNameInYear("Owen", 1974, 2014,"M");

//        int year = bn.yearOfHighestRank("Genevieve", "F");
//        System.out.println(year);

//        System.out.println(bn.getAverageRank("Emily", "F"));

//        System.out.println(bn.getTotalBirthsRankedHigher(1990, "Emily", "F"));
    }
}
