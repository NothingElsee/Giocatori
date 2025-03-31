package me.nothingelsee.ImplementazioniPostgresDAO;

import me.nothingelsee.Database.ConnessioneDatabase;
import me.nothingelsee.ENUM.COMPETIZIONE;
import me.nothingelsee.ENUM.TROFEO;
import me.nothingelsee.InterfacceDAO.TrofeoDAO;
import me.nothingelsee.Model.Giocatore;
import me.nothingelsee.Model.Trofeo;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

/**
 * The type Trofeo implementazione postgres dao.
 */
public class TrofeoImplementazionePostgresDAO implements TrofeoDAO {

    private Connection connection;

    /**
     * Instantiates a new Trofeo implementazione postgres dao.
     */
    public TrofeoImplementazionePostgresDAO() {

        try {

            connection = ConnessioneDatabase.getInstance().getConnection();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getTrofei(Giocatore giocatore) {

        PreparedStatement leggiTrofeiIndividuali = null;
        PreparedStatement leggiTrofeiSquadra = null;
        ArrayList<Trofeo> trofei = new ArrayList<>();

        try {

            leggiTrofeiIndividuali = connection.prepareStatement("select T.id_trofeo, T.nome, V.data, T.tipo from trofeo AS T JOIN Vittoria AS V ON V.id_trofeo = T.id_trofeo " +
                    "JOIN GIOCATORE AS G ON G.id_giocatore = V.id_giocatore " +
                    "WHERE T.tipo = 'INDIVIDUALE' AND G.id_giocatore =  " + giocatore.getId());

            ResultSet rs = leggiTrofeiIndividuali.executeQuery();

            while (rs.next()) {
                trofei.add(new Trofeo(rs.getInt("id_trofeo"), rs.getString("nome"), rs.getString("data"), null, rs.getString("tipo")));
            }

            rs.close();
            leggiTrofeiIndividuali.close();

            leggiTrofeiSquadra = connection.prepareStatement("select T.id_trofeo, T.nome, V.data, V.nomesquadra, T.tipo from trofeo AS T JOIN Vittoria AS V ON V.id_trofeo = T.id_trofeo " +
                    "JOIN GIOCATORE AS G ON G.id_giocatore = V.id_giocatore " +
                    "WHERE T.tipo = 'SQUADRA' AND G.id_giocatore =  " + giocatore.getId());

            ResultSet rs2 = leggiTrofeiSquadra.executeQuery();

            while (rs2.next()) {
                trofei.add(new Trofeo(rs2.getInt("id_trofeo"), rs2.getString("nome"), rs2.getString("data"), rs2.getString("nomesquadra"), rs2.getString("tipo")));
            }
            giocatore.setTrofei(trofei);
            rs2.close();
            leggiTrofeiSquadra.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getTrofeiNome(ArrayList<String> nomeTrofei) {
        PreparedStatement leggiTrofei = null;

        try {
            leggiTrofei = connection.prepareStatement("select nome from trofeo");
            ResultSet rs = leggiTrofei.executeQuery();
            while (rs.next()) {
                nomeTrofei.add(rs.getString("nome"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertTrofeo(Trofeo trofeo) {

        PreparedStatement insertTrofeo = null;
        PreparedStatement idTrofeo = null;

        try {
            insertTrofeo = connection.prepareStatement("insert into trofeo (nome, tipo) values (?, \'" + trofeo.getTipo() + "\')");
            insertTrofeo.setString(1, trofeo.getNome());
            insertTrofeo.executeUpdate();

            idTrofeo = connection.prepareStatement("SELECT currval(pg_get_serial_sequence('trofeo','id_trofeo'))");
            ResultSet rs = idTrofeo.executeQuery();
            rs.next();
            trofeo.setId(rs.getInt(1));

            insertTrofeo.close();
            idTrofeo.close();
            connection.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Errore durante il caricamento dei trofei", "Errore", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    @Override
    public void updateTrofeo(Trofeo trofeo) {
        PreparedStatement updateTrofeo = null;

        try {
            updateTrofeo = connection.prepareStatement("UPDATE trofeo SET nome = ?, tipo = \'" + trofeo.getTipo() + "\' WHERE id_trofeo = ?");
            updateTrofeo.setString(1, trofeo.getNome());
            updateTrofeo.setInt(2, trofeo.getId());
            updateTrofeo.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Errore nella modifica del trofeo", "Errore", JOptionPane.ERROR_MESSAGE);
        }
    }
}
