package com.User.dto;

import javax.validation.constraints.NotBlank;
import java.util.Date;

public class ManagerCreateDto extends AdminCreateDto{
    @NotBlank
    private Date datumZaposljavanja;
    @NotBlank
    private String nazivFisSale;
    @NotBlank
    private Boolean isBanovan;

    public Boolean getBanovan() {
        return isBanovan;
    }

    public void setBanovan(Boolean banovan) {
        isBanovan = banovan;
    }
    public Date getDatumZaposljavanja() {
        return datumZaposljavanja;
    }

    public void setDatumZaposljavanja(Date datumZaposljavanja) {
        this.datumZaposljavanja = datumZaposljavanja;
    }

    public String getNazivFisSale() {
        return nazivFisSale;
    }

    public void setNazivFisSale(String nazivFisSale) {
        this.nazivFisSale = nazivFisSale;
    }
}
