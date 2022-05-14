package com.yeol.ocle.model.intgcode;

import com.yeol.ocle.model.comn.CommonEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@Entity
@Table(name = "intg_code")
public class IntgCode extends CommonEntity {
    @Id
    @Column(name="intg_code_id", length = 29, columnDefinition = "varchar(29) not null comment '통합코드ID'")
    private String intgCodeId;

    @Column(name="intg_code_nm", length = 600, columnDefinition = "varchar(600) not null comment '통합코드명'")
    private String intgCodeNm;

    @Column(name="intc_cntn", length = 4000, columnDefinition = "varchar(4000) comment '설명내용'")
    private String intcCntn;
}
