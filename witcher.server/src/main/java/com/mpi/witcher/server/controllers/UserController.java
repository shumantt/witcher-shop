package com.mpi.witcher.server.controllers;
import com.mpi.witcher.server.models.users.NewUser;
import com.mpi.witcher.server.models.users.UserRoles;
import com.mpi.witcher.server.repositories.UsersRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api")
public class UserController {

    @GetMapping("/users")
    public ResponseEntity GetAllUsers() {
        return ok(UsersRepository.Users);
    }

    @PostMapping("/user")
    public ResponseEntity AddUser(@RequestBody NewUser user) {
        String role = user.getRole();

        try {
            UserRoles userRole = UserRoles.valueOf(role);
            boolean isOk = UsersRepository.createUser(user.getLogin(), user.getPassword(), userRole.ordinal());

            if (isOk) {
                return ok("ok");
            } else {
                return badRequest().body("Name already exist");
            }
        } catch (Exception e) {
            return badRequest().body("Wrong role");
        }
    }
}
