package raf.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import raf.dto.FiskulturnaSalaCreateDto;
import raf.dto.FiskulturnaSalaDto;
import raf.dto.FiskulturnaSalaUpdateDto;
import raf.security.CheckSecurity;
import raf.service.FiskulturnaSalaService;

@RestController
@RequestMapping("/sala")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class FiskulturnaSalaController {

    private FiskulturnaSalaService fiskulturnaSalaService;

    @GetMapping
    @CheckSecurity(roles={"ROLE_ADMIN","ROLE_CLIENT","ROLE_MANAGER"})
    public ResponseEntity<Page<FiskulturnaSalaDto>> getAllSale(@RequestHeader("Authorization") String authorization, Pageable pageable){
        return new ResponseEntity<>(fiskulturnaSalaService.findAll(pageable),HttpStatus.OK);
    }

    @PostMapping
    @CheckSecurity(roles={"ROLE_ADMIN"})
    public ResponseEntity<FiskulturnaSalaDto> saveSala(@RequestBody @Valid FiskulturnaSalaCreateDto fiskulturnaSalaCreateDto){
        return new ResponseEntity<>(fiskulturnaSalaService.addSala(fiskulturnaSalaCreateDto), HttpStatus.CREATED);
    }

    @PutMapping
    @CheckSecurity(roles={"ROLE_ADMIN"})
    public ResponseEntity<FiskulturnaSalaDto> updateSala(@RequestBody @Valid FiskulturnaSalaUpdateDto fiskulturnaSalaUpdateDto){
        return new ResponseEntity<>(fiskulturnaSalaService.update(1L,fiskulturnaSalaUpdateDto), HttpStatus.OK);
    }

    @PutMapping("/manager")
    @CheckSecurity(roles={"ROLE_MANAGER"})
    public ResponseEntity<FiskulturnaSalaDto> updateSalaByKapacitetOrBrTrenera(@RequestBody @Valid FiskulturnaSalaUpdateDto fiskulturnaSalaUpdateDto){
        return new ResponseEntity<>(fiskulturnaSalaService.updateManager(fiskulturnaSalaUpdateDto),HttpStatus.OK);
    }


    @DeleteMapping
    @CheckSecurity(roles={"ROLE_ADMIN"})
    public ResponseEntity deleteSala(@RequestBody FiskulturnaSalaUpdateDto fiskulturnaSalaDto){
        fiskulturnaSalaService.deleteById(fiskulturnaSalaDto.getId());
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

}
