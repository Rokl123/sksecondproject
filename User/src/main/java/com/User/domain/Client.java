package com.User.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "Client")
public class Client extends User{
    private Integer brojZakazanihTreninga;

    private Integer brojClanskeKartice;
    private Boolean IsBanovan;
    private Long id;


    public Boolean getBanovan() {
        return IsBanovan;
    }

    public void setBanovan(Boolean banovan) {
        IsBanovan = banovan;
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

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    public Long getId() {
        return id;
    }
}
