package com.User.dto;

import com.User.domain.Manager;
//import jakarta.validation.constraints.NotNull;

public class ClientCreateDto extends AdminCreateDto{

    private Integer brojZakazanihTreninga;
    //@NotNull
    private String brojClanskeKartice;

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

    public String getBrojClanskeKartice() {
        return brojClanskeKartice;
    }

    public void setBrojClanskeKartice(String brojClanskeKartice) {
        this.brojClanskeKartice = brojClanskeKartice;
    }
}
