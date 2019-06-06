package ru.avdey.podkova.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.avdey.podkova.model.Car;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * интерфейс для доступа к хранилищу автомобилей
 */

public interface CarRepo extends CrudRepository<Car, Integer> {


    /**
     * поиск авто в бд по названию
     * @param brandAuto
     * @return
     */
    List<Car> findByBrandAuto(String brandAuto);


    /**
     * поиск автомобилей по цвету
     * @param color
     * @return
     */
    List<Car> findByColor(String color);


    /**
     * поиск авто по дате добавления
     * @param date
     * @return
     */

    List<Car> findByDate( LocalDate date); //findByTimeAdd @Param("date")



}
