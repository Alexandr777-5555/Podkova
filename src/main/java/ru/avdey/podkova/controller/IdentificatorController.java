package ru.avdey.podkova.controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.avdey.podkova.model.User;

@Controller
@RequestMapping("/identificator")
@PreAuthorize("hasAuthority('USER')")
public class IdentificatorController {


    @GetMapping
    public String getUser(
            @AuthenticationPrincipal User user,
            Model model
    ) {

        model.addAttribute("user", user.getId());

        return "identificator";
    }


}
