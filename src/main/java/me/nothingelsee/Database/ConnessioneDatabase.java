package me.nothingelsee.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnessioneDatabase {

    private static ConnessioneDatabase instance;
    private Connection connection = null;
    private String nome = "postgres";
    private String password = "password";
    private String url = "jdbc:postgresql://localhost:5432/Giocatori";
    private String driver = "org.postgresql.Driver";


    public ConnessioneDatabase() throws SQLException {
        try{
            Class.forName(driver);
            connection = DriverManager.getConnection(url, nome, password);
        }catch (ClassNotFoundException e){
            System.out.println("Database connection failed: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public Connection getConnection(){
        return connection;
    }

    public static ConnessioneDatabase getInstance() throws SQLException {
        if(instance == null){
            instance = new ConnessioneDatabase();
        } else if (instance.getConnection().isClosed()) {
            instance = new ConnessioneDatabase();
            
        }
        return instance;
    }

}
