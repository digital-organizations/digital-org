package com.engg.digitalorg.api;

public interface EmailApi {
    void sendSimpleMessage(String to, String subject, String text);
}
