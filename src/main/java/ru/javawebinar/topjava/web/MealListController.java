package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.service.UserMealService;
import ru.javawebinar.topjava.to.UserMealWithExceed;
import ru.javawebinar.topjava.util.TimeUtil;
import ru.javawebinar.topjava.util.UserMealsUtil;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

/**
 * GKislin
 * 06.03.2015.
 */
@Controller
@RequestMapping(value = "/meals")
public class MealListController {
    private static final Logger LOG = LoggerFactory.getLogger(MealListController.class);

    @Autowired
    private UserMealService service;

    public UserMeal get(int id) {
        int userId = AuthorizedUser.id();
        LOG.info("get meal {} for User {}", id, userId);
        return service.get(id, userId);
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable int id, HttpServletRequest request) {
        int userId = AuthorizedUser.id();
        LOG.info("delete meal {} for User {}", id, userId);
        service.delete(id, userId);
        return "redirect:/meals";
    }

    @RequestMapping("/add")
    public String add(HttpServletRequest request)
    {
        request.setAttribute("action", "create");
        return "mealEdit";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getAll(HttpServletRequest request) {
        int userId = AuthorizedUser.id();
        LOG.info("getAll for User {}", userId);
        request.setAttribute("mealList", UserMealsUtil.getWithExceeded(service.getAll(userId), AuthorizedUser.getCaloriesPerDay()));
        return "mealList";
    }

    @RequestMapping("/update/{id}")
    public String update(@PathVariable int id, HttpServletRequest request) {
        UserMeal meal = service.get(id, AuthorizedUser.id());
        request.setAttribute("meal", meal);
        request.setAttribute("action", "update");
        return "mealEdit";
    }

    public List<UserMealWithExceed> getBetween(LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {
        int userId = AuthorizedUser.id();
        LOG.info("getBetween dates {} - {} for time {} - {} for User {}", startDate, endDate, startTime, endTime, userId);

        return UserMealsUtil.getFilteredWithExceeded(
                service.getBetweenDates(
                        startDate != null ? startDate : TimeUtil.MIN_DATE, endDate != null ? endDate : TimeUtil.MAX_DATE, userId),
                startTime != null ? startTime : LocalTime.MIN, endTime != null ? endTime : LocalTime.MAX, AuthorizedUser.getCaloriesPerDay()
        );
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.valueOf(paramId);
    }
}