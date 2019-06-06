package ru.avdey.podkova.model;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * класс автомобиль
 *
 */

@Entity
public class Car {

    public Car() {
    }

    public Car(String brandAuto, String pts, String color , User user) {
        this.owner=user;
        this.brandAuto = brandAuto;
        this.pts = pts;
        this.color = color;
        this.date=LocalDate.now();
            }


     @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String brandAuto;// марка автомобиля
    private String pts ;   // птс авто
    private String color ; // цвет автомобиля
    //@Temporal(TemporalType.DATE)
    private LocalDate date;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User  owner;


    /**
     * проверяет если ли у нас автор у сообщения
     * @return
     */
    public String getOwnerName(){

        return  owner!=null ? owner.getUsername() : "<none>";
    }




    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBrandAuto() {
        return brandAuto;
    }

    public void setBrandAuto(String brandAuto) {
        this.brandAuto = brandAuto;
    }

    public String getPts() {
        return pts;
    }

    public void setPts(String pts) {
        this.pts = pts;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
