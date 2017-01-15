package com.tech.ab.butler.algo.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;

/**
 * Created by shreenath on 11/1/17.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TimePeriod {
    private Time startTimeOfTheDay;
    private Time endTimeOfTheDay;

    public boolean contains(Time currentTime) {
        return currentTime.after(startTimeOfTheDay) && currentTime.before(endTimeOfTheDay);
    }
}
