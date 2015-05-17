package com.github.lemniscate.infoapp;

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
}
