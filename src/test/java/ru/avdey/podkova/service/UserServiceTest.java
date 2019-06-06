package ru.avdey.podkova.service;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import ru.avdey.podkova.model.Role;
import ru.avdey.podkova.model.User;
import ru.avdey.podkova.repository.UserRepo;

import java.util.Collections;

import static org.junit.Assert.*;

/**
 * модульное тестирование {@link UserService}
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService service;


    @MockBean
    private UserRepo userRepo;

    @Test
    public void testAddUser() {

        User user = new User();
        boolean b = service.addUser(user);
        Assert.assertTrue(b);

        Assert.assertTrue(CoreMatchers.is(user.getRoles()).matches(Collections.singleton(Role.USER)));

        Mockito.verify(userRepo, Mockito.times(1)).save(user);

    }


    @Test
    public void testAddUserFailed() {

        User user = new User();
        user.setUsername("Sanek");

        Mockito.doReturn(new User()) // возвращаем нового пользователя
                .when(userRepo)
                .findByUsername("Sanek");

        boolean isUserCreated = service.addUser(user);

        Assert.assertFalse(isUserCreated);
        Mockito.verify(userRepo, Mockito.times(0)).save(ArgumentMatchers.any(User.class));
    }
}