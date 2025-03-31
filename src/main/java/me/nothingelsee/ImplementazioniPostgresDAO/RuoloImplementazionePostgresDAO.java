package me.nothingelsee.ImplementazioniPostgresDAO;

import me.nothingelsee.Database.ConnessioneDatabase;
import me.nothingelsee.ENUM.RUOLO;
import me.nothingelsee.InterfacceDAO.RuoloDAO;
import me.nothingelsee.Model.Giocatore;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * The type Ruolo implementazione postgres dao.
 */
public class RuoloImplementazionePostgresDAO implements RuoloDAO {

    private Connection connection;

    /**
     * Instantiates a new Ruolo implementazione postgres dao.
     */
    public RuoloImplementazionePostgresDAO() {
        try {

            connection = ConnessioneDatabase.getInstance().getConnection();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getRuoli(Giocatore giocatore) {

        PreparedStatement leggiRuoli;
        ArrayList<RUOLO> ruoli = new ArrayList<>();

        try {
            leggiRuoli = connection.prepareStatement(
                    "SELECT tipo FROM Ruolo AS R JOIN Posizione AS P ON R.id_ruolo = P.id_ruolo JOIN Giocatore AS G ON G.id_giocatore = P.id_giocatore " +
                            "WHERE G.id_giocatore = " + giocatore.getId()
            );
            ResultSet rs = leggiRuoli.executeQuery();

            while (rs.next()) {
                ruoli.add(RUOLO.valueOf(rs.getString(1)));
            }
            giocatore.setRuoli(ruoli);
            rs.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
