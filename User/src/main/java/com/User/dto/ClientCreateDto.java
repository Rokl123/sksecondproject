package com.User.dto;

import com.User.domain.Manager;

import javax.validation.constraints.NotBlank;

public class ClientCreateDto extends AdminCreateDto{

    private Integer brojZakazanihTreninga;

    private Integer brojClanskeKartice;

    private Boolean isBanovan=false;

    private Manager manager;

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public Boolean getBanovan() {
        return isBanovan;
    }

    public void setBanovan(Boolean banovan) {
        isBanovan = banovan;
    }

    public Integer getBrojZakazanihTreninga() {
        return brojZakazanihTreninga;
    }

    public void setBrojZakazanihTreninga(Integer brojZakazanihTreninga) {
        this.brojZakazanihTreninga = brojZakazanihTreninga;
    }

    public Integer getBrojClanskeKartice() {
        return brojClanskeKartice;
    }

    public void setBrojClanskeKartice(Integer brojClanskeKartice) {
        this.brojClanskeKartice = brojClanskeKartice;
    }
}
