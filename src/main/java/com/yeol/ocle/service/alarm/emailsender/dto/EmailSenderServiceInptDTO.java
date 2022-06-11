package com.yeol.ocle.service.alarm.emailsender.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class EmailSenderServiceInptDTO {
    @NotNull
    private String toEmail; //수신자

    @NotNull
    private String subject; //제목

    @NotNull
    private String content; //내용
}

