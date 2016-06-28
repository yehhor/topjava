package ru.javawebinar.topjava.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

/**
 * User: gkisline
 * Date: 26.08.2014
 */

@Repository
@Transactional(readOnly = true)
public class JpaUserMealRepositoryImpl implements UserMealRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public UserMeal save(UserMeal userMeal, int userId) {

        if (userMeal.isNew()) {
            em.persist(userMeal);
            return userMeal;
        } else {
            return em.merge(userMeal);
        }
    }

    @Override
    @Transactional
    public boolean delete(int id, int userId) {

        return em.createNamedQuery(UserMeal.DELETE).setParameter("id", userId).executeUpdate() != 0;
    }

    @Override
    public UserMeal get(int id, int userId) {
        return (UserMeal) em.createNamedQuery(UserMeal.GET, UserMeal.class).setParameter("userId", userId).getSingleResult();
    }

    @Override
    public List<UserMeal> getAll(int userId) {
        return em.createNamedQuery(UserMeal.ALL_SORTED, UserMeal.class).getResultList();
    }

    @Override
    public List<UserMeal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return null;
    }
}