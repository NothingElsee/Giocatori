package me.nothingelsee.ImplementazioniPostgresDAO;

import me.nothingelsee.Database.ConnessioneDatabase;
import me.nothingelsee.InterfacceDAO.MilitanzaDAO;
import me.nothingelsee.Model.Giocatore;
import me.nothingelsee.Model.Militanza;
import me.nothingelsee.Model.Squadra;

import javax.swing.*;
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
    public void caricaMilitanta(Giocatore giocatore) {
        PreparedStatement caricaMilitanza = null;

        try{
            caricaMilitanza = connection.prepareStatement("INSERT INTO Militanza(id_giocatore, nomesquadra, datainizio, datafine) VALUES (?,?,?,?)");
            for(Militanza m : giocatore.getMilitanze()){
                caricaMilitanza.setInt(1, giocatore.getId());
                caricaMilitanza.setString(2, m.getSquadra().getNome());
                caricaMilitanza.setString(3, m.getDataInizio());
                caricaMilitanza.setString(4, m.getDataFine());
                caricaMilitanza.executeUpdate();
            }
            caricaMilitanza.close();
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Errore durante il caricamento delle militanze", "Errore", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}
