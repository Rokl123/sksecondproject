package com.User.domain;

//import javax.persistence.*;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Client")
@Getter
@Setter
public class Client extends User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long client_id;

    private Integer brojZakazanihTreninga=0;
    private String brojClanskeKartice;
    private boolean IsBanovan = false;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Manager manager;
}
