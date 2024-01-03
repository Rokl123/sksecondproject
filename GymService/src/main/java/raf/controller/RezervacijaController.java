package raf.controller;

import com.User.domain.Client;
import com.User.exception.NotFoundException;
import com.fasterxml.jackson.databind.util.JSONPObject;
import io.github.resilience4j.retry.Retry;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import raf.domain.Trening;
import raf.dto.RezervacijaCreateDto;
import raf.dto.RezervacijaDto;
import raf.dto.RezervacijaUpdateDto;
import raf.dto.TreningDto;
import raf.security.CheckSecurity;
import raf.service.RezervacijaService;
import raf.service.TreningService;
import raf.userservice.ClientDto;

import static org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder.decode;

@RestController
@AllArgsConstructor
@RequestMapping("/reservation")
public class RezervacijaController {

    private RezervacijaService rezervacijaService;


    @GetMapping
    @CheckSecurity(roles={"ROLE_ADMIN","ROLE_MANAGER"})
    public ResponseEntity<Page<RezervacijaDto>> getAllRezervacije(@RequestHeader("Authorization") String authorization, Pageable pageable){
        return new ResponseEntity<>(rezervacijaService.findAll(pageable), HttpStatus.OK);
    }
    @GetMapping("/client")
    @CheckSecurity(roles={"ROLE_ADMIN","ROLE_CLIENT"})
    public ResponseEntity<Page<RezervacijaDto>> getAllRezervacijeClient(@RequestHeader("Authorization") String authorization, Pageable pageable){
        String[] parts = authorization.split("\\.");
        JSONObject jsonObject = new JSONObject(decode(parts[1]));
        Long clientId = jsonObject.getLong("id");
        return new ResponseEntity<>(rezervacijaService.findByClientId(pageable,clientId), HttpStatus.OK);
    }

    @PostMapping // TODO: uradi da se dobija client_id(iz sesije?) kao i trening_id (ovo ces dobijati tek kad uradimo GUI)
    public ResponseEntity<RezervacijaDto> addReservation(@RequestBody @Valid RezervacijaCreateDto rezervacijaCreateDto){
        if(rezervacijaService.add(rezervacijaCreateDto) == null){
            return new ResponseEntity<>(null,HttpStatus.FORBIDDEN);
        }

        return new ResponseEntity<>(rezervacijaService.add(rezervacijaCreateDto),HttpStatus.OK);
    }



    @PutMapping
    @CheckSecurity(roles={"ROLE_ADMIN","ROLE_CLIENT"})
    public ResponseEntity<RezervacijaDto> updateReservation(@RequestHeader("Authorization") String authorization,RezervacijaUpdateDto rezervacijaUpdateDto){
        return new ResponseEntity<>(rezervacijaService.update(rezervacijaUpdateDto),HttpStatus.OK);
    }

    @DeleteMapping
    @CheckSecurity(roles={"ROLE_ADMIN","ROLE_MANAGER"})
    public ResponseEntity deleteReservation(@RequestHeader("Authorization") String authorization,RezervacijaUpdateDto rezervacijaUpdateDto){
        rezervacijaService.deleteById(rezervacijaUpdateDto);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/client")
    @CheckSecurity(roles={"ROLE_ADMIN","ROLE_CLIENT"})
    public ResponseEntity deleteReservationClient(@RequestHeader("Authorization") String authorization,RezervacijaUpdateDto rezervacijaUpdateDto){

        rezervacijaService.deleteById(rezervacijaUpdateDto);

        return new ResponseEntity<>(HttpStatus.OK);
    }


}
