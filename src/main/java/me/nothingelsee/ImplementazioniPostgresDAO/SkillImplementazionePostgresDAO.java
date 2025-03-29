package me.nothingelsee.ImplementazioniPostgresDAO;

import me.nothingelsee.Database.ConnessioneDatabase;
import me.nothingelsee.InterfacceDAO.SkillDAO;
import me.nothingelsee.Model.Giocatore;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
    public void insertSkill(ArrayList<Integer> skill) {
        PreparedStatement caricaSkill;
        try{
            caricaSkill = connection.prepareStatement("INSERT INTO 'Skill'(colpoditacco, colpoditesta, rovesciata, sforbiciata, dribbling, controllopalla)" +
                    "VALUES (?, ?, ?, ?, ?, ?);");

            for(int i=0; i<skill.size(); i++){
                caricaSkill.setInt(i+1, skill.get(i));
            }
            caricaSkill.executeUpdate();
            caricaSkill.close();
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Errore nel caricamento delle skill", "Errore", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    @Override
    public void updateSkill(int idGiocatore, ArrayList<Integer> skill){
        PreparedStatement caricaAbilita;

        try{
            caricaAbilita = connection.prepareStatement("UPDATE 'Abilità' " +
                    "SET 'velocità' = " + skill.get(0) +
                    " ,colpoditacco" + skill.get(1) +
                    " ,colpoditesta = " + skill.get(2) +
                    " ,rovesciata = " + skill.get(3) +
                    " ,sforbiciata = " + skill.get(4) +
                    " ,dribbling = " + skill.get(5) +
                    " ,controllopalla = " +skill.get(6) +
                    " WHERE id_giocatore = " + idGiocatore);

            caricaAbilita.executeUpdate();
            caricaAbilita.close();
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Errore nel caricamento delle abilità", "Errore", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}
