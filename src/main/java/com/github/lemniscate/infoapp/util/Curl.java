package com.github.lemniscate.infoapp.util;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

/**
 * Created by dave on 6/5/15.
 */
public class Curl {

    private final RestTemplate rt;
    private final HttpEntity<String> entity;

    public Curl() {
        this.rt = new RestTemplate();
        SimpleClientHttpRequestFactory rf = (SimpleClientHttpRequestFactory) rt.getRequestFactory();
        rf.setReadTimeout(5 * 1000);
        rf.setConnectTimeout(1 * 1000);

        HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent", "curl/7.37.1");
        this.entity = new HttpEntity<String>("parameters", headers);
    }

    public String get(String url){
        ResponseEntity<String> result = rt.exchange(url, HttpMethod.GET, entity, String.class);
        Assert.isTrue(result.getStatusCode().is2xxSuccessful(), "Non-2XX response (" + result.getStatusCode() + ") for " + url);
        return result.getBody();
    }

    public String getOrElse(String url, String orElse){
        try{
            return get(url);
        }catch(Exception e){
            e.printStackTrace(System.out);
            return orElse;
        }
    }
}
