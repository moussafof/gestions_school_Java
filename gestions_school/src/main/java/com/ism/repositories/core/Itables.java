package com.ism.repositories.core;

import java.util.ArrayList;

import com.ism.entities.AbstractEntity;

public interface Itables<T extends AbstractEntity> {
     int insert(T data);

    int update(T data);

    ArrayList<T> findAll();

    T findByID (int id);
    int archive(int id);
    int delete (int id);

    int indexOf (int id);

}
