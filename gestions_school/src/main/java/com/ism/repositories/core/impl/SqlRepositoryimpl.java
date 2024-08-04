package com.ism.repositories.core.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ism.entities.AbstractEntity;
import com.ism.repositories.core.Itables;

public abstract class SqlRepositoryimpl <T extends AbstractEntity> implements Itables<T>  {
     private final database Database;

     public SqlRepositoryimpl(database Database) {
                this.Database = Database;
            }

    protected abstract String getInsertSql();
    protected abstract String getUpdateSql();
    protected abstract String getFindAllSql();
    protected abstract String getFindByIdSql();
    protected abstract String getDeleteSql();
    protected abstract T mapResultSetToEntity(ResultSet rs);

    public int insert(T data) {
        int lastId = 0;
        try {
            Database.openConnexion();
            Database.initPrepareStatement1(getInsertSql());
            setInsertValues(Database.getPreparedStatement(),data);
            lastId = Database.executeUpdate();
            Database.closeConnexion();
        } catch (SQLException|ClassNotFoundException e) {
            System.out.println("Insert Problem");
        }
        
        return lastId;
    }

    public int update(T data) {
        int nbrLigne = 0;
        try {
            Database.openConnexion();
            Database.initPrepareStatement1(getUpdateSql());
            setUpdateValues(Database.getPreparedStatement(),data);
            nbrLigne = Database.executeUpdate();
            Database.closeConnexion();
        } catch (SQLException|ClassNotFoundException e) {
            System.out.println("Update Problem");
        }
        return nbrLigne;
    }

    public ArrayList<T> findAll() {
        ArrayList<T> entities = new ArrayList<>();
        try {
            Database.openConnexion();
            Database.initPrepareStatement1(getFindAllSql());
            ResultSet rs = Database.executeSelect(getFindAllSql());
            while (rs.next()) {
                entities.add(mapResultSetToEntity(rs));
                }
            Database.closeConnexion();
            rs.close();

        }catch (SQLException|ClassNotFoundException e) {
            System.out.println("FindAll Problem");
        }
        return entities;
    }

    public T findByID(int id) {
        T entity = null;
        try {
            Database.openConnexion();
            Database.initPrepareStatement(getFindAllSql());
            Database.getPreparedStatement().setInt(1, id);
            ResultSet rs = Database.executeSelect(getFindByIdSql());
            if (rs.next()) {
                entity=mapResultSetToEntity(rs);
            }
            Database.closeConnexion();
            rs.close();

        }catch (SQLException|ClassNotFoundException e) {
            System.out.println("FindID Problem");
        }
        return entity;
    }

    public int delete(int id) {
        int nbrLigne = 0;
        try {
            Database.openConnexion();
            Database.initPrepareStatement1(getDeleteSql());
            Database.getPreparedStatement().setInt(1, id);
            nbrLigne = Database.executeUpdate();
            Database.closeConnexion();
        } catch (SQLException|ClassNotFoundException e) {
            System.out.println("Delete Problem");
        }
        return nbrLigne;
    }

    protected abstract void setInsertValues(PreparedStatement ps, T data)throws SQLException;
    protected abstract void setUpdateValues(PreparedStatement ps,T data)throws SQLException;
}

