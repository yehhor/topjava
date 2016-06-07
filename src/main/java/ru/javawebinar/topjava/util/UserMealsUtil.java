package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.MealList;
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

    private static LocalTime startTime = LocalTime.of(0,0);
    private static LocalTime endTime = LocalTime.of(23,59);
    private static int caloriesPerDay = 4500;

    public static void main(String[] args) {
    }

    public static List<UserMealWithExceed> getFilteredWithExceeded(List<UserMeal> mealList) {
        Map<LocalDate, Integer> caloriesSumByDate = mealList.stream()
                .collect(
                        Collectors.groupingBy(um -> um.getDateTime().toLocalDate(),
                                Collectors.summingInt(UserMeal::getCalories))
                );

        return mealList.stream()
                .filter(um -> TimeUtil.isBetween(um.getDateTime().toLocalTime(), startTime, endTime))
                .map(um -> createWithExceed(um, caloriesSumByDate.get(um.getDateTime().toLocalDate()) > caloriesPerDay))
                .collect(Collectors.toList());
    }

    public static List<UserMealWithExceed> getFilteredWithExceededByCycle(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

        final Map<LocalDate, Integer> caloriesSumPerDate = new HashMap<>();
        mealList.forEach(meal -> caloriesSumPerDate.merge(meal.getDateTime().toLocalDate(), meal.getCalories(), Integer::sum));

        final List<UserMealWithExceed> mealExceeded = new ArrayList<>();
        mealList.forEach(meal -> {
            final LocalDateTime dateTime = meal.getDateTime();
            if (TimeUtil.isBetween(dateTime.toLocalTime(), startTime, endTime)) {
                mealExceeded.add(createWithExceed(meal, caloriesSumPerDate.get(dateTime.toLocalDate()) > caloriesPerDay));
            }
        });
        return mealExceeded;
    }

    public static UserMealWithExceed createWithExceed(UserMeal um, boolean exceeded) {
        return new UserMealWithExceed(um.getDateTime(), um.getDescription(), um.getCalories(), exceeded, um.getId());
    }

    public static void setStartTime(LocalTime startTime) {
        UserMealsUtil.startTime = startTime;
    }

    public static void setEndTime(LocalTime endTime) {
        UserMealsUtil.endTime = endTime;
    }

    public static void setCaloriesPerDay(int caloriesPerDay) {
        UserMealsUtil.caloriesPerDay = caloriesPerDay;
    }
}