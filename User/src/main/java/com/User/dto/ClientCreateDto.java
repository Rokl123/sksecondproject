package com.User.dto;

import com.User.domain.Manager;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Getter;
import lombok.Setter;

//import jakarta.validation.constraints.NotNull;
@Getter
@Setter
public class ClientCreateDto extends AdminCreateDto{

    private Integer brojZakazanihTreninga=0;
    //@NotNull
    @GeneratedValue(strategy = GenerationType.UUID)
    private String  brojClanskeKartice;

    private Boolean isBanovan=false;

    private Manager manager;

}
