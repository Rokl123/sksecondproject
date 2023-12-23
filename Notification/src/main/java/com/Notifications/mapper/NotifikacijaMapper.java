package com.Notifications.mapper;

import com.Notifications.domain.Notifikacija;
import com.Notifications.dto.NotifikacijaDto;
import com.Notifications.dto.NotifikacijeCreateDto;
import org.springframework.stereotype.Component;

@Component
public class NotifikacijaMapper {

    public NotifikacijaDto notifikacijaToDto(Notifikacija notifikacija){
        NotifikacijaDto notifikacijaDto = new NotifikacijaDto();
        notifikacijaDto.setId(notifikacija.getId());
        notifikacijaDto.setTekst(notifikacijaDto.getTekst());
        notifikacijaDto.setLink(notifikacijaDto.getLink());
        notifikacijaDto.setTipNotifikacije(notifikacija.getTipNotifikacije());
        notifikacijaDto.setVremeSlanja(notifikacija.getDatumSlanja());
        return notifikacijaDto;
    }

    public Notifikacija notifikacijaCreateDto(NotifikacijeCreateDto nCDto){
        Notifikacija notifikacija = new Notifikacija();
        notifikacija.setLink(nCDto.getLink());
        notifikacija.setText(nCDto.getTekst());
        notifikacija.setDatumSlanja(nCDto.getVremeSlanja());
        notifikacija.setTipNotifikacije(nCDto.getTipNotifikacije());
        return notifikacija;
    }
}
