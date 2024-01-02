package com.User.controller;


import com.User.dto.ClientCreateDto;
import com.User.dto.ClientDto;
import com.User.dto.TokenRequestDto;
import com.User.dto.TokenResponseDto;
import com.User.security.CheckSecurity;
import com.User.domain.Client;
import com.User.service.ClientService;

import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client/")
@CrossOrigin(origins = "http://localhost:4200")
public class ClientController {
    private ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }


    @CheckSecurity(roles = {"ROLE_CLIENTS"})
    @GetMapping("/getAllClient")
    public ResponseEntity<Page<ClientDto>> getAllClients(@RequestHeader("Authorization") String authorization,
                                                         Pageable pageable) {

        return new ResponseEntity<>(clientService.findAll(pageable), HttpStatus.OK);
    }
    @GetMapping("/{id}/getClient")
    @CheckSecurity(roles = {"ROLE_ADMIN"})
    public ResponseEntity<ClientDto> getAClientById(@RequestHeader("Authorization") String authorization,@PathVariable("id") Long id){

        return new ResponseEntity<>(clientService.findById(id),HttpStatus.OK);
    }

    @PostMapping("/{id}/addReservation")
    @CheckSecurity(roles = "ROLE_ADMIN")
    public ResponseEntity<ClientDto> addReservation(@RequestHeader("Authorization") String authorization, @PathVariable("id") Long id){

        return new ResponseEntity<>(clientService.addReservation(id),HttpStatus.OK);
    }


    @PostMapping("/saveClient")
    public ResponseEntity<ClientDto> saveClient(@RequestBody @Valid ClientCreateDto clientCreateDto) {
        return new ResponseEntity<>(clientService.add(clientCreateDto), HttpStatus.CREATED);
    }


    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> loginClient(@RequestBody @Valid TokenRequestDto tokenRequestDto) {
        return new ResponseEntity<>(clientService.login(tokenRequestDto), HttpStatus.OK);
    }


    @PutMapping("/{clientId}")
    public ResponseEntity<String> updateProfile(@PathVariable Long clientId, @RequestBody Client updatedClient) {
        clientService.updateProfile(Long.valueOf(clientId), updatedClient);

        return ResponseEntity.ok("Profile updated successfully.");
    }
}
