package ru.javawebinar.topjava.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.service.UserMealService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
 * Created by yehor on 11.07.2016.
 */
@Controller
@RequestMapping("/meals/edit")
public class MealEditController {

    @Autowired
    private UserMealService service;

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateMeal(HttpServletRequest request) {
        final UserMeal userMeal = new UserMeal(
                LocalDateTime.parse(request.getParameter("dateTime")),
                request.getParameter("description"),
                Integer.valueOf(request.getParameter("calories")));
        userMeal.setId(Integer.parseInt(request.getParameter("id")));
        service.update(userMeal, AuthorizedUser.id());
        return "redirect:/meals";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(HttpServletRequest request) {
        final UserMeal userMeal = new UserMeal(
                LocalDateTime.parse(request.getParameter("dateTime")),
                request.getParameter("description"),
                Integer.valueOf(request.getParameter("calories")));
        service.save(userMeal, AuthorizedUser.id());
        return "redirect:/meals";
    }
}
