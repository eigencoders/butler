package com.tech.ab.butler.algo.computeconstants;

import com.tech.ab.butler.algo.entities.TimePeriod;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by shreenath on 13/1/17.
 */
public class ComputeConstants {
    public static final DateFormat format2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    public static final DateFormat format = new SimpleDateFormat("EEE MMM d HH:mm:ss Z yyyy");
    public static final DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

    public static final String PKEY = "place";
    public static final String TKEY = "time";
    public static final String UKEY = "urgency";
    public static final String IKEY = "inherent";
    public static final String DKEY = "dependency";
    public static final String DMKEY = "deadlineMiss";

    public static final long ONE_HOUR_MS = 3600000;
    public static final long TWO_HOUR_MS = 2*ONE_HOUR_MS;
    public static final long FOUR_HOUR_MS = 4*ONE_HOUR_MS;
    public static final long SIX_HOUR_MS = 6*ONE_HOUR_MS;
    public static final long MINS_2_MS = 60000;

    public static final HashMap<String, Integer> freqStringToIntMap = new HashMap<String, Integer>(){{
        put("Daily",1);
        put("Weekly",2);
        put("Monthly",30);
    }};

    public static int freqStringToInt(String s) {
        if(freqStringToIntMap.containsKey(s))
            return freqStringToIntMap.get(s);
        else
            return 0;
    }

    public static String freqIntToString(int i) {
        if(freqStringToIntMap.containsValue(i)) {
            for (Map.Entry<String, Integer> e : freqStringToIntMap.entrySet())
                if (e.getValue() == i)
                    return e.getKey();
        }
        else
            return "None";
        return "None";
    }

    public static TimePeriod getTimeAffinityFromId(int id) {
        switch (id) {
            case 1:
                return new TimePeriod("03:00:00","06:00:00");
            case 2:
                return new TimePeriod("06:00:00","09:00:00");
            case 3:
                return new TimePeriod("09:00:00","12:00:00");
            case 4:
                return new TimePeriod("12:00:00","15:00:00");
            case 5:
                return new TimePeriod("15:00:00","18:00:00");
            case 6:
                return new TimePeriod("18:00:00","21:00:00");
            case 7:
                return new TimePeriod("21:00:00","00:00:00");
            default:
                return new TimePeriod("00:00:00","03:00:00");
        }
    }
}
