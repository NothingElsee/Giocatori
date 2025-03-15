package me.nothingelsee.ImplementazioniPostgresDAO;

import me.nothingelsee.Database.ConnessioneDatabase;
import me.nothingelsee.InterfacceDAO.GiocatoreDAO;
import me.nothingelsee.Model.Giocatore;

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
    public void getGiocatoriByFiltri(ArrayList<Giocatore> giocatoriAr, ArrayList<String> filtri) {

        String select = null;
        PreparedStatement leggiNomi;

            if(filtri.get(0).equals("Nessuno")) select ="Select DISTINCT  G.id_giocatore, G.nome, G.cognome, G.dataNascita, G.dataRitiro, G.piede FROM Giocatore AS G JOIN Posizione AS P ON G.id_giocatore = P.id_giocatore " +
                    "JOIN Ruolo AS R ON P.id_ruolo = R.id_ruolo " +
                    "JOIN Militanza AS M ON G.id_giocatore = M.id_giocatore " +
                    "WHERE " + filtri.get(0) + " " + filtri.get(1) + " " + filtri.get(2) + " " + filtri.get(3);

            try{
                leggiNomi = connection.prepareStatement(select);

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
        }
}
