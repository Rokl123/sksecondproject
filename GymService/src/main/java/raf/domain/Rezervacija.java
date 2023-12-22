package raf.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Getter
@Setter
@Entity
@Table(name="rezervacije")
public class Rezervacija {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rezervacija_id;

    private int brPrijavljenih; // if brPrijavljenih = kapacitet Trening-a then delete that training from list

    @JdbcTypeCode(SqlTypes.JSON)
    @ManyToOne
    @JoinColumn(name="rezervisaniTrening")
    private Trening rezervisaniTrening;

}
