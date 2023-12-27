package com.User.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
@Getter
@Setter
@Entity
@Table(name = "manager")
public class Manager extends User{
    private Date datumZaposljavanja;
    private String nazivFisSale;
    private Boolean IsBanovan;
    @Column
    @OneToMany(mappedBy = "manager")
    private List<Client> clients;


}
