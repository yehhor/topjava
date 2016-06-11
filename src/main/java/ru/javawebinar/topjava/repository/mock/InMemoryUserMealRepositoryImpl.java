package ru.javawebinar.topjava.repository.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.LoggedUser;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;
import ru.javawebinar.topjava.util.UserMealsUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.Collection;
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
            LOG.info("creating new meal id: " + userMeal.getId());
            userMeal.setUserId(LoggedUser.id());
        }
        if (!UserMealsUtil.isMealToCurrentUser(userMeal))
            throw new NotFoundException("wrong user");
        LOG.info("saving meal id: " + userMeal.getId());
        repository.put(userMeal.getId(), userMeal);
        return userMeal;
    }

    @Override
    public void delete(int id) {
        LOG.info("delete userMeal id " + id);
        if (UserMealsUtil.isMealToCurrentUser(repository.get(id)))
            repository.remove(id);
        else
            throw new NotFoundException("wrong user");
    }

    @Override
    public UserMeal get(int id) {
        if (UserMealsUtil.isMealToCurrentUser(repository.get(id)))
            return repository.get(id);
        else
            throw new NotFoundException("wrong user");
    }

    @Override
    public Collection<UserMeal> getAll() {
        return repository.values().stream().
                filter(UserMealsUtil::isMealToCurrentUser).
                sorted((u1, u2) -> -u1.getDateTime().compareTo(u2.getDateTime())).
                collect(Collectors.toList());
    }
}

