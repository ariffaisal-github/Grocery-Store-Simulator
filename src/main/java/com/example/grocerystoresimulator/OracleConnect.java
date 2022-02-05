package com.example.grocerystoresimulator;

import java.sql.*;

public class OracleConnect {
    private Connection connection;
    private static final String host = "localhost";
    private static final String dbname = "grocery";
    private static final String username = "store_manager";
    private static final String password = "store_manager";
    private static final String port = "1521";

    public OracleConnect() throws Exception {
//        Class.forName("oracle.jdbc.driver.OracleDriver");
        String url = "jdbc:oracle:thin:@//" + host + ":" + port + "/" + dbname;
        this.connection = DriverManager.getConnection(url, username, password);
        System.out.println("Connected");
    }

    public int updateDB(String query) throws Exception {
        Statement statement = this.connection.createStatement();
        return statement.executeUpdate(query);
    }

    public ResultSet searchDB(String query) throws Exception {
        Statement statement = this.connection.createStatement();
        return statement.executeQuery(query);
    }

    public void close() throws Exception {
        this.connection.close();
    }
}
