package com.yeol.ocle.controller.admin;

import com.yeol.ocle.comn.cmdto.PageNavInfoDTO;
import com.yeol.ocle.comn.consts.OcleConst;
import com.yeol.ocle.comn.exception.BizOcleException;
import com.yeol.ocle.comn.message.MessageService;
import com.yeol.ocle.comn.utils.OcleUtils;
import com.yeol.ocle.controller.admin.dto.IntgCodeDTO;
import com.yeol.ocle.controller.admin.dto.IntgMsgeDTO;
import com.yeol.ocle.model.intgcode.IntgCodeVal;
import com.yeol.ocle.repository.intgcode.IntgCodeValRepository;
import com.yeol.ocle.service.intgcodemgmt.IntgCodeMgmtService;
import com.yeol.ocle.service.intgmsgemgmt.IntgMsgeMgmtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/admin/comnmgmt")
public class ComnMgmtController {
    private final String preFix = "/admin/comnmgmt/";
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
     * 통합코드관리서비스
     */
    @Autowired
    private IntgCodeMgmtService intgCodeMgmtService;

    /**
     * 통합코드값 Repository
     */
    @Autowired
    private IntgCodeValRepository intgCodeValRepository;

    /**
     * 통합메시지관리서비스
     */
    @Autowired
    private IntgMsgeMgmtService intgMsgeMgmtService;


    /**
     * 통합코드관리
     * @param model
     * @return
     */
    @GetMapping("/IntgCodeMgmtM")
    public String IntgCodeMgmtM (
            Model model,
            @ModelAttribute("inqyInpt") IntgCodeDTO inqyInpt,
            @PageableDefault(page = 0, size = 10, sort = "intgCodeId", direction = Sort.Direction.ASC) Pageable pageable) {

        try {
            log.info("ComnMgmtController.IntgCodeMgmtM START");

            if(ObjectUtils.isEmpty(inqyInpt)) {
                inqyInpt = new IntgCodeDTO();
            }

            /** 통합코드관리서비스.통합코드목록조회  */
            Page<Object> intgCodeList = intgCodeMgmtService.selectIntgCodeList(inqyInpt, pageable);

            //검색조건 object
            model.addAttribute("inqyInpt", inqyInpt);

            //페이징처리정보
            PageNavInfoDTO pageNavInfo = OcleUtils.getPageNavInfo(intgCodeList);
            model.addAttribute("pageNavInfo", pageNavInfo);

            //통합코드목록
            model.addAttribute("intgCodeList", intgCodeList);

            model.addAttribute("prcsRsltCode", OcleConst.PRCS_RSLT_CODE_O);
        } catch (Exception e) {
            model.addAttribute("inqyInpt", new IntgCodeDTO());
            model.addAttribute("pageNavInfo", new PageNavInfoDTO());

            return messageService.exceptionPageRslt(e, model, this.getViewName("IntgCodeMgmtM"));
        }

        return this.getViewName("IntgCodeMgmtM");
    }

    /**
     * 통합코드값목록 조회
     * @param model
     * @param intgCode
     * @return
     */
    @PostMapping("/IntgCodeMgmtT01")
    public String IntgCodeMgmtT01(
            Model model,
            @RequestBody IntgCodeDTO intgCode
    ) {
        log.info("intgCodeId : {} ", intgCode.getIntgCodeId());

        String intgCodeId = intgCode.getIntgCodeId();

        //통합코드값 목록
        List<Object> intgCodeValList = intgCodeValRepository.findByIntgCodeId(intgCodeId);

        model.addAttribute("intgCodeValList", intgCodeValList);
        model.addAttribute("intgCodeId", intgCodeId);
        return this.getViewName("IntgCodeMgmtM :: #intgCodeMgmtT01");
    }

    /**
     * 통합메시지관리
     * @param model
     * @return
     */
    @GetMapping("/IntgMsgeMgmtM")
    public String IntgMsgeMgmtM(
            Model model,
            @ModelAttribute("inqyInpt") IntgMsgeDTO inqyInpt,
            @PageableDefault(page = 0, size = 10, sort = "intgMsgeId", direction = Sort.Direction.ASC) Pageable pageable) {

        try {
            log.info("ComnMgmtController.IntgCodeMgmtM START");

            if(ObjectUtils.isEmpty(inqyInpt)) {
                inqyInpt = new IntgMsgeDTO();
            }

            /** 통합메시지관리서비스.통합메시지목록조회 */
            Page<Object> intgMsgeList = intgMsgeMgmtService.selectIntgMsgeList(inqyInpt, pageable);

            //검색조건 object
            model.addAttribute("inqyInpt", inqyInpt);

            //페이징처리정보
            PageNavInfoDTO pageNavInfo = OcleUtils.getPageNavInfo(intgMsgeList);
            model.addAttribute("pageNavInfo", pageNavInfo);
            
            //업무구분코드목록
            /** 통합코드관리서비스.통합코드값목록조회 */
            List<IntgCodeVal> bswrDvsnCodeList = intgCodeMgmtService.selectIntgCodeValList("BSWR_DVSN_CODE");
            model.addAttribute("bswrDvsnCodeList", bswrDvsnCodeList);

            //메시지구분코드목록
            /** 통합코드관리서비스.통합코드값목록조회 */
            List<IntgCodeVal> msgeDvsnCodeList = intgCodeMgmtService.selectIntgCodeValList("MSGE_DVSN_CODE");
            model.addAttribute("msgeDvsnCodeList", msgeDvsnCodeList);

            //통합메시지목록
            model.addAttribute("intgMsgeList", intgMsgeList);

            //MAX 통합메시지ID
            List<String> maxIntgMsgeIdList = intgMsgeMgmtService.findMaxIntgMsgeIdList(bswrDvsnCodeList);
            model.addAttribute("maxIntgMsgeIdList", maxIntgMsgeIdList);

            model.addAttribute("prcsRsltCode", OcleConst.PRCS_RSLT_CODE_O);
        } catch (Exception e) {
            model.addAttribute("inqyInpt", new IntgMsgeDTO());
            model.addAttribute("pageNavInfo", new PageNavInfoDTO());

            return messageService.exceptionPageRslt(e, model, this.getViewName("IntgMsgeMgmtM"));
        }

        return this.getViewName("IntgMsgeMgmtM");
    }
}

