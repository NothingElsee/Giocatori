package me.nothingelsee.ImplementazioniPostgresDAO;

import me.nothingelsee.Database.ConnessioneDatabase;
import me.nothingelsee.InterfacceDAO.SquadraDAO;
import me.nothingelsee.Model.Giocatore;
import me.nothingelsee.Model.Militanza;
import me.nothingelsee.Model.Squadra;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SquadraImplementazionePostgresDAO implements SquadraDAO {

    private Connection connection;

    public SquadraImplementazionePostgresDAO() {

        try{

            connection = ConnessioneDatabase.getInstance().getConnection();

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<String> getSquadreNomi() {

        PreparedStatement leggiSquadra;
        ArrayList<String> squadre = new ArrayList<>();

        try{

            leggiSquadra = connection.prepareStatement("SELECT nome FROM squadra");

            ResultSet rs = leggiSquadra.executeQuery();

            while (rs.next()) {
                squadre.add(rs.getString("nome"));
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return squadre;
    }

    @Override
    public void caricaSquadra(Giocatore giocatore) {
        PreparedStatement insertSquadra = null;

        try{
            insertSquadra = connection.prepareStatement("INSERT INTO squadra VALUES (?,?,?,?,?)");

            for(Militanza m : giocatore.getMilitanze()){
                insertSquadra = connection.prepareStatement("INSERT INTO squadra (nome, nomenazione) VALUES (?,?)");
                insertSquadra.setString(1, m.getSquadra().getNome());
                insertSquadra.setString(2, m.getSquadra().getNazionalita());
                insertSquadra.executeUpdate();
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Errore nel caricamento delle squadre", "Errore", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}
