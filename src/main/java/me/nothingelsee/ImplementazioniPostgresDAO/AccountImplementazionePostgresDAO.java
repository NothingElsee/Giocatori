package me.nothingelsee.ImplementazioniPostgresDAO;

import me.nothingelsee.Database.ConnessioneDatabase;
import me.nothingelsee.InterfacceDAO.AccountDAO;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The type Account implementazione postgres dao.
 */
public class AccountImplementazionePostgresDAO implements AccountDAO {

    private Connection connection;

    /**
     * Instantiates a new Account implementazione postgres dao.
     */
    public AccountImplementazionePostgresDAO() {
        try {
            connection = ConnessioneDatabase.getInstance().getConnection();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isIn(String username, String password) {

        PreparedStatement ps = null;
        boolean result = false;

        try {
            ps = connection.prepareStatement("select * from account WHERE username = '" + username + "' AND pass = '" + password + "'");
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                result = true;
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
}