package uk.ac.ncl.cs.zequn.client4FP.monitor;

import uk.ac.ncl.cs.zequn.client4FP.test.LogAccess;

import java.sql.SQLException;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 * Created by zequnli on 16/05/2014.
 */
public class ShowLatencyTask extends TimerTask {
    private final Monitor4Latency monitor4Latency;
    private final LogAccess logAccess;
    private static int counter = 0;

    public ShowLatencyTask(Monitor4Latency monitor4Latency,LogAccess logName) throws SQLException {
        this.monitor4Latency = monitor4Latency;
        logAccess = logName;
        logAccess.init();
    }

    @Override
    public void run() {
        long avg = TimeUnit.MICROSECONDS.convert(monitor4Latency.getAvgLatency(),TimeUnit.NANOSECONDS);
        logAccess.insertTuple(counter++ +"",avg+"");
        System.out.println(avg);
    }
}
