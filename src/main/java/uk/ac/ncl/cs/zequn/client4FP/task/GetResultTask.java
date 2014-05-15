package uk.ac.ncl.cs.zequn.client4FP.task;

import org.springframework.web.client.RestTemplate;
import uk.ac.ncl.cs.zequn.client4FP.Config;
import uk.ac.ncl.cs.zequn.client4FP.entity.ResultSet;

import java.util.TimerTask;

/**
 * Created by Zequn on 2014/5/15.
 */
public class GetResultTask extends TimerTask {
    private StringBuilder serviceSb;
    private final RestTemplate restTemplate;
    private final String services;
    public GetResultTask(String services){
        this.restTemplate = new RestTemplate();
        this.services =services;
    }
    @Override
    public void run() {
        serviceSb = new StringBuilder();
        serviceSb.append(Config.getResultServiceUrl).append(services);
        ResultSet set = restTemplate.getForObject(serviceSb.toString(), ResultSet.class);
        System.out.println(set.getInfo());
    }
}
