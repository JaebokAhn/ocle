package com.yeol.ocle.controller.admin;

import com.yeol.ocle.comn.cmdto.PageNavInfoDTO;
import com.yeol.ocle.comn.consts.OcleConst;
import com.yeol.ocle.comn.exception.BizOcleException;
import com.yeol.ocle.comn.message.MessageService;
import com.yeol.ocle.comn.utils.OcleUtils;
import com.yeol.ocle.model.intgcode.IntgCodeVal;
import com.yeol.ocle.repository.intgcode.IntgCodeValRepository;
import com.yeol.ocle.service.intgcodemgmt.IntgCodeMgmtService;
import com.yeol.ocle.service.intgcodemgmt.dto.IntgCodeListInqyInptDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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


    /**
     * 통합코드조회 submit
     * @param model
     * @return
     */
    @GetMapping("/intgCodeMgmt1p")
    public String intgCodeMgmt1p(
            Model model,
            @ModelAttribute("inqyInpt") IntgCodeListInqyInptDTO inqyInpt,
            @PageableDefault(page = 0, size = 10, sort = "intgCodeId", direction = Sort.Direction.ASC) Pageable pageable) {

        try {
            log.info("AdminController.intgCodeMgmt1pc START");

            if(inqyInpt == null) {
                inqyInpt = new IntgCodeListInqyInptDTO();
            }
            String intgCodeId = OcleUtils.nvlToString(inqyInpt.getIntgCodeId(), "");
            String intgCodeNm = OcleUtils.nvlToString(inqyInpt.getIntgCodeNm(), "");
            inqyInpt.setIntgCodeId(intgCodeId);
            inqyInpt.setIntgCodeNm(intgCodeNm);

            Page<Object> intgCodeList = intgCodeMgmtService.selectIntgCodeList(inqyInpt, pageable);

            //검색조건 object
            model.addAttribute("inqyInpt", inqyInpt);

            //페이징처리정보
            PageNavInfoDTO pageNavInfo = OcleUtils.getPageNavInfo(intgCodeList);
            model.addAttribute("pageNavInfo", pageNavInfo);
            
            //통합코드목록
            model.addAttribute("intgCodeList", intgCodeList);
        } catch (BizOcleException e) {
            model.addAttribute("prcsRsltCode", OcleConst.PRCS_RSLT_CODE_E);
            model.addAttribute("msgeCode", e.getMessageId());
            model.addAttribute("msgeCntn", messageService.getMessage(e.getMessageId(), e.getArguments()));
            return this.getViewName("joinForm");
        } catch (Exception e) {
            model.addAttribute("prcsRsltCode", OcleConst.PRCS_RSLT_CODE_E);
            model.addAttribute("msgeCode", OcleConst.MSGE_CODE_SYSE0001);
            model.addAttribute("msgeCntn", messageService.getMessage(OcleConst.MSGE_CODE_SYSE0001));
            return this.getViewName("joinForm");
        }

        return this.getViewName("intgcodemgmt/intgCodeMgmt1p");
    }

    @PostMapping("/intgCodeMgmt1pc")
    public String intgCodeMgmt1pc(
            Model model,
            @RequestParam(value = "intgCodeId") String intgCodeId
    ) {
        log.info("intgCodeId : {} ", intgCodeId);
        
        //통합코드값 목록
        List<Object> intgCodeValList = intgCodeValRepository.findByIntgCodeId(intgCodeId);
        
        model.addAttribute("intgCodeValList", intgCodeValList);
        return this.getViewName("intgcodemgmt/intgCodeMgmt1pc");
    }

    /**
     * 통합코드관리
     * @param model
     * @return
     */
    @GetMapping("/intgCodeMgmt2p")
    public String intgCodeMgmt2p(Model model) {

        return this.getViewName("intgcodemgmt/intgCodeMgmt2p");
    }

    /**
     * 통합메시지조회
     * @param model
     * @return
     */
    @GetMapping("/intgMsgeMgmt1p")
    public String intgMsgeMgmt1p(Model model) {

        return this.getViewName("intgmsgemgmt/intgMsgeMgmt1p");
    }

    /**
     * 통합메시지관리
     * @param model
     * @return
     */
    @GetMapping("/intgMsgeMgmt2p")
    public String intgMsgeMgmt2p(Model model) {

        return this.getViewName("intgmsgemgmt/intgMsgeMgmt2p");
    }
}
