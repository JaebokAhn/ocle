package com.yeol.ocle.controller.admin;

import com.yeol.ocle.comn.cmdto.PageNavInfoDTO;
import com.yeol.ocle.comn.consts.OcleConst;
import com.yeol.ocle.comn.exception.BizOcleException;
import com.yeol.ocle.comn.message.MessageService;
import com.yeol.ocle.comn.utils.OcleUtils;
import com.yeol.ocle.controller.admin.modelattr.IntgCodeDTO;
import com.yeol.ocle.repository.intgcode.IntgCodeValRepository;
import com.yeol.ocle.service.intgcodemgmt.IntgCodeMgmtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

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
