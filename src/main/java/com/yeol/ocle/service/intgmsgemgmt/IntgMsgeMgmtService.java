package com.yeol.ocle.service.intgmsgemgmt;

import com.yeol.ocle.comn.utils.OcleUtils;
import com.yeol.ocle.controller.admin.dto.IntgMsgeDTO;
import com.yeol.ocle.repository.intgmsge.IntgMsgeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


/**
 * 통합 메시지 관리 서비스
 */
@Service
@Slf4j
public class IntgMsgeMgmtService {

    /**
     * 통합메시지 Repository
     */
    @Autowired
    private IntgMsgeRepository intgMsgeRepository;

    /**
     * 통합메시지목록조회
     *
     * @return Page<IntgMsge> intgMsgeList
     */
    public Page<Object> selectIntgMsgeList(IntgMsgeDTO inqyInpt, Pageable pageable) {
        log.info("IntgMsgeMgmtService.selectIntgMsgeList START");

        String intgMsgeId = OcleUtils.nvlToString(inqyInpt.getIntgMsgeId(), "");   //통합메시지ID
        String bswrDvsnCode = OcleUtils.nvlToString(inqyInpt.getBswrDvsnCode(), "");   //업무구분코드
        String msgeDvsnCode = OcleUtils.nvlToString(inqyInpt.getMsgeDvsnCode(), "");   //메시지구분코드
        String msgeCntn = OcleUtils.nvlToString(inqyInpt.getMsgeCntn(), "");   //메시지내용

        bswrDvsnCode = "A".equalsIgnoreCase(bswrDvsnCode) ? "" : bswrDvsnCode;  //A : 전체조회
        msgeDvsnCode = "A".equalsIgnoreCase(msgeDvsnCode) ? "" : msgeDvsnCode;  //A : 전체조회

        //통합메시지목록
        Page<Object> intgMsgeList = intgMsgeRepository.findBySearchCondition(intgMsgeId, msgeCntn, bswrDvsnCode, msgeDvsnCode, pageable);
        return intgMsgeList;
    }
}
