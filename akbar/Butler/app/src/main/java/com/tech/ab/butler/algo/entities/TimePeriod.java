package com.tech.ab.butler.algo.entities;

import java.sql.Time;

/**
 * Created by shreenath on 11/1/17.
 */
public class TimePeriod {
    private Time startTimeOfTheDay;
    private Time endTimeOfTheDay;

    public boolean contains(Time currentTime) {
        return currentTime.after(startTimeOfTheDay) && currentTime.before(endTimeOfTheDay);
    }
}
