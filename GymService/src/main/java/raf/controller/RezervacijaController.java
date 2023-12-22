package raf.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import raf.dto.RezervacijaCreateDto;
import raf.dto.RezervacijaDto;
import raf.dto.RezervacijaUpdateDto;
import raf.security.CheckSecurity;
import raf.service.RezervacijaService;

@RestController
@AllArgsConstructor
@RequestMapping("/reservation")
public class RezervacijaController {

    private RezervacijaService rezervacijaService ;

    @GetMapping
    @CheckSecurity(roles={"ROLE_ADMIN","ROLE_MANAGER"})
    public ResponseEntity<Page<RezervacijaDto>> getAllRezervacije(@RequestHeader("Authorization") String authorization, Pageable pageable){
        return new ResponseEntity<>(rezervacijaService.findAll(pageable), HttpStatus.OK);
    }
    @PostMapping
    @CheckSecurity(roles={"ROLE_ADMIN,ROLE_MANAGER,ROLE_CLIENT"})
    public ResponseEntity<RezervacijaDto> addReservation(RezervacijaCreateDto rezervacijaCreateDto){
        return new ResponseEntity<>(rezervacijaService.add(rezervacijaCreateDto),HttpStatus.OK);
    }

    @PutMapping
    @CheckSecurity(roles={"ROLE_ADMIN,ROLE_CLIENT"})
    public ResponseEntity<RezervacijaDto> updateReservation(RezervacijaUpdateDto rezervacijaUpdateDto){
        return new ResponseEntity<>(rezervacijaService.update(rezervacijaUpdateDto),HttpStatus.OK);
    }

    @DeleteMapping
    @CheckSecurity(roles={"ROLE_ADMIN,ROLE_MANAGER"})
    public ResponseEntity deleteReservation(RezervacijaUpdateDto rezervacijaUpdateDto){
        rezervacijaService.deleteById(rezervacijaUpdateDto);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/client")
    @CheckSecurity(roles={"ROLE_ADMIN,ROLE_CLIENT"})
    public ResponseEntity deleteReservationClient(RezervacijaUpdateDto rezervacijaUpdateDto){
        rezervacijaService.deleteById(rezervacijaUpdateDto);

        return new ResponseEntity<>(HttpStatus.OK);
    }


}
