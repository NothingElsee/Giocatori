package me.nothingelsee.ImplementazioniPostgresDAO;

import me.nothingelsee.Database.ConnessioneDatabase;
import me.nothingelsee.InterfacceDAO.NazionalitaDAO;
import me.nothingelsee.Model.Giocatore;

import javax.print.attribute.standard.Destination;
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
            leggiNazioni = connection.prepareStatement("select nome FROM nazionalità");

            ResultSet rs = leggiNazioni.executeQuery();

            while (rs.next()){
                nazioni.add(rs.getString(1));
            }

            rs.close();
            leggiNazioni.close();
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    @Override
    public void insertNazionalita(int idGiocatore, String nome) {
        PreparedStatement caricaNazionalita = null;
        PreparedStatement caricaAppartenenza = null;
        PreparedStatement esisteNazionalita = null;

        try{
            esisteNazionalita= connection.prepareStatement("Select * from Nazionalità where nome = ?");
            esisteNazionalita.setString(1, nome);
            ResultSet rs = esisteNazionalita.executeQuery();
            if(!rs.next()){
                caricaNazionalita = connection.prepareStatement("INSERT INTO Nazionalità(nome)" + " VALUES (?)");
                caricaNazionalita.setString(1, nome);
                caricaNazionalita.executeUpdate();
                caricaNazionalita.close();
            }
            caricaAppartenenza = connection.prepareStatement("INSERT INTO appartiene (id_giocatore, nomenazionalità) VALUES (?,?)");
            caricaAppartenenza.setInt(1, idGiocatore);
            caricaAppartenenza.setString(2, nome);
            caricaAppartenenza.executeUpdate();

            caricaAppartenenza.close();
            rs.close();
            connection.close();
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Errore durante l'inserimento della nazionalità", "Errore", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    @Override
    public void updateNazionalita(int idGiocatore, String nome) {
        PreparedStatement caricaNazionalita = null;
        PreparedStatement caricaAppartenenza = null;
        PreparedStatement esisteNazionalita = null;

        try{
            esisteNazionalita= connection.prepareStatement("Select * from Nazionalità where nome = ?");
            esisteNazionalita.setString(1, nome);
            ResultSet rs = esisteNazionalita.executeQuery();

            if(!rs.next()){
                caricaNazionalita = connection.prepareStatement("INSERT INTO Nazionalità(nome)" + " VALUES (?)");
                caricaNazionalita.setString(1, nome);
                caricaNazionalita.executeUpdate();
                caricaNazionalita.close();
            }
            caricaAppartenenza = connection.prepareStatement("UPDATE appartiene SET id_giocatore = (?), nomenazionalità = (?) WHERE id_giocatore = (?)");
            caricaAppartenenza.setInt(1, idGiocatore);
            caricaAppartenenza.setString(2, nome);
            caricaAppartenenza.setInt(3, idGiocatore);
            caricaAppartenenza.executeUpdate();

            caricaAppartenenza.close();
            rs.close();
            connection.close();
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Errore l'aggiornamento della nazionalità", "Errore", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}