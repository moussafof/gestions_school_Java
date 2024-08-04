package com.ism.repositories.bd.impl;

import java.sql.SQLException;

import com.ism.repositories.bd.profClasseRepository;
import com.ism.repositories.core.impl.database;

public class profClassesRepoImpl implements profClasseRepository {
 private final String SQL_ASSOCIATE_PROF_CLASSE = "INSERT INTO classe_professeur (professeur_id, classe_id) VALUES (?, ?)";
    private final database Database;

    public profClassesRepoImpl(database Database) {
        this.Database = Database;
    }

    @Override
    public int associateProfWithClasse(int profId, int classeId) {
        int nbrLigne = 0;
        try {
            Database.openConnexion();
            Database.initPrepareStatement(SQL_ASSOCIATE_PROF_CLASSE, profId, classeId);
            nbrLigne = Database.executeUpdate();
            Database.closeConnexion();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.printf("Erreur execution de request %s", profClassesRepoImpl.class);
        } 
        return nbrLigne;
    }
}
    
    

