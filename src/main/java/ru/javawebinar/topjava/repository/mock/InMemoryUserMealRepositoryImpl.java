package ru.javawebinar.topjava.repository.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.LoggedUser;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;
import ru.javawebinar.topjava.util.TimeUtil;
import ru.javawebinar.topjava.util.UserMealsUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * GKislin
 * 15.09.2015.
 */
@Repository
public class InMemoryUserMealRepositoryImpl implements UserMealRepository {
    private static final Logger LOG = LoggerFactory.getLogger(InMemoryUserMealRepositoryImpl.class);
    private Map<Integer, UserMeal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        UserMealsUtil.MEAL_LIST.forEach(this::save);
    }

    @Override
    public UserMeal save(UserMeal userMeal) {
        if (userMeal.isNew()) {
            userMeal.setId(counter.incrementAndGet());
            userMeal.setUserId(LoggedUser.id());
            LOG.info("creating new meal id: " + userMeal.getId());
        }
        LOG.info("saving meal id: " + userMeal.getId());
        repository.put(userMeal.getId(), userMeal);
        return userMeal;
    }

    @Override
    public boolean delete(int id) {
        LOG.info("delete userMeal id " + id);
        return repository.remove(id) != null;
    }

    @Override
    public UserMeal get(int id) {
        LOG.info("getting meal id " + id);
        return repository.get(id);
    }

    @Override
    public Collection<UserMeal> getAll() {
        LOG.info("getting all meals");
        return repository.values().stream().
                filter(UserMealsUtil::isMealToCurrentUser).
                sorted((u1, u2) -> -u1.getDateTime().compareTo(u2.getDateTime())).
                collect(Collectors.toList());
    }

    @Override
    public List<UserMeal> getFilteredList(LocalTime startTime, LocalDate startDate, LocalTime endTime, LocalDate endDate)
    {
        LOG.info("startTime = " + startTime +
        "\n startDate = " + startDate +
        "\n endTime = " + endTime +
        "\n endDate = " + endDate);
        return getAll().
                stream().
                filter(meal -> TimeUtil.isBetween(meal.getDateTime().toLocalTime(), startTime, endTime) &&
                TimeUtil.isBetween(meal.getDateTime().toLocalDate(), startDate, endDate)).
                collect(Collectors.toList());
    }
}

