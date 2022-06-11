package com.yeol.ocle.repository.intgcode;

import com.yeol.ocle.model.intgcode.IntgCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IntgCodeRepository extends JpaRepository<IntgCode, String> {

    /**
     * 통합코드목록조회 by 통합코드ID, 통합코드명
     * @param intgCodeId 통합코드ID
     * @param intgCodeNm 통합코드명
     * @param pageable  페이징처리
     * @return
     */
    @Query("select ic " +
            "from IntgCode ic " +
            "where ic.intgCodeId like %?1% " +
            "and ic.intgCodeNm like %?2% " +
            "and ic.dltnYn = 'N'")
    Page<Object> findBySearchCondition(String intgCodeId, String intgCodeNm, Pageable pageable);
}
