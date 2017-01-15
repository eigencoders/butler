package com.tech.ab.butler.algo.serving;

import com.tech.ab.butler.algo.compute.Byom;
import com.tech.ab.butler.algo.compute.ComputeWeights;
import com.tech.ab.butler.algo.compute.ComputeWeightsBuilder;
import com.tech.ab.butler.algo.computeconstants.ComputeConstants;
import com.tech.ab.butler.algo.dao.TaskDAO;
import com.tech.ab.butler.algo.entities.Request;
import com.tech.ab.butler.algo.entities.Task;
import org.skife.jdbi.v2.DBI;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.*;

/**
 * Created by shreenath on 12/1/17.
 */
public class Server {

    private static Request prepareRequest() throws ParseException {
        Date d = ComputeConstants.format.parse("15/01/2017 00:00:00");
        String p = "OFFICE";
        System.out.println(String.format("Request is for : %s,%s",d,p));
        return new Request(new Timestamp(d.getTime()),p);
    }

    public static void main(String args[]) throws ParseException {
        Request request = prepareRequest();
        Map<Task, Double> response = getResponseFor(request);
        System.out.println("Got Scores :");
        Map<Task, Double> sortedMap = sortByValue(response);

        for (Map.Entry<Task, Double> entry: sortedMap.entrySet()) {
            System.out.println(entry.getValue()+"="+entry.getKey());
        }
    }

    private static Map<Task, Double> getResponseFor(Request request) {
        DBI dbi = DBConnect.getDBI();
        TaskDAO taskDAO = dbi.open(TaskDAO.class);
        List<Task> availableTasks = taskDAO.getFutureTasks();
        Byom bakshi = instantiateSatyanveshi();
        return bakshi.getScoresMap(availableTasks, request);
    }

    private static Byom instantiateSatyanveshi() {
        ComputeWeights weights = new ComputeWeightsBuilder()
                .withSpatialWeights(new Double[]{10.0, 5.0, 0.0})
                .withDependencyPenalty(10)
                .withTemporalWeights(10, 0)
                .withInherentScoreWeight(10)
                .withUrgencyWeights(new Double[]{100.0,80.0,60.0,40.0,20.0,10.0})
                .withDeadlineMissPenalty(40)
                .build();

        return new Byom(weights);
    }


    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue( Map<K, V> map ) {
        List<Map.Entry<K, V>> list =
                new LinkedList<>( map.entrySet() );
        Collections.sort( list, new Comparator<Map.Entry<K, V>>() {
            @Override
            public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });

        Map<K, V> result = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : list) {
            result.put( entry.getKey(), entry.getValue() );
        }
        return result;
    }
}
