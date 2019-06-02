package com.mpi.witcher.server.security;

import com.mpi.witcher.server.repositories.UsersRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return UsersRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username " + username + " not found"));
    }
}