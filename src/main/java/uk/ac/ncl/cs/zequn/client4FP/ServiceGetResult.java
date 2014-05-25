package uk.ac.ncl.cs.zequn.client4FP;

import uk.ac.ncl.cs.zequn.client4FP.task.GetResultTask;

import java.util.Timer;

/**
 * Created by Zequn on 2014/5/24.
 */
public class ServiceGetResult {
    public static void main(String [] args) throws InterruptedException {
        String services = "/1";
        GetResultTask getResultTask = new GetResultTask(services);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(getResultTask,1000,1000);
        Thread.sleep(10000);
        timer.cancel();
    }
}
