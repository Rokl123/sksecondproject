package com.User.dto;

import com.User.domain.Manager;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientUpdateDto {

    private String ime;

    private String prezime;

    private String username;

    private String password;
    private String email;
}
