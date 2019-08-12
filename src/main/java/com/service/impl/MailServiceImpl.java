package com.service.impl;

import com.service.CartService;
import com.service.MailService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.Session;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class MailServiceImpl implements MailService {

    private static final Logger logger = Logger.getLogger(MailServiceImpl.class);
    private static final String EMAIL_LOGIN = "yngwar95@gmail.com";
    private static final String PASSWORD = "y8132781327";

    @Autowired
    private final CartService cartService;

    public MailServiceImpl(CartService cartService) {
        this.cartService = cartService;
    }

    @Transactional
    @Override
    public void sendConfirmationCode(String email, String code) {
        try {
            final String username = "test123test178@gmail.com";
            final String password = "Svistylin123";
            Properties prop = new Properties();
            prop.put("mail.smtp.host", "smtp.gmail.com");
            prop.put("mail.smtp.port", "587");
            prop.put("mail.smtp.auth", "true");
            prop.put("mail.smtp.starttls.enable", "true"); //TLS
            Session session = Session.getInstance(prop,
                    new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(username, password);
                        }
                    });
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("test123test178@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject("Testing Gmail TLS");
            message.setText("Dear user please enter this code  " + code);
            Transport.send(message);
        } catch (AddressException e) {
            logger.info("incorrect adress", e);
        } catch (MessagingException e) {
            logger.info("Some problems happened", e);
        }
    }
}
