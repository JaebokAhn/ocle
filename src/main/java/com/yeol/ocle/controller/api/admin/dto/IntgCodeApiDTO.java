package com.yeol.ocle.controller.api.admin.dto;

import com.yeol.ocle.comn.cmdto.ComnApiDTO;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class IntgCodeApiDTO extends ComnApiDTO {
    @NotBlank(message = "통합코드ID를 입력하세요.")
    private String intgCodeId;  //통합코드ID

    @NotBlank(message = "통합코드명을 입력하세요.")
    private String intgCodeNm;  //통합코드명

    //설명내용
    private String intcCntn;
}
