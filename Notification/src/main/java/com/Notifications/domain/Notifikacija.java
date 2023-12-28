package com.Notifications.domain;
import com.User.domain.Client;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import javax.persistence.*;
import java.time.LocalDateTime;
@Getter
@Setter
@Entity
@Table(name = "notifikacija")
public class Notifikacija {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tipNotifikacije")
    private TipNotifikacije tipNotifikacije;

    private String text;

    private String link;

    @Column(name = "datum_slanja")
    private LocalDateTime datumSlanja;

//    @ManyToOne
//    @JoinColumn(name = "client")
//    private Client client;
}
