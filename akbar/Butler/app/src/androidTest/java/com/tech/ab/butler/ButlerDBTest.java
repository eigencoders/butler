package com.tech.ab.butler;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.tech.ab.butler.algo.entities.Status;
import com.tech.ab.butler.algo.entities.Task;
import com.tech.ab.butler.algo.entities.TimePeriod;
import com.tech.ab.butler.db.ButlerSQLiteDB;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Date;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ButlerDBTest {
    @Test
    public void testDBio() throws Exception {
        // Context of the app under test.
        Context mockContext = InstrumentationRegistry.getTargetContext();
        ButlerSQLiteDB db = new ButlerSQLiteDB(mockContext);
        Task task = new Task("name", "tid", "dtid", (long)100, Status.FUTURE, (long) 100, 1, new Date(System.currentTimeMillis()),new TimePeriod("00:00:00","03:00:00"),"OFFICE",false);
        db.insertTask(task);
        List<Task> t = db.getAvailableTasks();
        assertEquals(t.size(),1);
        assertFalse(t.get(0).isRoutine());
        assertTrue(t.get(0).getName().equals("name"));
        assertTrue(t.get(0).getTaskId().equals("tid"));
        assertTrue(t.get(0).getTemporalAffinity().getEndTimeOfTheDay().equals("12:00:00"));
    }
}
