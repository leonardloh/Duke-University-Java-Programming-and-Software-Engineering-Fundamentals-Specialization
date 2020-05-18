package Course3.w3;
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.io.File;
import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         // complete constructor
         records = new ArrayList<>();
     }
        
     public void readFile(String filename) {
         // complete method
         FileResource fr = new FileResource(filename);
         for (String line : fr.lines())
         {
             Date accessTime = WebLogParser.parseEntry(line).getAccessTime();
             int bytesReturned = WebLogParser.parseEntry(line).getBytesReturned();
             String ipAddress = WebLogParser.parseEntry(line).getIpAddress();
             String request = WebLogParser.parseEntry(line).getRequest();
             int statusCode = WebLogParser.parseEntry(line).getStatusCode();
             records.add(new LogEntry(ipAddress, accessTime, request, statusCode, bytesReturned));

         }
     }

     public int countUniqueIPs()
     {
         ArrayList uniqueIPList = new ArrayList();
         for (int i=0; i < records.size(); i++)
         {
             String ipAddress = records.get(i).getIpAddress();
             if(!uniqueIPList.contains(ipAddress))
             {
                 uniqueIPList.add(ipAddress);
             }
         }
         return uniqueIPList.size();
     }

     public HashMap<String,Integer> countVisitsPerIP()
     {
         HashMap<String,Integer> countVisitsPerIP = new HashMap<String,Integer>();
         for ( LogEntry le : records)
         {
             String ipAddress = le.getIpAddress();
             if(!countVisitsPerIP.containsKey(ipAddress))
             {
                 countVisitsPerIP.put(ipAddress, 1);
             }
             else
             {
                 countVisitsPerIP.put(ipAddress, countVisitsPerIP.get(ipAddress)+1);
             }
         }

         return countVisitsPerIP;
     }

     public void printAllHigherThanNum (int num)
     {
         System.out.println("Status code greater than " + num + " are: ");
         for (int i=0; i < records.size(); i++)
         {
             int statusCode = records.get(i).getStatusCode();
             if (statusCode > num)
             {
                 System.out.println("" + statusCode);
             }
         }
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }

     public  ArrayList uniqueIPVisitsOnDay(String oneDay)
     {
         ArrayList uniqueIPList = new ArrayList();
         for (LogEntry le : records) {
             String ipAddress = le.getIpAddress();
             if (le.getAccessTime().toString().indexOf(oneDay) != -1 &&
                     !uniqueIPList.contains(ipAddress))
             {
                     uniqueIPList.add(ipAddress);
             }
         }
         return uniqueIPList;
     }

    /**
     * This method returns the number of unique IP addresses in records that have a status code in the range from low to high, inclusive.
     * @param low
     * @param high
     * @return
     */
     public int countUniqueIPInRange(int low, int high)
     {
         ArrayList uniqueIPList = new ArrayList();
         for (LogEntry le : records)
         {
             int statusCode = le.getStatusCode();
             if (statusCode >= low && statusCode <= high && !uniqueIPList.contains(le.getIpAddress()))
             {
                 uniqueIPList.add(le.getIpAddress());
             }
         }
//         System.out.println(uniqueIPList);
         return uniqueIPList.size();
     }

     public int mostNumberVisitsByIP(HashMap<String,Integer> uniqueIPMap)
     {
         int mostNumberVisitsByIP = 0;
         for (String ip : uniqueIPMap.keySet())
         {
             if(uniqueIPMap.get(ip) > mostNumberVisitsByIP)
             {
                 mostNumberVisitsByIP = uniqueIPMap.get(ip);
             }
         }
         return mostNumberVisitsByIP;
     }

     public ArrayList<String> iPsMostVisits(HashMap<String,Integer> uniqueIPMap)
     {
         ArrayList iPsMostVisitsList = new ArrayList<String> ();
         int mostNumberVisitsByIP = mostNumberVisitsByIP(uniqueIPMap);
         for (String ip : uniqueIPMap.keySet())
         {
             if(uniqueIPMap.get(ip) == mostNumberVisitsByIP)
             {
                 iPsMostVisitsList.add(ip);
             }
         }

         return iPsMostVisitsList;
     }

     public HashMap<String,ArrayList<String>> iPsForDays()
     {
         HashMap<String,ArrayList<String>> dayIPAddressMap = new HashMap<String,ArrayList<String>>();
         for (LogEntry le : records)
         {
             String date = le.getAccessTime().toString().substring(4,10);
             String ipAddress = le.getIpAddress();
             if (!dayIPAddressMap.containsKey(date))
             {
                 ArrayList<String> IPlists = new ArrayList<String>();
                 IPlists.add(ipAddress);
                 dayIPAddressMap.put(date, IPlists);
             }
             else
             {
                dayIPAddressMap.get(date).add(ipAddress);
             }
         }
         return dayIPAddressMap;
     }

     public String dayWithMostIPVisits(HashMap<String,ArrayList<String>> dayIPAddressMap)
     {
         int numMostIPVisits = 0;
         String day = null;
         for (String ipAddress : dayIPAddressMap.keySet())
         {
             int numberOfIPs = dayIPAddressMap.get(ipAddress).size();
             if(numberOfIPs > numMostIPVisits)
             {
                 numMostIPVisits = numberOfIPs;
                 day = ipAddress;
             }
         }
         return day;
     }

     public ArrayList<String> iPsWithMostVisitsOnDay (HashMap<String,ArrayList<String>> dayIPAddressMap, String day)
     {
         ArrayList<String> IPlists = dayIPAddressMap.get(day);

         HashMap<String,Integer> countVisitsPerIP = new HashMap<String,Integer>();

         for ( String ipAddress :IPlists)
         {
             if(!countVisitsPerIP.containsKey(ipAddress))
             {
                 countVisitsPerIP.put(ipAddress, 1);
             }
             else
             {
                 countVisitsPerIP.put(ipAddress, countVisitsPerIP.get(ipAddress)+1);
             }
         }

         ArrayList<String> iPsMostVisits = iPsMostVisits(countVisitsPerIP);

         return iPsMostVisits;
     }
     
}
