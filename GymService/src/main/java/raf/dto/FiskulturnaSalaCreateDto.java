package raf.dto;



import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class FiskulturnaSalaCreateDto {

    @Length(min = 1, max = 50)
    private String naziv;
    @NotNull
    private int kapacitet;

    private String opis;

    private int brojTrenera;



}
