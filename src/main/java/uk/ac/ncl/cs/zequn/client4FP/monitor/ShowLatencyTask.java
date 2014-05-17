package uk.ac.ncl.cs.zequn.client4FP.monitor;

import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 * Created by zequnli on 16/05/2014.
 */
public class ShowLatencyTask extends TimerTask {
    private final Monitor4Latency monitor4Latency;

    public ShowLatencyTask(Monitor4Latency monitor4Latency) {
        this.monitor4Latency = monitor4Latency;
    }

    @Override
    public void run() {
        System.out.println(TimeUnit.MICROSECONDS.convert(monitor4Latency.getAvgLatency(),TimeUnit.NANOSECONDS));
    }
}
