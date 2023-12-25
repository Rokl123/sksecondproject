package com.User.controller;


import com.User.dto.ManagerCreateDto;
import com.User.dto.ManagerDto;
import com.User.dto.TokenRequestDto;
import com.User.dto.TokenResponseDto;
import com.User.security.CheckSecurity;
import com.User.service.ManagerService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/manager")
public class ManagerController {
    private ManagerService managerService;

    public ManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }

    @GetMapping
   @CheckSecurity(roles = {"ROLE_MANAGER"})
    public ResponseEntity<Page<ManagerDto>> getAllMangers(@RequestHeader("Authorization") String authorization,
                                                          Pageable pageable) {

        return new ResponseEntity<>(managerService.findAll(pageable), HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<ManagerDto> saveManager(@RequestBody @Valid ManagerCreateDto managerCreateDto) {
        //notifikacija
        return new ResponseEntity<>(managerService.add(managerCreateDto), HttpStatus.CREATED);
    }


    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> loginManager(@RequestBody @Valid TokenRequestDto tokenRequestDto) {
        //notifikacija
        return new ResponseEntity<>(managerService.login(tokenRequestDto), HttpStatus.OK);
    }
}
