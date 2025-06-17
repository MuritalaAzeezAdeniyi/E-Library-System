package com.semicolon.africa.elibrarysystem.service;

import com.semicolon.africa.elibrarysystem.model.UserPrincipal;
import com.semicolon.africa.elibrarysystem.model.Users;
import com.semicolon.africa.elibrarysystem.repository.UsersRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailService implements UserDetailsService {
    private UsersRepo userRepo;
    public MyUserDetailService(UsersRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = userRepo.findByUsername(username);
        if (users == null) {
            throw new UsernameNotFoundException("User not found" + username);
        }
        return new UserPrincipal(users);
    }
}
