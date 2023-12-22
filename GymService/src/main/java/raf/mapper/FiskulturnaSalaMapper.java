package raf.mapper;

import raf.domain.FiskulturnaSala;
import raf.dto.FiskulturnaSalaCreateDto;
import raf.dto.FiskulturnaSalaDto;
import org.springframework.stereotype.Component;

@Component
public class FiskulturnaSalaMapper {

    public FiskulturnaSalaDto DomainObjectToDto(FiskulturnaSala fs){
        FiskulturnaSalaDto fsDto = new FiskulturnaSalaDto();
        fsDto.setId(fs.getSala_id());
        fsDto.setNaziv(fs.getName());
        fsDto.setKapacitet(fs.getKapacitet());
        fsDto.setBrTrenera(fs.getBrojTrenera());
        fsDto.setOpis(fs.getOpis());

        return fsDto;
    }

    public FiskulturnaSala DtoToDomainObject(FiskulturnaSalaCreateDto fsDto){
        FiskulturnaSala fs = new FiskulturnaSala();
        fs.setName(fsDto.getNaziv());
        fs.setOpis(fsDto.getOpis());
        fs.setKapacitet(fsDto.getKapacitet());
        fs.setBrojTrenera(fsDto.getBrojTrenera());

        return fs;
    }

}
