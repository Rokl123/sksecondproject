package raf.dto;

import lombok.Getter;
import lombok.Setter;
import raf.domain.Trening;
@Getter
@Setter
public class RezervacijaCreateDto {

    private int brPrijavljenih=0;

    private Trening trening;

}
