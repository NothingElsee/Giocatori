package me.nothingelsee.ImplementazioniPostgresDAO;

import me.nothingelsee.Database.ConnessioneDatabase;
import me.nothingelsee.ENUM.RUOLO;
import me.nothingelsee.InterfacceDAO.PosizioneDAO;
import me.nothingelsee.InterfacceDAO.RuoloDAO;
import me.nothingelsee.Model.Giocatore;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PosizioneImplementazionePostgresDAO implements PosizioneDAO {

    private Connection connection;

    public PosizioneImplementazionePostgresDAO() {
        try {
            connection = ConnessioneDatabase.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void caricaPosizione(Giocatore giocatore) {
        PreparedStatement insertPosizione = null;

        try {
            for (int i = 0; i < giocatore.getRuoli().size(); i++) {
                insertPosizione = connection.prepareStatement("insert into posizione values (?,?)");
                insertPosizione.setInt(1, giocatore.getId());
                insertPosizione.setInt(2, giocatore.getRuoli().get(i).ordinal());
                insertPosizione.executeUpdate();
                insertPosizione.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
