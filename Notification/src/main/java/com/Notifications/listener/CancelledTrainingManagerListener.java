package com.Notifications.listener;

import com.Notifications.domain.Notifikacija;
import com.Notifications.listener.helper.MessageHelper;
import com.Notifications.repository.NotificationRepository;
import com.Notifications.repository.NotificationTypeRepository;
import com.Notifications.service.EmailService;
import com.User.dto.ManagerDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.jms.JMSException;
import javax.jms.Message;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
@AllArgsConstructor
public class CancelledTrainingManagerListener {

    private MessageHelper messageHelper;

    private RestTemplate userServiceRestTemplate;

    private EmailService emailService;

    private NotificationRepository notificationRepository;

    private NotificationTypeRepository notificationTypeRepository;


    @JmsListener(destination = "${destination.cancelledTrainingManager}",concurrency = "5-10")
    private void cancelTrainingManager(Message message) throws JMSException {
        Long manager_id = messageHelper.getMessage(message,Long.class);

        ResponseEntity<ManagerDto> managerDtoResponseEntity =null;
        ManagerDto managerDto = null;
        try{
            managerDtoResponseEntity = userServiceRestTemplate.exchange("/manager/" + manager_id + "/getManager", HttpMethod.GET,null, ManagerDto.class);

            managerDto = managerDtoResponseEntity.getBody();
            Notifikacija notifikacija = new Notifikacija();
            notifikacija.setDatumSlanja(LocalDateTime.now());
            notifikacija.setTipNotifikacije(notificationTypeRepository.findById(4L).orElseThrow(RuntimeException::new));
            emailService.sendSimpleMessage(managerDto.getEmail(),"Training has been cancelled","Training has been successfully cancelled and users are informed about it!");
        }
        catch (HttpClientErrorException e){
            throw new JMSException(String.format("Manager with %d id has not been found!",manager_id));
        }

    }
}
