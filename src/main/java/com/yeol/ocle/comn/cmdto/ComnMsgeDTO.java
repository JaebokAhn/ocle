package com.yeol.ocle.comn.cmdto;

import lombok.Data;

import java.util.List;

@Data
public class ComnMsgeDTO {
    private String prcsRsltCode;    //처리결과코드
    private String msgeCode;    //메세지코드
    private String msgeCntn;    //메세지내용
    private List<ComnMsgeDetlDTO> msgeDetlList; //메시지상세목록
}
