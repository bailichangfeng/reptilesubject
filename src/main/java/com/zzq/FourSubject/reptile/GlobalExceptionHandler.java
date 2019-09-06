package com.zzq.FourSubject.reptile;

import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class GlobalExceptionHandler implements ErrorViewResolver {
    public ModelAndView resolveErrorView(HttpServletRequest request, HttpStatus status, Map<String,Object> model){
        ModelAndView mav = new ModelAndView();
        mav.addObject("url",request.getRequestURI());
        mav.addAllObjects(model);
        mav.setViewName("error");
        return mav;
    }
}
