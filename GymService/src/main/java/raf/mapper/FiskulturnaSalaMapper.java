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
        if(fsDto.getKapacitet() != 0)
        fs.setKapacitet(fsDto.getKapacitet());
        if(fsDto.getBrojTrenera() != 0)
        fs.setBrojTrenera(fsDto.getBrojTrenera());
        if(fsDto.getManager_id() != 0)
        fs.setManager_id(fsDto.getManager_id());

        return fs;
    }

}
