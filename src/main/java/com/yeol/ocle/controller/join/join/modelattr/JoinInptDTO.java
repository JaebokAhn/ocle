package com.yeol.ocle.controller.join.join.modelattr;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class JoinInptDTO {
    @Size(min=10, max=40)
    private String username;    //사용자ID
    
    private String password;    //비밀번호
    
    private String passwordCnfm;    //비밀번호 확인

    @Size(min=2)
    private String custNm;  //고객명
}
