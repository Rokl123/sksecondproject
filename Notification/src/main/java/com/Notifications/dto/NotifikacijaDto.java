package com.Notifications.dto;

import com.Notifications.domain.TipNotifikacije;

import java.time.LocalDateTime;
import java.util.Date;

public class NotifikacijaDto {
    private Long id;
    private TipNotifikacije tipNotifikacije;
    private LocalDateTime vremeSlanja;

    private String tekst;

    private String link;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
