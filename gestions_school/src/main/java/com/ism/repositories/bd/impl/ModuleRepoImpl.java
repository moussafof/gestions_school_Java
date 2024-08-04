package com.ism.repositories.bd.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ism.entities.Modules;
import com.ism.repositories.bd.ModuleRepository;
import com.ism.repositories.core.impl.database;

public class ModuleRepoImpl implements ModuleRepository {
    private final String SQL_INSERT = "INSERT INTO `Modules` (`id`, `nom`, `isArchived`) VALUES (?, ?, DEFAULT)";
    private final String SQL_UPDATE = "UPDATE `Modules` SET `nom` = ? WHERE `id` = ?";
    private final String SQL_FIND_ALL = "SELECT id, nom FROM Modules";
    private final String SQL_FIND_BY_ID = "SELECT id, nom FROM Modules WHERE id = ?";
    private final String SQL_DELETE = "DELETE FROM `Modules` WHERE id = ?";
    private final String SQL_ARCHIVE = "UPDATE modules SET isArchived = 1 WHERE id = ?";

    private final database Database;

    public ModuleRepoImpl(database Database) {
        this.Database = Database;
    }

    @Override
    public int insert(Modules data) {
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

            // Insérez une nouvelle ligne dans la table modules avec la référence à abstractentity
            Database.initPrepareStatement(SQL_INSERT, abstractEntityId, data.getNom());
            nbrLigne = Database.executeUpdate();

            Database.closeConnexion();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.printf("Erreur execution de request %s", ModuleRepoImpl.class);
        }
        return nbrLigne;
    }

    @Override
    public int update(Modules data) {
        int nbrLigne = 0;
        try {
            Database.openConnexion();
            Database.initPrepareStatement(SQL_UPDATE, data.getNom(), data.getId());
            nbrLigne = Database.executeUpdate();
            Database.closeConnexion();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.printf("Erreur execution de request %s", ModuleRepoImpl.class);
        }
        return nbrLigne;
    }

    @Override
    public ArrayList<Modules> findAll() {
        ArrayList<Modules> modulesList = new ArrayList<>();
        try {
            Database.openConnexion();
            Database.initPrepareStatement(SQL_FIND_ALL);
            ResultSet rs = Database.executeSelect(SQL_FIND_ALL);
            while (rs.next()) {
                Modules module = new Modules(
                        rs.getInt("id"),
                        rs.getString("nom")
                );
                modulesList.add(module);
            }
            Database.closeConnexion();
            rs.close();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.printf("Erreur execution de request %s", ModuleRepoImpl.class);
        }
        return modulesList;
    }

    @Override
    public Modules findByID(int id) {
        Modules module = null;
        try {
            Database.openConnexion();
            Database.initPrepareStatement(SQL_FIND_BY_ID, id);
            ResultSet rs = Database.executeSelect(SQL_FIND_BY_ID);
            if (rs.next()) {
                module = new Modules(
                        rs.getInt("id"),
                        rs.getString("nom")
                );
            }
            Database.closeConnexion();
            rs.close();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.printf("Erreur execution de request %s", ModuleRepoImpl.class);
        }
        return module;
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
            System.out.printf("Erreur execution de request %s", ModuleRepoImpl.class);
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

    @Override
    public List<Module> findModulesByClasse(int classeId) {
        
        throw new UnsupportedOperationException("Unimplemented method 'findModulesByClasse'");
    }
}

