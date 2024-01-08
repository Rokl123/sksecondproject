package com.User.controller;


import com.User.dto.*;
import com.User.listener.helper.MessageHelper;
import com.User.security.CheckSecurity;
import com.User.domain.Client;
import com.User.service.ClientService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client/")
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
public class ClientController {
    private ClientService clientService;

    @Value("${destination.sendEmails}")
    private String destination;

    private MessageHelper messageHelper;

    private JmsTemplate jmsTemplate;

    //@CheckSecurity(roles = {"ROLE_CLIENTS"})
    @GetMapping("/getAllClient")
    public ResponseEntity<Page<ClientDto>> getAllClients(Pageable pageable) {
        return new ResponseEntity<>(clientService.findAll(pageable), HttpStatus.OK);
    }
    @GetMapping("{id}/getClient")
    @CheckSecurity(roles = {"ROLE_ADMIN"})
    public ResponseEntity<ClientDto> getAClientById(@RequestHeader("Authorization") String authorization,@PathVariable("id") Long id){

        return new ResponseEntity<>(clientService.findById(id),HttpStatus.OK);
    }

    @PostMapping("/{id}/addReservation")
    @CheckSecurity(roles = {"ROLE_ADMIN","ROLE_CLIENT","ROLE_MANAGER"})
    public ResponseEntity<ClientDto> addReservation(@RequestHeader("Authorization") String authorization, @PathVariable("id") Long id){

        return new ResponseEntity<>(clientService.addReservation(id),HttpStatus.OK);
    }


    @PostMapping("/registerClient")
    public ResponseEntity<ClientDto> saveClient(@RequestBody @Valid ClientCreateDto clientCreateDto) {
       ClientDto clientDto= clientService.add(clientCreateDto);

       jmsTemplate.convertAndSend(destination,messageHelper.createTextMessage(clientDto));

       return new ResponseEntity<>(clientDto, HttpStatus.CREATED);
    }


    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> loginClient(@RequestBody @Valid TokenRequestDto tokenRequestDto) {
        return new ResponseEntity<>(clientService.login(tokenRequestDto), HttpStatus.OK);
    }


    @PutMapping("/{clientId}")
    public ResponseEntity<String> updateProfile(@PathVariable("clientId") Long clientId, @RequestBody ClientUpdateDto updatedClient) {
        clientService.updateProfile(Long.valueOf(clientId), updatedClient);

        return ResponseEntity.ok("Profile updated successfully.");
    }

}
