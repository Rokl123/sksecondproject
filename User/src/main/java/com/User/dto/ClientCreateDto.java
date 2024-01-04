package com.User.dto;

import com.User.domain.Manager;
import lombok.Getter;
import lombok.Setter;

//import jakarta.validation.constraints.NotNull;
@Getter
@Setter
public class ClientCreateDto extends AdminCreateDto{

    private Integer brojZakazanihTreninga=0;
    //@NotNull
    private String  brojClanskeKartice;

    private Boolean isBanovan=false;

    private Manager manager;

}
