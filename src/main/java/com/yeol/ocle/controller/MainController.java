package com.yeol.ocle.controller;

import com.yeol.ocle.comn.consts.OcleConst;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
    @GetMapping({"", "/"})
    public String index() {
        return "main/main";
    }

    @GetMapping("consumer")
    public @ResponseBody
    String  consumer(){
        return "consumer";
    }

    @GetMapping("supplier")
    public @ResponseBody
    String  supplier(){
        return "supplier";
    }

    @GetMapping("admin")
    public @ResponseBody
    String  admin(){
        return "admin";
    }

    /** Example of @Secured*/
//    @Secured(OcleConst.USER_ROLE_DVSN_CODE_SPLR)
//    @GetMapping("info")
//    public @ResponseBody
//    String info() {
//        return "개인정보";
//    }
    /** Example of @PreAuthorize */
//    @PreAuthorize("hasRole('ROLE_SUPPLIER') or hasRole('ROLE_ADMIN')")
//    @GetMapping("data")
//    public @ResponseBody
//    String date() {
//        return "데이터";
//    }
}
