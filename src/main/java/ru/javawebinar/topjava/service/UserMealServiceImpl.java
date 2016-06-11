package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;
import ru.javawebinar.topjava.util.TimeUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
        return repository.save(userMeal);
    }

    @Override
    public void delete(int id) {
        repository.delete(id);
    }

    public UserMeal get(int id) {
        return repository.get(id);
    }

    @Override
    public List<UserMeal> getAll() {
        return repository.getAll().stream().collect(Collectors.toList());
        //ToDo collection or List?
    }

    @Override
    public List<UserMeal> filterByTime(LocalTime startTime, LocalTime endTime) {
        return repository.getAll().stream().
                filter(u -> TimeUtil.isBetween(u.getDateTime().toLocalTime(), startTime, endTime)).
                collect(Collectors.toList());
    }

    @Override
    public void filterByDateTime(LocalDateTime startTime, LocalDateTime endTime) {
        //ToDo filterByDateTime
    }

    @Override
    public void filterByDate(LocalDate startTime, LocalDate endTime) {
        //ToDo FilterByDate
    }

    @Override
    public void search() {
        //ToDo search
    }
}
