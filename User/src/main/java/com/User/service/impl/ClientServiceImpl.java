package com.User.service.impl;


import com.User.domain.Client;
import com.User.dto.*;
import com.User.exception.NotFoundException;
import com.User.mapper.ClientMapper;
import com.User.repository.ClientRepository;
import com.User.security.service.TokenService;
import com.User.service.ClientService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {

    private TokenService tokenService;
    private ClientRepository clientRepository;
    private ClientMapper clientMapper;
    @Override
    public Page<ClientDto> findAll(Pageable pageable) {
        return clientRepository.findAll(pageable)
                .map(entity -> {
                    if (entity != null) {
                        return clientMapper.clientToClientDto(entity);
                    }
                    throw new NotFoundException("Expected Client entity but found different entity type.");
                });
    }

    @Override
    public ClientDto findById(Long id) {
        Client client = clientRepository.findById(id).orElseThrow(RuntimeException::new);

        return clientMapper.clientToClientDto(client);
    }


    @Override
    public ClientDto add(ClientCreateDto clientCreateDto) {
        Client client =  clientMapper.clientCreateDtoToClient(clientCreateDto);
        clientRepository.save(client);
        return  clientMapper.clientToClientDto(client);
    }

    @Override
    public TokenResponseDto login(TokenRequestDto tokenRequestDto) {
        Client client =  clientRepository
                .findUserByUsernameAndPassword(tokenRequestDto.getUsername(), tokenRequestDto.getPassword())
                .orElseThrow(() -> new NotFoundException(String
                        .format("Client with username: %s and password: %s not found.", tokenRequestDto.getUsername(),
                                tokenRequestDto.getPassword())));

        Claims claims = Jwts.claims();
        claims.put("id", client.getId());
        claims.put("role", client.getRole().getName());
        //Generate token
        return new TokenResponseDto(tokenService.generate(claims));
    }

    @Override
    public void updateProfile(Long id, ClientUpdateDto updatedProfile) {
        Client client = clientRepository.findById(id).orElseThrow(RuntimeException::new);

        client.setUsername(updatedProfile.getUsername());
        client.setPassword(updatedProfile.getPassword());
        client.setEmail(updatedProfile.getEmail());
        client.setIme(updatedProfile.getIme());
        client.setPrezime(updatedProfile.getPrezime());


        clientRepository.save(client);
    }

    @Override
    public ClientDto addReservation(Long id) {
        Client client =clientRepository.findById(id).orElseThrow(RuntimeException::new);
        client.setBrojZakazanihTreninga(client.getBrojZakazanihTreninga()+1);
        clientRepository.save(client);
        return clientMapper.clientToClientDto(client);
    }

}
