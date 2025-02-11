package me.nothingelsee.ImplementazioniPostgresDAO;

import me.nothingelsee.Database.ConnessioneDatabase;
import me.nothingelsee.InterfacceDAO.GiocatoreDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class GiocatoreImplementazionePostgresDAO implements GiocatoreDAO {

    private Connection connection;

    public GiocatoreImplementazionePostgresDAO() {
        try{
            connection = ConnessioneDatabase.getInstance().getConnection();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void getGiocatoriByNome(String nome,ArrayList<Integer> codiciGiocatori, ArrayList<String> cognomiGiocatore, ArrayList<Date> dateGiocatori){

        PreparedStatement leggiNomi;

        try{
            leggiNomi = connection.prepareStatement(
                "Select  id_giocatore, cognome, dataNascita FROM Giocatore WHERE nome='"+nome+"'"
            );
        ResultSet rs = leggiNomi.executeQuery();
        while(rs.next()){
            codiciGiocatori.add(rs.getInt("id_Giocatore"));
            cognomiGiocatore.add(rs.getString("cognome"));
            dateGiocatori.add(rs.getDate("dataNascita"));
        }
        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("ASDADASDASDASDASD");
        }
    }
}
