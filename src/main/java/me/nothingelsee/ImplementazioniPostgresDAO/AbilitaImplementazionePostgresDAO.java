package me.nothingelsee.ImplementazioniPostgresDAO;

import me.nothingelsee.Database.ConnessioneDatabase;
import me.nothingelsee.InterfacceDAO.AbilitaDAO;
import me.nothingelsee.Model.Giocatore;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AbilitaImplementazionePostgresDAO implements AbilitaDAO {

    private Connection connection;

    public AbilitaImplementazionePostgresDAO() {

        try{

            connection = ConnessioneDatabase.getInstance().getConnection();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void getAbilities(Giocatore giocatore){

        PreparedStatement leggiAbilita;
        PreparedStatement leggiNomeAbilita;

        try{
            leggiAbilita = connection.prepareStatement(
                    "SELECT * FROM Abilità WHERE id_giocatore = " + giocatore.getId()
            );

            leggiNomeAbilita = connection.prepareStatement(
                    "SELECT column_name FROM information_schema.columns WHERE table_name   = 'abilità'"
            );

            ResultSet rs = leggiAbilita.executeQuery();
            ResultSet rs2 = leggiNomeAbilita.executeQuery();

            rs.next();
            while(rs2.next()){
                giocatore.addAbilita(rs2.getString(1), rs.getInt(rs2.getRow()));
            }

            rs.close();
            rs2.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void caricaAbilita(Giocatore giocatore){
        PreparedStatement caricaAbilita;

        try{
            caricaAbilita = connection.prepareStatement("INSERT INTO 'Abilità'('velocità', 'tiro', 'passaggio', 'piededebole', 'resistenza', 'difesa', 'tirosupunizione')" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?);");

            for(int i=0; i<giocatore.getAbilita().size(); i++){
                caricaAbilita.setInt(i+1, giocatore.getAbilita().get(i));
            }
            caricaAbilita.executeUpdate();
            caricaAbilita.close();
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Errore nel caricamento delle abilità", "Errore", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}
