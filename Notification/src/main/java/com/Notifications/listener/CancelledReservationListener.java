package com.Notifications.listener;


import com.Notifications.domain.Notifikacija;
import com.Notifications.dto.ClientDto;
import com.Notifications.listener.helper.MessageHelper;
import com.Notifications.repository.NotificationRepository;
import com.Notifications.repository.NotificationTypeRepository;
import com.Notifications.service.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.jms.JMSException;
import javax.jms.Message;
import java.time.LocalDateTime;

@Component
@AllArgsConstructor
public class CancelledReservationListener {

    private MessageHelper messageHelper;

    private JmsTemplate jmsTemplate;

    private RestTemplate userServiceRestTemplate;

    private EmailService emailService;

    private NotificationTypeRepository notificationTypeRepository;

    private NotificationRepository notificationRepository;

    @JmsListener(destination = "${destination.cancelledReservation}",concurrency = "5-10")
    private void reservationIsCancelled(Message message) throws JMSException {

        Long id = messageHelper.getMessage(message,Long.class);

        ResponseEntity<ClientDto> clientDtoResponse = null;
        ClientDto clientDto = null;
        try{
            clientDtoResponse = userServiceRestTemplate.exchange("/client/" + id + "/getClient", HttpMethod.GET,null, ClientDto.class);

            clientDto = clientDtoResponse.getBody();
            emailService.sendSimpleMessage(clientDto.getEmail(),"Cancelled Reservation","Your reservation is successfully cancelled!");
            Notifikacija notifikacija = new Notifikacija();
            notifikacija.setDatumSlanja(LocalDateTime.now());
            notifikacija.setTipNotifikacije(notificationTypeRepository.findById(3L).orElseThrow(RuntimeException::new));
            notifikacija.setText(String.format("Your reservation is successfully cancelled!, for client with %d id.",clientDto.getId()));
            notificationRepository.save(notifikacija);
        }
        catch(HttpClientErrorException e){
            throw new RuntimeException(String.format("Client with id: %d has not been found!",id));
        }



    }

}
