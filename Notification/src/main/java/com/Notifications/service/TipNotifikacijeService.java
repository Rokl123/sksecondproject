package com.Notifications.service;

import com.Notifications.dto.TipNotifikacijeCreateDto;
import com.Notifications.dto.TipNotifikacijeDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TipNotifikacijeService {
    TipNotifikacijeDto add(TipNotifikacijeCreateDto tipNotifikacijeCreateDto);
    Page<TipNotifikacijeDto> findAll(Pageable pageable);

    void delete(Long id);

    TipNotifikacijeDto update(TipNotifikacijeDto tipNotifikacijeDto);

}
