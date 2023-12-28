package com.User.mapper;

import com.User.repository.RoleRepository;
import com.User.domain.Admin;
import com.User.dto.AdminCreateDto;
import com.User.dto.AdminDto;
import org.springframework.stereotype.Component;

@Component
public class AdminMapper {
    private RoleRepository roleRepository;

    public AdminMapper(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public AdminDto adminToAdminDto(Admin admin) {
        AdminDto adminDto = new AdminDto();
        adminDto.setId(admin.getAdmmin_id());
        adminDto.setEmail(admin.getEmail());
        adminDto.setFirstName(admin.getIme());
        adminDto.setLastName(admin.getPrezime());
        adminDto.setUsername(admin.getUsername());
        adminDto.setDatumRodjenja(admin.getDatumRodjenja());
        return adminDto;
    }
    public Admin adminCreateDtoToAdmin(AdminCreateDto adminCreateDto) {
        Admin admin = new Admin();
        admin.setEmail(adminCreateDto.getEmail());
        admin.setIme(adminCreateDto.getFirstName());
        admin.setPrezime(adminCreateDto.getLastName());
        admin.setUsername(adminCreateDto.getUsername());
        admin.setPassword(adminCreateDto.getPassword());
        admin.setDatumRodjenja(adminCreateDto.getDatumRodjenja());
        admin.setRole(roleRepository.findRoleByName("ROLE_ADMIN").get());
        return admin;
    }
}
