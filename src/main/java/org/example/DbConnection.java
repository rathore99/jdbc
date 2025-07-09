package org.example;

import org.example.config.ConfigLoader;
import org.example.config.DatabaseConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private static Connection connection ;
    public static Connection getConnection(){
        if(connection == null){

            ConfigLoader configLoader = new ConfigLoader("db.properties");
            DatabaseConfig databaseConfig = new DatabaseConfig(configLoader);
            databaseConfig.printConfig();

            try{
                connection = DriverManager.getConnection(databaseConfig.getDbUrl(),
                        databaseConfig.getDbUser(),
                        databaseConfig.getDbPassword());
                System.out.println("connection created.... ");
            }
            catch (SQLException e ){
                e.printStackTrace();
            }
        }
        return connection;
    }
}
