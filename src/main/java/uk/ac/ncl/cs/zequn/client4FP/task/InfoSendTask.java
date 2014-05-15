package uk.ac.ncl.cs.zequn.client4FP.task;

import org.springframework.web.client.RestTemplate;
import uk.ac.ncl.cs.zequn.client4FP.Config;

import java.util.Random;
import java.util.TimerTask;

/**
 * Created by Zequn on 2014/5/15.
 */
public class InfoSendTask extends TimerTask {
    private final Random random;
    private StringBuilder serviceSb;
    private final RestTemplate restTemplate;
    private final String services;
    public InfoSendTask(String services){
        random = new Random();
        this.restTemplate = new RestTemplate();
        this.services = services;
    }
    @Override
    public void run() {
        serviceSb = new StringBuilder();
        serviceSb.append(Config.sendInfoServiceUrl).append(services).append("/").append(System.currentTimeMillis()).append("/").append(random.nextDouble()*100);
        restTemplate.getForObject(serviceSb.toString(),Integer.class);
    }
}