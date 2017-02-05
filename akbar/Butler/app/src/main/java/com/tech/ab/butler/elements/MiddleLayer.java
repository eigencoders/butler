package com.tech.ab.butler.elements;

import com.tech.ab.butler.R;
import com.tech.ab.butler.algo.computeconstants.ComputeConstants;
import com.tech.ab.butler.algo.entities.Task;

import java.util.Date;

/**
 * Created by Ankita on 05-Feb-17.
 */

public class MiddleLayer {
    public static int getTimeAffinityR_Id(Task task) {
        switch (task.getTemporalAffinity().getStartTimeOfTheDay()) {
            case "03:00:00":
                return R.string.slot1;
            case "06:00:00":
                return R.string.slot2;
            case "09:00:00":
                return R.string.slot3;
            case "12:00:00":
                return R.string.slot4;
            case "15:00:00":
                return R.string.slot5;
            case "18:00:00":
                return R.string.slot6;
            case "21:00:00":
                return R.string.slot7;
            case "00:00:00":
                return R.string.slot0;
            default:
                return R.string.slot0;
        }
    }

    public static String convertMiliSecToFancyString(long milis) {
        long hr = milis/3600000;
        long min = (milis%3600000)/60000;
        return hr + "hr " + min + "mins";
    }

    public static String convertDeadlineToFancyString(Date date) {
        return  ComputeConstants.format2.format(date);
    }

    public static String convertSpatialAffinityToFancyString(String string) {
        if(string.endsWith(","))
            return string.substring(0,string.length()-1);
        return string;
    }
}
