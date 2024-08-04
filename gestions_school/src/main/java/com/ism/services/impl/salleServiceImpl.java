package com.ism.services.impl;

import java.util.ArrayList;

import com.ism.entities.Salle;
import com.ism.repositories.bd.SalleRepository;
import com.ism.repositories.bd.impl.SalleRepoImpl;
import com.ism.repositories.core.impl.database;
import com.ism.services.salleService;

public class salleServiceImpl implements salleService {

    database Database=new database();

    public salleServiceImpl(database Database) {
        this.Database = Database;
    }
    SalleRepository salleRepository = new SalleRepoImpl(Database);

    @Override
    public int add(Salle entity) {
        return salleRepository.insert(entity);
     
    }

   @Override
    public int archive(int id) {
       return salleRepository.archive(id);
    }


    @Override
    public int update(Salle entity) {
        return salleRepository.update(entity);
    }

    @Override
    public ArrayList<Salle> findAll() {
        return salleRepository.findAll();
        
    }

    @Override
    public Salle findByID(int id) {
        return salleRepository.findByID(id);
        
    }
}

