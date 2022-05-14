package com.yeol.ocle.repository.intgcode;

import com.yeol.ocle.model.intgcode.IntgCodeVal;
import com.yeol.ocle.model.intgcode.idclass.IntgCodeValId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IntgCodeValRepository extends JpaRepository<IntgCodeVal, IntgCodeValId> {
}
