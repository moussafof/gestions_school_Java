package com.ism.services.impl;

import java.util.ArrayList;

import com.ism.entities.professeur;
import com.ism.repositories.bd.ProfRepository;
import com.ism.repositories.bd.impl.ProfRepoImpl;
import com.ism.repositories.core.impl.database;
import com.ism.services.professeurService;

public class profServiceImpl implements professeurService {

    database Database=new database();

    public profServiceImpl(database Database) {
        this.Database = Database;
    }
    ProfRepository profRepo = new ProfRepoImpl(Database);

    @Override
    public int add(professeur entity) {
       return profRepo.insert(entity);
        
    }

    @Override
    public int archive(int id) {
        return profRepo.archive(id);
    }

    @Override
    public ArrayList<professeur> findAll() {
        return profRepo.findAll();
    }

    @Override
    public professeur findByID(int id) {
        return profRepo.findByID(id);
    }

    @Override
    public int update(professeur entity) {
       return profRepo.update(entity);
    }
    
}
