package com.yeol.ocle.controller.join;

import com.yeol.ocle.comn.cmdto.ComnMsgeDTO;
import com.yeol.ocle.comn.consts.OcleConst;
import com.yeol.ocle.controller.join.join.modelattr.JoinInptDTO;
import com.yeol.ocle.controller.join.join.validator.JoinInptValidator;
import com.yeol.ocle.service.usermgmt.join.JoinService;
import com.yeol.ocle.service.usermgmt.join.dto.JoinServiceInptDTO;
import com.yeol.ocle.service.usermgmt.join.dto.JoinServiceRsltDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/main/join")
@Slf4j
public class JoinController {
    private final String preFix = "/main/join/";
    private final String postFix = "";

    private final String getViewName(String pageName){
        return preFix + pageName + postFix;
    }


    /**
     * 회원가입 입력값 검증
     */
    @Autowired
    private JoinInptValidator joinInptValidator;

    /**
     * 회원가입서비스
     */
    @Autowired
    private JoinService joinService;


    /**
     * 회원가입 폼
     * @param
     * @return
     */
    @GetMapping("/join")
    public String joinForm(Model model){
        log.info("JoinController.joinForm START");

        model.addAttribute("joinInpt", new JoinInptDTO());
        return this.getViewName("joinForm");
    }

    /**
     * 회원가입 submit
     * @param joinInpt
     * @return
     */
    @PostMapping("/join")
    public String joinSubmit(@ModelAttribute("joinInpt") @Valid JoinInptDTO joinInpt,
                             BindingResult bindingResult,
                             Model model){
        try {
            log.info("JoinController.joinSubmit START");
            log.info("joinInpt : ", joinInpt.toString());

            joinInptValidator.validate(joinInpt, bindingResult);

            if(bindingResult.hasErrors()) {
                return this.getViewName("joinForm");
            }

            String username = joinInpt.getUsername();    //사용자ID
            String pasword = joinInpt.getPassword(); //비밀번호
            String custNm = joinInpt.getCustNm();    //고객명

            /** JoinService.join OP 호출 */
            JoinServiceInptDTO joinServiceInpt = new JoinServiceInptDTO();
            joinServiceInpt.setUsername(username);
            joinServiceInpt.setPassword(pasword);
            joinServiceInpt.setCustNm(custNm);

            JoinServiceRsltDTO joinServiceRslt = joinService.join(joinServiceInpt);

            if(joinServiceRslt != null) {
                ComnMsgeDTO comnMsge = joinServiceRslt.getComnMsge();   //공통메시지

                //처리결과 오류 인경우
                if(comnMsge == null || OcleConst.PRCS_RSLT_CODE_E.equals(comnMsge.getPrcsRsltCode())) {
                    model.addAttribute("comnMsge", comnMsge);
                    return this.getViewName("joinForm");
                }
            }

        } catch (Exception e) {

        }

        return "redirect:/main/login/login";
    }
}
