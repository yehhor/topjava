package ru.javawebinar.topjava.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * GKislin
 * 11.01.2015.
 */
public class UserMealWithExceed {

    private final int id;

    private final LocalDateTime dateTime;

    private final String formatDateTime;

    private final String description;

    private final int calories;

    private final boolean exceed;

    public UserMealWithExceed(LocalDateTime dateTime, String description, int calories, boolean exceed, int id) {
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.exceed = exceed;
        this.formatDateTime = dateTime.format(DateTimeFormatter.ofPattern("d.L.u H:mm:00"));
        this.id = id;
    }

    @Override
    public String toString() {
        return "UserMealWithExceed{" +
                ", dateTime=" + dateTime +
                ", formatDateTime='" + formatDateTime + '\'' +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                ", exceed=" + exceed +
                '}';
    }

    public String getFormatDateTime() {
        return formatDateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public boolean isExceed() {
        return exceed;
    }

    public int getId() {
        return id;
    }
}
