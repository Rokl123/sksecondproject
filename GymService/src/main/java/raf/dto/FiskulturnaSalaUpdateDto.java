package raf.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FiskulturnaSalaUpdateDto {
    private Long sala_id;

    private String name;

    private int kapacitet;

    private String opis;

    private int brojTrenera;

    private int loyalty;

    private Long manager_id;

}
