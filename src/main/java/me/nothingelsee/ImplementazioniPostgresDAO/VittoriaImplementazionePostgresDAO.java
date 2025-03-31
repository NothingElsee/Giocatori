package me.nothingelsee.ImplementazioniPostgresDAO;

import me.nothingelsee.Database.ConnessioneDatabase;
import me.nothingelsee.InterfacceDAO.VittoriaDAO;
import me.nothingelsee.Model.Giocatore;
import me.nothingelsee.Model.Trofeo;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

/**
 * The type Vittoria implementazione postgres dao.
 */
public class VittoriaImplementazionePostgresDAO implements VittoriaDAO {

    private Connection connection;

    /**
     * Instantiates a new Vittoria implementazione postgres dao.
     */
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
        PreparedStatement esisteTrofeo = null;


        try {
            esisteTrofeo = connection.prepareStatement("SELECT id_trofeo FROM trofeo WHERE nome=?");
            esisteTrofeo.setString(1, trofeo.getNome());
            ResultSet rs = esisteTrofeo.executeQuery();
            if (!rs.next()) {
                PreparedStatement insertTrofeo = null;
                PreparedStatement idTrofeo = null;

                insertTrofeo = connection.prepareStatement("insert into trofeo (nome, tipo) values (?, \'" + trofeo.getTipo() + "\')");
                insertTrofeo.setString(1, trofeo.getNome());
                insertTrofeo.executeUpdate();

                idTrofeo = connection.prepareStatement("SELECT currval(pg_get_serial_sequence('trofeo','id_trofeo'))");
                ResultSet rs2 = idTrofeo.executeQuery();
                rs2.next();
                trofeo.setId(rs2.getInt(1));

                rs2.close();
                insertTrofeo.close();
                idTrofeo.close();
            } else trofeo.setId(rs.getInt(1));
            esisteTrofeo.close();

            insertVittoria = connection.prepareStatement("insert into vittoria(id_trofeo, id_giocatore, nomesquadra, data) values(?,?,?,?)");

            insertVittoria.setInt(1, trofeo.getId());
            insertVittoria.setInt(2, idGiocatore);
            insertVittoria.setNull(3, Types.VARCHAR);
            insertVittoria.setDate(4, Date.valueOf(trofeo.getData()));
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
    public void insertVittoriaSquadra(int id_giocatore, Trofeo trofeo) {
        PreparedStatement insertVittoria = null;
        PreparedStatement esisteTrofeo = null;

        try {
            esisteTrofeo = connection.prepareStatement("SELECT id_trofeo FROM trofeo WHERE nome=?");
            esisteTrofeo.setString(1, trofeo.getNome());
            ResultSet rs = esisteTrofeo.executeQuery();
            if (!rs.next()) {
                PreparedStatement insertTrofeo = null;
                PreparedStatement idTrofeo = null;

                insertTrofeo = connection.prepareStatement("insert into trofeo (nome, tipo) values (?, \'" + trofeo.getTipo() + "\')");
                insertTrofeo.setString(1, trofeo.getNome());
                insertTrofeo.executeUpdate();

                idTrofeo = connection.prepareStatement("SELECT currval(pg_get_serial_sequence('trofeo','id_trofeo'))");
                ResultSet rs2 = idTrofeo.executeQuery();
                rs2.next();
                trofeo.setId(rs2.getInt(1));

                rs2.close();
                insertTrofeo.close();
                idTrofeo.close();
            } else trofeo.setId(rs.getInt(1));
            esisteTrofeo.close();

            insertVittoria = connection.prepareStatement("insert into vittoria(id_trofeo, id_giocatore, nomesquadra, data) values(?,?,?,?)");
            insertVittoria.setInt(1, trofeo.getId());
            insertVittoria.setInt(2, id_giocatore);
            insertVittoria.setString(3, trofeo.getSquadra());
            insertVittoria.setDate(4, Date.valueOf(trofeo.getData()));

            insertVittoria.executeUpdate();
            insertVittoria.close();
            connection.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Errore durante il caricametno della vittoria indivisuale!", "Errore", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    @Override
    public void deleteVittoria(Trofeo trofeo) {
        PreparedStatement deleteVittoria = null;
        try {

            deleteVittoria = connection.prepareStatement("delete from vittoria where id_trofeo = ? AND data = ?");
            deleteVittoria.setInt(1, trofeo.getId());
            deleteVittoria.setDate(2, Date.valueOf(trofeo.getData()));
            deleteVittoria.executeUpdate();
            deleteVittoria.close();
            connection.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Errore durante l'eliminazione del trofeo", "Errore", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

}
