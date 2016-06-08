package ru.javawebinar.topjava.dao;


import java.time.LocalDateTime;

/**
 * Created by yehor on 07.06.2016.
 */
public interface CRUD {

    void create(LocalDateTime dateTime, String description, int calories);

    void read();

    void update(Integer id, LocalDateTime dateTime, String description, int calories);

    void delete(Integer id);

}
