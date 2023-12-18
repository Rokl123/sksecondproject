package com.User.service.impl;

import com.User.domain.Manager;
import com.User.dto.ManagerCreateDto;
import com.User.dto.ManagerDto;
import com.User.dto.TokenRequestDto;
import com.User.dto.TokenResponseDto;
import com.User.exception.NotFoundException;
import com.User.mapper.ManagerMapper;
import com.User.repository.ManagerRepository;
import com.User.secutiry.service.TokenService;
import com.User.service.ManagerService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ManagerServiceImpl implements ManagerService {

    private TokenService tokenService;
    private ManagerRepository managerRepository;
    private ManagerMapper managerMapper;

    public ManagerServiceImpl(TokenService tokenService, ManagerRepository managerRepository, ManagerMapper managerMapper) {
        this.tokenService = tokenService;
        this.managerRepository = managerRepository;
        this.managerMapper = managerMapper;
    }

    @Override
    public Page<ManagerDto> findAll(Pageable pageable) {
        return managerRepository.findAll(pageable)
                .map(entity -> {
                    if (entity instanceof Manager) {
                        return managerMapper.managerToManagerDto(entity);
                    }
                    throw new NotFoundException("Expected Manager entity but found different entity type.");
                });
    }

    @Override
    public ManagerDto add(ManagerCreateDto managerCreateDto) {
        Manager manager =  managerMapper.managerCreateDtoToManager(managerCreateDto);
        managerRepository.save(manager);
        return  managerMapper.managerToManagerDto(manager);
    }

    @Override
    public TokenResponseDto login(TokenRequestDto tokenRequestDto) {
        Manager manager = managerRepository
                .findUserByUsernameAndPassword(tokenRequestDto.getUsername(), tokenRequestDto.getPassword())
                .orElseThrow(() -> new NotFoundException(String
                        .format("Manager with username: %s and password: %s not found.", tokenRequestDto.getUsername(),
                                tokenRequestDto.getPassword())));
        //Create token payload
        Claims claims = Jwts.claims();
        claims.put("id", manager.getId());
        claims.put("role", manager.getRole().getName());
        //Generate token
        return new TokenResponseDto(tokenService.generate(claims));
    }
}