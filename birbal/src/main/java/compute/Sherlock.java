package compute;

import constants.ComputeConstants;
import entities.Request;
import entities.Task;

import java.sql.Time;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by shreenath on 13/1/17.
 */
public class Sherlock {
    private static Map<String, Double> weightsMap = new HashMap<String, Double>(){{
        put(ComputeConstants.PKEY,1.0);
        put(ComputeConstants.TKEY,1.0);
        put(ComputeConstants.UKEY,1.0);
        put(ComputeConstants.IKEY,1.0);
    }};

    public static Map<Task, Double> getScoresMap(List<Task> applicableTasks, Request request) {
        Map<Task, Double> scores = new HashMap<Task, Double>();
        for (Task task: applicableTasks) {
            scores.put(task, computeScore(task, request));
        }
        return scores;
    }

    private static double computeScore(Task t, Request r) {
        Map<String, Double> scoreMap = new HashMap<String, Double>();
        scoreMap.put(ComputeConstants.PKEY,weightsMap.get(ComputeConstants.PKEY)*calculateSpatialPoints(t,r));
        scoreMap.put(ComputeConstants.TKEY,weightsMap.get(ComputeConstants.TKEY)*calculateTemporalPoints(t,r));
        scoreMap.put(ComputeConstants.UKEY,weightsMap.get(ComputeConstants.UKEY)*calculateUrgencyPoints(t,r));
        scoreMap.put(ComputeConstants.IKEY,weightsMap.get(ComputeConstants.IKEY)*t.getStaticScore());

        double score = 0.0;

        for (Map.Entry<String, Double> entry :scoreMap.entrySet()) {
            score = score + entry.getValue();
        }
        return score;
    }

    private static double calculateSpatialPoints(Task t, Request r){
        if(t.getSpatialAffinity().contains(r.getPlace()))
            return 10.0;
        return -10.0;
    }

    private static double calculateTemporalPoints(Task t, Request r){
        if(t.getTemporalAffinity().contains(new Time(r.getRequestTime().getTime())))
            return 10.0;
        return -10.0;
    }

    private static double calculateUrgencyPoints(Task t, Request r){
        double timeLeft = t.getDeadline().getTime() - r.getRequestTime().getTime();

        if (timeLeft <= ComputeConstants.ONE_HOUR_MS || timeLeft < t.getDuration())
            return 100.0;
        else if (timeLeft <= ComputeConstants.TWO_HOUR_MS && timeLeft > ComputeConstants.ONE_HOUR_MS)
            return 80.0;
        else if (timeLeft <= ComputeConstants.FOUR_HOUR_MS && timeLeft > ComputeConstants.TWO_HOUR_MS)
            return 60.0;
        else if (timeLeft <= ComputeConstants.SIX_HOUR_MS && timeLeft > ComputeConstants.FOUR_HOUR_MS)
            return 40.0;
        return 10.0;
    }

}
