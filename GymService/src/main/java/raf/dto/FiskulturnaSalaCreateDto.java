package raf.dto;



import com.User.domain.Manager;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class FiskulturnaSalaCreateDto {

    @Length(min = 1, max = 50)
    private String naziv;

    private int kapacitet=12;

    private String opis;

    private int brojTrenera;

    @NotNull
    private Long manager_id;

}
