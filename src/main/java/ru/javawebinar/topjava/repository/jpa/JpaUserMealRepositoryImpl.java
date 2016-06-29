package ru.javawebinar.topjava.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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
            User user = em.getReference(User.class, userId);
            userMeal.setUser(user);
            em.persist(userMeal);
            return userMeal;
        } else {
            return em.createNamedQuery(UserMeal.UPDATE).
                    setParameter("calories", userMeal.getCalories()).
                    setParameter("datetime", userMeal.getDateTime()).
                    setParameter("description", userMeal.getDescription()).
                    setParameter("id", userMeal.getId()).
                    setParameter("userId", userId).executeUpdate() == 0 ? null : userMeal;
        }
    }

    @Override
    @Transactional
    public boolean delete(int id, int userId) {

        return em.createNamedQuery(UserMeal.DELETE).setParameter("id", id).setParameter("userId", userId).executeUpdate() != 0;
    }

    @Override
    public UserMeal get(int id, int userId) {
        try {
            return em.createNamedQuery(UserMeal.GET, UserMeal.class).setParameter("userId", userId).setParameter("id", id).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<UserMeal> getAll(int userId) {
        return em.createNamedQuery(UserMeal.ALL_SORTED, UserMeal.class).setParameter("userId", userId).getResultList();
    }

    @Override
    public List<UserMeal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {

        return em.createNamedQuery(UserMeal.GET_BETWEEN, UserMeal.class).
                setParameter(1, startDate).setParameter(2, endDate).setParameter(3, userId).getResultList();
    }
}