package me.nothingelsee.ImplementazioniPostgresDAO;

import me.nothingelsee.Database.ConnessioneDatabase;
import me.nothingelsee.ENUM.RUOLO;
import me.nothingelsee.InterfacceDAO.PosizioneDAO;
import me.nothingelsee.InterfacceDAO.RuoloDAO;
import me.nothingelsee.Model.Giocatore;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

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
    public void insertPosizione(int idGiocatore, ArrayList<RUOLO> listaRuoli) {
        PreparedStatement insertPosizione = null;

        try {
            for (int i = 0; i < listaRuoli.size(); i++) {
                insertPosizione = connection.prepareStatement("insert into posizione values (?,?)");
                insertPosizione.setInt(1, idGiocatore);
                insertPosizione.setInt(2, listaRuoli.get(i).ordinal());
                insertPosizione.executeUpdate();
                insertPosizione.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updatePosizione(int idGiocatore, ArrayList<RUOLO> listaRuoli) {
        PreparedStatement insertPosizione = null;

        try {
            insertPosizione = connection.prepareStatement("DELETE FROM posizione WHERE idGiocatore=?");
            insertPosizione.setInt(1, idGiocatore);
            insertPosizione.executeUpdate();
            insertPosizione.close();

            insertPosizione = connection.prepareStatement("insert into posizione values (?,?)");
            for (int i = 0; i < listaRuoli.size(); i++) {
                insertPosizione.setInt(1, idGiocatore);
                insertPosizione.setInt(2, listaRuoli.get(i).ordinal());
                insertPosizione.executeUpdate();
            }

            insertPosizione.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
