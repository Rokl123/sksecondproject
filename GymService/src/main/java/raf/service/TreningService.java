package raf.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import raf.dto.*;

public interface TreningService {

    Page<TreningDto> findAll(Pageable pageable); //read

    TreningDto add(TreningCreateDto treningCreateDto);

    TreningDto update(TreningUpdateDto treningUpdateDto); //update

    void deleteById(TreningUpdateDto treningUpdateDto); //delete

}
