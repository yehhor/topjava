package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.UserMeal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * Created by yehor on 11.06.2016.
 */
public interface UserMealService {
    UserMeal save(UserMeal userMeal);

    void delete(int id);

    UserMeal get(int id);

    List<UserMeal> getAll();

    List<UserMeal> filterByTime(LocalTime startTime, LocalTime endTime);

    void filterByDateTime(LocalDateTime startTime, LocalDateTime endTime);

    void filterByDate(LocalDate startTime, LocalDate endTime);

    void search();
}
