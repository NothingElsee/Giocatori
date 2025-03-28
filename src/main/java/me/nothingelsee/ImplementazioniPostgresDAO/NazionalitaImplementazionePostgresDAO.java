package me.nothingelsee.ImplementazioniPostgresDAO;

import me.nothingelsee.Database.ConnessioneDatabase;
import me.nothingelsee.InterfacceDAO.NazionalitaDAO;
import me.nothingelsee.Model.Giocatore;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class NazionalitaImplementazionePostgresDAO implements NazionalitaDAO {

    private Connection connection;

    public NazionalitaImplementazionePostgresDAO() {
        try{
            connection = ConnessioneDatabase.getInstance().getConnection();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void getNazionalita(ArrayList<String> nazioni){

        PreparedStatement leggiNazioni = null;

        try{
            leggiNazioni = connection.prepareStatement("select nome from 'Nazionalità'");

            ResultSet rs = leggiNazioni.executeQuery();

            while (rs.next()){
                nazioni.add(rs.getString(1));
            }

            rs.close();
            leggiNazioni.close();
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    @Override
    public void caricaNazionalita(Giocatore giocatore) {
        PreparedStatement caricaNazionalita = null;

        try{
            caricaNazionalita = connection.prepareStatement("INSERT INTO 'Nazionalità'(id_giocatore, 'nomenazionalità')" +
                    "VALUES (?,?)");

            caricaNazionalita.setInt(1, giocatore.getId());
            caricaNazionalita.setString(2, giocatore.getNazionalita());
            caricaNazionalita.executeUpdate();
            caricaNazionalita.close();
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Errore nel caricamento della nazionalità", "Errore", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}