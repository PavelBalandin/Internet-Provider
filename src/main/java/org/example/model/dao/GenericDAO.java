package org.example.model.dao;

import java.util.List;

public interface GenericDAO<T> {
    T create(T entity);

    T findById(int id);

    List<T> findAll();

    T update(T entity);

    void delete(int id);
}
