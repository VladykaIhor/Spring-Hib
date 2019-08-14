package com.service;

public interface MailService {

    void sendConfirmationCode(String email, String code);
}
