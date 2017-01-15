package com.tech.ab.butler.algo.entities;

import com.tech.ab.butler.algo.computeconstants.ComputeConstants;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * Created by shreenath on 11/1/17.
 */
@Getter
@Setter
@ToString(exclude = {"deadline","temporalAffinity","frequency","duration", "status"})
@AllArgsConstructor
public class Task {
    private String name;
    private String taskId;
    private String dependentTaskId;
    private Long duration;
    private Status status;
    private Long staticScore;
    private Integer frequency;
    private Date deadline;
    private TimePeriod temporalAffinity;
    private String spatialAffinity;


    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Task)) {
            return false;
        }

        Task that = (Task) other;
        return this.taskId.equals(that.taskId);
    }

    public Task(String taskId) {
        //#HACK Only to be used for comparing tasks having just an Id.
        this.taskId = taskId;
//        return this(null,taskId,null,null,null,null,null,null,null, null);
    }


    public static class TaskMapper implements ResultSetMapper<Task> {

        @Override
        public Task map(int i, ResultSet r, StatementContext sc) throws SQLException {
            TimePeriod timePeriod = null;
            timePeriod = new TimePeriod(r.getString("startTimeOfTheDay"), r.getString("endTimeOfTheDay"));
            return new Task(r.getString("name"),
                    r.getString("taskId"),
                    r.getString("dependentTaskId"),
                    r.getLong("durationMins")* ComputeConstants.MINS_2_MS,
                    Status.valueOf(r.getString("status")),
                    r.getLong("staticScore"),
                    r.getInt("frequency"),
                    r.getTimestamp("deadline"),
                    timePeriod,
                    r.getString("places")
                    );
        }
    }
}
