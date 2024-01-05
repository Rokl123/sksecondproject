package com.User.service;

import com.User.dto.ManagerCreateDto;
import com.User.dto.ManagerDto;
import com.User.dto.TokenRequestDto;
import com.User.dto.TokenResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ManagerService {
    Page<ManagerDto> findAll(Pageable pageable);

    ManagerDto add(ManagerCreateDto managerCreateDto);

    TokenResponseDto login(TokenRequestDto tokenRequestDto);

    ManagerDto findById(Long id);

}
