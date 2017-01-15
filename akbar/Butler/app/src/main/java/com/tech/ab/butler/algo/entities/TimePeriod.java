package com.tech.ab.butler.algo.entities;

import java.sql.Time;

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
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TimePeriod {
    private Time startTimeOfTheDay;
    private Time endTimeOfTheDay;

    public boolean contains(Time currentTime) {
        return currentTime.after(startTimeOfTheDay) && currentTime.before(endTimeOfTheDay);
    }
}
