package me.nothingelsee.ImplementazioniPostgresDAO;

import me.nothingelsee.Database.ConnessioneDatabase;
import me.nothingelsee.InterfacceDAO.PartitaDAO;
import me.nothingelsee.Model.Giocatore;
import me.nothingelsee.Model.Militanza;
import me.nothingelsee.Model.Partita;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PartitaImplementazionePostgresDAO implements PartitaDAO {

    private Connection connection;

    public PartitaImplementazionePostgresDAO() {

        try {

            connection = ConnessioneDatabase.getInstance().getConnection();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getPartite(Militanza militanza) {

        PreparedStatement leggiPartite;

        try {

            leggiPartite = connection.prepareStatement(
                    "SELECT Ma.id_partita, MA.datapartita, MA.goalcasa, Ma.goaltrasferta, MA.nomecomp, MA.tipocomp, P.squadracasa, P.squadratrasferta " +
                            "FROM Militanza AS M JOIN Match AS MA ON M.id_militanza = MA.id_militanza " +
                            "JOIN Partecipazione AS P ON MA.id_partita = P.id_partita " +
                            "WHERE M.id_militanza = " + militanza.getId()
            );

            ResultSet rs = leggiPartite.executeQuery();

            while (rs.next()) {
                militanza.getPartite().add(new Partita(rs.getInt("id_partita"), rs.getString("squadracasa"), rs.getString("squadratrasferta"), rs.getInt("goalcasa"), rs.getInt("goaltrasferta"), rs.getString("datapartita"), rs.getString("tipocomp"), rs.getString("nomecomp")));
            }

            rs.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertPartita(int idMilitanza, Partita p) {
        PreparedStatement insertPartite;

        try {

            insertPartite = connection.prepareStatement("insert into match(id_militanza, datapartita, goalcasa, goaltrasferta) values(?,?,?,?)");


            insertPartite.setInt(1, idMilitanza);
            insertPartite.setString(2, p.getData());
            insertPartite.setInt(3, p.getGoalCasa());
            insertPartite.setInt(4, p.getGoalTrasferta());
            insertPartite.executeUpdate();
            insertPartite.close();
            insertPartite = connection.prepareStatement("SELECT currval(pg_get_serial_sequence('match','id_partita'))");
            ResultSet rs = insertPartite.executeQuery();
            p.setId(rs.getInt(1));
            rs.close();
            insertPartite.close();
            connection.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Errore nel caricamento delle partite!", "Errore", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    @Override
    public void updatePartita(Partita p) {
        PreparedStatement insertPartite;

        try {

            insertPartite = connection.prepareStatement("update match SET datapartita = ?, goalcasa = ?, goaltrasferta = ?)WHERE id_partita = ?");
            insertPartite.setString(1, p.getData());
            insertPartite.setInt(2, p.getGoalCasa());
            insertPartite.setInt(3, p.getGoalTrasferta());
            insertPartite.setInt(4, p.getId());
            insertPartite.executeUpdate();

            insertPartite.close();
            connection.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Errore nel caricamento delle partite!", "Errore", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    @Override
    public void deletePartita(Partita p){
        PreparedStatement deleteP = null;

        try{
            deleteP = connection.prepareStatement("DELETE FROM match WHERE id_partita = ?");
            deleteP.setInt(1, p.getId());
            deleteP.executeUpdate();

            deleteP.close();
            connection.close();

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Errore durante l'eliminazione della partita", "Errore", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}
