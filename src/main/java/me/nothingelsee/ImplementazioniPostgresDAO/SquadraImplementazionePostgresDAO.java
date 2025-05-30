package me.nothingelsee.ImplementazioniPostgresDAO;

import me.nothingelsee.Database.ConnessioneDatabase;
import me.nothingelsee.InterfacceDAO.SquadraDAO;
import me.nothingelsee.Model.Giocatore;
import me.nothingelsee.Model.Militanza;
import me.nothingelsee.Model.Squadra;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * The type Squadra implementazione postgres dao.
 */
public class SquadraImplementazionePostgresDAO implements SquadraDAO {

    private Connection connection;

    /**
     * Instantiates a new Squadra implementazione postgres dao.
     */
    public SquadraImplementazionePostgresDAO() {

        try {

            connection = ConnessioneDatabase.getInstance().getConnection();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<String> getSquadreNomi() {

        PreparedStatement leggiSquadra;
        ArrayList<String> squadre = new ArrayList<>();

        try {

            leggiSquadra = connection.prepareStatement("SELECT nome FROM squadra");

            ResultSet rs = leggiSquadra.executeQuery();

            while (rs.next()) {
                squadre.add(rs.getString("nome"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return squadre;
    }

    @Override
    public void insertSquadra(Squadra squadra) {
        PreparedStatement insertSquadra = null;

        try {
            insertSquadra = connection.prepareStatement("SELECT * FROM squadra WHERE nome = ?");
            insertSquadra.setString(1, squadra.getNome());
            ResultSet rs = insertSquadra.executeQuery();
            if (rs.getRow() == 0) {
                insertSquadra.close();
                insertSquadra = connection.prepareStatement("INSERT INTO squadra (nome, nomenazione) VALUES (?, ?)");
                insertSquadra.setString(1, squadra.getNome());
                insertSquadra.setString(2, squadra.getNazionalita());
                insertSquadra.executeUpdate();
            }
            rs.close();
            insertSquadra.close();
            connection.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Errore nel caricamento delle squadre", "Errore", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<String> getSquadreGiocatore(int id) {
        ArrayList<String> squadre = new ArrayList<>();
        PreparedStatement leggiSquadre;

        try {
            leggiSquadre = connection.prepareStatement("SELECT S.nome FROM squadra AS S JOIN Militanza AS M ON S.nome = M.nomesquadra JOIN Giocatore AS G ON M.id_giocatore = G.id_giocatore WHERE G.id_giocatore = ?");
            leggiSquadre.setInt(1, id);
            ResultSet rs = leggiSquadre.executeQuery();
            while (rs.next()) {
                squadre.add(rs.getString("nome"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return squadre;
    }
}
