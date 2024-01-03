package raf.service.impl;

import com.User.exception.NotFoundException;
import com.User.repository.ClientRepository;
import io.github.resilience4j.retry.Retry;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.id.IncrementGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import raf.domain.Rezervacija;
import raf.domain.Trening;
import raf.dto.*;
import raf.listener.helper.MessageHelper;
import raf.mapper.RezervacijaMapper;
import raf.repository.RezervacijaRepository;
import raf.repository.TreningRepository;
import raf.service.RezervacijaService;
import raf.service.TreningService;
import raf.userservice.ClientDto;

@Service
@AllArgsConstructor
public class RezervacijaServiceImpl implements RezervacijaService {

    private RezervacijaRepository rezervacijaRepository;

    private RezervacijaMapper rezervacijaMapper;

    private TreningRepository treningRepository;

    private TreningService treningService;

    private JmsTemplate jmsTemplate;

    private MessageHelper messageHelper;

    private RestTemplate userServiceRestTemplate;

    private Retry userServiceRetry;

    @Value("${destination.incrementReservation}")
    private String destination;

    @Override
    public Page<RezervacijaDto> findAll(Pageable pageable) {
        return rezervacijaRepository.findAll(pageable).map(rezervacijaMapper::DomainObjectToDto);
    }

    @Override
    public Page<RezervacijaDto> findByClientId(Pageable pageable,Long client_id) {

        return rezervacijaRepository.findAllByClientID(pageable,client_id).map(rezervacijaMapper::DomainObjectToDto);
    }

    @Override
    public RezervacijaDto add(RezervacijaCreateDto rezervacijaCreateDto)
    {
        Trening trening = treningRepository.findById(rezervacijaCreateDto.getTrening_id()).orElseThrow(RuntimeException::new);
        rezervacijaCreateDto.setTrening(trening);
        Rezervacija rezervacija = rezervacijaMapper.DtoToDomainObject(rezervacijaCreateDto);
        for(Rezervacija r: rezervacijaRepository.findAll()){
            if(r.getClientID().equals(rezervacija.getClientID()) && r.getRezervisaniTrening().equals(rezervacija.getRezervisaniTrening()) &&
                    (r.getRezervisaniTrening().getTerminTreninga().equals(rezervacija.getRezervisaniTrening().getTerminTreninga())
                    && r.getRezervisaniTrening().getPocetakTermina().equals(rezervacija.getRezervisaniTrening().getPocetakTermina()))
                    &&  r.getRezervisaniTrening().getKrajTermina().equals(rezervacija.getRezervisaniTrening().getKrajTermina())){
                return null;
            }
        }

        ClientDto clientDto = null;

        clientDto = Retry.decorateSupplier(userServiceRetry,()->getClient(rezervacijaCreateDto.getClient_id())).get();

        if(trening.getSala().getKapacitet() == trening.getBrRezervacija())
            return null;
        if((clientDto.getBrojZakazanihTreninga()+1) % trening.getSala().getLoyalty() == 0)
            rezervacijaCreateDto.setCenaTreninga(0);
        else
            rezervacijaCreateDto.setCenaTreninga(trening.getCenaTreninga());

        rezervacijaCreateDto.setTrening_id(trening.getTrening_id());
        rezervacijaCreateDto.setClient_id(clientDto.getId());
        trening.setBrRezervacija(trening.getBrRezervacija()+1);
        rezervacija.setCenaTreninga(rezervacijaCreateDto.getCenaTreninga());

        rezervacijaRepository.save(rezervacija);
        RezervacijaDto rDto = rezervacijaMapper.DomainObjectToDto(rezervacija);

        IncrementReservationDto incrementReservationDto = new IncrementReservationDto();
        incrementReservationDto.setClient_id(rezervacijaCreateDto.getClient_id());
        jmsTemplate.convertAndSend(destination,messageHelper.createTextMessage(incrementReservationDto));

        return rDto;
    }
    private ClientDto getClient(Long id){
        ResponseEntity<ClientDto> clientDtoResponseEntity = null;
        try{
            clientDtoResponseEntity = userServiceRestTemplate.exchange("/client/" + id + "/getClient",
                    HttpMethod.GET,null,ClientDto.class);

            return clientDtoResponseEntity.getBody();
        }
        catch (HttpClientErrorException e){
            if(e.getStatusCode().equals(HttpStatus.NOT_FOUND))
                throw new NotFoundException(String.format("Client with this id %d has not been found! ",id));
        }
        catch(Exception e){
            throw new RuntimeException("Internal server error");
        }
        return null;
    }
    @Override
    public RezervacijaDto update(RezervacijaUpdateDto rezervacijaUpdateDto) {
        Rezervacija rezervacija = rezervacijaRepository.findById(rezervacijaUpdateDto.getId()).orElseThrow(RuntimeException::new);

        return rezervacijaMapper.DomainObjectToDto(rezervacija);
    }

    @Override
    public void deleteById(RezervacijaUpdateDto rezervacijaUpdateDto) {
        rezervacijaRepository.deleteById(rezervacijaUpdateDto.getId());
    }
}
