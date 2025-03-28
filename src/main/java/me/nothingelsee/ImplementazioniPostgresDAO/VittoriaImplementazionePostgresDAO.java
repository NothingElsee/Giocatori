package me.nothingelsee.ImplementazioniPostgresDAO;

import me.nothingelsee.Database.ConnessioneDatabase;
import me.nothingelsee.InterfacceDAO.VittoriaDAO;
import me.nothingelsee.Model.Giocatore;
import me.nothingelsee.Model.Trofeo;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class VittoriaImplementazionePostgresDAO implements VittoriaDAO {

    private Connection connection;

    public VittoriaImplementazionePostgresDAO(){
        try{
            connection = ConnessioneDatabase.getInstance().getConnection();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void caricaVittoria(Giocatore giocatore) {
        PreparedStatement insertVittoria = null;


    }

    private void caricaVittoriaIndivisuale(Giocatore giocatore) {
        PreparedStatement insertVittoria = null;

        try{
            insertVittoria = connection.prepareStatement("insert into vittoria(id_trofeo, id_giocatore, nomesquadra, data) values(?,?,?,?)");

            for(Trofeo trofeo : giocatore.getTrofei()){
                if(trofeo.getTipo().equals("INDIVIDUALE")){
                    insertVittoria.setInt(1, trofeo.getId());
                    insertVittoria.setInt(2, giocatore.getId());
                    insertVittoria.setString(3, null);
                    insertVittoria.setString(4, trofeo.getData());
                } else if(trofeo.getTipo().equals("SQUADRA")){
                    insertVittoria.setInt(1, trofeo.getId());
                    insertVittoria.setNull(2, Types.NULL);
                    insertVittoria.setString(3, trofeo.getSquadra());
                    insertVittoria.setString(4, trofeo.getData());
                }
                insertVittoria.executeUpdate();
            }
            insertVittoria.close();
            connection.close();
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Errore durante il caricametno della vittoria indivisuale!", "Errore", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

}
