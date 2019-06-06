package ru.avdey.podkova.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.avdey.podkova.model.Car;
import ru.avdey.podkova.model.User;
import ru.avdey.podkova.repository.CarRepo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Map;

/**
 * контролллер слушает запросы от пользователя
 */

@Controller
public class MainController {

    @Autowired // говорим спрингу что это бин
    private CarRepo carRepo;


    @GetMapping("/")
    public String indexing() {

        return "index";
    }


    /**
     * получаем все авто
     *
     * @param model
     * @return
     */
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('OPERATOR')")
    @GetMapping("/main") // замэппим на мэйн!!!
    public String main(Map<String, Object> model) {

        Iterable<Car> cars = carRepo.findAll();
        model.put("cars", cars);

        return "main";
    }


    /**
     * это метод добавляет автомобиль в список
     *
     * @param user
     * @param brandAuto
     * @param pts
     * @param color
     * @param model
     * @return
     */
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('OPERATOR')")
    @PostMapping("/main")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam String brandAuto,
            @RequestParam String pts,
            @RequestParam String color,
            Map<String, Object> model) {

        Car car = new Car(brandAuto, pts, color, user);
        carRepo.save(car);

        // список автомобилей
        Iterable<Car> cars = carRepo.findAll();
        model.put("cars", cars);
        return "main";
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('OPERATOR')")
    @GetMapping("filter")  // был   @PostMapping("filter")
    public String filter(@RequestParam String filter, Model model) {
        Iterable<Car> carList;
        if (filter != null && !filter.isEmpty()) {
            carList = carRepo.findByBrandAuto(filter);
        } else {
            carList = carRepo.findAll();
        }
        model.addAttribute("cars", carList);
        return "main";
    }


    /**
     * поиск автомобилей по цвету
     *
     * @param color
     * @param model
     * @return
     */
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('OPERATOR')")
    @PostMapping("filterColor")
    public String filterColor(@RequestParam String color, Model model) {

        Iterable<Car> carList;

        if (color != null && !color.isEmpty()) {
            carList = carRepo.findByColor(color);
        } else {
            carList = carRepo.findAll();
        }

        model.addAttribute("cars", carList);

        return "main";
    }


    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('OPERATOR')")
    @PostMapping("date")
    public String date(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date ,
            Model model) throws ParseException {


        Iterable<Car> carList;
        if (date != null) {
            //yyyy-dd-mm
         //  Date dateFormat=new SimpleDateFormat("yyyy-mm-dd").parse(date); //  yyyy-mm-dd   dd-mm-yyyy
            carList = carRepo.findByDate(date);
        } else {
            carList = carRepo.findAll();
        }
        model.addAttribute("cars", carList);
        return "main";
    }


}
