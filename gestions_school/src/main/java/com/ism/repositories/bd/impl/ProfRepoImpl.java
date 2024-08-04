package com.ism.repositories.bd.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ism.entities.Classe;
import com.ism.entities.Modules;
import com.ism.entities.professeur;
import com.ism.repositories.bd.ProfModulesRepo;
import com.ism.repositories.bd.ProfRepository;
import com.ism.repositories.bd.profClasseRepository;
import com.ism.repositories.core.impl.database;

public class ProfRepoImpl implements ProfRepository {
    private final String SQL_INSERT = "INSERT INTO `Prof` (`id`, `nom`, `isArchived`,numero_tel) VALUES (?, ?, DEFAULT,?)";
    private final String SQL_UPDATE = "UPDATE `Prof` SET `nom` = ? WHERE `id` = ?";
    private final String SQL_FIND_ALL = "SELECT id, nom, numero_tel FROM Prof";
    private final String SQL_FIND_BY_ID = "SELECT id, nom, numero_tel FROM Prof WHERE id = ?";
    private final String SQL_DELETE = "DELETE FROM `Prof` WHERE id = ?";
    private final String SQL_ARCHIVE = "UPDATE prof SET isArchived = 1 WHERE id = ?";

    private final database Database;

    public ProfRepoImpl(database Database) {
        this.Database = Database;
    }

    @Override
    
    public int insert(professeur data) {
        int nbrLigne = 0;
        try {
            Database.openConnexion();

            // Insérez une nouvelle ligne dans abstractentity pour obtenir un nouvel ID
            String sqlInsertAbstractEntity = "INSERT INTO abstractentity () VALUES ()";
            Database.initPrepareStatement(sqlInsertAbstractEntity);
            Database.executeUpdate();

            // Récupérez l'ID généré
            ResultSet generatedKeys = Database.getPreparedStatement().getGeneratedKeys();
            int abstractEntityId = -1;
            if (generatedKeys.next()) {
                abstractEntityId = generatedKeys.getInt(1);
            }

            // Insérez une nouvelle ligne dans la table professeur avec la référence à abstractentity
            Database.initPrepareStatement(SQL_INSERT, abstractEntityId, data.getNom(), data.getNumero_tel());
            nbrLigne = Database.executeUpdate();
            
            // Associez le professeur avec les classes et modules
            if (nbrLigne > 0) {
                int profId = data.getId();
                associateProfWithClassesAndModules(profId, data.getClassesEnseignees(), data.getModulesEnseignes());
            }
            Database.closeConnexion();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.printf("Erreur execution de request %s", ProfRepoImpl.class);
        } 
        return nbrLigne;
    }

    private void associateProfWithClassesAndModules(int profId, List<Classe> classes, List<Modules> modules) {
        profClasseRepository profClasseRepository=new profClassesRepoImpl(Database) ;
        ProfModulesRepo ProfModulesRepo=new profModulesRepoImpl(Database);
           
        for (Classe classe : classes) {
            int classeId = classe.getId();
            profClasseRepository.associateProfWithClasse(profId, classeId);

            for (Modules module : modules) {
                int moduleId = module.getId();
                ProfModulesRepo.associateProfWithModule(profId, moduleId);
            }
        }
    }

    @Override
    public int update(professeur data) {
        int nbrLigne = 0;
        try {
            Database.openConnexion();
            Database.initPrepareStatement(SQL_UPDATE, data.getNom(), data.getId(),data.getNumero_tel());
            nbrLigne = Database.executeUpdate();
            Database.closeConnexion();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.printf("Erreur execution de request %s", ProfRepoImpl.class);
        }
        return nbrLigne;
    }

    @Override
    public ArrayList<professeur> findAll() {
        ArrayList<professeur> professeurs = new ArrayList<>();
        try {
            Database.openConnexion();
            Database.initPrepareStatement(SQL_FIND_ALL);
            ResultSet rs = Database.executeSelect(SQL_FIND_ALL);
            while (rs.next()) {
                professeur professeur = new professeur(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getInt("numero_tel")
                );
                professeurs.add(professeur);
            }
            Database.closeConnexion();
            rs.close();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.printf("Erreur execution de request %s", ProfRepoImpl.class);
        }
        return professeurs;
    }

    @Override
    public professeur findByID(int id) {
        professeur professeur = null;
        try {
            Database.openConnexion();
            Database.initPrepareStatement(SQL_FIND_BY_ID, id);
            ResultSet rs = Database.executeSelect(SQL_FIND_BY_ID);
            if (rs.next()) {
                professeur = new professeur(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getInt("numero_tel")
                );
            }
            Database.closeConnexion();
            rs.close();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.printf("Erreur execution de request %s", ProfRepoImpl.class);
        }
        return professeur;
    }

    @Override
    public int delete(int id) {
        int nbrLigne = 0;
        try {
            Database.openConnexion();
            Database.initPrepareStatement(SQL_DELETE, id);
            nbrLigne = Database.executeUpdate();
            Database.closeConnexion();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.printf("Erreur execution de request %s", ProfRepoImpl.class);
        }
        return nbrLigne;
    }

    @Override
    public int indexOf(int id) {
        return 0;
    }

    @Override
    public int archive(int id) {
        int nbrLigne = 0;
        try {
            Database.openConnexion();
            // Utilisez 1 pour indiquer que la salle est archivée
            Database.initPrepareStatement(SQL_ARCHIVE, id);
            nbrLigne = Database.executeUpdate();
            Database.closeConnexion();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.printf("Erreur execution de request %s", SalleRepoImpl.class);
        }
        return nbrLigne;
    }
}
