package raf.domain;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "sale")
public class FiskulturnaSala {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sala_id;
    @Column(unique = true)
    private String name;
    @Column(nullable = false)
    private int kapacitet;

    @Nullable
    private String opis;

    @NotNull
    private int brojTrenera;
}
