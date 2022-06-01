package com.yeol.ocle.service.intgmsgemgmt;

import com.yeol.ocle.comn.consts.OcleConst;
import com.yeol.ocle.model.intgmsge.IntgMsge;
import com.yeol.ocle.repository.intgmsge.IntgMsgeRepository;
import com.yeol.ocle.service.intgmsgemgmt.dto.IntgMsgeListInqyInptDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


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
    public Page<IntgMsge> selectIntgMsgeList(IntgMsgeListInqyInptDTO intgMsgeListInqyInpt, Pageable pageable) {
        log.info("IntgMsgeMgmtService.selectIntgMsgeList START");

        String intgMsgeId = intgMsgeListInqyInpt.getIntgMsgeId();   //통합메시지ID
        String bswrDvsnCode = intgMsgeListInqyInpt.getBswrDvsnCode();   //업무구분코드
        String msgeDvsnCode = intgMsgeListInqyInpt.getMsgeDvsnCode();   //메시지구분코드
        String msgeCntn = intgMsgeListInqyInpt.getMsgeCntn();   //메시지내용

        log.info("intgMsgeId : {}", intgMsgeId);
        log.info("msgeCntn : {}", msgeCntn);
        log.info("bswrDvsnCode : {}", bswrDvsnCode);
        log.info("msgeDvsnCode : {}", msgeDvsnCode);

        //통합메시지목록
        Page<IntgMsge> intgMsgeList = intgMsgeRepository.findBySearchCondition(intgMsgeId, msgeCntn, bswrDvsnCode, msgeDvsnCode, pageable);
        return intgMsgeList;
    }
}
