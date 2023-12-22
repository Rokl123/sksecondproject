package raf.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import raf.dto.RezervacijaCreateDto;
import raf.dto.RezervacijaDto;
import raf.dto.RezervacijaUpdateDto;


public interface RezervacijaService {
    Page<RezervacijaDto> findAll(Pageable pageable); //read

    RezervacijaDto add(RezervacijaCreateDto rezervacijaCreateDto); //create

    RezervacijaDto update(RezervacijaUpdateDto rezervacijaUpdateDto);

    void deleteById(RezervacijaUpdateDto rezervacijaUpdateDto); //delete
}
