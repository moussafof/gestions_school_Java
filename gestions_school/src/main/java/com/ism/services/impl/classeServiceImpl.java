package com.ism.services.impl;

import java.util.ArrayList;

import com.ism.entities.Classe;
import com.ism.repositories.bd.ClasseRepository;
import com.ism.repositories.bd.impl.ClasseRepoImpl;
import com.ism.repositories.core.impl.database;
import com.ism.services.classeService;

public class classeServiceImpl implements classeService {

    database Database=new database();
    public classeServiceImpl(database Database) {
        this.Database = Database;
    }
    ClasseRepository classerepo = new ClasseRepoImpl(Database);
    @Override
    public int add(Classe entity) {
        return classerepo.insert(entity);
    }

    @Override
    public int archive(int id) {
        return classerepo.archive(id);
    }

    @Override
    public ArrayList<Classe> findAll() {
        return classerepo.findAll();
    }

    @Override
    public Classe findByID(int id) {
       return classerepo.findByID(id);
    }

    @Override
    public int update(Classe entity) {
        return classerepo.update(entity);
    }
    
}
