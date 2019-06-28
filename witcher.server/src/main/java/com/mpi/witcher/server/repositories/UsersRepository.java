package com.mpi.witcher.server.repositories;

import com.mpi.witcher.server.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Optional;

public class UsersRepository {
    private static PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    public static ArrayList<User> Users = new ArrayList<User>() {
        {
            add(new User("client", "Geralt of Rivia","/img/witcher-face.jpg","CLIENT", passwordEncoder.encode("123")));
            add(new User("employee", "Алхимик","/img/alchim.jpg","EMPLOYEE", passwordEncoder.encode("123")));
            add(new User("manager", "Руководитель","/img/boss.jpg","MANAGER", passwordEncoder.encode("123")));
        }
    };

    public static boolean createUser(String login, String password, String role) {
        for (User user : Users) {
            if (user.getLogin().toLowerCase().equals(login)) {
                return false;
            }
        }
        Users.add(new User(login, login, "/img/witcher-face.jpg", role.toUpperCase(), passwordEncoder.encode(password)));
        return true;
    }

    public static Optional<User> findByUserName(String name) {
        User currentUser = null;
        for (User user: UsersRepository.Users) {
            if(user.getUsername().equals(name)) {
                currentUser = user;
                break;
            }
        }
        if(currentUser == null) {
            return Optional.empty();
        }

        return Optional.of(currentUser);
    }
}
