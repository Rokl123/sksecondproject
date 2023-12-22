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
       rezervacijaDto.setTrening(rezervacija.getRezervisaniTrening());
       rezervacijaDto.setBrPrijavljenih(rezervacija.getBrPrijavljenih());

       return rezervacijaDto;
    }

    public Rezervacija DtoToDomainObject(RezervacijaCreateDto rezervacijaCreateDto){
       Rezervacija rezervacija = new Rezervacija();
       rezervacija.setBrPrijavljenih(rezervacija.getBrPrijavljenih());
       rezervacija.setRezervisaniTrening(rezervacijaCreateDto.getTrening());


       return rezervacija;
    }
}
