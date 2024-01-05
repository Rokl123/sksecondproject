package com.Notifications.listener;

import com.Notifications.domain.Notifikacija;
import com.Notifications.dto.ClientDto;
import com.Notifications.listener.helper.MessageHelper;
import com.Notifications.repository.NotificationRepository;
import com.Notifications.repository.NotificationTypeRepository;
import com.Notifications.service.EmailService;
import com.User.domain.Manager;
import com.User.dto.ManagerDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.JmsException;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.jms.JMSException;
import javax.jms.Message;
import java.time.LocalDateTime;

@Component
@AllArgsConstructor
public class CancelledTrainingListener{

    private MessageHelper messageHelper;

    private EmailService emailService;

    private NotificationRepository notificationRepository;

    private NotificationTypeRepository notificationTypeRepository;

    @JmsListener(destination = "${destination.cancelledTraining}",concurrency = "5-10")
    private void cancelTraining(Message message) throws JMSException {

        ClientDto clientDto = messageHelper.getMessage(message, ClientDto.class);
        Notifikacija notifikacija = new Notifikacija();
        notifikacija.setTipNotifikacije(notificationTypeRepository.findById(4L).orElseThrow(RuntimeException::new));
        notifikacija.setDatumSlanja(LocalDateTime.now());
        notificationRepository.save(notifikacija);

        emailService.sendSimpleMessage(clientDto.getEmail(),"Training cancellation","We are sorry to inform you that your reservation for training" +
                " has been cancelled.");

    }


}
