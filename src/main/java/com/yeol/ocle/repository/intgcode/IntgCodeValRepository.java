package com.yeol.ocle.repository.intgcode;

import com.yeol.ocle.model.intgcode.IntgCode;
import com.yeol.ocle.model.intgcode.IntgCodeVal;
import com.yeol.ocle.model.intgcode.idclass.IntgCodeValId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IntgCodeValRepository extends JpaRepository<IntgCodeVal, IntgCodeValId> {

    List<Object> findByIntgCodeId(String intgCodeId);

    List<IntgCodeVal> findByIntgCodeIdAndDltnYn(String intgCodeId, String dltnYn);
}
