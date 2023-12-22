package raf.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import raf.domain.Rezervacija;
import raf.dto.RezervacijaCreateDto;
import raf.dto.RezervacijaDto;
import raf.dto.RezervacijaUpdateDto;
import raf.mapper.RezervacijaMapper;
import raf.repository.RezervacijaRepository;
import raf.service.RezervacijaService;
@Service
@AllArgsConstructor
public class RezervacijaServiceImpl implements RezervacijaService {

    private RezervacijaRepository rezervacijaRepository;

    private RezervacijaMapper rezervacijaMapper;

    @Override
    public Page<RezervacijaDto> findAll(Pageable pageable) {


        return rezervacijaRepository.findAll(pageable).map(rezervacijaMapper::DomainObjectToDto);
    }

    @Override

    public RezervacijaDto add(RezervacijaCreateDto rezervacijaCreateDto)
    {
        Rezervacija rezervacija = rezervacijaMapper.DtoToDomainObject(rezervacijaCreateDto);
        rezervacijaRepository.save(rezervacija);
        RezervacijaDto rDto = rezervacijaMapper.DomainObjectToDto(rezervacija);
        return rDto;
    }

    @Override
    public RezervacijaDto update(RezervacijaUpdateDto rezervacijaUpdateDto) {
        Rezervacija rezervacija = rezervacijaRepository.findById(rezervacijaUpdateDto.getId()).orElseThrow(() -> new RuntimeException());
        rezervacija.setBrPrijavljenih(rezervacijaUpdateDto.getBrPrijavljenih());

        return rezervacijaMapper.DomainObjectToDto(rezervacija);
    }

    @Override
    public void deleteById(RezervacijaUpdateDto rezervacijaUpdateDto) {
        rezervacijaRepository.deleteById(rezervacijaUpdateDto.getId());
    }
}
