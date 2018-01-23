package com.epam.controller;


import com.epam.model.User;
import com.epam.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Slf4j
@Controller
@RequestMapping
@RequiredArgsConstructor
public class UserInfoController {
    private final UserService userService;

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

    @GetMapping(value = "/username")
    @ResponseBody
    public User currentUser(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        return userService.findUserByLogin(principal.getName());
    }

    @GetMapping("error")
    public ModelAndView error() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("403TEST");
        return mav;
    }
}

