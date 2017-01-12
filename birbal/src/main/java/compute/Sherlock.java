package compute;

import constants.ComputeConstants;
import entities.Request;
import entities.Task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by shreenath on 13/1/17.
 */
public class Sherlock {
    private static Map<String, Double> weightsMap = new HashMap<String, Double>(){{
        put(ComputeConstants.PKEY,5.0);
        put(ComputeConstants.TKEY,1.0);
        put(ComputeConstants.UKEY,10.0);
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
        double score = weightsMap.get(ComputeConstants.PKEY)*calculateSpatialPoints(t,r)
                + weightsMap.get(ComputeConstants.TKEY)*calculateTemporalPoints(t,r)
                + weightsMap.get(ComputeConstants.UKEY)*calculateUrgencyPoints(t,r)
                + weightsMap.get(ComputeConstants.IKEY)*t.getStaticScore();
        return score;
    }

    private static double calculateSpatialPoints(Task t, Request r){
        if(t.getSpatialAffinity().contains(r.getPlace()))
            return 10.0;
        return -10.0;
    }

    private static double calculateTemporalPoints(Task t, Request r){
        if(t.getTemporalAffinity().contains(r.getCurrentTimestamp()))
            return 10.0;
        return -10.0;
    }

    private static double calculateUrgencyPoints(Task t, Request r){
        double timeLeft = t.getDeadline() - r.getCurrentTimestamp();

        if (timeLeft < ComputeConstants.ONE_HOUR || timeLeft < t.getDuration())
            return 100.0;
        else if (timeLeft < ComputeConstants.TWO_HOUR && timeLeft > ComputeConstants.ONE_HOUR )
            return 80.0;
        else if (timeLeft < ComputeConstants.FOUR_HOUR && timeLeft > ComputeConstants.TWO_HOUR )
            return 60.0;
        else if (timeLeft < ComputeConstants.SIX_HOUR && timeLeft > ComputeConstants.FOUR_HOUR )
            return 40.0;
        return 10.0;
    }

}
