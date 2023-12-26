package raf.mapper;

import com.User.repository.ManagerRepository;
import lombok.AllArgsConstructor;
import raf.domain.FiskulturnaSala;
import raf.dto.FiskulturnaSalaCreateDto;
import raf.dto.FiskulturnaSalaDto;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
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
        fs.setManager_id(fsDto.getManager_id());

        return fs;
    }

}
