package com.yeol.ocle.comn.cmdto;

import lombok.Data;

@Data
public class PageNavInfoDTO {
    int startPage;  //시작페이지
    int endPage;    //종료페이지
    int currPage;   //현재페이지
    int totalPage;  //총페이지
}
