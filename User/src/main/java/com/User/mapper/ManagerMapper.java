package com.User.mapper;

import com.User.domain.Client;
import com.User.repository.ClientRepository;
import com.User.repository.RoleRepository;
import com.User.domain.Manager;
import com.User.dto.ManagerCreateDto;
import com.User.dto.ManagerDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ManagerMapper {

    private RoleRepository roleRepository;

    private ClientRepository clientRepository;

    public ManagerMapper(RoleRepository roleRepository, ClientRepository clientRepository) {
        this.roleRepository = roleRepository;
        this.clientRepository = clientRepository;
    }

    public ManagerDto managerToManagerDto(Manager manager) {
        ManagerDto managerDto = new ManagerDto();
        managerDto.setId(manager.getId());
        managerDto.setEmail(manager.getEmail());
        managerDto.setFirstName(manager.getIme());
        managerDto.setLastName(manager.getPrezime());
        managerDto.setUsername(manager.getUsername());
        managerDto.setDatumRodjenja(manager.getDatumRodjenja());
        managerDto.setBanovan(manager.getIsBanovan());
        managerDto.setDatumZaposljavanja(manager.getDatumZaposljavanja());
        managerDto.setNazivFisSale(manager.getNazivFisSale());
        List<Client> client = new ArrayList<>();
        for (Client client1 : manager.getClients()) {
            client.add(client1);
        }
        managerDto.setClients(client);
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
        manager.setIsBanovan(managerCreateDto.getBanovan());
        manager.setRole(roleRepository.findRoleByName("ROLE_MANAGER").get());
        manager.setDatumZaposljavanja(managerCreateDto.getDatumZaposljavanja());
        manager.setNazivFisSale(managerCreateDto.getNazivFisSale());
        if (managerCreateDto.getClients() != null) {
            List<Client> clients = new ArrayList<>();
            for (Client clientId : managerCreateDto.getClients()) {
                Client client = clientRepository.findById(clientId.getId()).orElse(null);
                if (client != null) {
                    clients.add(client);
                }
            }
            manager.setClients(clients);
        }
        return manager;
    }
}