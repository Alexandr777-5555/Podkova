package ru.avdey.podkova.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.avdey.podkova.model.Role;
import ru.avdey.podkova.model.User;
import ru.avdey.podkova.repository.UserRepo;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


/**
 *
 *
 *  контролллер слушает запросы от пользователя в данном случае по этому пути @RequestMapping("/user")
 */
@Controller
@RequestMapping("/user") // что бы не подписывать что метод содержит в своем имени юзеп поэтому на уровне класса
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {
    @Autowired
    private UserRepo repository;


    /**
     * список пользователей возвращает
     * @param model
     * @return
     */
    @GetMapping
    public String userList(Model model) {
        model.addAttribute("users", repository.findAll());


        return "userList";
    }


    /**
     * маппинг для редактирования пользователя
     * @PathVariable User user - получаем пользователя по идентификатору пользователя
     *
     */
    @GetMapping("{user}") // {user} - это идентификатор пользователя / нотификатор юзера

    public String userEditForm(@PathVariable User user, Model model) {


        model.addAttribute("user", user);

        // вывести список ролей

        model.addAttribute("roles", Role.values());

        return "userEdit";
    }



    @PostMapping
    public String userSave(
            @RequestParam String username,
            @RequestParam Map<String, String> form,  // получаем только те параметры у
            // которого ключи и значения являются строками
            @RequestParam("userId") User user) {

        user.setUsername(username);
        Set<String> rolles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());

        user.getRoles().clear(); //

        for (String key : form.keySet()) {
            if (rolles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }

        repository.save(user);

        return "redirect:/user"; // редирект на список пользователей
    }

}