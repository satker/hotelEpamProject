package com.epam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping()
public class UserInfoController {

    @GetMapping("login")
    public ModelAndView login() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("custom-loginTEST");
        return mav;
    }
    @GetMapping("logout")
    public ModelAndView getAllUserArticles() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("logoutTEST");
        return mav;
    }

    @GetMapping("error")
    public ModelAndView error() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("403TEST");
        return mav;
    }
}

