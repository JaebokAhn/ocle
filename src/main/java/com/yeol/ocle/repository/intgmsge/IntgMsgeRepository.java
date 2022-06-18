package com.yeol.ocle.repository.intgmsge;

import com.yeol.ocle.model.intgmsge.IntgMsge;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IntgMsgeRepository extends JpaRepository<IntgMsge, String> {

    /**
     * 통합메시지목록 전체 조회
     * select * from intg_msge where dltnYn = ?1;
     * @param dltnYn
     * @return List<IntgMsge>
     */
    List<IntgMsge> findByDltnYn(String dltnYn);


    /**
     * select * from intg_msge where intg_msge_id like %?1%
     * @param intgMsgeId
     * @param pageable
     * @return
     */
    Page<IntgMsge> findByIntgMsgeIdContaining(String intgMsgeId, Pageable pageable);


    /**
     * 통합메시지조회 by 검색조건
     * @param intgMsgeId 통합메시지ID
     * @param msgeCntn  메시지내용
     * @param bswrDvsnCode  업무구분코드
     * @param msgeDvsnCode  메시지구분코드 (E : error, B : business error, I : info)
     * @param pageable  페이징처리
     * @return
     */
    @Query("select im " +
            "from IntgMsge im " +
            "where im.intgMsgeId like %?1% " +
            "and im.msgeCntn like %?2% " +
            "and im.bswrDvsnCode like %?3% " +
            "and im.msgeDvsnCode like %?4%" +
            "and im.dltnYn = 'N'")
    Page<Object> findBySearchCondition(String intgMsgeId, String msgeCntn, String bswrDvsnCode, String msgeDvsnCode, Pageable pageable);
}
