package me.nothingelsee.ImplementazioniPostgresDAO;

import me.nothingelsee.Database.ConnessioneDatabase;
import me.nothingelsee.InterfacceDAO.MilitanzaDAO;
import me.nothingelsee.Model.Giocatore;
import me.nothingelsee.Model.Militanza;
import me.nothingelsee.Model.Squadra;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MilitanzaImplementazionePostgresDAO implements MilitanzaDAO {

    private Connection connection;

    public MilitanzaImplementazionePostgresDAO() {
        try{
            connection = ConnessioneDatabase.getInstance().getConnection();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void getMilitanze(Giocatore giocatore) {
        PreparedStatement leggiMilitanze;

        try{

            leggiMilitanze = connection.prepareStatement(
                    "SELECT M.id_militanza, M.datainizio, M.datafine, S.nome, S.nomenazione FROM GIOCATORE AS G JOIN Militanza AS M ON G.id_giocatore = M.id_giocatore" +
                            " JOIN Squadra AS S ON M.nomesquadra = S.nome WHERE G.id_giocatore = " + giocatore.getId() + " ORDER BY M.datafine DESC"
            );

            ResultSet rs = leggiMilitanze.executeQuery();
            giocatore.clearMilitanze();
            while (rs.next()){
                giocatore.addMilitanza(new Militanza(rs.getInt("id_militanza"), rs.getString("datainizio"), rs.getString("datafine"), new Squadra(rs.getString("nome"), rs.getString("nomenazione"))));
            }
            rs.close();
            connection.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void caricaMilitanta(Militanza militanza) {
        PreparedStatement caricaMilitanza = null;

        try{
            caricaMilitanza = connection.prepareStatement("INSERT INTO Militanza(id_giocatore, nomesquadra, datainizio, datafine) VALUES (?,?,?,?)");

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
