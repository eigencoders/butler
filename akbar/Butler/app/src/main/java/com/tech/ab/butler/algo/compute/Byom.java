package com.tech.ab.butler.algo.compute;

import com.tech.ab.butler.algo.computeconstants.ComputeConstants;
import com.tech.ab.butler.algo.entities.Place;
import com.tech.ab.butler.algo.entities.Request;
import com.tech.ab.butler.algo.entities.Task;
import lombok.AllArgsConstructor;

import java.sql.Time;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by shreenath on 13/1/17.
 */
@AllArgsConstructor
public class Byom {
    private ComputeWeights weightsMap;

    public Map<Task, Double> getScoresMap(List<Task> applicableTasks, Request request) {
        Map<Task, Double> scores = new HashMap<Task, Double>();
        for (Task task: applicableTasks) {
            scores.put(task, computeScore(task, request, applicableTasks));
        }
        return scores;
    }

    private double computeScore(Task t, Request r, List<Task> applicableTasks) {
        Map<String, Double> scoreMap = new HashMap<String, Double>();
        scoreMap.put(ComputeConstants.PKEY,calculateSpatialPoints(t,r));
        scoreMap.put(ComputeConstants.TKEY,calculateTemporalPoints(t,r));
        scoreMap.put(ComputeConstants.UKEY,calculateUrgencyPoints(t,r));
        scoreMap.put(ComputeConstants.IKEY,calculateStaticScorePoints(t));
        scoreMap.put(ComputeConstants.DKEY,calculateDependencyPenalty(t,applicableTasks));
        scoreMap.put(ComputeConstants.DMKEY, calculateDeadlineMissPenalty(t,r));

        double score = 0.0;
        for (Map.Entry<String, Double> entry :scoreMap.entrySet()) score = score + entry.getValue();
        return score;
    }

    private double calculateSpatialPoints(Task t, Request r){
        if(t.getSpatialAffinity().contains(r.getPlace()))
            return weightsMap.spatialWeights[0];
        else if (t.getSpatialAffinity().equalsIgnoreCase(Place.ANY.toString()))
            return weightsMap.spatialWeights[1];
        return weightsMap.spatialWeights[2];
    }

    private double calculateTemporalPoints(Task t, Request r){
        if(t.getTemporalAffinity().contains(new Time(r.getRequestTime().getTime())))
            return weightsMap.temporalWeights.getYes();
        return weightsMap.temporalWeights.getNo();
    }

    private double calculateUrgencyPoints(Task t, Request r){
        double timeLeft = t.getDeadline().getTime() - r.getRequestTime().getTime();

        if (timeLeft <= ComputeConstants.ONE_HOUR_MS || timeLeft < t.getDuration())
            return weightsMap.urgencyWeights[0];
        else if (timeLeft <= ComputeConstants.TWO_HOUR_MS && timeLeft > ComputeConstants.ONE_HOUR_MS)
            return weightsMap.urgencyWeights[1];
        else if (timeLeft <= ComputeConstants.FOUR_HOUR_MS && timeLeft > ComputeConstants.TWO_HOUR_MS)
            return weightsMap.urgencyWeights[2];
        else if (timeLeft <= ComputeConstants.SIX_HOUR_MS && timeLeft > ComputeConstants.FOUR_HOUR_MS)
            return weightsMap.urgencyWeights[3];
        return weightsMap.urgencyWeights[4];
    }

    private double calculateStaticScorePoints(Task t){
        return weightsMap.inherentScoreWeight * t.getStaticScore();
    }

    private double calculateDependencyPenalty(Task t, List<Task> allTasks) {
        return allTasks.contains(new Task(t.getDependentTaskId()))? -weightsMap.dependencyPenalty : 0.0;
    }

    private double calculateDeadlineMissPenalty(Task t, Request r) {
        return (r.getRequestTime().after(t.getDeadline())) ? -weightsMap.deadlineMissPenalty : 0.0;
    }

}
