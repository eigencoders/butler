package serving;

import compute.Sherlock;
import constants.ComputeConstants;
import dao.TaskDAO;
import entities.Request;
import entities.Task;
import org.skife.jdbi.v2.DBI;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by shreenath on 12/1/17.
 */
public class Server {

    private static Request prepareRequest() throws ParseException {
        Date d = ComputeConstants.format.parse("01/01/2017 00:00:00");
        String p = "OTHER";
        System.out.println(String.format("Request is for : %s,%s",d,p));
        return new Request(new Timestamp(d.getTime()),'%'+p+'%');
    }

    public static void main(String args[]) throws ParseException {
        Request request = prepareRequest();
        Map<Task, Double> response = getResponseFor(request);


        System.out.println("Got Scores :");
        System.out.println(Arrays.toString(response.entrySet().toArray()));
    }

    private static Map<Task, Double> getResponseFor(Request request) {
        DBI dbi = DBConnect.getDBI();
        TaskDAO taskDAO = dbi.open(TaskDAO.class);
        List<Task> availableTasks = taskDAO.getFutureTasks();

        return Sherlock.getScoresMap(availableTasks, request);
    }
}
