package com.tech.ab.butler.algo.entities;

import lombok.*;

import java.sql.Time;
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
    private Date startTimeOfTheDay;
    private Date endTimeOfTheDay;

    public boolean contains(Time currentTime) {
        long st = startTimeOfTheDay.getHours()*10000 + startTimeOfTheDay.getMinutes()*100 + startTimeOfTheDay.getSeconds();
        long et = endTimeOfTheDay.getHours()*10000 + endTimeOfTheDay.getMinutes()*100 + endTimeOfTheDay.getSeconds();
        long ct = currentTime.getHours()*10000 + currentTime.getMinutes()*100 + currentTime.getSeconds();
        return st<=ct && ct<et;
//        return currentTime.after(startTimeOfTheDay) && currentTime.before(endTimeOfTheDay);
    }
}
