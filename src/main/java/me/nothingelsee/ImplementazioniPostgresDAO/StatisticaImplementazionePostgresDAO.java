package me.nothingelsee.ImplementazioniPostgresDAO;

import me.nothingelsee.Database.ConnessioneDatabase;
import me.nothingelsee.InterfacceDAO.StatisticaDAO;
import me.nothingelsee.Model.Partita;
import me.nothingelsee.Model.Statistiche;

import javax.swing.*;
import java.sql.*;

public class StatisticaImplementazionePostgresDAO implements StatisticaDAO {

    private Connection connection;

    public StatisticaImplementazionePostgresDAO() {
        try{

            connection = ConnessioneDatabase.getInstance().getConnection();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void getStat(Partita partita){

        PreparedStatement leggiStat;

        try{

            leggiStat = connection.prepareStatement(
                    "SELECT S.goal, S.assist, S.cartellinirossi, S.cartellinigialli, S.rigorisegnati, SP.numparate, SP.goalsubiti " +
                            "FROM Statistica AS S LEFT JOIN statportiere AS SP ON SP.id_partita = S.id_partita " +
                            "WHERE S.id_partita = " + partita.getId()
            );
            ResultSet rs = leggiStat.executeQuery();

            while(rs.next()){
                partita.setStat(new Statistiche(rs.getInt("goal"), rs.getInt("assist"), rs.getInt("cartellinirossi"), rs.getInt("cartellinigialli"), rs.getInt("rigorisegnati"), rs.getInt("numparate"), rs.getInt("goalsubiti")));
            }
            leggiStat.close();
            rs.close();
            connection.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void insertStatistiche(Statistiche statistiche){
        PreparedStatement insStat;

        try {
            insStat = connection.prepareStatement("INSERT INTO Statistica(goal, assist, cartellinirossi, cartellinigialli, rigorisegnati) VALUES (?,?,?,?,?)");
            insStat.setInt(1, statistiche.getGoal());
            insStat.setInt(2, statistiche.getAssist());
            insStat.setInt(3, statistiche.getCartelliniRossi());
            insStat.setInt(4, statistiche.getCartelliniGialli());
            insStat.setInt(5, statistiche.getRigoriSegnati());
            insStat.executeUpdate();
            insStat.close();
            connection.close();
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Errore nel inserimento delle statisthe", "Errore", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    @Override
    public void updateStatistiche(int idPartita, Statistiche statistiche){
        PreparedStatement insStat;

        try {
            insStat = connection.prepareStatement("UPDATE Statistica SET goal = ?, assist = ?, cartellinirossi = ?, cartellinigialli = ?, rigorisegnati = ? WHERE id_partita = ?");
            insStat.setInt(1, statistiche.getGoal());
            insStat.setInt(2, statistiche.getAssist());
            insStat.setInt(3, statistiche.getCartelliniRossi());
            insStat.setInt(4, statistiche.getCartelliniGialli());
            insStat.setInt(5, statistiche.getRigoriSegnati());
            insStat.setInt(6, idPartita);
            insStat.executeUpdate();
            insStat.close();
            connection.close();
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Errore nel inserimento delle statisthe", "Errore", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    @Override
    public void insertStatistichePor(Statistiche statistiche){
        PreparedStatement insStat;

        try {
            insStat = connection.prepareStatement("INSERT INTO statportiere(numParate, goalsubiti) VALUES (?,?)");
            insStat.setInt(1, statistiche.getNumParate());
            insStat.setInt(2, statistiche.getGoalSubiti());
            insStat.executeUpdate();
            insStat.close();
            connection.close();
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Errore nel inserimento delle statisthe", "Errore", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    @Override
    public void updateStatistichePor(int idPartita, Statistiche statistiche){
        PreparedStatement insStat;

        try {
            insStat = connection.prepareStatement("UPDATE statportiere SET numParate = ?, goalsubiti =? ) VALUES (?,?) WHERE id_partita = ?");
            insStat.setInt(1, statistiche.getNumParate());
            insStat.setInt(2, statistiche.getGoalSubiti());
            insStat.setInt(3, idPartita);
            insStat.executeUpdate();
            insStat.close();
            connection.close();
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Errore nel inserimento delle statisthe", "Errore", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}
