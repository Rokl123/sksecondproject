package raf.service.impl;

import raf.domain.FiskulturnaSala;
import raf.dto.FiskulturnaSalaCreateDto;
import raf.dto.FiskulturnaSalaDto;
import raf.dto.FiskulturnaSalaUpdateDto;
import raf.mapper.FiskulturnaSalaMapper;
import raf.repository.FiskulturnaSalaRepository;
import raf.service.FiskulturnaSalaService;
import lombok.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FiskulturnaSalaServiceImpl implements FiskulturnaSalaService {

    private FiskulturnaSalaRepository fiskulturnaSalaRepository;

    private FiskulturnaSalaMapper fiskulturnaSalaMapper;

    @Override
    public Page<FiskulturnaSalaDto> findAll(Pageable pageable) {
        return fiskulturnaSalaRepository.findAll(pageable).map(fiskulturnaSalaMapper::DomainObjectToDto);
    }

    @Override
    public FiskulturnaSalaDto add(FiskulturnaSalaCreateDto fiskulturnaSalaCreateDto) {
        FiskulturnaSala fs = fiskulturnaSalaMapper.DtoToDomainObject(fiskulturnaSalaCreateDto);
        fiskulturnaSalaRepository.save(fs);

        return fiskulturnaSalaMapper.DomainObjectToDto(fs);

    }

    @Override
    public FiskulturnaSalaDto update(Long id, FiskulturnaSalaUpdateDto fiskulturnaSalaUpdateDto) {
        FiskulturnaSala fs = fiskulturnaSalaRepository.findById(fiskulturnaSalaUpdateDto.getId()).orElseThrow(()-> new RuntimeException());
        if(fiskulturnaSalaUpdateDto.getNaziv()!=null)
        fs.setName(fiskulturnaSalaUpdateDto.getNaziv());
        fs.setOpis(fiskulturnaSalaUpdateDto.getOpis());
        if(fiskulturnaSalaUpdateDto.getKapacitet()!= 0)
        fs.setKapacitet(fiskulturnaSalaUpdateDto.getKapacitet());
        if(fiskulturnaSalaUpdateDto.getBrTrenera() != 0)
        fs.setBrojTrenera(fiskulturnaSalaUpdateDto.getBrTrenera());

        fiskulturnaSalaRepository.save(fs);

        return fiskulturnaSalaMapper.DomainObjectToDto(fs);
    }

    @Override
    public void deleteById(Long id) {
        fiskulturnaSalaRepository.deleteById(id);
    }

    @Override
    public FiskulturnaSalaDto updateManager(FiskulturnaSalaUpdateDto fiskulturnaSalaUpdateDto) {
        FiskulturnaSala fs = fiskulturnaSalaRepository.findById(fiskulturnaSalaUpdateDto.getId()).orElseThrow(()-> new RuntimeException());

        if(fiskulturnaSalaUpdateDto.getBrTrenera() != 0)
            fs.setBrojTrenera(fiskulturnaSalaUpdateDto.getBrTrenera());
        if(fiskulturnaSalaUpdateDto.getKapacitet()!=0)
            fs.setKapacitet(fiskulturnaSalaUpdateDto.getKapacitet());
        fiskulturnaSalaRepository.save(fs);

        return fiskulturnaSalaMapper.DomainObjectToDto(fs);
    }
}
