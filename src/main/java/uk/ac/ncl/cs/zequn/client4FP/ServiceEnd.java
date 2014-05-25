package uk.ac.ncl.cs.zequn.client4FP;

import org.springframework.web.client.RestTemplate;

/**
 * Created by Zequn on 2014/5/24.
 */
public class ServiceEnd {
    public static void main(String [] args){
        final String services = "/1";
        RestTemplate restTemplate = new RestTemplate();
        StringBuilder urlStop = new StringBuilder();
        urlStop.append(Config.stopServiceUrl).append(services);
        restTemplate.getForObject(urlStop.toString(),Integer.class);
    }
}
