package com.Notifications.dto;

import com.Notifications.domain.TipNotifikacije;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.Date;

public class NotifikacijeCreateDto {
    @NotNull
    private TipNotifikacije tipNotifikacije;
    @NotNull
    private LocalDateTime vremeSlanja;
    @NotNull
    private String tekst;
    @NotNull
    private String link;

    public TipNotifikacije getTipNotifikacije() {
        return tipNotifikacije;
    }

    public void setTipNotifikacije(TipNotifikacije tipNotifikacije) {
        this.tipNotifikacije = tipNotifikacije;
    }

    public LocalDateTime getVremeSlanja() {
        return vremeSlanja;
    }

    public void setVremeSlanja(LocalDateTime vremeSlanja) {
        this.vremeSlanja = vremeSlanja;
    }

    public String getTekst() {
        return tekst;
    }

    public void setTekst(String tekst) {
        this.tekst = tekst;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
