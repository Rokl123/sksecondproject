package raf.service.impl;

import com.User.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import raf.domain.Rezervacija;
import raf.domain.Trening;
import raf.dto.RezervacijaCreateDto;
import raf.dto.RezervacijaDto;
import raf.dto.RezervacijaUpdateDto;
import raf.mapper.RezervacijaMapper;
import raf.repository.RezervacijaRepository;
import raf.repository.TreningRepository;
import raf.service.RezervacijaService;
@Service
@AllArgsConstructor
public class RezervacijaServiceImpl implements RezervacijaService {

    private RezervacijaRepository rezervacijaRepository;

    private RezervacijaMapper rezervacijaMapper;

    private TreningRepository treningRepository;

    @Override
    public Page<RezervacijaDto> findAll(Pageable pageable) {
        return rezervacijaRepository.findAll(pageable).map(rezervacijaMapper::DomainObjectToDto);
    }

    @Override
    public Page<RezervacijaDto> findByClientId(Pageable pageable,Long client_id) {

        return rezervacijaRepository.findAllByClientID(pageable,client_id).map(rezervacijaMapper::DomainObjectToDto);
    }

    @Override

    public RezervacijaDto add(RezervacijaCreateDto rezervacijaCreateDto)
    {
        rezervacijaCreateDto.setTrening(treningRepository.findById(rezervacijaCreateDto.getTrening_id()).orElseThrow(RuntimeException::new));
        Rezervacija rezervacija = rezervacijaMapper.DtoToDomainObject(rezervacijaCreateDto);
        for(Rezervacija r: rezervacijaRepository.findAll()){
            if(r.getClientID().equals(rezervacija.getClientID()) && r.getRezervisaniTrening().equals(rezervacija.getRezervisaniTrening()) &&
                    (r.getRezervisaniTrening().getTerminTreninga().equals(rezervacija.getRezervisaniTrening().getTerminTreninga())
                    && r.getRezervisaniTrening().getPocetakTermina().equals(rezervacija.getRezervisaniTrening().getPocetakTermina()))
                    &&  r.getRezervisaniTrening().getKrajTermina().equals(rezervacija.getRezervisaniTrening().getKrajTermina())){
                return null;
            }
        }
        Trening trening = treningRepository.findById(rezervacijaCreateDto.getTrening_id()).orElseThrow(RuntimeException::new);
        trening.setBrRezervacija(trening.getBrRezervacija()+1);
        rezervacija.setCenaTreninga(rezervacijaCreateDto.getCenaTreninga());
        rezervacijaRepository.save(rezervacija);
        RezervacijaDto rDto = rezervacijaMapper.DomainObjectToDto(rezervacija);
        return rDto;
    }

    @Override
    public RezervacijaDto update(RezervacijaUpdateDto rezervacijaUpdateDto) {
        Rezervacija rezervacija = rezervacijaRepository.findById(rezervacijaUpdateDto.getId()).orElseThrow(RuntimeException::new);

        return rezervacijaMapper.DomainObjectToDto(rezervacija);
    }

    @Override
    public void deleteById(RezervacijaUpdateDto rezervacijaUpdateDto) {
        rezervacijaRepository.deleteById(rezervacijaUpdateDto.getId());
    }
}
