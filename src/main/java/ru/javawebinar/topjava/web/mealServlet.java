package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.Database.DataObject;
import ru.javawebinar.topjava.util.UserMealsUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalTime;
import java.util.LinkedList;

/**
 * Created by yehor on 07.06.2016.
 */
public class MealServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(MealServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.debug("redirect to mealList");

        request.setAttribute("mealList", UserMealsUtil.getFilteredWithExceeded(
                new LinkedList<>(DataObject.getMealList()), LocalTime.MIN, LocalTime.MAX, 2000
        ));

        RequestDispatcher view = request.getRequestDispatcher("/mealList.jsp");
        view.forward(request, response);
    }
}
