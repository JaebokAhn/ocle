package com.yeol.ocle.model.intgmsge;

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
@Table(name = "intg_msge")
public class IntgMsge extends CommonEntity {
    @Id
    @Column(name="intg_msge_id", length=20, columnDefinition="varchar(20) not null comment '통합메시지ID'")
    private String intgMsgeId;

    @Column(name="bswr_dvsn_code", length=10, columnDefinition="varchar(10) default 'comn' not null comment '업무구분코드'")
    private String bswrDvsnCode;

    @Column(name="msge_dvsn_code", length=10, columnDefinition="varchar(10) default 'E' not null comment '메시지구분코드'")
    private String msgeDvsnCode;

    @Column(name="msge_cntn", length=4000, columnDefinition="varchar(4000) not null comment '메시지내용'")  		       
    private String msgeCntn;
}
