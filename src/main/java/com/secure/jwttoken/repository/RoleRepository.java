package com.secure.jwttoken.repository;

import com.secure.jwttoken.constant.ERole;
import com.secure.jwttoken.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByName(ERole name);
}
