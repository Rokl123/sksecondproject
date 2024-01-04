package raf.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import raf.dto.TreningCreateDto;
import raf.dto.TreningDto;
import raf.dto.TreningUpdateDto;
import raf.security.CheckSecurity;
import raf.service.TreningService;

@RestController
@AllArgsConstructor
@RequestMapping("/treninzi")
public class TreningController {

    private TreningService treningService;

    @GetMapping
    @CheckSecurity(roles={"ROLE_ADMIN","ROLE_CLIENT","ROLE_MANAGER"})
    public ResponseEntity<Page<TreningDto>> findAllTrainings(@RequestHeader("Authorization") String authorization, Pageable pageable){
        return new ResponseEntity<>(treningService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}/getTraining")
    @CheckSecurity(roles={"ROLE_ADMIN","ROLE_MANAGER"})
    public ResponseEntity<TreningDto> findTrainingById(@RequestHeader("Authorization") String authorization,@PathVariable("id") Long id){
        return new ResponseEntity<>(treningService.findById(id), HttpStatus.OK);
    }

    @PutMapping
    @CheckSecurity(roles={"ROLE_ADMIN","ROLE_MANAGER"})
    public ResponseEntity<TreningDto> saveTrening(@RequestBody @Valid TreningCreateDto treningCreateDto){
        return new ResponseEntity<>(treningService.add(treningCreateDto),HttpStatus.OK);
    }

    @PostMapping
    @CheckSecurity(roles={"ROLE_ADMIN","ROLE_MANAGER"})
    public ResponseEntity<TreningDto> updateTrening(@RequestBody @Valid TreningUpdateDto treningUpdateDto){
        return new ResponseEntity<>(treningService.update(treningUpdateDto),HttpStatus.OK);
    }

    @DeleteMapping
    @CheckSecurity(roles="ROLE_ADMIN")
    public ResponseEntity<TreningDto> deleteTraining(@RequestBody TreningUpdateDto treningUpdateDto){
        treningService.deleteById(treningUpdateDto);
            // TODO make notification for deleting(cancelling) training session
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
