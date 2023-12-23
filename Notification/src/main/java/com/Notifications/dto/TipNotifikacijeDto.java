package com.Notifications.dto;

import java.util.List;

public class TipNotifikacijeDto {

    private Long id;
    private String naziv;

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
