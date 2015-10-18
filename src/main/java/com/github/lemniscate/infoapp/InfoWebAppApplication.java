package com.github.lemniscate.infoapp;

import com.github.lemniscate.infoapp.util.Curl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;

@SpringBootApplication
public class InfoWebAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(InfoWebAppApplication.class, args);
    }

}

@Controller
class InfoController {

    private final Curl curl = new Curl();

    @ResponseBody
    @RequestMapping(value = "/headers", produces = "application/json")
    public Object headers(HttpServletRequest request){
        MultiValueMap<String, Object> result = new LinkedMultiValueMap<String, Object>();
        Enumeration<String> names = request.getHeaderNames();
        while( names.hasMoreElements() ){
            String name = names.nextElement();
            Enumeration<String> headers = request.getHeaders(name);
            while( headers.hasMoreElements() ){
                String header = headers.nextElement();
                result.add(name, header);
            }
        }
        return result;
    }



    @ResponseBody
    @RequestMapping(value = "/servlet", produces = "application/json")
    public Object servlet(HttpServletRequest request){
        MultiValueMap<String, Object> result = new LinkedMultiValueMap<String, Object>();
        result.add("serverName", request.getServerName());
        result.add("serverPort", request.getServerPort());
        result.add("contextPath", request.getContextPath());
        result.add("remoteAddr", request.getRemoteAddr());
        result.add("remoteHost", request.getRemoteHost());
        result.add("remotePort", request.getRemotePort());
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/aws", produces = "application/json")
    public Object aws(HttpServletRequest request){
        MultiValueMap<String, Object> result = new LinkedMultiValueMap<String, Object>();

        result.add("ifconfigIP", curl.getOrElse("http://ifconfig.me", "unknown").replace("\n", ""));
        result.add("awsLocalIP", curl.getOrElse("http://169.254.169.254/latest/meta-data/local-ipv4", "unknown"));
        result.add("awsPublicIP", curl.getOrElse("http://169.254.169.254/latest/meta-data/public-ipv4", "unknown"));

        return result;
    }
}
