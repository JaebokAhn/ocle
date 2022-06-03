package com.yeol.ocle.controller.admin;

import com.yeol.ocle.comn.message.MessageService;
import com.yeol.ocle.repository.intgcode.IntgCodeValRepository;
import com.yeol.ocle.service.intgcodemgmt.IntgCodeMgmtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
@Slf4j
public class AdminController {
    private final String preFix = "/admin/";
    private final String postFix = "";

    private final String getViewName(String pageName){
        return this.preFix + pageName + this.postFix;
    }

    /**
     * 메시지서비스
     */
    @Autowired
    private MessageService messageService;

    /**
     * 공통코드관리서비스
     */
    @Autowired
    private IntgCodeMgmtService intgCodeMgmtService;

    /**
     * 공통코드값 Repository
     */
    @Autowired
    private IntgCodeValRepository intgCodeValRepository;



    /**
     * 관리자 메인 화면
     * @return
     */
    @GetMapping({"", "/"})
    public String adminMain() {
        return this.getViewName("adminMain");
    }
}
