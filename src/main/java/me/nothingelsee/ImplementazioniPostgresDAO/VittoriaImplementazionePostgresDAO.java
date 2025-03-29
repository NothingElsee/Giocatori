package me.nothingelsee.ImplementazioniPostgresDAO;

import me.nothingelsee.Database.ConnessioneDatabase;
import me.nothingelsee.InterfacceDAO.VittoriaDAO;
import me.nothingelsee.Model.Giocatore;
import me.nothingelsee.Model.Trofeo;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class VittoriaImplementazionePostgresDAO implements VittoriaDAO {

    private Connection connection;

    public VittoriaImplementazionePostgresDAO() {
        try {
            connection = ConnessioneDatabase.getInstance().getConnection();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertVittoriaIndividuale(int idGiocatore, Trofeo trofeo) {
        PreparedStatement insertVittoria = null;

        try {
            insertVittoria = connection.prepareStatement("insert into vittoria(id_trofeo, id_giocatore, nomesquadra, data) values(?,?,?,?)");

            insertVittoria.setInt(1, trofeo.getId());
            insertVittoria.setInt(2, idGiocatore);
            insertVittoria.setNull(3, Types.VARCHAR);
            insertVittoria.setString(4, trofeo.getData());
            insertVittoria.executeUpdate();
            insertVittoria.close();
            connection.close();
        } catch (
                SQLException e) {
            JOptionPane.showMessageDialog(null, "Errore durante il caricametno della vittoria indivisuale!", "Errore", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    @Override
    public void insertVittoriaSquadra(Trofeo trofeo) {
        PreparedStatement insertVittoria = null;

        try {
            insertVittoria = connection.prepareStatement("insert into vittoria(id_trofeo, id_giocatore, nomesquadra, data) values(?,?,?,?)");
            insertVittoria.setInt(1, trofeo.getId());
            insertVittoria.setNull(2, Types.INTEGER);
            insertVittoria.setString(3, trofeo.getSquadra());
            insertVittoria.setString(4, trofeo.getData());

            insertVittoria.executeUpdate();
            insertVittoria.close();
            connection.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Errore durante il caricametno della vittoria indivisuale!", "Errore", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    @Override
    public void deleteVittoria(Trofeo trofeo){
        PreparedStatement deleteVittoria = null;
        try{

            deleteVittoria = connection.prepareStatement("delete from vittoria where id_trofeo = ?, data = ?");
            deleteVittoria.setInt(1, trofeo.getId());
            deleteVittoria.setString(2, trofeo.getData());
            deleteVittoria.executeUpdate();
            deleteVittoria.close();
            connection.close();
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Errore durante l'eliminazione del trofeo", "Errore", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

}
