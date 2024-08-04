package com.ism.services.impl;

import java.util.ArrayList;

import com.ism.entities.Modules;
import com.ism.repositories.bd.ModuleRepository;
import com.ism.repositories.bd.impl.ModuleRepoImpl;
import com.ism.repositories.core.impl.database;
import com.ism.services.moduleService;

public class moduleServiceImpl implements moduleService {
    database Database=new database();
    public moduleServiceImpl(database Database) {
        this.Database = Database;
    }
    ModuleRepository modulerepo = new ModuleRepoImpl(Database);
    @Override
    public int add(Modules entity) {
        return modulerepo.insert(entity);
    }

    @Override
    public int archive(int id) {
        return modulerepo.archive(id);
    }

    @Override
    public ArrayList<Modules> findAll() {
        return modulerepo.findAll();
    }

    @Override
    public Modules findByID(int id) {
        return modulerepo.findByID(id);
    }

    @Override
    public int update(Modules entity) {
        return modulerepo.update(entity);
    }
    
}
