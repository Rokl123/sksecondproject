package raf.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import raf.domain.FiskulturnaSala;
import raf.domain.Rezervacija;
import raf.domain.TipTreninga;
import raf.domain.Trening;
import raf.dto.TreningCreateDto;
import raf.dto.TreningDto;
import raf.dto.TreningUpdateDto;
import raf.mapper.TreningMapper;
import raf.repository.FiskulturnaSalaRepository;
import raf.repository.RezervacijaRepository;
import raf.repository.TipTreningaRepository;
import raf.repository.TreningRepository;
import raf.service.TreningService;

@Service
@AllArgsConstructor
public class TreningServiceImpl implements TreningService {
    private RezervacijaRepository rezervacijaRepository;
    private FiskulturnaSalaRepository fiskulturnaSalaRepository;

    private TipTreningaRepository tipTreningaRepository;

    private TreningRepository treningRepository;

    private TreningMapper treningMapper;


    @Override
    public Page<TreningDto> findAll(Pageable pageable) {

        return treningRepository.findAll(pageable).map(treningMapper::DomainObjectToDto);
    }

    @Override
    public TreningDto findById(Long id) {
        Trening trening = treningRepository.findById(id).orElseThrow(()->new RuntimeException());


        return treningMapper.DomainObjectToDto(trening);
    }


    @Override
    public TreningDto add(TreningCreateDto treningCreateDto) {

        FiskulturnaSala fs = fiskulturnaSalaRepository.findById(treningCreateDto.getSala_id()).orElseThrow(()->new RuntimeException());
        TipTreninga tt  = tipTreningaRepository.findById(treningCreateDto.getTip_id()).orElseThrow(()->new RuntimeException());
        treningCreateDto.setSala(fs);
        treningCreateDto.setTip(tt);

        Trening trening = treningMapper.DtoToDomainObject(treningCreateDto);
        treningRepository.save(trening);

        TreningDto treningDto = treningMapper.DomainObjectToDto(trening);

        return treningDto;
    }


    @Override
    public TreningDto update(TreningUpdateDto treningUpdateDto) {
        Trening trening = treningRepository.findById(treningUpdateDto.getId()).orElseThrow(()-> new RuntimeException());
        if(treningUpdateDto.getCenaTreninga() != 0)
        trening.setCenaTreninga(treningUpdateDto.getCenaTreninga());

        trening.setGrupni(treningUpdateDto.isGrupni());
        if(treningUpdateDto.getDatum() != null)
        trening.setTerminTreninga(treningUpdateDto.getDatum());

        if(treningUpdateDto.getPocetakTermina() != null)
        trening.setPocetakTermina(treningUpdateDto.getPocetakTermina());

        if(treningUpdateDto.getKrajTermina() != null)
        trening.setKrajTermina(treningUpdateDto.getKrajTermina());

        treningRepository.save(trening);

        return treningMapper.DomainObjectToDto(trening);
    }

    @Override
    public void deleteById(TreningUpdateDto treningUpdateDto) {

        treningRepository.deleteById(treningUpdateDto.getId());
    }
}
