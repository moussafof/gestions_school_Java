package com.ism.repositories.bd.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ism.entities.Classe;
import com.ism.repositories.bd.ClasseRepository;
import com.ism.repositories.core.impl.database;

public class ClasseRepoImpl implements ClasseRepository {
    private final String SQL_INSERT = "INSERT INTO `Classe` (`id`, `nom`, `nbreEleve`, `isArchived`) VALUES (?, ?, ?, DEFAULT)";
    private final String SQL_UPDATE = "UPDATE `Classe` SET `nom` = ?, `nbreEleve` = ? WHERE `id` = ?";
    private final String SQL_FIND_ALL = "SELECT id, nom, nbreEleve FROM Classe";
    private final String SQL_FIND_BY_ID = "SELECT id, nom, nbreEleve FROM Classe WHERE id = ?";
    private final String SQL_DELETE = "DELETE FROM `Classe` WHERE id = ?";
    private final String SQL_ARCHIVE = "UPDATE classe SET isArchived = 1 WHERE id = ?";

    private final database Database;

    public ClasseRepoImpl(database Database) {
        this.Database = Database;
    }

    @Override
    public int insert(Classe data) {
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
            System.out.println("Abstract Entity ID: " + abstractEntityId);

            // Insérez une nouvelle ligne dans la table classe avec la référence à abstractentity
            Database.initPrepareStatement(SQL_INSERT, abstractEntityId, data.getNom(), data.getNbreEleve());
            nbrLigne = Database.executeUpdate();

            Database.closeConnexion();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.printf("Erreur execution de request %s", ClasseRepoImpl.class);
        }
        return nbrLigne;
    }

    @Override
    public int update(Classe data) {
        int nbrLigne = 0;
        try {
            Database.openConnexion();
            Database.initPrepareStatement(SQL_UPDATE, data.getNom(), data.getNbreEleve(), data.getId());
            nbrLigne = Database.executeUpdate();
            Database.closeConnexion();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.printf("Erreur execution de request %s", ClasseRepoImpl.class);
        }
        return nbrLigne;
    }

    @Override
    public ArrayList<Classe> findAll() {
        ArrayList<Classe> classesList = new ArrayList<>();
        try {
            Database.openConnexion();
            Database.initPrepareStatement(SQL_FIND_ALL);
            ResultSet rs = Database.executeSelect(SQL_FIND_ALL);
            while (rs.next()) {
                Classe classe = new Classe(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getInt("nbreEleve")
                );
                classesList.add(classe);
            }
            Database.closeConnexion();
            rs.close();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.printf("Erreur execution de request %s", ClasseRepoImpl.class);
        }
        return classesList;
    }

    @Override
    public Classe findByID(int id) {
        Classe classe = null;
        try {
            Database.openConnexion();
            Database.initPrepareStatement(SQL_FIND_BY_ID, id);
            ResultSet rs = Database.executeSelect(SQL_FIND_BY_ID);
            if (rs.next()) {
                classe = new Classe(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getInt("nbreEleve")
                        
                );
            }
            Database.closeConnexion();
            rs.close();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.printf("Erreur execution de request %s", ClasseRepoImpl.class);
        }
        return classe;
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
            System.out.printf("Erreur execution de request %s", ClasseRepoImpl.class);
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
    
