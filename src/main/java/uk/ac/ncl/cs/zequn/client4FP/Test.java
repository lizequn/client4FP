package uk.ac.ncl.cs.zequn.client4FP;

import org.springframework.web.client.RestTemplate;
import uk.ac.ncl.cs.zequn.client4FP.monitor.Monitor4Latency;
import uk.ac.ncl.cs.zequn.client4FP.monitor.ShowLatencyTask;
import uk.ac.ncl.cs.zequn.client4FP.task.GetResultTask;
import uk.ac.ncl.cs.zequn.client4FP.task.InfoSendTask;

import java.util.Timer;

/**
 * Created by Zequn on 2014/5/15.
 */
public class Test {
    public static void main(String[] args) throws InterruptedException {
        RestTemplate restTemplate = new RestTemplate();
        StringBuilder urlSb = new StringBuilder();
        urlSb.append(Config.beginServiceUrl).append("/AVG").append("/1000").append("/600000");
        int i = restTemplate.getForObject(urlSb.toString(),Integer.class);
        String services = "/"+i;
        Timer timer = new Timer();
        Monitor4Latency latency = new Monitor4Latency();
        ShowLatencyTask showResultTask = new ShowLatencyTask(latency);
        GetResultTask getResultTask = new GetResultTask(services);
        for (int j = 0; j < 100; j++) {
            InfoSendTask infoSendTask = new InfoSendTask(services,latency);
            timer.scheduleAtFixedRate(infoSendTask,0,10);
        }
        timer.scheduleAtFixedRate(getResultTask,1000,1000);
        timer.scheduleAtFixedRate(showResultTask,0,1000);
        Thread.sleep(100000);
        timer.cancel();
        System.out.println(InfoSendTask.realCount);
        StringBuilder serviceSb = new StringBuilder();
        //serviceSb.append(Config.getResultServiceUrl).append(services);
        serviceSb.append(Config.getResultServiceUrl).append("/test/testinfo");
        int set = restTemplate.getForObject(serviceSb.toString(), Integer.class);
        System.out.println(set);
    }
}
