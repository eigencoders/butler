package com.tech.ab.butler.algo.entities;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by shreenath on 11/1/17.
 */
@AllArgsConstructor(suppressConstructorProperties = true)
@NoArgsConstructor
@Getter
@Setter
@ToString//(exclude = {"deadline","temporalAffinity","frequency","duration", "status"})
public class Task implements Serializable {
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
    private boolean isRoutine;


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
    }

}
