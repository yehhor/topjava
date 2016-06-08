package ru.javawebinar.topjava.Database;

/**
 * Created by yehor on 07.06.2016.
 */


import ru.javawebinar.topjava.model.UserMeal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class DataObject {

    private static int counter = 0;

    public DataObject() {

    }

    private static final Map<Integer, UserMeal> mealMap = new ConcurrentHashMap<>();

    static {
        mealMap.put(counter, new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500, counter++));
        mealMap.put(counter, new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000, counter++));
        mealMap.put(counter, new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500, counter++));
        mealMap.put(counter, new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000, counter++));
        mealMap.put(counter, new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500, counter++));
        mealMap.put(counter, new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510, counter++));

    }

    public static List<UserMeal> getMealList()
    {
        return new LinkedList<>(mealMap.values());
    }

    public static void removeMeal(int id)
    {
        mealMap.remove(id);
    }

    public static void putMeal(LocalDateTime dateTime, String description, int calories) {
        mealMap.put(counter, new UserMeal(dateTime, description, calories, counter++));
    }

    public static void updateMeal(Integer id, LocalDateTime dateTime, String description, int calories)
    {
        mealMap.get(id).setCalories(calories);
        mealMap.get(id).setDateTime(dateTime);
        mealMap.get(id).setDescription(description);
    }
}
