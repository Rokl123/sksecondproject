package raf.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name="treninzi")
public class Trening {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long trening_id;

    @ManyToOne
    @JoinColumn(name="salaOdrzavanja")
    private FiskulturnaSala sala;

    @ManyToOne
    @JoinColumn(name = "tipTreninga")
    private TipTreninga tip;

    private int cenaTreninga;

    private boolean grupni = false;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date terminTreninga;

    private LocalTime pocetakTermina;

    private LocalTime krajTermina;
}