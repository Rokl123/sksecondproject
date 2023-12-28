package com.User.domain;

//import javax.persistence.*;


import jakarta.persistence.*;

@Entity
@Table(name = "Client")
public class Client extends User{
    private Integer brojZakazanihTreninga;
    private Integer brojClanskeKartice;
    private boolean IsBanovan = false;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Manager manager;

    public boolean getBanovan() {
        return IsBanovan;
    }

    public void setBanovan(boolean banovan) {
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

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }
}
