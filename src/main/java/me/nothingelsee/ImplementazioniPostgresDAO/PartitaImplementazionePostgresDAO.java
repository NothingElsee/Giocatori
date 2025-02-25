package me.nothingelsee.ImplementazioniPostgresDAO;

import me.nothingelsee.Database.ConnessioneDatabase;
import me.nothingelsee.InterfacceDAO.PartitaDAO;
import me.nothingelsee.Model.Militanza;
import me.nothingelsee.Model.Partita;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PartitaImplementazionePostgresDAO implements PartitaDAO {

    private Connection connection;

    public PartitaImplementazionePostgresDAO() {

        try {

            connection = ConnessioneDatabase.getInstance().getConnection();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getPartite(Militanza militanza){

        PreparedStatement leggiPartite;

        try{

            leggiPartite = connection.prepareStatement(
                    "SELECT Ma.id_partita, MA.datapartita, MA.goalcasa, Ma.goaltrasferta, MA.nomecomp, MA.tipocomp, P.squadracasa, P.squadratrasferta " +
                            "FROM Militanza AS M JOIN Match AS MA ON M.id_militanza = MA.id_militanza " +
                            "JOIN Partecipazione AS P ON MA.id_partita = P.id_partita " +
                            "WHERE M.id_militanza = " + militanza.getId()
            );

            ResultSet rs = leggiPartite.executeQuery();

            while(rs.next()){
                militanza.getPartite().add(new Partita(rs.getInt("id_partita"), rs.getString("squadracasa"), rs.getString("squadratrasferta"), rs.getInt("goalcasa"), rs.getInt("goaltrasferta"), rs.getString("datapartita"), rs.getString("tipocomp"), rs.getString("nomecomp")));
            }

            rs.close();
            connection.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
