package com.Notifications.service.impl;

import com.Notifications.domain.Notifikacija;
import com.Notifications.dto.NotifikacijaDto;
import com.Notifications.dto.NotifikacijeCreateDto;
import com.Notifications.mapper.NotifikacijaMapper;
import com.Notifications.repository.NotificationRepository;
import com.Notifications.service.EmailService;
import com.Notifications.service.NotifikacijaService;
import com.User.domain.Client;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import java.util.Date;
@EnableAsync
@Service
public class NotifikacijaServiceImpl implements NotifikacijaService {

    private NotificationRepository notificationRepository;
    private NotifikacijaMapper notifikacijaMapper;
    private MessageBrokerServiceImpl messageBrokerService;
    private EmailService emailService;
    private Client userData;
    public NotifikacijaServiceImpl(NotificationRepository notificationRepository, NotifikacijaMapper notifikacijaMapper, MessageBrokerServiceImpl messageBrokerService, EmailService emailService,Client userData) {
        this.notificationRepository = notificationRepository;
        this.notifikacijaMapper = notifikacijaMapper;
        this.messageBrokerService = messageBrokerService;
        this.emailService = emailService;
        this.userData = userData;
    }

    @Override
    public NotifikacijaDto add(NotifikacijeCreateDto notifikacijeCreateDto) {
        Notifikacija notifikacija = notifikacijaMapper.notifikacijaCreateDto(notifikacijeCreateDto);
        notificationRepository.save(notifikacija);
        return notifikacijaMapper.notifikacijaToDto(notifikacija);
    }

    @Async
    @Override
    public void posaljiAktivacioniImejl(Notifikacija notifikacija) {
        String subject = String.valueOf(notifikacija.getTipNotifikacije());//Aktivacioni emial
        String messageBody = String.format("Pozdrav, "+userData.getIme() + userData.getPrezime()+" Za verifikaciju posetite sledeći link: %s", notifikacija.getLink());
        messageBrokerService.sendMessage(emailService.sendSimpleMessage(userData.getEmail(), subject, messageBody));
        notificationRepository.save(notifikacija);
    }
    @Async
    @Override
    public void posaljiImejlZaPromenuLozinke(Notifikacija notifikacija) {
        String subject = String.valueOf(notifikacija.getTipNotifikacije());//Promena lozinke
        String messageBody = String.format("Pozdrav, "+userData.getIme() + userData.getPrezime()+ " Za promenu lozinke posetite sledeći link: %s", notifikacija.getLink());
        messageBrokerService.sendMessage(emailService.sendSimpleMessage(userData.getEmail(), subject, messageBody));
        notificationRepository.save(notifikacija);
    }
    @Async
    @Override
    public void posaljiNotifikacijuZakazivanja(Notifikacija notifikacija) {
        String subject = String.valueOf(notifikacija.getTipNotifikacije());//Zakazivanje treninga
        String text = notifikacija.getText();//Trening je uspešno zakazan.
        messageBrokerService.sendMessage(emailService.sendSimpleMessage(String.valueOf(userData.getManager().getEmail()), subject, text));
        messageBrokerService.sendMessage(emailService.sendSimpleMessage(userData.getEmail(), subject, text));
        notificationRepository.save(notifikacija);
    }
    @Async
    @Override
    public void posaljiNotifikacijuOtkazivanja(Notifikacija notifikacija) {
        String subject = String.valueOf(notifikacija.getTipNotifikacije());//Otkazivanje treninga
        String text = notifikacija.getText();//Trening je otkazan.
        messageBrokerService.sendMessage(emailService.sendSimpleMessage(userData.getEmail(), subject, text));
        messageBrokerService.sendMessage( emailService.sendSimpleMessage(String.valueOf(userData.getManager().getEmail()), subject, text));
        notificationRepository.save(notifikacija);
    }
    @Async
    @Override
    public void posaljiPodsetnik(Date datumTreninga,Notifikacija notifikacija) {
        String subject = String.valueOf(notifikacija.getTipNotifikacije());//Podsetnik za trening
        String text = notifikacija.getText();//Vaš trening počinje za 24 sata.

        long razlika = datumTreninga.getTime() - System.currentTimeMillis();
        long jedanDan = 24 * 60 * 60 * 1000; // Milisekunde u danu

        if (razlika <= jedanDan) {
            messageBrokerService.sendMessage(emailService.sendSimpleMessage(userData.getEmail(), subject, text));
            notificationRepository.save(notifikacija);
        }
    }
}
