package ru.javawebinar.topjava;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.util.UserMealsUtil;

/**
 * GKislin
 * 06.03.2015.
 */
public class LoggedUser {
    private static final Logger LOG = LoggerFactory.getLogger(LoggedUser.class);
    private static int id = 1;

    public static int getId() {
        return id;
    }

    public static void setId(int newId) {
        LOG.info("userId selected = " + newId);
        id = newId;
    }

    public static int id() {
        return getId();
    }


    public static int getCaloriesPerDay() {
        return UserMealsUtil.DEFAULT_CALORIES_PER_DAY;
    }
}
