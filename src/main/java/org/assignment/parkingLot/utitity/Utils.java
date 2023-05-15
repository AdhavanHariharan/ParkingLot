package org.assignment.parkingLot.utitity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Utils {
    public static Map<String, Integer> calculateTimeDiff(String startDateTime, String endDateTime) {
        SimpleDateFormat obj = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
        Map<String, Integer> timeDiff = new HashMap<>();
        try {
            Date date1 = obj.parse(startDateTime);
            Date date2 = obj.parse(endDateTime);
            long diff = date2.getTime() - date1.getTime();
            timeDiff.put("days", Math.round(diff / 86400000));
            timeDiff.put("hours", Math.round((diff % 86400000) / 3600000));
            timeDiff.put("minutes", Math.round((diff % 86400000) % 3600000) / 60000);
        } catch (Exception e) {
            System.out.println("Exception in date parsing");
        }
        return timeDiff;
    }
}
