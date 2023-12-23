package com.Notifications.dto;

import javax.validation.constraints.NotNull;

public class TipNotifikacijeCreateDto {
    @NotNull
    private String naziv;

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }
}
