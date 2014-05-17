package uk.ac.ncl.cs.zequn.client4FP.monitor;

/**
 * Created by zequnli on 16/05/2014.
 */
public class Monitor4Latency {
    private long beforeTime;
    private long sum;
    private int count;
    private Object lock = new Object();
    public Monitor4Latency() {

    }

    public void before(){
        synchronized (lock){
            beforeTime = System.nanoTime();
        }
    }
    public void after(){
        synchronized (lock) {
            sum += System.nanoTime() - beforeTime;
            count++;
            beforeTime = 0;
        }
    }

    public long getAvgLatency(){
        synchronized (lock) {
            long result =  sum / count;
            sum = 0;
            count = 0;
            return result;
        }
    }
}
