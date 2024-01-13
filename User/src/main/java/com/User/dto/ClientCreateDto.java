package com.User.dto;

import com.User.domain.Manager;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Getter;
import lombok.Setter;

import java.util.Random;
import java.util.UUID;

//import jakarta.validation.constraints.NotNull;
@Getter
@Setter
public class ClientCreateDto extends AdminCreateDto{

    private Integer brojZakazanihTreninga=0;
    //@NotNull

    private String brojClanskeKartice = createKartu();

    private Boolean isBanovan=false;

    private Manager manager;

    private String createKartu(){

        String karta = "";
        Random r = new Random();
        karta = String.valueOf(r.nextInt(1000000000,2147483647));

        return karta;
    }
}
