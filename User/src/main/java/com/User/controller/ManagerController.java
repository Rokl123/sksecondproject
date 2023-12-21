package com.User.controller;


import com.User.dto.ManagerCreateDto;
import com.User.dto.ManagerDto;
import com.User.dto.TokenRequestDto;
import com.User.dto.TokenResponseDto;
import com.User.security.CheckSecurity;
import com.User.service.ManagerService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@RestController
@RequestMapping("/manager")
public class ManagerController {
    private ManagerService managerService;

    public ManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }

    @ApiOperation(value = "Get all managers")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "What page number you want", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "size", value = "Number of items to return", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "Sorting criteria in the format: property(,asc|desc). " +
                            "Default sort order is ascending. " +
                            "Multiple sort criteria are supported.")})
    @GetMapping
   @CheckSecurity(roles = {"ROLE_MANAGER"})
    public ResponseEntity<Page<ManagerDto>> getAllMangers(@RequestHeader("Authorization") String authorization,
                                                          Pageable pageable) {

        return new ResponseEntity<>(managerService.findAll(pageable), HttpStatus.OK);
    }

    @ApiOperation(value = "Register manager")
    @PostMapping
    public ResponseEntity<ManagerDto> saveManager(@RequestBody @Valid ManagerCreateDto managerCreateDto) {
        //notifikacija
        return new ResponseEntity<>(managerService.add(managerCreateDto), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Login")
    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> loginManager(@RequestBody @Valid TokenRequestDto tokenRequestDto) {
        //notifikacija
        return new ResponseEntity<>(managerService.login(tokenRequestDto), HttpStatus.OK);
    }
}
