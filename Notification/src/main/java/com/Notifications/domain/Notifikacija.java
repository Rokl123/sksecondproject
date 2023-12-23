package com.Notifications.domain;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Notifikacija")
public class Notifikacija {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tip_notifikacije_id")
    private TipNotifikacije tipNotifikacije;

    private String text;

    private String link;

    @Column(name = "datum_slanja")
    @Convert(converter = Jsr310JpaConverters.LocalDateTimeConverter.class)
    private LocalDateTime datumSlanja;


//    @ManyToOne
//    @JoinColumn(name = "klijent_id")
//    private Klijent klijent;
//
//    @ManyToOne
//    @JoinColumn(name = "menadzer_id")
//    private Menadzer menadzer;


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipNotifikacije getTipNotifikacije() {
        return tipNotifikacije;
    }

    public void setTipNotifikacije(TipNotifikacije tipNotifikacije) {
        this.tipNotifikacije = tipNotifikacije;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public LocalDateTime getDatumSlanja() {
        return datumSlanja;
    }

    public void setDatumSlanja(LocalDateTime datumSlanja) {
        this.datumSlanja = datumSlanja;
    }
}
