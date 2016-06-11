package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.LoggedUser;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;
import ru.javawebinar.topjava.util.exception.ExceptionUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * GKislin
 * 06.03.2015.
 */
@Service
public class UserMealServiceImpl implements UserMealService {

    @Autowired
    private UserMealRepository repository;

    @Override
    public UserMeal save(UserMeal userMeal) {
        ExceptionUtil.checkNotFound(userMeal, "meal is null");
        UserMeal userMealDB;
        if (userMeal.isNew()) {
            return repository.save(userMeal);
        } else if ((userMealDB = repository.get(userMeal.getId())).getUserId() == LoggedUser.id()) {
            userMeal.setUserId(userMealDB.getUserId());
            return repository.save(userMeal);
        } else
            throw new NotFoundException("wrong userdId");
    }

    @Override
    public void delete(int id) {
        ExceptionUtil.checkNotFoundWithId(repository.delete(id), id);
    }

    public UserMeal get(int id) {
        return ExceptionUtil.checkNotFoundWithId(repository.get(id), id);
    }

    @Override
    public List<UserMeal> getAll() {
        return repository.getAll().stream().collect(Collectors.toList());
        //ToDo collection or List?
    }

    @Override
    public List<UserMeal> filterByDateTime(String sDate, String sTime, String eDate, String eTime) {
        LocalDate startDate, endDate;
        LocalTime startTime, endTime;
        if (sDate.isEmpty())
            startDate = LocalDate.MIN;
        else
            startDate = LocalDate.parse(sDate);

        if (sTime.isEmpty())
            startTime = LocalTime.MIN;
        else
            startTime = LocalTime.parse(sTime);

        if (eDate.isEmpty())
            endDate = LocalDate.now();
        else
            endDate = LocalDate.parse(eDate);

        if (eTime.isEmpty())
            endTime = LocalTime.MAX;
        else
            endTime = LocalTime.parse(eTime);

        return repository.getFilteredList(startTime, startDate, endTime, endDate);
    }

    @Override
    public void search() {
        //ToDo search
    }
}
