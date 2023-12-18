package com.User.runner;

import com.User.domain.Client;
import com.User.domain.Role;
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


    public TestDataRunner(RoleRepository roleRepository, ClientRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;

    }

    @Override
    public void run(String... args) throws Exception {
        //Insert roles
        Role roleUser = new Role("ROLE_USER", "User role");
        Role roleAdmin = new Role("ROLE_ADMIN", "Admin role");
        roleRepository.save(roleUser);
        roleRepository.save(roleAdmin);
        //Insert admin
        Client admin = new Client();
        admin.setEmail("admin@gmail.com");
        admin.setUsername("admin");
        admin.setPassword("admin");
        admin.setRole(roleAdmin);

        userRepository.save(admin);
        //User statuses
    }
}
