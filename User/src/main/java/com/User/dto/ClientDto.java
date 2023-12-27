package com.User.dto;

import com.User.domain.Manager;

public class ClientDto extends AdminDto{
    private Integer brojZakazanihTreninga;

    private Integer brojClanskeKartice;



    private Boolean isBanovan;

    private Manager manager;
    public Integer getBrojZakazanihTreninga() {
        return brojZakazanihTreninga;
    }

    public Boolean getBanovan() {
        return isBanovan;
    }

    public void setBanovan(Boolean banovan) {
        isBanovan = banovan;
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

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }
}
