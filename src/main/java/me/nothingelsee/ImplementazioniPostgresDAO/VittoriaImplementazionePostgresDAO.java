package me.nothingelsee.ImplementazioniPostgresDAO;

import me.nothingelsee.Database.ConnessioneDatabase;
import me.nothingelsee.InterfacceDAO.VittoriaDAO;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
    public int addVittoriaIndividuale(int idTrofeo, int idGiocatore, String data){
        PreparedStatement addVittoria;

        try {
            addVittoria = connection.prepareStatement("insert into vittoria (id_trofeo, id_giocatore, data) values (" + idTrofeo + ", " + idGiocatore + ", \'" +  data + "\')");

            addVittoria.executeUpdate();
            addVittoria.close();
            addVittoria = connection.prepareStatement("select pg_get_serial_sequence('vittoria', 'id_vittoria')");
            ResultSet rs = addVittoria.executeQuery();
            rs.next();
            return rs.getInt(1);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int addVittoriaSquadra(int idTrofeo, String nomeSquadra, String data){
        PreparedStatement addVittoria;

        try {
            addVittoria = connection.prepareStatement("insert into vittoria (id_trofeo, NomeSquadra, data) values (" + idTrofeo + ", " + nomeSquadra + ", \'" +  data + "\')");

            addVittoria.executeUpdate();
            addVittoria.close();
            addVittoria = connection.prepareStatement("select pg_get_serial_sequence('vittoria', 'id_vittoria')");
            ResultSet rs = addVittoria.executeQuery();
            rs.next();
            return rs.getInt(1);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }

}
