package ru.javawebinar.topjava.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.javawebinar.topjava.LoggedUser;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.util.DbPopulator;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by yehor on 22.06.2016.
 */
@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class UserMealServiceTest {

    private static final Logger LOG = LoggerFactory.getLogger(UserMealServiceTest.class);

    @Autowired
    protected UserMealService service;

    @Autowired
    private DbPopulator dbPopulator;

    @Before
    public void setUp() throws Exception {
        dbPopulator.execute();
    }

    @Test
    public void testSaveNew() throws Exception {
        service.save(new UserMeal(LocalDateTime.now(), "TestNew", 15), LoggedUser.id());
    }

    @Test
    public void testSaveNotNew() throws Exception {
        UserMeal meal = new UserMeal(LocalDateTime.now(), "TestNotNew", 1555);
        meal.setId(100002);
        service.save(meal, 100001);
    }

    @Test
    public void testGetAll() throws Exception {
        List<UserMeal> meals = service.getAll(100001).stream().collect(Collectors.toList());
        if (meals.size() != 4)
            throw new Exception();
    }

    @Test
    public void testDelete() throws Exception {
        UserMeal meal = new UserMeal(LocalDateTime.now(), "TestNotNew", 1555);
        meal.setId(100002);
        service.save(meal, 100001);
        service.delete(100002, 100001);
        if (service.getAll(100001).contains(meal))
            throw new Exception();
    }

    @Test
    public void testGet() throws Exception{
        UserMeal meal = service.get(100003, 100001);
        if(meal == null || meal.getId() != 100003)
            throw new Exception();
    }

    @Test
    public void getBetween() throws Exception{
        List<UserMeal> meals = service.getBetweenDateTimes(LocalDateTime.MIN, LocalDateTime.now().minusHours(1), 100001).
                stream().collect(Collectors.toList());
        if(meals.size() != 1)
            throw new Exception();
    }
}
