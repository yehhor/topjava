package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.MealList;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalTime;
import java.util.List;

/**
 * Created by yehor on 07.06.2016.
 */
public class CrudUtil implements CRUD {
    @Override
    public void create(UserMeal um) {
        MealList.getMealList().add(um);
    }

    @Override
    public void read() {

    }


    @Override
    public void update(LocalTime from, LocalTime dueTo, Integer calPerDay) {
        UserMealsUtil.setStartTime(from);
        UserMealsUtil.setEndTime(dueTo);
        UserMealsUtil.setCaloriesPerDay(calPerDay);
    }

    @Override
    public void delete(Integer id) {
        for (UserMeal userMeal : MealList.getMealList())
        {
            if (userMeal.getId().equals(id))
            {
                MealList.getMealList().remove(userMeal);
                break;
            }
        }
    }
}
