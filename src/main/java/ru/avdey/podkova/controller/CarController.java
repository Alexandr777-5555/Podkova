package ru.avdey.podkova.controller;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import ru.avdey.podkova.model.Car;
import ru.avdey.podkova.model.User;

import java.util.Set;

@Controller
public class CarController {



    @GetMapping("/user-cars/{user}")
    public String userCars(
            @AuthenticationPrincipal User currentUser,
            @PathVariable User user,
            Model model,
            @RequestParam(required = false) Car car

    ){

      Set<Car> cars=user.getCars();

        model.addAttribute("cars", cars);
        model.addAttribute("car", car);
        model.addAttribute("isCurrentUser", currentUser.equals(user));



        return "userCars";
    }
}
