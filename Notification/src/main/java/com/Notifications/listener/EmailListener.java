package com.Notifications.listener;

import com.Notifications.dto.MatchesDto;
import com.Notifications.listener.helper.MessageHelper;
import com.Notifications.service.EmailService;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;

@Component
public class EmailListener {

    private MessageHelper messageHelper;
    private EmailService emailService;

    public EmailListener(MessageHelper messageHelper, EmailService emailService) {
        this.messageHelper = messageHelper;
        this.emailService = emailService;
    }

    @JmsListener(destination = "${destination.sendEmails}", concurrency = "5-10")
    public void addOrder(Message message) throws JMSException {
        MatchesDto matchesDto = messageHelper.getMessage(message, MatchesDto.class);
        emailService.sendSimpleMessage("nikolajr93og@gmail.com", "subject", matchesDto.toString());
    }
}
