package me.nothingelsee.ImplementazioniPostgresDAO;

import me.nothingelsee.Database.ConnessioneDatabase;
import me.nothingelsee.InterfacceDAO.SkillDAO;
import me.nothingelsee.Model.Giocatore;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SkillImplementazionePostgresDAO implements SkillDAO {

    private Connection connection;

    public SkillImplementazionePostgresDAO() {
        try{

            connection = ConnessioneDatabase.getInstance().getConnection();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void getSkills(Giocatore giocatore) {
        PreparedStatement leggiSkill;

        try{
            leggiSkill = connection.prepareStatement(
                    "SELECT * FROM Skill WHERE id_giocatore = " + giocatore.getId()
            );

            ResultSet rs = leggiSkill.executeQuery();

            int i=1;
            while (rs.next()) {
                giocatore.addSkill(rs.getInt(i));
                i++;
            }

            rs.close();
            connection.close();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void caricaSkill(Giocatore giocatore) {
        PreparedStatement caricaSkill;
        try{
            caricaSkill = connection.prepareStatement("INSERT INTO 'Skill'(colpoditacco, colpoditesta, rovesciata, sforbiciata, dribbling, controllopalla)" +
                    "VALUES (?, ?, ?, ?, ?, ?);");

            for(int i=0; i<giocatore.getSkill().size(); i++){
                caricaSkill.setInt(i+1, giocatore.getSkill().get(i));
            }
            caricaSkill.executeUpdate();
            caricaSkill.close();
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Errore nel caricamento delle skill", "Errore", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}
