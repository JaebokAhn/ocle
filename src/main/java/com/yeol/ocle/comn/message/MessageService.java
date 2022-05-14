package com.yeol.ocle.comn.message;

import com.yeol.ocle.comn.utils.OcleUtils;
import com.yeol.ocle.model.intgmsge.IntgMsge;
import com.yeol.ocle.repository.intgmsge.IntgMsgeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
}
