package com.tech.ab.butler.algo.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

/**
 * Created by shreenath on 11/1/17.
 */
@AllArgsConstructor(suppressConstructorProperties = true)
@Getter
@Setter
@NoArgsConstructor
public class Task {
    private String name;
    private String taskId;
    private String dependentTaskId;
    private Long duration;
    private Status status;
    private Long staticScore;
    private Integer frequency;
    private Timestamp deadline;
    private TimePeriod temporalAffinity;
    private String spatialAffinity;

    public String toString() {
        return name + "-" +
                taskId + "-" +
                dependentTaskId + "-" +
                duration + "-" +
                status + "-" +
                staticScore + "-" +
                frequency + "-" +
                deadline + "-" +
                spatialAffinity;
    }

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

}
