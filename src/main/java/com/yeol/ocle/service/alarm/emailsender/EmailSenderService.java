package com.yeol.ocle.service.alarm.emailsender;

import com.yeol.ocle.comn.consts.OcleConst;
import com.yeol.ocle.config.emailsender.EmailSenderConfig;
import com.yeol.ocle.service.alarm.emailsender.dto.EmailSenderServiceInptDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.Properties;

/**
 * 이메일 발송 서비스
 */

@Slf4j  //lombok logger
@Service
public class EmailSenderService {

    @Autowired
    private EmailSenderConfig emailSenderConfig;

    public void sendEmail(EmailSenderServiceInptDTO inpt) {
        log.info("spring.mail.hosts : " + emailSenderConfig.getHosts());
        log.info("spring.mail.port : " + emailSenderConfig.getPort());
        
        //mail sender 설정
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(emailSenderConfig.getHosts());   //${spring.mail.hosts}
        mailSender.setPort(emailSenderConfig.getPort());    //${spring.mail.port}
        mailSender.setUsername(emailSenderConfig.getUsername());    //${spring.mail.username}
        mailSender.setPassword(emailSenderConfig.getPassword());    //${spring.mail.password}
        
        //mail sender property 설정
        Properties prop = new Properties();
        prop.setProperty("mail.smtp.auth", emailSenderConfig.getSmtpAuth());
        prop.setProperty("mail.smtp.starttls.enable", emailSenderConfig.getSmtpStarttlsEnable());
        mailSender.setJavaMailProperties(prop);
        
        //mail 내용 설정
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(OcleConst.OCLE_MAIL_SENDER);    //발신자
        message.setTo(inpt.getToEmail());   //수신자
        message.setSubject(inpt.getSubject());  //제목
        message.setText(inpt.getContent()); //내용
        
        //mail 발송
        mailSender.send(message);

        log.info("Email Sent Successfully...");
    }
}
