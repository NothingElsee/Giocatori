package me.nothingelsee.ImplementazioniPostgresDAO;

import me.nothingelsee.Database.ConnessioneDatabase;
import me.nothingelsee.InterfacceDAO.AbilitaDAO;
import me.nothingelsee.Model.Giocatore;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * The type Abilita implementazione postgres dao.
 */
public class AbilitaImplementazionePostgresDAO implements AbilitaDAO {

    private Connection connection;

    /**
     * Instantiates a new Abilita implementazione postgres dao.
     */
    public AbilitaImplementazionePostgresDAO() {

        try {

            connection = ConnessioneDatabase.getInstance().getConnection();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getAbilities(Giocatore giocatore) {
        PreparedStatement leggiAbilita;
        ArrayList<Integer> abilita = new ArrayList<>();

        try {
            leggiAbilita = connection.prepareStatement(
                    "SELECT * FROM Abilità WHERE id_giocatore = " + giocatore.getId()
            );

            ResultSet rs = leggiAbilita.executeQuery();

            int i = 2;
            rs.next();
            while (i < 9) {
                abilita.add(rs.getInt(i));
                i++;
            }

            giocatore.setAbilita(abilita);
            rs.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertAbilita(int idGiocatore, ArrayList<Integer> abilita) {
        PreparedStatement caricaAbilita;

        try {
            caricaAbilita = connection.prepareStatement("INSERT INTO Abilità (id_giocatore, velocità, tiro, passaggio, piededebole, resistenza, difesa, tirosupunizione)" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?);");

            caricaAbilita.setInt(1, idGiocatore);
            for (int i = 0; i < abilita.size(); i++) {
                caricaAbilita.setInt(i + 2, abilita.get(i));
            }
            caricaAbilita.executeUpdate();
            caricaAbilita.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Errore nel caricamento delle abilità", "Errore", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    @Override
    public void updateAbilita(int idGiocatore, ArrayList<Integer> abilita) {
        PreparedStatement caricaAbilita;

        try {
            caricaAbilita = connection.prepareStatement("UPDATE Abilità " +
                    "SET velocità = " + abilita.get(0) +
                    " ,tiro = " + abilita.get(1) +
                    " ,passaggio = " + abilita.get(2) +
                    " ,piededebole = " + abilita.get(3) +
                    " ,resistenza = " + abilita.get(4) +
                    " ,difesa = " + abilita.get(5) +
                    " ,tirosupunizione = " + abilita.get(6) +
                    " WHERE id_giocatore = " + idGiocatore);

            caricaAbilita.executeUpdate();
            caricaAbilita.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Errore nel caricamento delle abilità", "Errore", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}
