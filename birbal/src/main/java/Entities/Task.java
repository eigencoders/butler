package Entities;

import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.util.Stack;

/**
 * Created by shreenath on 11/1/17.
 */
@Getter
@Setter
public class Task {
    private String name;
    private String taskId;
    private String dependentTaskId;
    private Long durationMs;
    private Status status;
    private Long staticScore;
    private Integer frequency;
    private Time deadline;
    private TimePeriod temporalAffinity;
    private Place spatialAffinity;



    /*
    String Name
    String taskId
    Long Duration(mins)
    Enum Status (in progress/expired/??)
    Long Static Score
    Integer Frequency (no. of days)  (One time, or regularly)
    Timestamp taskDeadline
    TimePeriod Temporal Affinity (when tbd)
    Place Spatial Affinity (where tbd) ( fixed number of places)
    String dependentTaskId
     */
}
