package com.bbm.khodar.service.impl;

import com.bbm.khodar.service.EmailService;
import com.bbm.khodar.utils.EmailUtils;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    @Value("${spring.mail.verify.host}")
    private String host;
    @Value("${spring.mail.username}")
    private String username;
    private final JavaMailSender javaMailSender;

    public void sendEmail(String name, String destination, String eventName) {
        try {

            MimeMessage message = getMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setPriority(1);
            helper.setSubject("Inscricão para o evento: " + eventName);
            helper.setFrom(new InternetAddress(username, "Equipe Técnica - Belmiro Mungoi"));
            helper.setTo(destination);
            helper.setText(EmailUtils.getEmailMessage(name, eventName, host));
            javaMailSender.send(message);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    private MimeMessage getMimeMessage() {
        return javaMailSender.createMimeMessage();
    }
}
