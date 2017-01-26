package com.tech.ab.butler.db;

/**
 * Created by shreenath on 12/1/17.
 */
public interface TaskDAO {
//    String tableName = "taskTable1";

//    @SqlQuery("select * from " + tableName )
//    List<Task> getAllTasks();
//
//    @SqlQuery("select * from " + tableName + " where status = 'FUTURE' ")
//    List<Task> getFutureTasks();
//
//    @SqlQuery("select * from " + tableName + " where status != 'EXPIRED' and startTimeOfTheDay <= :timeStamp and endTimeOfTheDay > :timeStamp")
//    List<Task> getTemporallyEligibleTasks(@Bind("timeStamp") Long timeStamp);
//
//    @SqlQuery("select * from " + tableName + " where status != 'EXPIRED' and places LIKE :place ")
//    List<Task> getSpatiallyEligibleTasks(@Bind("place") String place);
//
//    @SqlQuery("select * from " + tableName + " where status != 'EXPIRED' and places LIKE :place and startTimeOfTheDay <= :timeStamp and endTimeOfTheDay > :timeStamp ")
//    List<Task> getSpatioTemporallyEligibleTasks(@Bind("place") String place, @Bind("timeStamp") Long timeStamp);
//
//
//    @SqlUpdate("CREATE TABLE " + tableName +" (" +
//            "  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL," +
//            "  `taskId` mediumint(9) unsigned NOT NULL AUTO_INCREMENT," +
//            "  `dependentTaskId` mediumint(9) unsigned DEFAULT '0'," +
//            "  `durationMins` double DEFAULT '0'," +
//            "  `status` enum('ACTIVE','EXPIRED','FUTURE') COLLATE utf8_unicode_ci NOT NULL DEFAULT 'FUTURE'," +
//            "  `staticScore` double DEFAULT '0'," +
//            "  `frequency` double DEFAULT '0'," +
//            "  `deadline` DATETIME DEFAULT '2066-01-01 00:00:00'," +
//            "  `startTimeOfTheDay` TIME DEFAULT '00:00:00'," +
//            "  `endTimeOfTheDay` Time DEFAULT '23:59:59'," +
//            "  `places` varchar(255) COLLATE utf8_unicode_ci NOT NULL DEFAULT 'OTHER'," +
//            "  PRIMARY KEY (`taskId`)," +
//            "  UNIQUE KEY `id_UNIQUE` (`taskId`)" +
//            ") ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;")
//    void createTable();
//
//
//    @SqlUpdate("DROP TABLE " + tableName)
//    void dropTable();

}
