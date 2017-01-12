package Serving;

import Entities.Request;
import Entities.Task;
import dao.TaskDAO;
import org.skife.jdbi.v2.DBI;

import java.util.List;

/**
 * Created by shreenath on 12/1/17.
 */
public class Server {
    public static void main(String args[]) {
        Request request = new Request(200, "%OTHER%");
        System.out.println("Request is for :" + request.getPlace() + request.getCurrentTimestamp());
        List<Task> response = getResponseFor(request);
        if(response.size()>0) {
            System.out.println("Got Response :");
            for(Task task: response) System.out.println(task.toString());
        } else {
            System.out.println("No Eligible Tasks found");
        }
    }

    private static List<Task> getResponseFor(Request request) {
        DBI dbi = SqlConnect.getDBI();
        TaskDAO taskDAO = dbi.open(TaskDAO.class);
        List<Task> tasks = taskDAO.getSpatioTemporallyEligibleTasks(request.getPlace(),request.getCurrentTimestamp());
        return tasks;
    }
}
