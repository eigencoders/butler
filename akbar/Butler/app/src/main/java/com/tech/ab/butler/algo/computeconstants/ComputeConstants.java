package com.tech.ab.butler.algo.computeconstants;

import com.tech.ab.butler.algo.entities.TimePeriod;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import static android.R.attr.id;

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

    public static int freqStringToInt(String s) {
        char sf = s.charAt(0);
        switch (sf) {
            case 'D':
                return 1;
            case 'W':
                return 7;
            case 'M':
                return 30;
            default:
                return 0;
        }
    }

    public static TimePeriod getTimeAffinityFromId(int id) {
        switch (id) {
            case 0:
                return new TimePeriod("0000","0300");
            case 1:
                return new TimePeriod("0300","0600");
            case 2:
                return new TimePeriod("0600","0900");
            case 3:
                return new TimePeriod("0900","1200");
            case 4:
                return new TimePeriod("1200","1500");
            case 5:
                return new TimePeriod("1500","1800");
            case 6:
                return new TimePeriod("1800","2100");
            case 7:
                return new TimePeriod("2100","0000");
            default:
                return new TimePeriod("0000","0300");
        }
    }

    public static int getTimeAffinityFromId(TimePeriod timePeriod) {
        switch (timePeriod.getStartTimeOfTheDay()) {
            case "0000":
                return 0;
            case "0300":
                return 1;
            case "0600":
                return 2;
            case "0900":
                return 3;
            case "1200":
                return 4;
            case "1500":
                return 5;
            case "1800":
                return 6;
            case "2100":
                return 7;
            default:
                return 0;
        }
    }
}
