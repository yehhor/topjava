package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

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
                new UserMeal(LocalDateTime.of(2015, Month.JANUARY, 31,13,0), "Обед", 5000), // 500 cal
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,13,0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,13,0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,13,0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,13,0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,20,0), "Ужин", 510) // 2510 cal
        );
        getFilteredMealsWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(22,0), 2000).stream().
        forEach(System.out::println);

    }

    public static List<UserMealWithExceed>  getFilteredMealsWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

        if (mealList == null || startTime == null || endTime == null)
            return null;

        List<UserMealWithExceed> list = mealList.parallelStream().map(userMeal -> new UserMealWithExceed(userMeal.getDateTime(), userMeal.getDescription(),
                userMeal.getCalories(),
                mealList.parallelStream().
                        filter(userMeal1 ->
                userMeal.getDateTime().toLocalDate().equals(userMeal1.getDateTime().toLocalDate()) &&
                        TimeUtil.isBetween(userMeal1.getDateTime().toLocalTime(), startTime, endTime)).
                        mapToInt(UserMeal::getCalories).sum() > caloriesPerDay)).collect(Collectors.toList()).
                stream().sorted().collect(Collectors.toList());


        return list;
    }
}
