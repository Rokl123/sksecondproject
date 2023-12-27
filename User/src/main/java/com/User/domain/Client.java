package com.User.domain;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "client")
public class Client extends User{
    private Integer brojZakazanihTreninga;
    private Integer brojClanskeKartice;
    private boolean IsBanovan = false;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Manager manager;

//    @Column
//    @OneToMany(mappedBy = "notifikacija")
//    private List<Notifikacija> notifikacija;


}
