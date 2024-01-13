package raf.mapper;

import org.springframework.stereotype.Component;
import raf.domain.Rezervacija;
import raf.dto.RezervacijaCreateDto;
import raf.dto.RezervacijaDto;

@Component
public class RezervacijaMapper {
    public RezervacijaDto DomainObjectToDto(Rezervacija rezervacija){
       RezervacijaDto rezervacijaDto = new RezervacijaDto();
       rezervacijaDto.setId(rezervacija.getRezervacija_id());
       rezervacijaDto.setCenaTreninga(rezervacija.getCenaTreninga());
       rezervacijaDto.setTrening(rezervacija.getRezervisaniTrening());
       rezervacijaDto.setClient_id(rezervacija.getClientID());
       return rezervacijaDto;
    }

    public Rezervacija DtoToDomainObject(RezervacijaCreateDto rezervacijaCreateDto){
       Rezervacija rezervacija = new Rezervacija();
       rezervacija.setClientID(rezervacijaCreateDto.getClient_id());
       rezervacija.setRezervisaniTrening(rezervacijaCreateDto.getTrening());

       return rezervacija;
    }
}
