package ru.javawebinar.topjava;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.web.meal.UserMealRestController;
import ru.javawebinar.topjava.web.user.AdminRestController;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;

/**
 * User: gkislin
 * Date: 22.08.2014
 */
public class SpringMain {
    public static void main(String[] args) {
        // java 7 Automatic resource management
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml")) {
            System.out.println(Arrays.toString(appCtx.getBeanDefinitionNames()));
            AdminRestController adminUserController = appCtx.getBean(AdminRestController.class);
            System.out.println(adminUserController.create(new User(1, "userName", "email", "password", Role.ROLE_ADMIN)));
            UserMealRestController userMealRestController = appCtx.getBean(UserMealRestController.class);

            userMealRestController.getAll().forEach(System.out::println);
            System.out.println("************* save next ***********");
            System.out.println(userMealRestController.save(new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 5050)));
            System.out.println("saved above?");

            userMealRestController.getAll().forEach(System.out::println);

            System.out.println("************delete id 3******************");
            userMealRestController.delete(3);
            System.out.println("deleted?");

            userMealRestController.getAll().forEach(System.out::println);
            System.out.println("********************* filtering next from 19 to 23 ****************");
            userMealRestController.filterByTime(LocalTime.of(10, 0), LocalTime.of(13, 0)).forEach(System.out::println);
        }
    }
}
