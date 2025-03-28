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
        PreparedStatement caricaAppartenenza = null;

        try{
            caricaNazionalita = connection.prepareStatement("INSERT INTO 'Nazionalità'(nome)" + " VALUES (?)");
            caricaNazionalita.setString(1, giocatore.getNazionalita());

            caricaAppartenenza = connection.prepareStatement("INSERT INTO appartiene (ig_giocatore, 'nomenazionalità') VALUES (?,?)");
            caricaAppartenenza.setInt(1, giocatore.getId());
            caricaAppartenenza.setString(2, giocatore.getNazionalita());

            caricaNazionalita.executeUpdate();
            caricaAppartenenza.executeUpdate();
            caricaNazionalita.close();
            caricaAppartenenza.close();
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Errore nel caricamento della nazionalità", "Errore", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}