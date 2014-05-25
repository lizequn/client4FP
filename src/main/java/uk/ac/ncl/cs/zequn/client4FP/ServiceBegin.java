package uk.ac.ncl.cs.zequn.client4FP;

import org.springframework.web.client.RestTemplate;

/**
 * Created by Zequn on 2014/5/24.
 */
public class ServiceBegin {
    public static void main(String [] args){
        RestTemplate restTemplate = new RestTemplate();
        StringBuilder urlSb = new StringBuilder();
        urlSb.append(Config.beginServiceUrl).append("/AVG").append("/1000").append("/600000");
        int i = restTemplate.getForObject(urlSb.toString(),Integer.class);
        String services = "/"+i;
        StringBuilder serviceSb = new StringBuilder();
        serviceSb.append(Config.sendInfoServiceUrl).append(services).append("/").append(System.currentTimeMillis()).append("/").append(100);
        System.out.println(serviceSb.toString());
    }
}
