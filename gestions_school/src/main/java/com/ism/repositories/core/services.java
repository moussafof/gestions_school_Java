package com.ism.repositories.core;

import java.util.ArrayList;

import com.ism.entities.AbstractEntity;

public interface services<T extends AbstractEntity> {
    int add(T entity);
    
    int update(T entity);
    ArrayList<T> findAll();
    T findByID(int id);
    int archive(int id);

}
