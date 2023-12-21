package com.User.dto;

import javax.validation.constraints.NotBlank;

public class ClientCreateDto extends AdminCreateDto{
    @NotBlank
    private Integer brojZakazanihTreninga;
    @NotBlank
    private Integer brojClanskeKartice;
    @NotBlank
    private Boolean isBanovan;

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
