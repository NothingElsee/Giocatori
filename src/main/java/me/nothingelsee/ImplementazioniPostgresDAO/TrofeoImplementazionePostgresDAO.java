package me.nothingelsee.ImplementazioniPostgresDAO;

import me.nothingelsee.Database.ConnessioneDatabase;
import me.nothingelsee.InterfacceDAO.TrofeoDAO;
import me.nothingelsee.Model.Giocatore;
import me.nothingelsee.Model.Trofeo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TrofeoImplementazionePostgresDAO implements TrofeoDAO {

    private Connection connection;

    public TrofeoImplementazionePostgresDAO (){

        try{

            connection = ConnessioneDatabase.getInstance().getConnection();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getTrofei(Giocatore giocatore) {

        PreparedStatement leggiTrofeiIndividuali = null;
        PreparedStatement leggiTrofeiSquadra = null;

        try{

            leggiTrofeiIndividuali = connection.prepareStatement("select T.nome, V.data, T.tipo from trofeo AS T JOIN Vittoria AS V ON V.id_trofeo = T.id_trofeo " +
                    "JOIN GIOCATORE AS G ON G.id_giocatore = V.id_giocatore\n" +
                    "WHERE G.id_giocatore =  " + giocatore.getId());

            ResultSet rs = leggiTrofeiIndividuali.executeQuery();

            while(rs.next()){
                giocatore.addTrofeo(new Trofeo(rs.getString("nome"), rs.getString("data"), rs.getString("tipo")));
            }

            rs.close();

            leggiTrofeiSquadra = connection.prepareStatement("select T.nome, V.data, T.tipo, V.nomesquadra from trofeo AS T JOIN Vittoria AS V ON V.id_trofeo = T.id_trofeo " +
                    "JOIN Squadra AS S ON S.nome = V.nomesquadra " +
                    "JOIN Militanza AS M ON M.nomesquadra = S.nome " +
                    "JOIN Giocatore AS G ON G.id_giocatore = M.id_giocatore " +
                    "WHERE G.id_giocatore = " + giocatore.getId());

            rs = leggiTrofeiSquadra.executeQuery();

            while(rs.next()){
                giocatore.addTrofeo(new Trofeo(rs.getString("nome"), rs.getString("data"), rs.getString("nomesquadra"), rs.getString("tipo")));
            }

            rs.close();
            connection.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
