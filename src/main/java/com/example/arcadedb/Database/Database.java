package com.example.arcadedb.Database;

import java.io.File;
import java.sql.*;
import java.util.Scanner;

import static com.example.arcadedb.Database.Const.*;

public class Database {
    private static Database instance;
    private Connection connection = null;

    private Database(){
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager
                    .getConnection("jdbc:mysql://localhost/"+ DB_NAME +
                                    "?serverTimezone=UTC",
                            DB_USER,
                            DB_PASS);
            System.out.println("Created Connection!");

            createTable(DBConst.MACHINES_TABLE_NAME, DBConst.CREATE_MACHINES_TABLE, connection);
            createTable(DBConst.MACHINE_DETAILS_TABLE_NAME, DBConst.CREATE_MACHINE_DETAILS_TABLE, connection);
            createTable(DBConst.GAME_STATS_TABLE_NAME, DBConst.CREATE_GAME_STATS_TABLE, connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Database getInstance(){
        if(instance == null){
            instance = new Database();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    private void createTable(String tableName, String tableQuery, Connection connection) throws SQLException {
        Statement createTable;
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet resultSet = metaData.getTables(DB_NAME, null, tableName, null);

        if(resultSet.next()){
            System.out.println("The table " + tableName + " already exists");
        }
        else{
            createTable = connection.createStatement();
            createTable.execute(tableQuery);
            System.out.println("Table Created: " + tableName);
        }
    }
}
