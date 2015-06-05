package com.github.lemniscate.infoapp;

import com.github.lemniscate.infoapp.util.Curl;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

/**
 * Created by dave on 6/5/15.
 */
public class CurlTest {

    @Test
    public void foo() throws IOException {
        String ip = new Curl().get("http://ifconfig.me");
        System.out.println(ip);
    }
}
