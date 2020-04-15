package com.ufpr.tads.sac.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

    /**
     * Fornece a conexão JDBC com a base de dados.
     * @return A conexão aberta.
     */
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/sac", "root", "admin");
        } catch(Exception e) {
            System.out.println("Erro ao estabelecer conexão! :(");
        }
        return null;
    }

}