package raf.dto;

import lombok.Getter;
import lombok.Setter;
import raf.domain.Trening;
@Getter
@Setter
public class RezervacijaDto {

    private Long id;

    private int brPrijavljenih;

    private Trening trening;

    private int cenaTreninga;

    private Long client_id;

}
