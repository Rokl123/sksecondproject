package com.User.domain;


import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Manager")
public class Manager extends User{
    private Date datumZaposljavanja;
    private String nazivFisSale;
    private Boolean IsBanovan;
    @Column
    @OneToMany(mappedBy = "manager")
    private List<Client> clients;

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

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

}
