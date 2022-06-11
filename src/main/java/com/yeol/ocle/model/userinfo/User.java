package com.yeol.ocle.model.userinfo;

import com.yeol.ocle.model.comn.CommonEntity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@Entity
@Table(name = "user")
public class User extends CommonEntity {
    @Id
    @NotNull
    @Column(length = 100, updatable = false,
            columnDefinition = "varchar(100) not null comment '사용자ID'")
    private String username;    //사용자ID

    @NotNull
    @Column(length = 500, columnDefinition = "varchar(500) not null comment '비밀번호'")
    private String password;    //비밀번호

    @NotNull
    @Column(length = 20, columnDefinition = "varchar(20) default 'ROLE_CONSUMER' not null comment '사용자역할코드 ROLE_CONSUMER, ROLE_SUPPLIER, ROLE_ADMIN'")
    private String role;    //ROLE_CONSUMER, ROLE_SUPPLIER, ROLE_ADMIN

    @Column(name="cust_nm", length = 100, columnDefinition = "varchar(100) comment '고객명'")
    private String custNm;

    @Column(name="join_mthd_dvsn_code", length = 1, columnDefinition = "varchar(1) default '1' not null comment '가입방법구분코드'")
    private String joinMthdDvsnCode;

    @Column(name="user_atrt_grad_code", length = 1, columnDefinition = "varchar(3) default '001' not null comment '사용자권한등급코드'")
    private String userAtrtGradCode;
}
