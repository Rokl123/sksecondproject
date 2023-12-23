package com.Notifications.service;

import com.Notifications.domain.Notifikacija;
import com.Notifications.dto.*;

import java.util.Date;
import java.util.List;

public interface NotifikacijaService {

    NotifikacijaDto add(NotifikacijeCreateDto notifikacijeCreateDto);

    void posaljiAktivacioniImejl(Notifikacija notifikacija);

    void posaljiImejlZaPromenuLozinke(Notifikacija notifikacija);

    void posaljiNotifikacijuZakazivanja(Notifikacija notifikacija);

    void posaljiNotifikacijuOtkazivanja(Notifikacija notifikacija);

    void posaljiPodsetnik(Date datumTreninga,Notifikacija notifikacija);

}
