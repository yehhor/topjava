package ru.javawebinar.topjava.model;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * GKislin
 * 11.01.2015.
 */
@NamedQueries({
        @NamedQuery(name = UserMeal.DELETE, query = "DELETE FROM UserMeal u WHERE u.id=:id and u.user.id=:userId"),
        @NamedQuery(name = UserMeal.UPDATE, query = "UPDATE UserMeal u SET u.calories=:calories, u.dateTime=:datetime," +
                " u.description=:description WHERE u.id=:id AND u.user.id=:userId"),
        @NamedQuery(name = UserMeal.GET, query = "SELECT u FROM UserMeal u WHERE u.user.id=:userId and u.id=:id"),
        @NamedQuery(name = UserMeal.ALL_SORTED, query = "SELECT u FROM UserMeal u where u.user.id=:userId ORDER BY u.dateTime desc "),
        @NamedQuery(name = UserMeal.GET_BETWEEN, query = "SELECT u FROM UserMeal u where u.dateTime between ?1 and ?2 and u.user.id=?3 ORDER BY u.dateTime desc ")
})
@Entity
@Table(name = "meals", uniqueConstraints = @UniqueConstraint(columnNames = {"date_time", "user_id"}))
public class UserMeal extends BaseEntity {

    public static final String DELETE = "UserMeal.delete";
    public static final String UPDATE = "UserMeal.update";
    public static final String GET = "UserMeal.get";
    public static final String ALL_SORTED = "UserMeal.getAllSorted";
    public static final String GET_BETWEEN = "UserMeal.getBetween";

    @Column(name = "date_time", nullable = false)
    @DateTimeFormat
    @NotNull
    private LocalDateTime dateTime;

    @Column(name = "description", nullable = false)
    @NotNull
    private String description;

    @Column(name = "calories", nullable = false)
    @NotNull
    protected int calories;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public UserMeal() {
    }

    public UserMeal(LocalDateTime dateTime, String description, int calories) {
        this(null, dateTime, description, calories);
    }

    public UserMeal(Integer id, LocalDateTime dateTime, String description, int calories) {
        super(id);
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserMeal{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                '}';
    }
}
