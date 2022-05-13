package com.yeol.ocle.service.alarm.emailsender.dto;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import lombok.Data;

@Data
public class EmailSenderServiceInptDTO {
    @NotNull
    private String toEmail; //수신자

    @NotNull
    private String subject; //제목

    @NotNull
    private String content; //내용
}

