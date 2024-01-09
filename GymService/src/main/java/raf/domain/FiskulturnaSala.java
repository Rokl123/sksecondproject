package raf.domain;

import com.User.domain.Manager;
import com.User.domain.User;
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

    private int loyalty;

    @NotNull
    private Long manager_id;

    @Nullable
    private String opis;

    @NotNull
    private int brojTrenera;
}
