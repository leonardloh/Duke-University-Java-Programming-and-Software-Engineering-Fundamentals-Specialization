package Course3.w3;
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        // complete method
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile("web log\\short-test_log");
        logAnalyzer.printAll();
    }

    public void testUniqueIP()
    {
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile("web log\\short-test_log");
        int numUniqueIP = logAnalyzer.countUniqueIPs();
        System.out.println("num of unique ips = " + numUniqueIP);
    }

    public void testPrintAllHigherThanNum(int num)
    {
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile("web log\\weblog1_log");
        logAnalyzer.printAllHigherThanNum(num);
    }

    public void testUniqueIPVisitsOnDay()
    {
        String date = "Sep 27";
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile("web log\\weblog2_log");
        ArrayList uniqueIPList = logAnalyzer.uniqueIPVisitsOnDay(date);
        System.out.println("Unique IP on day " + date + " are " + uniqueIPList);
        System.out.println("Size of the unique IP list is " + uniqueIPList.size());
    }

    public void testCountUniqueIPsInRange()
    {
        int low = 200;
        int high = 299;
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile("web log\\weblog2_log");
        int uniqueIPCount = logAnalyzer.countUniqueIPInRange(low, high);
        System.out.println("Unique IP of status code ranges from " + low + " to " + high + " is " + uniqueIPCount);
    }

    public void testcountVisitsPerIP()
    {
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile("web log\\weblog1_log");
        HashMap<String,Integer> countIPMap = logAnalyzer.countVisitsPerIP();
        System.out.println(countIPMap);
        System.out.println(logAnalyzer.iPsMostVisits(countIPMap));
    }

    public void mostNumberVisitsByIP()
    {
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile("web log\\weblog2_log");
        HashMap<String,Integer> countIPMap = logAnalyzer.countVisitsPerIP();
        System.out.println("Most number of visits by IP is " + logAnalyzer.mostNumberVisitsByIP(countIPMap));
    }

    public void testIPsMostVisits()
    {
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile("web log\\weblog2_log");
        HashMap<String,Integer> countIPMap = logAnalyzer.countVisitsPerIP();
        System.out.println("List of IPs with most visits are: " + logAnalyzer.iPsMostVisits(countIPMap));
    }

    public void testIPsForDays()
    {
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile("web log\\weblog3-short_log");
        HashMap<String,ArrayList<String>> dayIPListMap = logAnalyzer.iPsForDays();
        System.out.println("IPs address list for each day is: ");
        System.out.println(dayIPListMap);
    }

    public void testDayWithMostIPVisits()
    {
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile("web log\\weblog2_log");
        HashMap<String,ArrayList<String>> dayIPListMap = logAnalyzer.iPsForDays();
        System.out.println("The day with most ip visits is " + logAnalyzer.dayWithMostIPVisits(dayIPListMap));
    }

    public void testiPsWithMostVisitsOnDay()
    {
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile("web log\\weblog2_log");
        HashMap<String,ArrayList<String>> dayIPListMap = logAnalyzer.iPsForDays();
        ArrayList<String> iplist = logAnalyzer.iPsWithMostVisitsOnDay(dayIPListMap, "Sep 30");
        System.out.println("IPs with most visits on day is " + iplist);
    }

    public void testCountUniqueIP()
    {
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile("web log\\weblog2_log");
        System.out.println("Count unique IP: " + logAnalyzer.countUniqueIPs());
    }

    public static void main(String[] args) {
        Tester tester = new Tester();
//        tester.mostNumberVisitsByIP();
//        tester.testLogAnalyzer();
//        tester.testUniqueIP();
//        tester.testPrintAllHigherThanNum(400);
//        tester.testUniqueIPVisitsOnDay();
//        tester.testCountUniqueIPsInRange();
//        tester.testcountVisitsPerIP();
//        tester.testIPsMostVisits();
//        tester.testIPsForDays();
//        tester.testDayWithMostIPVisits();
        tester.testiPsWithMostVisitsOnDay();
//        tester.testCountUniqueIP();
    }
}
