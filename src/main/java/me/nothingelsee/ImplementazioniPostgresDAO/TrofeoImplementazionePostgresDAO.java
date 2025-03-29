package me.nothingelsee.ImplementazioniPostgresDAO;

import me.nothingelsee.Database.ConnessioneDatabase;
import me.nothingelsee.ENUM.COMPETIZIONE;
import me.nothingelsee.ENUM.TROFEO;
import me.nothingelsee.InterfacceDAO.TrofeoDAO;
import me.nothingelsee.Model.Giocatore;
import me.nothingelsee.Model.Trofeo;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TrofeoImplementazionePostgresDAO implements TrofeoDAO {

    private Connection connection;

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

        try {

            leggiTrofeiIndividuali = connection.prepareStatement("select T.id_trofeo, T.nome, V.data, T.tipo from trofeo AS T JOIN Vittoria AS V ON V.id_trofeo = T.id_trofeo " +
                    "JOIN GIOCATORE AS G ON G.id_giocatore = V.id_giocatore\n" +
                    "WHERE G.id_giocatore =  " + giocatore.getId());

            ResultSet rs = leggiTrofeiIndividuali.executeQuery();

            while (rs.next()) {
                giocatore.addTrofeo(new Trofeo(rs.getInt("id_trofeo"), rs.getString("nome"), rs.getString("data"), null ,rs.getString("tipo")));
            }

            rs.close();

            leggiTrofeiSquadra = connection.prepareStatement("select T.id_trofeo, T.nome, V.data, T.tipo, V.nomesquadra from trofeo AS T JOIN Vittoria AS V ON V.id_trofeo = T.id_trofeo " +
                    "JOIN Squadra AS S ON S.nome = V.nomesquadra " +
                    "JOIN Militanza AS M ON M.nomesquadra = S.nome " +
                    "JOIN Giocatore AS G ON G.id_giocatore = M.id_giocatore " +
                    "WHERE G.id_giocatore = " + giocatore.getId());

            rs = leggiTrofeiSquadra.executeQuery();

            while (rs.next()) {
                giocatore.addTrofeo(new Trofeo(rs.getInt("id_trofeo"), rs.getString("nome"), rs.getString("data"), rs.getString("nomesquadra"), rs.getString("tipo")));
            }

            rs.close();
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
            insertTrofeo = connection.prepareStatement("insert into trofeo (nome, data) values (?, ?)");
            insertTrofeo.setString(1, trofeo.getNome());
            insertTrofeo.setString(2, trofeo.getData());
            insertTrofeo.executeUpdate();

            idTrofeo = connection.prepareStatement("SELECT currval(pg_get_serial_sequence('trofeo','id_trofeo'))");
            ResultSet rs = idTrofeo.executeQuery();
            trofeo.setId(rs.getInt(1));

            insertTrofeo.close();
            idTrofeo.close();
            connection.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Errore durante il caricamento dei trofei", "Errore", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}
