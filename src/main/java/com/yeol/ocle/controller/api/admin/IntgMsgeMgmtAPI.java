package com.yeol.ocle.controller.api.admin;

import com.yeol.ocle.comn.cmdto.ComnMsgeDTO;
import com.yeol.ocle.comn.exception.BizOcleException;
import com.yeol.ocle.comn.message.MessageService;
import com.yeol.ocle.comn.utils.OcleUtils;
import com.yeol.ocle.controller.GenericApiController;
import com.yeol.ocle.controller.api.admin.dto.IntgMsgeApiDTO;
import com.yeol.ocle.model.intgmsge.IntgMsge;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/admin/intgmsge")
public class IntgMsgeMgmtAPI extends GenericApiController<IntgMsge, String> {
    /**
     * 메시지서비스
     */
    @Autowired
    private MessageService messageService;

    /**
     * 통합메시지저장
     * @param intgMsgeList
     * @return
     */
    @PostMapping("/IntgMsgeMgmtAPI001c")
    public ResponseEntity<?> saveIntgMsge(@RequestBody List<IntgMsgeApiDTO> intgMsgeList) {
        ComnMsgeDTO comnMsge = null;
        try {
            log.info("IntgMsgeMgmtAPI.saveIntgMsge START");

            if(CollectionUtils.isEmpty(intgMsgeList)) {
                throw new BizOcleException("COMN0006", "통합메시지목록");
            }

            for(IntgMsgeApiDTO intgMsge : intgMsgeList) {
                String jobDvsnCode = intgMsge.getJobDvsnCode(); //작업구분코드
                String intgMsgeId = intgMsge.getIntgMsgeId();   //통합메시지ID
                String bswrDvsnCode = intgMsge.getBswrDvsnCode();   //업무구분코드
                String msgeDvsnCode = intgMsge.getMsgeDvsnCode();   //메시지구분코드
                String msgeCntn = intgMsge.getMsgeCntn();   //메시지내용

            }

            /* 처리가 완료되었습니다. */
            comnMsge = OcleUtils.getNormPrcsRsltMsge();

            log.info("IntgMsgeMgmtAPI.saveIntgMsge END");
        } catch (Exception e) {
            return messageService.exceptionApiRslt(e);
        }
        return new ResponseEntity(comnMsge, HttpStatus.CREATED);
    }
}
