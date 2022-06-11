package com.yeol.ocle.controller.api.admin;

import com.yeol.ocle.comn.cmdto.ComnMsgeDTO;
import com.yeol.ocle.comn.consts.OcleConst;
import com.yeol.ocle.comn.exception.BizOcleException;
import com.yeol.ocle.comn.message.MessageService;
import com.yeol.ocle.comn.utils.BeanCopyUtils;
import com.yeol.ocle.comn.utils.OcleUtils;
import com.yeol.ocle.config.security.auth.PrincipalDetails;
import com.yeol.ocle.controller.GenericApiController;
import com.yeol.ocle.controller.admin.dto.IntgCodeDTO;
import com.yeol.ocle.controller.api.admin.dto.IntgCodeApiDTO;
import com.yeol.ocle.model.intgcode.IntgCode;
import com.yeol.ocle.model.userinfo.User;
import com.yeol.ocle.service.intgcodemgmt.IntgCodeMgmtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.util.StringUtils;

import javax.validation.Valid;

@RestController
@Slf4j
@RequestMapping("/api/admin/intgcode")
public class IntgCodeMgmtAPI extends GenericApiController<IntgCode, String> {

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
     * 통합코드저장
     * @param intgCodeApi
     * @return
     */
    @PostMapping("/IntnCodeMgmtAPI001c")
    public ResponseEntity<?> saveIntgCode(@Valid @RequestBody IntgCodeApiDTO intgCodeApi,
                                          BindingResult bindingResult) {
        ComnMsgeDTO comnMsge = null;
        try {
            log.info("IntgCodeMgmtAPI.saveIntgCode START");

            //참고) @RequestBody는 Validator를 사용할 수 없음.
            if(bindingResult.hasErrors()) {
                comnMsge = OcleUtils.convertBindingResultToComnMsge(bindingResult);

                return new ResponseEntity(comnMsge, HttpStatus.OK);
            }

            //통합코드 MODEL
            IntgCode intgCode = BeanCopyUtils.copyDto(intgCodeApi, IntgCode.class);

            /** 통합코드관리서비스.통합코드저장 OP 호출 */
            IntgCode saveRslt = intgCodeMgmtService.saveIntgCode(intgCode);

            if(ObjectUtils.isEmpty(saveRslt)) {
                /* {0} 처리 중 오류가 발생하였습니다. */
                throw new BizOcleException("COMN0003", "통합코드저장");
            }

            /* 처리가 완료되었습니다. */
            comnMsge = OcleUtils.getNormPrcsRsltMsge();

            log.info("IntgCodeMgmtAPI.saveIntgCode END");
        } catch (Exception e) {
            comnMsge = new ComnMsgeDTO();
            comnMsge.setPrcsRsltCode(OcleConst.PRCS_RSLT_CODE_E);   //처리결과코드 : E (오류)

            /* Biz 오류인 경우 */
            if(e.getClass().equals(BizOcleException.class)) {
                BizOcleException eb = (BizOcleException) e;
                comnMsge.setMsgeCode(eb.getMessageId());
                comnMsge.setMsgeCntn( messageService.getMessage(eb.getMessageId(), eb.getArguments()));
            
            /* 시스템 오류 */
            } else {
                comnMsge.setMsgeCode(OcleConst.MSGE_CODE_SYSE0001);
                comnMsge.setMsgeCntn(messageService.getMessage(OcleConst.MSGE_CODE_SYSE0001));
            }
            return new ResponseEntity(comnMsge, HttpStatus.OK);
        }

        return new ResponseEntity(comnMsge, HttpStatus.CREATED);
    }
}
