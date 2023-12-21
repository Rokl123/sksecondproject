package com.User.mapper;

import com.User.repository.RoleRepository;
import com.User.domain.Manager;
import com.User.dto.ManagerCreateDto;
import com.User.dto.ManagerDto;
import org.springframework.stereotype.Component;

@Component
public class ManagerMapper {

    private RoleRepository roleRepository;

    public ManagerMapper(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public ManagerDto managerToManagerDto(Manager manager) {
        ManagerDto managerDto = new ManagerDto();
        managerDto.setId(manager.getId());
        managerDto.setEmail(manager.getEmail());
        managerDto.setFirstName(manager.getIme());
        managerDto.setLastName(manager.getPrezime());
        managerDto.setUsername(manager.getUsername());
        managerDto.setDatumRodjenja(manager.getDatumRodjenja());
        managerDto.setBanovan(manager.getBanovan());
        managerDto.setDatumZaposljavanja(manager.getDatumZaposljavanja());
        managerDto.setNazivFisSale(manager.getNazivFisSale());
        return managerDto;
    }

    public Manager managerCreateDtoToManager(ManagerCreateDto managerCreateDto) {
        Manager manager = new Manager();
        manager.setEmail(managerCreateDto.getEmail());
        manager.setIme(managerCreateDto.getFirstName());
        manager.setPrezime(managerCreateDto.getLastName());
        manager.setUsername(managerCreateDto.getUsername());
        manager.setPassword(managerCreateDto.getPassword());
        manager.setDatumRodjenja(managerCreateDto.getDatumRodjenja());
        manager.setBanovan(managerCreateDto.getBanovan());
        manager.setRole(roleRepository.findRoleByName("ROLE_MANAGER").get());
        manager.setDatumZaposljavanja(managerCreateDto.getDatumZaposljavanja());
        manager.setNazivFisSale(managerCreateDto.getNazivFisSale());
        return manager;
    }
}