package raf.dto;

import lombok.Getter;
import lombok.Setter;
import raf.domain.FiskulturnaSala;
import raf.domain.TipTreninga;

import java.sql.Time;
import java.util.Date;

@Getter
@Setter
public class TreningDto {

    private Long id;

    private FiskulturnaSala sala;

    private TipTreninga tip;

    private int cenaTreninga;

    private boolean grupni;

    private Date terminTreninga;

    private Time pocetakTermina;

    private Time krajTermina;
}
