package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.UserMeal;

import java.util.List;

/**
 * Created by yehor on 11.06.2016.
 */
public interface UserMealService {
    UserMeal save(UserMeal userMeal);

    void delete(int id);

    UserMeal get(int id);

    List<UserMeal> getAll();

    List<UserMeal> filterByDateTime(String startDate, String startTime, String endDate, String endTime);

    void search();
}
