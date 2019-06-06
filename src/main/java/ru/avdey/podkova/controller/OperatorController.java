package ru.avdey.podkova.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.avdey.podkova.model.Car;
import ru.avdey.podkova.model.User;
import ru.avdey.podkova.repository.CarRepo;

import java.util.Map;

@Controller
@RequestMapping("/operator")
@PreAuthorize("hasAuthority('OPERATOR')")
public class OperatorController {

    @Autowired
    private CarRepo repository;  // список тачек



    @GetMapping
    public String carList() {


        return "carOperatorList";
    }


    /**
     * сохраняем авто на странице /operator
     *
     * @param user_id
     * @param brandAuto
     * @param pts
     * @param color
     * @param model
     * @return
     */
    @PostMapping()
    public String add(
            @RequestParam String brandAuto,
            @RequestParam String pts,
            @RequestParam String color,
            @RequestParam User user_id,
            Map<String, Object> model) {

        Car car = new Car( brandAuto, pts, color, user_id);
        repository.save(car);



        return "carOperatorList";
    }



}



