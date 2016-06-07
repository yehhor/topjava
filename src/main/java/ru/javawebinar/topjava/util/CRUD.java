package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;

import java.time.LocalTime;

/**
 * Created by yehor on 07.06.2016.
 */
public interface CRUD {

    void create(UserMeal um);

    void read();

    void update(LocalTime from, LocalTime dueTo, Integer calPerDay);

    void delete(Integer id);

}
