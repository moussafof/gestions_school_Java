package com.ism.repositories.bd.impl;
import com.ism.repositories.core.impl.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ism.entities.Salle;
import com.ism.repositories.bd.SalleRepository;

public class SalleRepoImpl implements SalleRepository{
    private final String SQL_INSERT = "INSERT INTO `Salle` (`id`,`numero`, `capacite`,`isArchived`) VALUES (?,?, ?,DEFAULT)";
    private final String SQL_UPDATE = "UPDATE `Salle` SET `numero` = ?, `capacite` = ? WHERE `id` = ?";
    private final String SQL_FIND_ALL = "SELECT id, numero, capacite, isArchived FROM salle";
    private final String SQL_FIND_BY_ID = "SELECT id, numero, capacite FROM salle WHERE id = ?";
    private final String SQL_DELETE = "DELETE FROM `salle` WHERE id = ?";
    private final String SQL_ARCHIVE = "UPDATE salle SET isArchived = 1 WHERE id = ?";
    private final database Database;

    public SalleRepoImpl(database Database) {
        this.Database = Database;
    }


    @Override
    public int insert(Salle data) {
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
    
            // Insérez une nouvelle ligne dans la table salle avec la référence à abstractentity
            Database.initPrepareStatement(SQL_INSERT, abstractEntityId, data.getNumero(), data.getCapacite());
            nbrLigne = Database.executeUpdate();
    
            Database.closeConnexion();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.printf("Erreur execution de request %s", SalleRepoImpl.class);
        }
        return nbrLigne;
    }
    

    @Override
    public int update(Salle data) {
        int nbrLigne = 0;
        try {
            Database.openConnexion();
            Database.initPrepareStatement(SQL_UPDATE, data.getNumero(), data.getCapacite(), data.getId());
            nbrLigne = Database.executeUpdate();
            Database.closeConnexion();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.printf("Erreur execution de request %s", SalleRepoImpl.class);
        }
        return nbrLigne;
    }

    @Override
    public ArrayList<Salle> findAll() {
        ArrayList<Salle> salles = new ArrayList<>();
        try {
            Database.openConnexion();
            Database.initPrepareStatement(SQL_FIND_ALL);
            ResultSet rs = Database.executeSelect(SQL_FIND_ALL);
             
            while (rs.next()) {
                Salle salle = new Salle();
                salle.setId(rs.getInt("id"));
                salle.setNumero(rs.getInt("numero"));
                salle.setCapacite(rs.getInt("capacite"));
                salles.add(salle);          
            }   
            
            Database.closeConnexion();
            rs.close();
           
        } catch (SQLException | ClassNotFoundException e) {
           
            System.out.printf("Erreur execution de request %s", SalleRepoImpl.class);
        }
        return salles;
    }

    @Override
    public Salle findByID(int id) {
        Salle salle = null;
        try {
            Database.openConnexion();
            Database.initPrepareStatement(SQL_FIND_BY_ID, id);
            ResultSet rs = Database.executeSelect(SQL_FIND_BY_ID);
            
            if (rs.next()) {
                salle = new Salle();
                salle.setId(rs.getInt("id"));
                salle.setNumero(rs.getInt("numero"));
                salle.setCapacite(rs.getInt("capacite"));

                
            }
            Database.closeConnexion();
            rs.close();
        } catch (SQLException | ClassNotFoundException e) {
             e.printStackTrace();
            System.out.printf("Erreur execution de request %s", SalleRepoImpl.class);
        }
        return salle;
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
            System.out.printf("Erreur execution de request %s", SalleRepoImpl.class);
        }
        return nbrLigne;
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


    @Override
    public int indexOf(int id) {
        return 0;
    }
}
    

