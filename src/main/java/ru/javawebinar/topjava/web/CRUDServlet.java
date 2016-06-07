package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.model.MealList;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.util.CRUD;
import ru.javawebinar.topjava.util.CrudUtil;
import ru.javawebinar.topjava.util.UserMealsUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Created by yehor on 07.06.2016.
 */
public class CRUDServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(CRUDServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        CRUD crud = new CrudUtil();
        if ("add".equals(action)) {
            try {
                String date = request.getParameter("date");
                int year = Integer.parseInt(date.split("-")[0]);
                int month = Integer.parseInt(date.split("-")[1]);
                int day = Integer.parseInt(date.split("T")[0].split("-")[2]);
                int hour = Integer.parseInt(date.split("T")[1].split(":")[0]);
                int min = Integer.parseInt(date.split("T")[1].split(":")[1]);
                String description = request.getParameter("description");
                int calories = Integer.parseInt(request.getParameter("calories"));
                UserMeal um = new UserMeal(LocalDateTime.of(year, month, day, hour, min), description, calories);
                crud.create(um);
            } catch (Exception e) {
                LOG.debug(e.getMessage());
            }
        } else if ("delete".equals(action)) {
            try {
                int uid = Integer.parseInt(request.getParameter("uid"));
                crud.delete(uid);
            } catch (Exception e) {
                LOG.debug(e.getMessage());
            }
        } else if ("filter".equals(action))
        {
            try{
                String timeFrom = request.getParameter("timeFrom");
                String timeDue = request.getParameter("timeDue");
                Integer cals = Integer.parseInt(request.getParameter("caloriesPerDay"));
                LocalTime startTime = LocalTime.parse(timeFrom, DateTimeFormatter.ofPattern("HH:mm"));
                LocalTime endTime = LocalTime.parse(timeDue, DateTimeFormatter.ofPattern("HH:mm"));
                crud.update(startTime, endTime, cals);
            }catch (Exception e)
            {
                LOG.debug(e.getLocalizedMessage());
            }
        }

        request.setAttribute("mealList", UserMealsUtil.getFilteredWithExceeded(MealList.getMealList()));
        RequestDispatcher view = request.getRequestDispatcher("/mealList.jsp");
        view.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }
}
