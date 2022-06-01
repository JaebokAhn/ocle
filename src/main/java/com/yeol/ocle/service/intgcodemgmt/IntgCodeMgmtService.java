package com.yeol.ocle.service.intgcodemgmt;

import com.yeol.ocle.model.intgcode.IntgCode;
import com.yeol.ocle.repository.intgcode.IntgCodeRepository;
import com.yeol.ocle.repository.intgcode.IntgCodeValRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
}
