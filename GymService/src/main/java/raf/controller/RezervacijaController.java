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
    private TreningService treningService;
    private RestTemplate userServiceRestTemplate;
    private RestTemplate trainingServiceRestTemplate;
    private Retry userServiceRetry;

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

    @PostMapping
    public ResponseEntity<RezervacijaDto> addReservation(@RequestBody @Valid RezervacijaCreateDto rezervacijaCreateDto){
        ClientDto clientDto = null;

        clientDto = Retry.decorateSupplier(userServiceRetry,()->getClient(rezervacijaCreateDto.getClient_id())).get();
        TreningDto treningDto = treningService.findById(rezervacijaCreateDto.getTrening_id());

        if(treningDto.getSala().getKapacitet() == treningDto.getBrRezervacija()){
            return new ResponseEntity<>(HttpStatus.LOCKED);
        }

        if((clientDto.getBrojZakazanihTreninga()+1) % treningDto.getSala().getLoyalty() == 0)
            rezervacijaCreateDto.setCenaTreninga(0);
        else
            rezervacijaCreateDto.setCenaTreninga(treningDto.getCenaTreninga());

        rezervacijaCreateDto.setTrening_id(treningDto.getId());
        rezervacijaCreateDto.setClient_id(clientDto.getId());

        if(rezervacijaService.add(rezervacijaCreateDto) == null){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

        incrementReservations(rezervacijaCreateDto.getClient_id());


        return new ResponseEntity<>(rezervacijaService.add(rezervacijaCreateDto),HttpStatus.OK);
    }
    private void incrementReservations(Long id){
        try{
            userServiceRestTemplate.exchange("/client/" + id + "/addReservation",
                    HttpMethod.POST,null,ClientDto.class);
        }
        catch(HttpClientErrorException e){
            throw new NotFoundException(String.format("Client with id %d has not been found",id));
        }
    }
    private ClientDto getClient(Long id){
        ResponseEntity<ClientDto> clientDtoResponseEntity = null;
        try{
            clientDtoResponseEntity = userServiceRestTemplate.exchange("/client/" + id + "/getClient",
                    HttpMethod.GET,null,ClientDto.class);

            return clientDtoResponseEntity.getBody();
        }
        catch (HttpClientErrorException e){
            throw new NotFoundException(String.format("Client with this id %d has not been found! ",id));
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
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
