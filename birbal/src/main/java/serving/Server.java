package serving;

import compute.Sherlock;
import dao.TaskDAO;
import entities.Request;
import entities.Task;
import org.skife.jdbi.v2.DBI;

import java.util.List;
import java.util.Map;

/**
 * Created by shreenath on 12/1/17.
 */
public class Server {
    public static void main(String args[]) {
        Request request = new Request(200, "%OTHER%");
        System.out.println("Request is for :" + request.getPlace() + request.getCurrentTimestamp());
        Map<Task, Double> response = getResponseFor(request);
        if(response.size()>0) {
            System.out.println("Got Response :");
            for(Map.Entry<Task, Double> scoreTask : response.entrySet())
                System.out.println(scoreTask.getKey().toString() +" scores "+ scoreTask.getValue());
        } else {
            System.out.println("No Eligible Tasks found");
        }
    }

    private static Map<Task, Double> getResponseFor(Request request) {
        DBI dbi = SqlConnect.getDBI();
        TaskDAO taskDAO = dbi.open(TaskDAO.class);
        List<Task> availableTasks = taskDAO.getFutureTasks();

//        System.out.println("Total Available:");
//        for(Task t : availableTasks)
//            System.out.println(t.toString());
//        System.out.println("\n");

        Map<Task, Double> taskScoreMap = Sherlock.getScoresMap(availableTasks, request);
        return taskScoreMap;
    }
}
