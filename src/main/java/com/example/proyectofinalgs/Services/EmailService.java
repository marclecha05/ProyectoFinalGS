package com.example.proyectofinalgs.Services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void enviarCorreo(String destinatario, String asunto, String contenido) throws MessagingException {
        MimeMessage mensaje = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mensaje, true);
        helper.setFrom("turnify2025@gmail.com");
        helper.setTo(destinatario);
        helper.setSubject(asunto);
        helper.setText(contenido, true); //true para HTML
        mailSender.send(mensaje);
    }
}
