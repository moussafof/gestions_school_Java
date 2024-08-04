package com.ism.repositories.core.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.ism.repositories.core.Idatabase;

public class database implements Idatabase {
    private final String driver = "com.mysql.cj.jdbc.Driver";
    private final String url = "jdbc:mysql://localhost:3306/javaexam";
    private final String user = "root";
    private final String password = "";
    
    private Connection conn = null;
    private PreparedStatement ps;

    
    @Override
    public PreparedStatement getPreparedStatement() {
        return ps;
    }

    @Override
    public void openConnexion()throws SQLException,ClassNotFoundException {
        Class.forName(driver);
        conn = DriverManager.getConnection(url, user, password);
    }
   

    @Override
    public void closeConnexion() throws SQLException{
        conn.close();
        ps.close();
    }

    @Override
    public void initPrepareStatement(String sql, Object... params) throws SQLException {
        if (params.length > 0) {
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }
        } else {
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        }
    }
    
    
    
    
    
    
    @Override
    public void initPrepareStatement1(String sql) throws SQLException {
        if (sql.toLowerCase().startsWith("insert")) {
            
            ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
        } else {
            ps = conn.prepareStatement(sql);
        }
    }

    


    @Override
    public ResultSet executeSelect(String sql)throws SQLException {
            return ps.executeQuery();
    }
    @Override
    public int executeUpdate() throws SQLException {
        return ps.executeUpdate();
    }

   
}

