package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.LoggedUser;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.service.UserMealService;
import ru.javawebinar.topjava.to.UserMealWithExceed;
import ru.javawebinar.topjava.util.TimeUtil;
import ru.javawebinar.topjava.util.UserMealsUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * GKislin
 * 06.03.2015.
 */
@Controller
public class UserMealRestController {

    @Autowired
    private UserMealService service;

    public UserMeal save(UserMeal userMeal) {
        return service.save(userMeal);
    }

    public void delete(int id) {
        service.delete(id);
    }

    public UserMeal get(int id) {
        return service.get(id);
    }

    public List<UserMealWithExceed> getAll() {
        return UserMealsUtil.getWithExceeded(service.getAll(), UserMealsUtil.DEFAULT_CALORIES_PER_DAY);
    }

    public List<UserMealWithExceed> filter(String startDate, String startTime, String endDate, String endTime) {
        return UserMealsUtil.getWithExceeded(service.filterByDateTime(startDate, startTime, endDate, endTime),
                UserMealsUtil.DEFAULT_CALORIES_PER_DAY);
    }
}
