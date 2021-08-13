import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Tester {

    public void logEntryTest() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }

    public void logAnalyzerTest() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog3-short_log");
        la.printAll();
    }

    public void uniqueIpTest() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log.txt");
        System.out.println("\nTotal unique IPs: " + la.countUniqueIps() + "\n");
    }

    public void printAllHigherThanNumTest() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        la.printAllHigherThanNum(400);
        System.out.println();
    }

    public void uniqueIPVisitsOnDayTest() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        String date = "Mar 17"; // "MMM DD"
        System.out.println("Unique IP on Day " + date);
        ArrayList<String> onDay = la.uniqueIPVisitsOnDay(date);
        for (String s : onDay) {
            System.out.println(s);
        }
        System.out.println(onDay.size());
    }

    public void countUniqueIPsInRangeTest() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        int low = 300;
        int high = 399;
        System.out.println("\n" + la.countUniqueIPsInRange(low, high));
    }

    public void countVisitsPerIpTest() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog3-short_log");
        HashMap<String, Integer> counts = la.countVisitsPerIp();
        System.out.println(counts);
    }

    public void mostNumberVisitsByIpTest() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        System.out.println(la.mostNumberVisitsByIP(la.countVisitsPerIp()));
    }

    public void ipsMostVisitsTest() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        System.out.println(la.ipsMostVisits(la.countVisitsPerIp()));
    }

    public void ipsForDaysTest() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog3-short_log");
        HashMap<String, ArrayList<String>> daysIps = la.ipsForDays();
        for (Map.Entry<String, ArrayList<String>> entry : daysIps.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }

    public void dayWithMostIPVisitsTest() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        HashMap<String, ArrayList<String>> daysIps = la.ipsForDays();
        System.out.println(la.dayWithMostIPVisits(daysIps));
    }

    public void iPsWithMostVisitsOnDayTest() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        HashMap<String, ArrayList<String>> daysIps = la.ipsForDays();
        String day = la.dayWithMostIPVisits(daysIps);
        System.out.println(la.iPsWithMostVisitsOnDay(daysIps, "Mar 17"));
    }

    public static void main(String[] args) {
        Tester test = new Tester();
//        test.logAnalyzerTest();
//        test.logEntryTest();
//        test.uniqueIpTest();
//        test.printAllHigherThanNumTest();
//        test.uniqueIPVisitsOnDayTest();
//        test.countUniqueIPsInRangeTest();
//        test.countVisitsPerIpTest();
//        test.mostNumberVisitsByIpTest();
//        test.ipsMostVisitsTest();
//        test.ipsForDaysTest();
//        test.dayWithMostIPVisitsTest();
        test.iPsWithMostVisitsOnDayTest();
    }
}
