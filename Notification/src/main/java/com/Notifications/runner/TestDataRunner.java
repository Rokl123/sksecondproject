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
        tipNotifikacije.setId(Long.valueOf(1));

        TipNotifikacije tipNotifikacije1 = new TipNotifikacije();
        tipNotifikacije1.setNaziv("Promena lozinke");
        tipNotifikacije1.setId(Long.valueOf(2));

        TipNotifikacije tipNotifikacije2 = new TipNotifikacije();
        tipNotifikacije2.setNaziv("Zakazivanje treninga");
        tipNotifikacije2.setId(Long.valueOf(3));

        notificationTypeRepository.save(tipNotifikacije);
        notificationTypeRepository.save(tipNotifikacije1);
        notificationTypeRepository.save(tipNotifikacije2);

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
