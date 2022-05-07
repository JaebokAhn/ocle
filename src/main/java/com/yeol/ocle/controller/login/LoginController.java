package com.yeol.ocle.controller.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/main/login")
public class LoginController {
    private String preFix = "main/login/";
    private String postFix = "";

    private String getViewName(String pageName){
        return preFix + pageName + postFix;
    }

    /**
     * 로그인 폼
     * @param
     * @return
     */
    @GetMapping("/login")
    public String loginForm(){
        return this.getViewName("loginForm");
    }
}
