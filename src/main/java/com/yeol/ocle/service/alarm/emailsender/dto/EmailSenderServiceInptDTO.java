package com.yeol.ocle.service.alarm.emailsender.dto;

import com.yeol.ocle.service.cmdto.ComnServiceInptDTO;
import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import lombok.Data;

@Data
public class EmailSenderServiceInptDTO extends ComnServiceInptDTO {
    @NotNull
    private String toEmail; //수신자

    @NotNull
    private String subject; //제목

    @NotNull
    private String content; //내용
}

