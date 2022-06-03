package com.yeol.ocle.service.intgcodemgmt;

import com.yeol.ocle.comn.utils.OcleUtils;
import com.yeol.ocle.controller.admin.dto.IntgCodeDTO;
import com.yeol.ocle.model.intgcode.IntgCode;
import com.yeol.ocle.repository.intgcode.IntgCodeRepository;
import com.yeol.ocle.repository.intgcode.IntgCodeValRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * 통합코드관리 서비스
 */

@Slf4j
@Service
public class IntgCodeMgmtService {

    /**
     * 통합코드 Repository
     */
    @Autowired
    private IntgCodeRepository intgCodeRepository;

    /**
     * 통합코드값 Ropository
     */
    @Autowired
    private IntgCodeValRepository intgCodeValRepository;

    /**
     * 통합코드등록
     * @param intgCode
     * @return
     */
    public int insertIntgCode(IntgCode intgCode) {
        int insertIntgCodeRslt = 0;

        return insertIntgCodeRslt;
    }

    /**
     * 통합코드목록조회
     * @param intgCodeListInqyInpt
     * @param pageable
     * @return Page<IntgCode> intgCodeList 통합코드목록
     */
    public Page<Object> selectIntgCodeList(IntgCodeDTO intgCodeListInqyInpt, Pageable pageable) {
        log.info("IntgCodeMgmtService.selectIntgCodeList START");

        //통합코드ID
        String intgCodeId = OcleUtils.nvlToString(intgCodeListInqyInpt.getIntgCodeId(), "");

        //통합코드명
        String intgCodeNm = OcleUtils.nvlToString(intgCodeListInqyInpt.getIntgCodeNm(), "");

        //통합코드목록조회
        Page<Object> intgCodeList = intgCodeRepository.findBySearchCondition(intgCodeId, intgCodeNm, pageable);
        return intgCodeList;
    }
}
