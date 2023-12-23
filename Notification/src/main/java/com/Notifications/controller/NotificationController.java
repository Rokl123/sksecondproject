package com.Notifications.controller;

import com.Notifications.domain.Notifikacija;
import com.Notifications.dto.*;
import com.Notifications.security.CheckSecurity;
import com.Notifications.service.NotifikacijaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    private final NotifikacijaService notificationService;

    public NotificationController(NotifikacijaService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping
    @CheckSecurity(roles={"ROLE_ADMIN"})
    public ResponseEntity<NotifikacijaDto> addNotification(@RequestBody NotifikacijeCreateDto NotifikacijeCreateDto) {
        return new ResponseEntity<>(notificationService.add(NotifikacijeCreateDto), HttpStatus.CREATED);

    }

    @PostMapping("/send-activation-email")
    public ResponseEntity<String> sendActivationEmail(@RequestBody Notifikacija notifikacija) {
        notificationService.posaljiAktivacioniImejl(notifikacija);
        return ResponseEntity.ok("Activation email sent successfully.");
    }

    @PostMapping("/send-password-change-email")
    public ResponseEntity<String> sendPasswordChangeEmail(@RequestBody Notifikacija notifikacija) {
        notificationService.posaljiImejlZaPromenuLozinke(notifikacija);
        return ResponseEntity.ok("Password change email sent successfully.");
    }

    @PostMapping("/send-schedule-notification")
    public ResponseEntity<String> sendScheduleNotification(@RequestBody Notifikacija notifikacija) {
        notificationService.posaljiNotifikacijuZakazivanja(notifikacija);
        return ResponseEntity.ok("Schedule notification sent successfully.");
    }

    @PostMapping("/send-cancellation-notification")
    public ResponseEntity<String> sendCancellationNotification(@RequestBody Notifikacija notifikacija) {
        notificationService.posaljiNotifikacijuOtkazivanja(notifikacija);
        return ResponseEntity.ok("Cancellation notification sent successfully.");
    }

    @PostMapping("/send-reminder")
    public ResponseEntity<String> sendReminder(@RequestBody Date trainingDate,@RequestBody Notifikacija notifikacija) {
        notificationService.posaljiPodsetnik(trainingDate,notifikacija);
        return ResponseEntity.ok("Reminder sent successfully.");
    }
}

