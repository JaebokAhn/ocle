package com.yeol.ocle.service.usermgmt.join.dto;

import lombok.Data;

@Data
public class JoinServiceInptDTO {
    private String username;    //사용자ID

    private String password;    //비밀번호

    private String role;    //역할

    private String custNm;  //고객명

    private String joinMthdDvsnCode;    //가입방법구분코드

    private String userAtrtGradCode;    //사용자권한등급코드
}
