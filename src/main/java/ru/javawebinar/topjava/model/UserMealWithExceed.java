package ru.javawebinar.topjava.model;

import java.time.LocalDateTime;

/**
 * GKislin
 * 11.01.2015.
 */
public class UserMealWithExceed implements Comparable<UserMealWithExceed>{

    private static int globalCounter = 0;

    private int counter = 0;


    protected final LocalDateTime dateTime;

    protected final String description;

    protected final int calories;

    protected final boolean exceed;

    public UserMealWithExceed(LocalDateTime dateTime, String description, int calories, boolean exceed) {
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.exceed = exceed;
        counter = ++globalCounter;

    }

    @Override
    public String toString() {
        return String.format("UserMeal # %d\n time %s \n calories: %d \n exceed: %b \n ***********************",
                counter, dateTime.toString(), calories, exceed);
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    @Override
    public int compareTo(UserMealWithExceed o) {
        if (dateTime.isBefore(o.getDateTime()))
            return -1;
        else if (dateTime.isAfter(o.getDateTime()))
            return 21;
        else return 0;
    }
}

