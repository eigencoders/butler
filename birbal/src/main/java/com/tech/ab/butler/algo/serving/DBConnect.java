package com.tech.ab.butler.algo.serving;

import com.tech.ab.butler.algo.dao.TaskDAO;
import com.tech.ab.butler.algo.entities.Task;
import org.skife.jdbi.v2.DBI;

import java.util.List;


/**
 * Created by shreenath on 11/1/17.
 */
public class DBConnect {
    public static void main(String args[]){
        DBI dbi = getDBI();
        TaskDAO taskDAO = dbi.open(TaskDAO.class);
//        taskDAO.dropTable();
//        taskDAO.createTable();
//        List<Task> allTasks = taskDAO.getAllTasks();
        List<Task> allTasks = taskDAO.getFutureTasks();
//        List<Task> allTasks = taskDAO.getTemporallyEligibleTasks((long)250);
//        List<Task> allTasks = taskDAO.getSpatiallyEligibleTasks("%OTHER%");

        for (Task t: allTasks) {
            System.out.println(t);
        }
//        System.out.println(Arrays.toString(allTasks.toArray()));

    }

    public static DBI getDBI() {
        String dbURl = "jdbc:mysql://localhost:3306/javadb";
        String username = "java";
        String password = "java";
        return new DBI(dbURl, username, password);
    }
}
