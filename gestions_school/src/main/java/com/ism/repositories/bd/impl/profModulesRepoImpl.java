package com.ism.repositories.bd.impl;

import java.sql.SQLException;

import com.ism.repositories.bd.ProfModulesRepo;
import com.ism.repositories.core.impl.database;

public class profModulesRepoImpl implements ProfModulesRepo {
  private final String SQL_ASSOCIATE_PROF_MODULE = "INSERT INTO professeurs_modules (professeur_id, module_id) VALUES (?, ?)";
    private final database Database;

    public profModulesRepoImpl(database Database) {
        this.Database = Database;
    }

    @Override
    public int associateProfWithModule(int profId, int moduleId) {
        int nbrLigne = 0;
        try {
            Database.openConnexion();
            Database.initPrepareStatement(SQL_ASSOCIATE_PROF_MODULE, profId, moduleId);
            nbrLigne = Database.executeUpdate();
            Database.closeConnexion();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.printf("Erreur execution de request %s", profModulesRepoImpl.class);
        } 
        return nbrLigne;
    }

    
}
    
