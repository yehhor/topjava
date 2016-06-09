package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.Database.DataObject;

import java.time.LocalDateTime;


/**
 * Created by yehor on 07.06.2016.
 */
public class MealServiceImpl implements CRUD {

    @Override
    public void create(LocalDateTime dateTime, String description, int calories) {
        DataObject.putMeal(dateTime, description, calories);
    }


    @Override
    public void update(Integer id, LocalDateTime dateTime, String description, int calories) {
        DataObject.updateMeal(id, dateTime, description, calories);
    }

    @Override
    public void delete(Integer id) {
        //ToDo fix removing logic
        DataObject.removeMeal(id);
    }
}
