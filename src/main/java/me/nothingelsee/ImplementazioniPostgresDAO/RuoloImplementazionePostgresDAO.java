package me.nothingelsee.ImplementazioniPostgresDAO;

import me.nothingelsee.Database.ConnessioneDatabase;
import me.nothingelsee.InterfacceDAO.RuoloDAO;
import me.nothingelsee.Model.Giocatore;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RuoloImplementazionePostgresDAO implements RuoloDAO {

    private Connection connection;

    public RuoloImplementazionePostgresDAO() {
        try{

            connection = ConnessioneDatabase.getInstance().getConnection();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void getRuoli(Giocatore giocatore) {

        PreparedStatement leggiRuoli;

        try{
            leggiRuoli = connection.prepareStatement(
                    "SELECT tipo FROM Ruolo AS R JOIN Posizione AS P ON R.id_ruolo = P.id_ruolo JOIN Giocatore AS G ON G.id_giocatore = P.id_giocatore " +
                            "WHERE G.id_giocatore = " + giocatore.getId()
            );
            ResultSet rs = leggiRuoli.executeQuery();
            giocatore.clearRuoli();
            while(rs.next()){
                giocatore.addRuolo(rs.getString(1));
            }
            rs.close();
            connection.close();
        } catch(SQLException e ){
            e.printStackTrace();
        }
    }
}
