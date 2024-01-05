package com.User.runner;

import com.User.domain.Admin;
import com.User.domain.Client;
import com.User.domain.Manager;
import com.User.domain.Role;
import com.User.repository.AdminRepository;
import com.User.repository.ClientRepository;
import com.User.repository.ManagerRepository;
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

    private ManagerRepository managerRepository;

    public TestDataRunner(RoleRepository roleRepository, ClientRepository userRepository, AdminRepository adminRepository,
                          ManagerRepository managerRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.adminRepository=adminRepository;
        this.managerRepository=managerRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Role roleUser = new Role("ROLE_USER", "User role");
        Role roleAdmin = new Role("ROLE_ADMIN", "Admin role");
        Role roleClient = new Role("ROLE_CLIENT","Client role");
        Role roleManager = new Role ("ROLE_MANAGER","Manager role");
        roleRepository.save(roleUser);
        roleRepository.save(roleAdmin);
        roleRepository.save(roleClient);
        roleRepository.save(roleManager);

        Admin admin = new Admin();
        admin.setEmail("admin@gmail.com");
        admin.setUsername("admin");
        admin.setPassword("admin");
        admin.setRole(roleAdmin);

        adminRepository.save(admin);
        //User statuses
        Manager manager = new Manager();
        manager.setIme("Arsenije");
        manager.setEmail("panteon566@gmail.com");
        manager.setRole(roleManager);
        manager.setNazivFisSale("Ahilej");
        manager.setUsername("Rokl123");
        manager.setPassword("arsen123");
        managerRepository.save(manager);

        Client client = new Client();
        client.setIme("Mihailo");
        client.setPrezime("Protic");
        client.setBrojClanskeKartice("123456789");
        client.setEmail("test@gmail.com");
        client.setUsername("dsdsads");
        client.setRole(roleClient);
        client.setPassword("1234");
        userRepository.save(client);
    }
}
