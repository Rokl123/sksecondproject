package com.User.dto;

public class ClientDto extends AdminDto{
    private Integer brojZakazanihTreninga;

    private Integer brojClanskeKartice;

    public Integer getBrojZakazanihTreninga() {
        return brojZakazanihTreninga;
    }

    private Boolean isBanovan;

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
}
