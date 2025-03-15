package me.nothingelsee.ImplementazioniPostgresDAO;

import me.nothingelsee.Database.ConnessioneDatabase;
import me.nothingelsee.InterfacceDAO.SquadraDAO;
import me.nothingelsee.Model.Squadra;

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
}
