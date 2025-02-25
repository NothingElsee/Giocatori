package me.nothingelsee.ImplementazioniPostgresDAO;

import me.nothingelsee.Database.ConnessioneDatabase;
import me.nothingelsee.InterfacceDAO.StatisticaDAO;
import me.nothingelsee.Model.Partita;
import me.nothingelsee.Model.Statistiche;

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
            rs.close();
            connection.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
