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
    private Integer brojZakazanihTreninga=0;
    private String brojClanskeKartice;
    private boolean IsBanovan = false;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Manager manager;

}
