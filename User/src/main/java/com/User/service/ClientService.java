package com.User.service;

import com.User.domain.Client;
import com.User.dto.ClientCreateDto;
import com.User.dto.ClientDto;
import com.User.dto.TokenRequestDto;
import com.User.dto.TokenResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClientService {
    Page<ClientDto> findAll(Pageable pageable);
    ClientDto findById(Long id);

    ClientDto add(ClientCreateDto clientCreateDto);

    TokenResponseDto login(TokenRequestDto tokenRequestDto);

    void updateProfile(Long id, Client updatedProfile);

    ClientDto addReservation(Long id);

}
