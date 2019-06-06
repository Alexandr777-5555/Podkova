package ru.avdey.podkova.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.avdey.podkova.model.User;

/**
 *
 * хранит данные о пользователях
 */

public interface UserRepo  extends JpaRepository<User, Long> {


    /**
     * метод который возвращает пользователя
     * @return
     */
    User findByUsername(String username);



}
