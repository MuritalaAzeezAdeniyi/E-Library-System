package com.semicolon.africa.elibrarysystem.repository;

import com.semicolon.africa.elibrarysystem.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface  UsersRepo extends JpaRepository<Users, UUID> {
    Users findByUsername(String username);
    Users findByEmail(String email);
   Optional <Users> findByUserId(UUID id);
}
