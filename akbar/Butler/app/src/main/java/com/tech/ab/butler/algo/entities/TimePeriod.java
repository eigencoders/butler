package com.tech.ab.butler.algo.entities;

import com.tech.ab.butler.algo.computeconstants.ComputeConstants;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by shreenath on 11/1/17.
 */
@Getter
@Setter
@AllArgsConstructor(suppressConstructorProperties = true)
@NoArgsConstructor
@Data
public class TimePeriod implements Serializable{
    private String startTimeOfTheDay;
    private String endTimeOfTheDay;

    public boolean contains(Timestamp currentTime) throws ParseException {
        Date start = ComputeConstants.timeFormat.parse(startTimeOfTheDay);
        Date end = ComputeConstants.timeFormat.parse(endTimeOfTheDay);
        long st = start.getHours()*10000 + start.getMinutes()*100 + start.getSeconds();
        long et = end.getHours()*10000 + end.getMinutes()*100 + end.getSeconds();
        long ct = currentTime.getHours()*10000 + currentTime.getMinutes()*100 + currentTime.getSeconds();
        return st<=ct && ct<et;
    }
    public String getFancyString(){
        return startTimeOfTheDay+" - "+endTimeOfTheDay;
    }
}
