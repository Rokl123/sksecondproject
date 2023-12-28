package com.User.service.impl;


import com.User.domain.Client;
import com.User.dto.ClientCreateDto;
import com.User.dto.ClientDto;
import com.User.dto.TokenRequestDto;
import com.User.dto.TokenResponseDto;
import com.User.exception.NotFoundException;
import com.User.mapper.ClientMapper;
import com.User.repository.ClientRepository;
import com.User.security.service.TokenService;
import com.User.service.ClientService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
@Service
public class ClientServiceImpl implements ClientService {

    private TokenService tokenService;
    private ClientRepository clientRepository;
    private ClientMapper clientMapper;

    public ClientServiceImpl(TokenService tokenService, ClientRepository clientRepository, ClientMapper clientMapper) {
        this.tokenService = tokenService;
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }

    @Override
    public Page<ClientDto> findAll(Pageable pageable) {
        return clientRepository.findAll(pageable)
                .map(entity -> {
                    if (entity instanceof Client) {
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
        claims.put("id", client.getClient_id());
        claims.put("role", client.getRole().getName());
        //Generate token
        return new TokenResponseDto(tokenService.generate(claims));
    }

    @Override
    public void updateProfile(Long id, Client updatedProfile) {
        Client existingUser = clientRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Client not found"));

        if (existingUser instanceof Client) {
            Client updatedClientProfile = (Client) updatedProfile;

            Client existingClient = (Client) existingUser;
            existingClient.setUsername(updatedClientProfile.getUsername());
            existingClient.setPassword(updatedClientProfile.getPassword());
            existingClient.setEmail(updatedClientProfile.getEmail());
            existingClient.setDatumRodjenja(updatedClientProfile.getDatumRodjenja());
            existingClient.setIme(updatedClientProfile.getIme());
            existingClient.setPrezime(updatedClientProfile.getPrezime());

            // Čuvanje promena u bazi podataka
            clientRepository.save(existingClient);
        } else {
            throw new IllegalArgumentException("Cannot update profile: User is not a Client");
        }
    }

    @Override
    public ClientDto addReservation(Long id) {
        Client client =clientRepository.findById(id).orElseThrow(RuntimeException::new);
        client.setBrojZakazanihTreninga(client.getBrojZakazanihTreninga()+1);
        clientRepository.save(client);
        return clientMapper.clientToClientDto(client);
    }

}
