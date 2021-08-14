import edu.duke.FileResource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LogAnalyzer {
    private final ArrayList<LogEntry> records;

    public LogAnalyzer() {
        records = new ArrayList<>();
    }

    public void readFile(String filename) {
        FileResource fr = new FileResource(filename);
        for (String s : fr.lines()) {
            LogEntry le = WebLogParser.parseEntry(s);
            records.add(le);
        }
    }

    public void printAll() {
        for (LogEntry le : records) {
            System.out.println(le);
        }
    }

    public int countUniqueIps() {
        ArrayList<String> uniqueIps = new ArrayList<>();
        for (LogEntry le : records) {
            String ipAddress = le.getIpAddress();
            if (!uniqueIps.contains(ipAddress)) {
                uniqueIps.add(ipAddress);
            }
        }
        return uniqueIps.size();
    }

    public void printAllHigherThanNum(int num) {
        for (LogEntry le : records) {
            int statusCode = le.getStatusCode();
            if (statusCode > num) {
                System.out.println(le);
            }
        }
    }

    public ArrayList<String> uniqueIPVisitsOnDay(String someday) { //someday: "MMM DD"
        ArrayList<String> ipAddress = new ArrayList<>();
        for (LogEntry le : records) {
            String date = le.getAccessTime().toString();
            if (date.contains(someday)) {
                String ip = le.getIpAddress();
                if (!ipAddress.contains(ip)) {
                    ipAddress.add(ip);
                }
            }
        }
        return ipAddress;
    }

    public int countUniqueIPsInRange(int low, int high) {
        ArrayList<String> ipAddress = new ArrayList<>();
        for (LogEntry le : records) {
            int statusCode = le.getStatusCode();
            if (statusCode >= low && statusCode <= high) {
                String ip = le.getIpAddress();
                if (!ipAddress.contains(ip)) {
                    ipAddress.add(ip);
                }
            }
        }
        return ipAddress.size();
    }

    public HashMap<String, Integer> countVisitsPerIp() {
        HashMap<String, Integer> counts = new HashMap<>();
        Integer count;
        for (LogEntry le : records) {
            String ip = le.getIpAddress();
            count = counts.get(ip);
            if (count == null) {
                counts.put(ip, 1);
            } else {
                counts.put(ip, count + 1);
            }
        }
        return counts;
    }

    public int mostNumberVisitsByIP(HashMap<String, Integer> hashMap) {
        int max = 0;
        for (Integer value : hashMap.values()) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }

    public ArrayList<String> ipsMostVisits(HashMap<String, Integer> hashMap) {
        ArrayList<String> ipList = new ArrayList<>();
        int currentMax = 0;
        for (Map.Entry<String, Integer> entry : hashMap.entrySet()) {
            String hmKey = entry.getKey();
            Integer hmValue = entry.getValue();
            if (hmValue > currentMax) {
                ipList.clear();
                ipList.add(hmKey);
                currentMax = hmValue;
            } else if (hmValue == currentMax) {
                ipList.add(hmKey);
            }
        }
        return ipList;
    }

    public HashMap<String, ArrayList<String>> ipsForDays() {
        HashMap<String, ArrayList<String>> daysAndIpList = new HashMap<>();
        ArrayList<String> ipList;
        for (LogEntry le : records) {
            String day = le.getAccessTime().toString();
            day = day.substring(4, 10);                  //cut out the Date in the desire format "Mmm DD"
            String ipAddress = le.getIpAddress();
            ipList = daysAndIpList.get(day);
            if (ipList != null) {
                ipList.add(ipAddress);
            } else {
                ipList = new ArrayList<>();
                ipList.add(ipAddress);
                daysAndIpList.put(day, ipList);
            }
        }
        return daysAndIpList;
    }

    public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> daysAndIpList) {
        String day = "";
        int maxValue = 0;
        for (Map.Entry<String, ArrayList<String>> entry : daysAndIpList.entrySet()) {
            int currValue = entry.getValue().size();
            if (currValue > maxValue) {
                maxValue = currValue;
                day = entry.getKey();
            }
        }
        return day;
    }

    public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> daysAndIpList, String day) {
        ArrayList<String> ips = daysAndIpList.get(day);
        if (ips == null) {
            System.out.println("day not found");
            return null;
        }
        HashMap<String, Integer> ipsMap = new HashMap<>();
        Integer count;
        for (String ip : ips) {
            count = ipsMap.get(ip);
            if (count == null) {
                ipsMap.put(ip, 1);
            } else {
                ipsMap.put(ip, count + 1);
            }
        }
        return ipsMostVisits(ipsMap);
    }
}
