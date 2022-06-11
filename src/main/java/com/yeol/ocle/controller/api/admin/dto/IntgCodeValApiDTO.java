package com.yeol.ocle.controller.api.admin.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class IntgCodeValApiDTO {
    @NotBlank(message = "통합코드ID를 입력하세요.")
    private String IntgCodeId;  //통합코드ID
    
    @NotBlank(message = "통합코드값을 입력하세요.")
    private String IntgCodeVal;  //통합코드값

    @NotBlank(message = "통합코드값명을 입력하세요.")
    private String IntgCodeValNm;  //통합코드값명

    private String intcCntn;    //설명내용
}
