package com.User.runner;

import com.User.domain.Admin;
import com.User.domain.Client;
import com.User.domain.Role;
import com.User.repository.AdminRepository;
import com.User.repository.ClientRepository;
import com.User.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile({"default"})
@Component
public class TestDataRunner implements CommandLineRunner {

    private RoleRepository roleRepository;
    private ClientRepository userRepository;
    private AdminRepository adminRepository;

    public TestDataRunner(RoleRepository roleRepository, ClientRepository userRepository, AdminRepository adminRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.adminRepository=adminRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Role roleUser = new Role("ROLE_USER", "User role");
        Role roleAdmin = new Role("ROLE_ADMIN", "Admin role");
        Role roleClient = new Role("ROLE_CLIENT","Client role");
        roleRepository.save(roleUser);
        roleRepository.save(roleAdmin);
        roleRepository.save(roleClient);

        Admin admin = new Admin();
        admin.setEmail("admin@gmail.com");
        admin.setUsername("admin");
        admin.setPassword("admin");
        admin.setRole(roleAdmin);

        adminRepository.save(admin);
        //User statuses
    }
}
