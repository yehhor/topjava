package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.UserMeal;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;

/**
 * GKislin
 * 06.03.2015.
 */
public interface UserMealRepository {
    UserMeal save(UserMeal userMeal);

    boolean delete(int id);

    UserMeal get(int id);

    Collection<UserMeal> getAll();

    List<UserMeal> getFilteredList(LocalTime startTime, LocalDate startDate, LocalTime endTime, LocalDate endDate);
}
