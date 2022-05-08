package com.yeol.ocle.config.emailsender;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class EmailSenderConfig {
    @Value("${spring.mail.hosts}")
    private String hosts;

    @Value("${spring.mail.port}")
    private int port;

    @Value("${spring.mail.username}")
    private String username;

    @Value("${spring.mail.password}")
    private String password;

    @Value("${spring.mail.properties.mail.smtp.auth}")
    private String smtpAuth;

    @Value("${spring.mail.properties.mail.smtp.starttls.enable}")
    private String smtpStarttlsEnable;
}
