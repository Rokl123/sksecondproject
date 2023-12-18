package com.User.domain;


import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;
@Entity
@Table(name = "Manager")
public class Manager extends User{
    private Date datumZaposljavanja;

    private String nazivFisSale;
    private Boolean IsBanovan;

    public Boolean getBanovan() {
        return IsBanovan;
    }

    public void setBanovan(Boolean banovan) {
        IsBanovan = banovan;
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
