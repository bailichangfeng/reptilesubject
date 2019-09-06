package com.zzq.FourSubject.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class helloworld {
    @ResponseBody
    @RequestMapping(path="/helloword")
    public String hello(){
        String message = "helloworlds";
        return message;
    }

}
