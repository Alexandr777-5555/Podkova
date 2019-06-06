package ru.avdey.podkova.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.avdey.podkova.model.User;
import ru.avdey.podkova.service.UserService;

import java.util.Map;

@Controller
public class RegistrationController {



    @Autowired
    private UserService service;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }


    /**
     * добавление юзера
     *
     * @param user
     * @param model
     * @return
     */
    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model) {
        if (!service.addUser(user)) {
            model.put("usernameError", "User exists!");
            return "registration";
        }

        service.addUser(user);

        return "redirect:/login";
    }

}
