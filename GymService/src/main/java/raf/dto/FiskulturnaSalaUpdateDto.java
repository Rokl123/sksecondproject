package raf.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FiskulturnaSalaUpdateDto {
    private Long id;

    private String naziv;

    private int kapacitet;

    private String opis;

    private int brTrenera;

}
