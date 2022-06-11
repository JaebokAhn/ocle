package com.yeol.ocle.comn.message;

import com.yeol.ocle.comn.cmdto.ComnMsgeDTO;
import com.yeol.ocle.comn.consts.OcleConst;
import com.yeol.ocle.comn.exception.BizOcleException;
import com.yeol.ocle.comn.utils.OcleUtils;
import com.yeol.ocle.model.intgmsge.IntgMsge;
import com.yeol.ocle.repository.intgmsge.IntgMsgeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.NumberUtils;

@Slf4j
@Service
public class MessageService {
    /**
     * 통합메시지 Repository
     */
    @Autowired
    IntgMsgeRepository intgMsgeRepository;

    public String getMessage(String messageId) {
        String message = "";

        try {
            /** 메시지ID로 통합메시지 조회 */
            IntgMsge intgMsge = intgMsgeRepository.findById(messageId).orElse(null);

            //메시지ID로 찾은 결과가 없으면 return
            if(intgMsge == null) {
                return message;
            }
            return intgMsge.getMsgeCntn();
        } catch (Exception e) {
            //[TODO] 삭제
            e.printStackTrace();
        }

        return message;
    }

    public String getMessage(String messageId, String[] args) {
        String message = "";

        log.info("MessageService.getMessage START");

        try {
            /** 메시지ID로 통합메시지 조회 */
            IntgMsge intgMsge = intgMsgeRepository.findById(messageId).orElse(null);

            //메시지ID로 찾은 결과가 없으면 return
            if(intgMsge == null) {
                return message;
            }

            //[T.통합메시지][메시지내용]
            String msgeCntn = intgMsge.getMsgeCntn();

            if(msgeCntn.indexOf("{") < 0) {
                return msgeCntn;
            }


            String[] msgeCntnArr = msgeCntn.split("\\{");
            StringBuffer sb = new StringBuffer();

            for(int i = 0; i < msgeCntnArr.length; i++) {
                log.info("msgeCntnArr[" + i + "] : " + msgeCntnArr[i]);

                if(msgeCntnArr[i].indexOf("}") < 0) {
                    sb.append(msgeCntnArr[i]);
                } else {
                    String[] tempArr = msgeCntnArr[i].split("\\}");

                    int idx = OcleUtils.isNumber(tempArr[0]) ? Integer.parseInt(tempArr[0]) : 0;

                    //파라미터로 전달받은 메시지
                    String strArgs = "";
                    if(args.length > idx) {
                        strArgs = args[idx];
                    }
                    sb.append(strArgs);

                    //DB에 저장 된 텍스트 영역
                    String strMsg = "";

                    if(tempArr.length > 1) {
                        strMsg = OcleUtils.nvlToString(tempArr[1], "");
                    }
                    sb.append(strMsg);
                }
            }

            message = sb.toString();

        } catch (Exception e) {
            //[TODO] 삭제
            e.printStackTrace();
        }
        return message;
    }

    /**
     * API EXCEPTION RETURN
     * @param e
     * @return
     */
    public ResponseEntity<?> exceptionApiRslt(Exception e) {
        ComnMsgeDTO comnMsge = new ComnMsgeDTO();

        comnMsge.setPrcsRsltCode(OcleConst.PRCS_RSLT_CODE_E);   //처리결과코드 : E (오류)

        if (e.getClass().equals(BizOcleException.class)) {
            /* Biz 오류인 경우 */

            BizOcleException eb = (BizOcleException) e;
            comnMsge.setMsgeCode(eb.getMessageId());
            comnMsge.setMsgeCntn(this.getMessage(eb.getMessageId(), eb.getArguments()));

        } else {
            /* 시스템 오류 */

            comnMsge.setMsgeCode(OcleConst.MSGE_CODE_SYSE0001);
            comnMsge.setMsgeCntn(this.getMessage(OcleConst.MSGE_CODE_SYSE0001));
        }

        return new ResponseEntity<>(comnMsge, HttpStatus.OK);
    }

    /**
     * VIEW EXCEPTION RETURN
     * @param e
     * @param model
     * @param viewName
     * @return
     */
    public String exceptionPageRslt(Exception e, Model model, String viewName) {
        model.addAttribute("prcsRsltCode", OcleConst.PRCS_RSLT_CODE_E);

        if (e.getClass().equals(BizOcleException.class)) {
            /* Biz 오류인 경우 */

            BizOcleException eb = (BizOcleException) e;
            model.addAttribute("msgeCode", eb.getMessageId());
            model.addAttribute("msgeCntn", this.getMessage(eb.getMessageId(), eb.getArguments()));

        } else {
            /* 시스템 오류 */

            model.addAttribute("msgeCode", OcleConst.MSGE_CODE_SYSE0001);
            model.addAttribute("msgeCntn", this.getMessage(OcleConst.MSGE_CODE_SYSE0001));
        }

        return viewName;
    }
}
