package com.Notifications.runner;

import com.Notifications.domain.Notifikacija;
import com.Notifications.domain.TipNotifikacije;
import com.Notifications.repository.NotificationRepository;
import com.Notifications.repository.NotificationTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@AllArgsConstructor
@Component
public class TestDataRunner implements CommandLineRunner {

    private NotificationTypeRepository notificationTypeRepository;

    private NotificationRepository notificationRepository;

    @Override
    public void run(String... args) throws Exception {
        TipNotifikacije tipNotifikacije = new TipNotifikacije();
        tipNotifikacije.setNaziv("Aktivacioni email");

        TipNotifikacije tipNotifikacije1 = new TipNotifikacije();
        tipNotifikacije1.setNaziv("Promena lozinke");

        TipNotifikacije tipNotifikacije2 = new TipNotifikacije();
        tipNotifikacije2.setNaziv("Zakazivanje treninga");

        TipNotifikacije tipNotifikacije3 = new TipNotifikacije();
        tipNotifikacije3.setNaziv("Otkazivanje rezervacije za trening");

        TipNotifikacije tipNotifikacije4 = new TipNotifikacije();
        tipNotifikacije4.setNaziv("Otkazivanje treninga");

        TipNotifikacije tipNotifikacije5 = new TipNotifikacije();
        tipNotifikacije4.setNaziv("Azuriranje profila");

        notificationTypeRepository.save(tipNotifikacije);
        notificationTypeRepository.save(tipNotifikacije1);
        notificationTypeRepository.save(tipNotifikacije2);
        notificationTypeRepository.save(tipNotifikacije3);
        notificationTypeRepository.save(tipNotifikacije4);
        notificationTypeRepository.save(tipNotifikacije5);

        Notifikacija notifikacija = new Notifikacija();
        notifikacija.setId(Long.valueOf(1));
        notifikacija.setTipNotifikacije(tipNotifikacije1);
        notifikacija.setLink("nekiLink");
        notifikacija.setText("Neki tekst");
        LocalDateTime today = LocalDateTime.now();
        notifikacija.setDatumSlanja(today);


        Notifikacija notifikacija1 = new Notifikacija();
        notifikacija1.setId(Long.valueOf(2));
        notifikacija1.setTipNotifikacije(tipNotifikacije);
        notifikacija1.setLink("nekiLink1");
        notifikacija1.setText("Neki tekst1");
        notifikacija1.setDatumSlanja(today);

        Notifikacija notifikacija2 = new Notifikacija();
        notifikacija2.setId(Long.valueOf(3));
        notifikacija2.setTipNotifikacije(tipNotifikacije2);
        notifikacija2.setLink("nekiLink2");
        notifikacija2.setText("Neki tekst2");
        notifikacija2.setDatumSlanja(today);

        notificationRepository.save(notifikacija);
        notificationRepository.save(notifikacija1);
        notificationRepository.save(notifikacija2);
    }
}
