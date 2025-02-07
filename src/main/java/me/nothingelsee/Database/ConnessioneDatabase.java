package me.nothingelsee.Database;

import org.postgresql.Driver;

public class Connection {

    private static Connection instance;
    private Driver driver;

    public Connection(){
        Class.forName(driver);
    }

}
