package Entities;

import lombok.Getter;
import lombok.Setter;

import java.sql.Time;

/**
 * Created by shreenath on 11/1/17.
 */
@Getter
@Setter
public class TimePeriod {
    private Time startTime;
    private Time endTime;
}
