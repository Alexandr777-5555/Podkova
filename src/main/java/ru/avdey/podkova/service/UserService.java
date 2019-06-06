package ru.avdey.podkova.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.avdey.podkova.model.Role;
import ru.avdey.podkova.model.User;
import ru.avdey.podkova.repository.UserRepo;

import java.util.Collections;

/**
 *
 */
@Service
public class UserService implements UserDetailsService {


    @Autowired
    private UserRepo repo;



    /**
     * @param s
     * @return возвращаем пользователя
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return repo.findByUsername(s);

    }


    public boolean addUser(User user) {

        User userFromDb = repo.findByUsername(user.getUsername());

        if (userFromDb != null) {

            return false;
        }

        if (repo.count() < 1 ) {
            user.setRoles(Collections.singleton(Role.ADMIN));
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));

        repo.save(user);

        return true;
    }
}
