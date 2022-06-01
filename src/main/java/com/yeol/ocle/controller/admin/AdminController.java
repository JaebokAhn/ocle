package com.yeol.ocle.controller.admin;

import com.yeol.ocle.comn.message.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
     * 관리자 메인 화면
     * @return
     */
    @GetMapping({"", "/"})
    public String adminMain() {
        return this.getViewName("adminMain");
    }


    /**
     * 통합 메시지 관리
     * @param model
     * @return
     */
    @GetMapping("/intgMsgeMgmt1p")
    public String intgMsgeMgmt1p(Model model) {

        return this.getViewName("intgmsgemgmt/intgMsgeMgmt1p");
    }
}
