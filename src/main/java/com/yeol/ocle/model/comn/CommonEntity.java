package com.yeol.ocle.model.comn;

import com.yeol.ocle.comn.consts.OcleConst;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import java.sql.Timestamp;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class CommonEntity {
    @CreatedBy
    @Column(name="rgsr_id", nullable = false, updatable = false, length = 100,
            columnDefinition = "varchar(100) not null comment '등록자ID'")
    private String rgsrId;

    @CreatedDate
    @Column(name="rgsr_dttm", nullable = false, updatable = false,
            columnDefinition = "datetime(6) default CURRENT_TIMESTAMP not null comment '등록일시'")
    private Timestamp rgsrDttm;

    @LastModifiedBy
    @Column(name="last_chnr_id", length = 100,
            columnDefinition = "varchar(100) not null comment '최종변경자ID'")
    private String lastChnrId;

    @LastModifiedDate
    @Column(name="last_chng_dttm",
            columnDefinition = "datetime(6) default CURRENT_TIMESTAMP not null comment '최종변경일시'")
    private Timestamp lastChngDttm;
    
    @Column(name="dltn_yn",
            columnDefinition = "varchar(1) default 'N' not null comment '삭제여부'")
    private String dltnYn;

    @PrePersist
    public void prePersist() {
        //삭제여부 : default N
        dltnYn = OcleConst.GENERAL_N;
    }
}
