package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.Database.DataObject;
import ru.javawebinar.topjava.dao.CRUD;
import ru.javawebinar.topjava.dao.MealServiceImpl;
import ru.javawebinar.topjava.util.UserMealsUtil;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;

/**
 * Created by yehor on 07.06.2016.
 */
public class CRUDServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(CRUDServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        CRUD crud = new MealServiceImpl();
        Integer maxCalories = 2000;
        LocalTime timeStart = LocalTime.MIN;
        LocalTime timeEnd = LocalTime.MAX;
        if ("add".equals(action)) {
            try {
                String date = request.getParameter("date");
                String description = request.getParameter("description");
                int calories = Integer.parseInt(request.getParameter("calories"));
                crud.create(LocalDateTime.parse(date), description, calories);
            } catch (Exception e) {
                LOG.debug(e.getMessage());
            }
        } else if ("delete".equals(action)) {
            try {
                int uid = Integer.parseInt(request.getParameter("mealId"));
                crud.delete(uid);

            } catch (Exception e) {
                LOG.debug(e.getMessage());
            }
        } else if ("filter".equals(action))
        {
            try{
                String timeFrom = request.getParameter("timeFrom");
                String timeDue = request.getParameter("timeDue");
                maxCalories = Integer.parseInt(request.getParameter("caloriesPerDay"));
                timeStart = LocalTime.parse(timeFrom, DateTimeFormatter.ofPattern("HH:mm"));
                timeEnd = LocalTime.parse(timeDue, DateTimeFormatter.ofPattern("HH:mm"));
                //ToDo update?
            }catch (Exception e)
            {
                LOG.debug(e.getLocalizedMessage());
            }
        }else if ("update".equals(action))
        {
            try{
                String date = request.getParameter("date");
                String description = request.getParameter("description");
                int calories = Integer.parseInt(request.getParameter("calories"));
                int id = Integer.parseInt(request.getParameter("mealId"));
                crud.update(id, LocalDateTime.parse(date), description, calories);
            }catch (Exception e)
            {
                LOG.debug(e.getLocalizedMessage());
            }
        }
        request.setAttribute("mealList", UserMealsUtil.getFilteredWithExceeded(
                new LinkedList<>(DataObject.getMealList()), timeStart, timeEnd, maxCalories
        ));
        RequestDispatcher view = request.getRequestDispatcher("/mealList.jsp");
        view.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }
}
