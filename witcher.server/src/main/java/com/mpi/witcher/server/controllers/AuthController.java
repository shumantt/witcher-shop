package com.mpi.witcher.server.controllers;

import com.mpi.witcher.server.models.requests.AuthenticationRequest;
import com.mpi.witcher.server.models.users.User;
import com.mpi.witcher.server.models.users.WebUser;
import com.mpi.witcher.server.repositories.UsersRepository;
import com.mpi.witcher.server.security.jwt.JwtTokenProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    public AuthController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/signin")
    public ResponseEntity signin(@RequestBody AuthenticationRequest data) {

        try {
            String login = data.getLogin();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login, data.getPassword()));
            User user = UsersRepository
                    .findByUserName(login)
                    .orElseThrow(() -> new UsernameNotFoundException("Login " + login + "not found"));
            String token = jwtTokenProvider.createToken(login, user.getRoles());

            WebUser webUser = new WebUser(user.getName(), user.getPictureUrl(), user.getRole(), token, login);
            return ok(webUser);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username/password supplied");
        }
    }
}