package com.secure.jwttoken;

import com.secure.jwttoken.constant.ERole;
import com.secure.jwttoken.entity.Role;
import com.secure.jwttoken.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class JwttokenApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(JwttokenApplication.class, args);
    }

    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {

        Role role = new Role();
        role.setName(ERole.USER);
        Role role2 = new Role();
        role2.setName(ERole.ADMIN);
        roleRepository.save(role);
        roleRepository.save(role2);
    }
}
