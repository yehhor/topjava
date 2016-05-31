package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

/**
 * GKislin
 * 31.05.2015.
 */
public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 27,10,0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 27,13,0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 27,20,0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 27,20,0), "Завтрак", 1000), // 3000 cal
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 22,10,0), "Завтрак", 1000), // 1000 cal
                new UserMeal(LocalDateTime.of(2015, Month.JANUARY, 31,13,0), "Обед", 5000), // 5000 cal
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 28,13,0), "Обед", 2500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 28,13,55), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 28,18,44), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,13,0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,13,0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,13,0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,13,0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,20,0), "Ужин", 510) // 2510 cal
        );
        getFilteredMealsWithExceeded(mealList, LocalTime.of(13, 30), LocalTime.of(21,0), 2000).stream().
        forEach(System.out::println);

    }

    public static List<UserMealWithExceed>  getFilteredMealsWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

        if (mealList == null || startTime == null || endTime == null)
            throw new RuntimeException(); //ToDo replace mealList = null exception

        Map<LocalDate, Integer> map = mealList.stream().
                collect(Collectors.toMap(
                        p -> p.getDateTime().toLocalDate(),
                        UserMeal::getCalories,
                        (i, i1) -> i + i1));
        List<UserMealWithExceed> list = mealList.stream().
                filter(userMeal -> TimeUtil.isBetween(userMeal.getDateTime().toLocalTime(), startTime, endTime)).
                map(userMeal -> new UserMealWithExceed(userMeal.getDateTime(),
                        userMeal.getDescription(), userMeal.getCalories(), map.get(userMeal.getDateTime().toLocalDate()) > caloriesPerDay)).
                collect(Collectors.toList());

        return list;
    }
}
