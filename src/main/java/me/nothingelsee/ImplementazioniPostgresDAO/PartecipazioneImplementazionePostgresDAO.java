package me.nothingelsee.ImplementazioniPostgresDAO;

import me.nothingelsee.Database.ConnessioneDatabase;
import me.nothingelsee.InterfacceDAO.PartecipazioneDAO;

import javax.swing.*;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The type Partecipazione implementazione postgres dao.
 */
public class PartecipazioneImplementazionePostgresDAO implements PartecipazioneDAO {
    private Connection connection;

    /**
     * Instantiates a new Partecipazione implementazione postgres dao.
     */
    public PartecipazioneImplementazionePostgresDAO() {
        try {
            connection = ConnessioneDatabase.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertPartecipazione(String casa, String trasferta) {
        PreparedStatement ps = null;

        try {
            ps = connection.prepareStatement("INSERT INTO partecipazione(squadracasa, squadratrasferta) VALUES (?,?)");
            ps.setString(1, casa);
            ps.setString(2, trasferta);
            ps.executeUpdate();
            ps.close();
            connection.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Errpre durante il caricamento delle squadre", "Errore", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}
