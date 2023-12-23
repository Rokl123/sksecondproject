package com.Notifications.service.impl;

import com.Notifications.domain.Notifikacija;
import com.Notifications.domain.TipNotifikacije;
import com.Notifications.dto.NotifikacijaDto;
import com.Notifications.dto.NotifikacijaFilterDto;
import com.Notifications.dto.NotifikacijeCreateDto;
import com.Notifications.mapper.NotifikacijaMapper;
import com.Notifications.repository.NotificationRepository;
import com.Notifications.service.EmailService;
import com.Notifications.service.NotifikacijaService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class NotifikacijaServiceImpl implements NotifikacijaService {

    private NotificationRepository notificationRepository;

    private NotifikacijaMapper notifikacijaMapper;
    private MessageBrokerServiceImpl messageBrokerService;
    private EmailService emailService;

    String userData;//Treba da stoji korisnik klasa da izvlacim njegove podatke preko getMetode()
    String managerData;//Treba da stoji manger kalsa da izvlacim njegove podatke preko getMetode()

    public NotifikacijaServiceImpl(NotificationRepository notificationRepository, NotifikacijaMapper notifikacijaMapper, MessageBrokerServiceImpl messageBrokerService, EmailService emailService) {
        this.notificationRepository = notificationRepository;
        this.notifikacijaMapper = notifikacijaMapper;
        this.messageBrokerService = messageBrokerService;
        this.emailService = emailService;
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
        String messageBody = String.format("Pozdrav,userData.getIme() userdataGetPrezime() Za verifikaciju posetite sledeći link: %s", notifikacija.getLink());
        messageBrokerService.sendMessage(emailService.sendSimpleMessage(userData, subject, messageBody));
        notificationRepository.save(notifikacija);
    }
    @Async
    @Override
    public void posaljiImejlZaPromenuLozinke(Notifikacija notifikacija) {
        String subject = String.valueOf(notifikacija.getTipNotifikacije());//Promena lozinke
        String messageBody = String.format("Pozdrav,userData.getIme() userdataGetPrezime()  Za promenu lozinke posetite sledeći link: %s", notifikacija.getLink());

        // Slanje emaila koristeci EmailService
        //umesto userData treba da pise userData.getEmail()
        messageBrokerService.sendMessage(emailService.sendSimpleMessage(userData, subject, messageBody));
        notificationRepository.save(notifikacija);
    }
    @Async
    @Override
    public void posaljiNotifikacijuZakazivanja(Notifikacija notifikacija) {
        String subject = String.valueOf(notifikacija.getTipNotifikacije());//Zakazivanje treninga
        String text = notifikacija.getText();//Trening je uspešno zakazan.
        messageBrokerService.sendMessage(emailService.sendSimpleMessage(managerData, subject, text));
        messageBrokerService.sendMessage(emailService.sendSimpleMessage(userData, subject, text));
        notificationRepository.save(notifikacija);
    }
    @Async
    @Override
    public void posaljiNotifikacijuOtkazivanja(Notifikacija notifikacija) {
        String subject = String.valueOf(notifikacija.getTipNotifikacije());//Otkazivanje treninga
        String text = notifikacija.getText();//Trening je otkazan.
        messageBrokerService.sendMessage(emailService.sendSimpleMessage(userData, subject, text));
        messageBrokerService.sendMessage( emailService.sendSimpleMessage(managerData, subject, text));
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
            messageBrokerService.sendMessage(emailService.sendSimpleMessage(userData, subject, text));
            notificationRepository.save(notifikacija);
        }
    }
}
