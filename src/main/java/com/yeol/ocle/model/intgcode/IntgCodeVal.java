package com.yeol.ocle.model.intgcode;

import com.yeol.ocle.model.comn.CommonEntity;
import com.yeol.ocle.model.intgcode.idclass.IntgCodeValId;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@Entity
@IdClass(IntgCodeValId.class)   //복합키를 사용하는 경우 @IdClass annotation 사용, 식별자클래스를 지정해주어야함
@Table(name = "intg_code_val")
public class IntgCodeVal extends CommonEntity {
    @Id
    @NotNull
    @Column(name="intg_code_id", length = 29, updatable = false,
            columnDefinition = "varchar(29) not null comment '통합코드ID'")
    private String intgCodeId;

    @Id
    @NotNull
    @Column(name="intg_code_val", length = 100, nullable = false, updatable = false,
            columnDefinition = "varchar(100) not null comment '통합코드값'")
    private String intgCodeVal;

    @Column(name="intg_code_val_nm", length = 600, columnDefinition = "varchar(600) not null comment '통합코드값명'")
    private String intgCodeValNm;

    @Column(name="intc_cntn", length = 4000, columnDefinition = "varchar(4000) comment '설명내용'")
    private String intcCntn;
}
