package com.ism.repositories.core;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface Idatabase {
    void openConnexion()throws SQLException,ClassNotFoundException ;
    void closeConnexion()throws SQLException;

    PreparedStatement getPreparedStatement();

    void initPrepareStatement(String sql, Object... params) throws SQLException;
    void initPrepareStatement1(String sql) throws SQLException;

    ResultSet executeSelect(String sql)throws SQLException;
    int executeUpdate()throws SQLException;
}
