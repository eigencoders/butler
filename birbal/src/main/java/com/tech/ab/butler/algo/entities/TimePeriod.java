package com.tech.ab.butler.algo.entities;

import com.tech.ab.butler.algo.computeconstants.ComputeConstants;
import lombok.*;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;

/**
 * Created by shreenath on 11/1/17.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TimePeriod {
    private String startTimeOfTheDay;
    private String endTimeOfTheDay;

    public boolean contains(Timestamp currentTime) throws ParseException {
        Date start = ComputeConstants.timeFormat.parse(startTimeOfTheDay);
        Date end = ComputeConstants.timeFormat.parse(endTimeOfTheDay);
        long st = start.getHours()*10000 + start.getMinutes()*100 + start.getSeconds();
        long et = end.getHours()*10000 + end.getMinutes()*100 + end.getSeconds();
        long ct = currentTime.getHours()*10000 + currentTime.getMinutes()*100 + currentTime.getSeconds();
        return st<=ct && ct<et;
//        return currentTime.after(startTimeOfTheDay) && currentTime.before(endTimeOfTheDay);
    }
}
