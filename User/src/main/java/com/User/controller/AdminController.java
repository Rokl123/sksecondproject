package com.User.controller;


import com.User.dto.AdminDto;
import com.User.dto.TokenRequestDto;
import com.User.dto.TokenResponseDto;
import com.User.security.CheckSecurity;
import com.User.service.AdminService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:4200")
public class AdminController {
    private AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }


    @PostMapping("/ban/{userId}")
    @CheckSecurity(roles={"ROLE_ADMIN"})
    public ResponseEntity<String> banUser(@RequestHeader("Authorization") String authorization, @RequestBody @Valid @PathVariable Long userId) {
        adminService.banClient(userId);
        //notifikacija
        return ResponseEntity.ok("Client with ID " + userId + " has been banned.");
    }


    @PostMapping("/unban/{userId}")
    @CheckSecurity(roles={"ROLE_ADMIN"})
    public ResponseEntity<String> unbanUser(@RequestHeader("Authorization") String authorization,@RequestBody @Valid @PathVariable Long userId) {
        adminService.unBanClient(userId);
        //notifikacija
        return ResponseEntity.ok("Client with ID " + userId + " has been unbanned.");
    }


    @GetMapping
    @CheckSecurity(roles = {"ROLE_ADMIN"})
    public ResponseEntity<Page<AdminDto>> getAllAdmins(@RequestHeader("Authorization") String authorization,
                                                       Pageable pageable) {

        return new ResponseEntity<>(adminService.findAll(pageable), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> loginUser(@RequestBody @Valid TokenRequestDto tokenRequestDto) {
        return new ResponseEntity<>(adminService.login(tokenRequestDto), HttpStatus.OK);
    }
}
