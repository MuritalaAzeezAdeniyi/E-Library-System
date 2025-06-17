package com.semicolon.africa.elibrarysystem.repository;

import com.semicolon.africa.elibrarysystem.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface  UsersRepo extends JpaRepository<Users, UUID> {
    Users findByUsername(String username);
}
