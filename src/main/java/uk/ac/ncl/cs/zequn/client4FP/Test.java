package uk.ac.ncl.cs.zequn.client4FP;

import org.springframework.web.client.RestTemplate;
import uk.ac.ncl.cs.zequn.client4FP.monitor.Monitor4Latency;
import uk.ac.ncl.cs.zequn.client4FP.monitor.ShowLatencyTask;
import uk.ac.ncl.cs.zequn.client4FP.task.GetResultTask;
import uk.ac.ncl.cs.zequn.client4FP.task.InfoSendTask;
import uk.ac.ncl.cs.zequn.client4FP.test.LogAccess;

import java.sql.SQLException;
import java.util.Timer;

/**
 * Created by Zequn on 2014/5/15.
 */
public class Test {
    public static void main(String[] args) throws InterruptedException, SQLException {
        RestTemplate restTemplate = new RestTemplate();
        StringBuilder urlSb = new StringBuilder();
        urlSb.append(Config.beginServiceUrl).append("/AVG").append("/1000").append("/600000");
        int i = restTemplate.getForObject(urlSb.toString(),Integer.class);
        String services = "/"+i;
        Timer timer = new Timer();
        Monitor4Latency latency = new Monitor4Latency();
        LogAccess logAccess = new LogAccess("latency100");
        ShowLatencyTask showResultTask = new ShowLatencyTask(latency,logAccess);
        GetResultTask getResultTask = new GetResultTask(services);
        for (int j = 0; j < 10; j++) {
            if(j==0){
                InfoSendTask infoSendTask = new InfoSendTask(services,latency);
                timer.scheduleAtFixedRate(infoSendTask,0,1);
            }else {
                InfoSendTask infoSendTask = new InfoSendTask(services,null);
                timer.scheduleAtFixedRate(infoSendTask,0,1);
            }
        }
        timer.scheduleAtFixedRate(getResultTask,1000,1000);
        timer.scheduleAtFixedRate(showResultTask,0,1000);
        Thread.sleep(60000);
        timer.cancel();
        System.out.println(InfoSendTask.realCount);
        //serviceSb.append(Config.getResultServiceUrl).append(services);
        int set = restTemplate.getForObject(Config.getResultServiceUrl + "/test/testinfo", Integer.class);
        System.out.println(set);
       // logAccess.output2CSV("D://",logAccess.getTable()+".csv");
        StringBuilder urlStop = new StringBuilder();
        urlStop.append(Config.stopServiceUrl).append(services);
        restTemplate.getForObject(urlStop.toString(),Integer.class);
    }
}
