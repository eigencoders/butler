package dao;

import Entities.Task;
import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.List;

/**
 * Created by shreenath on 12/1/17.
 */
@RegisterMapper(Task.TaskMapper.class)
public interface TaskDAO {
    String tableName = "taskTable";

    @SqlQuery("select * from " + tableName )
    List<Task> getAllTasks();

    @SqlQuery("select * from " + tableName + " where status = 'FUTURE' ")
    List<Task> getFutureTasks();

    @SqlQuery("select * from " + tableName + " where status != 'EXPIRED' and startTime <= :timeStamp and endTime > :timeStamp")
    List<Task> getTemporallyEligibleTasks(@Bind("timeStamp")Long timeStamp);

    @SqlQuery("select * from " + tableName + " where status != 'EXPIRED' and places LIKE :place ")
    List<Task> getSpatiallyEligibleTasks(@Bind("place") String place);

    @SqlQuery("select * from " + tableName + " where status != 'EXPIRED' and places LIKE :place and startTime <= :timeStamp and endTime > :timeStamp ")
    List<Task> getSpatioTemporallyEligibleTasks(@Bind("place") String place, @Bind("timeStamp")Long timeStamp);


    @SqlUpdate("CREATE TABLE IF NOT EXISTS "+tableName+" (" +
            "  `name` varchar(255) NOT NULL," +
            "  `taskId` mediumint(9) unsigned NOT NULL AUTO_INCREMENT," +
            "  `dependentTaskId` mediumint(9) unsigned DEFAULT '0'," +
            "  `duration` double DEFAULT '0'," +
            "  `status` enum('ACTIVE','EXPIRED','FUTURE') NOT NULL DEFAULT 'FUTURE'," +
            "  `staticScore` double DEFAULT '0'," +
            "  `frequency` double DEFAULT '0'," +
            "  `deadline` mediumint(9) unsigned DEFAULT '0'," +
            "  `startTime` mediumint(9) unsigned DEFAULT '0'," +
            "  `endTime` mediumint(9) unsigned DEFAULT '0'," +
            "  `places` varchar(255) NOT NULL DEFAULT 'OTHER'," +
            "  PRIMARY KEY (`taskId`)," +
            "  UNIQUE KEY `id_UNIQUE` (`taskId`)" +
            ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;")
    void createTable();


    @SqlUpdate("DROP TABLE " + tableName)
    void dropTable();

}
