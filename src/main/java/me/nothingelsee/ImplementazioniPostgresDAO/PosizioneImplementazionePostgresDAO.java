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
    public boolean insertPosizione(int idGiocatore, ArrayList<RUOLO> listaRuoli) {
        PreparedStatement insertPosizione = null;

        try {
            for (int i = 0; i < listaRuoli.size(); i++) {
                insertPosizione = connection.prepareStatement("insert into posizione values (?, ?)");
                insertPosizione.setInt(1, idGiocatore);
                insertPosizione.setInt(2, (listaRuoli.get(i).ordinal()+1));
                insertPosizione.executeUpdate();
                insertPosizione.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean updatePosizione(int idGiocatore, ArrayList<RUOLO> listaRuoli) {
        PreparedStatement insertPosizione = null;

        try {
            insertPosizione = connection.prepareStatement("DELETE FROM posizione WHERE id_giocatore=?");
            insertPosizione.setInt(1, idGiocatore);
            insertPosizione.executeUpdate();
            insertPosizione.close();

            insertPosizione = connection.prepareStatement("insert into posizione values (?,?)");
            for (int i = 0; i < listaRuoli.size(); i++) {
                insertPosizione.setInt(1, idGiocatore);
                insertPosizione.setInt(2, (listaRuoli.get(i).ordinal()+1));
                insertPosizione.executeUpdate();
            }

            insertPosizione.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


}
