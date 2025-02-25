package me.nothingelsee.ImplementazioniPostgresDAO;

import me.nothingelsee.Database.ConnessioneDatabase;
import me.nothingelsee.InterfacceDAO.GiocatoreDAO;
import me.nothingelsee.Model.Giocatore;
import me.nothingelsee.Model.Squadra;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
    public void getGiocatoriByNome(String nome, ArrayList<Giocatore> giocatoriAr) {

        PreparedStatement leggiNomi;

        if(nome.equals("")) {
            try{
                leggiNomi = connection.prepareStatement(
                        "Select  id_giocatore, nome, cognome, dataNascita, dataRitiro, piede FROM Giocatore"
                );

                ResultSet rs = leggiNomi.executeQuery();
                while(rs.next()){
                    giocatoriAr.add(new Giocatore(rs.getInt("id_Giocatore"), rs.getString("nome"), rs.getString("cognome"),
                            rs.getString("dataNascita"), rs.getString("dataRitiro"), rs.getString("piede")));
                }
                rs.close();
                connection.close();
            } catch (SQLException e){
                e.printStackTrace();
            }
        } else {
            try{
                leggiNomi = connection.prepareStatement(
                        "Select  id_giocatore, cognome, dataNascita, dataRitiro, piede FROM Giocatore WHERE nome='"+nome+"'"
                );
                ResultSet rs = leggiNomi.executeQuery();
                while(rs.next()){
                    giocatoriAr.add(new Giocatore(rs.getInt("id_Giocatore"), nome, rs.getString("cognome"),
                            rs.getString("dataNascita"), rs.getString("dataRitiro"), rs.getString("piede")));
                }
                rs.close();
                connection.close();
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
}
