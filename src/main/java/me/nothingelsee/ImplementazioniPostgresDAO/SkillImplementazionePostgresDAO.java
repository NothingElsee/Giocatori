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
        ArrayList<Integer> skills = new ArrayList<>();

        try{
            leggiSkill = connection.prepareStatement(
                    "SELECT * FROM Skill WHERE id_giocatore = " + giocatore.getId()
            );

            ResultSet rs = leggiSkill.executeQuery();

            int i=2;
            rs.next();
            while (i<8) {
                skills.add(rs.getInt(i));
                i++;
            }
            giocatore.setSkills(skills);
            rs.close();
            connection.close();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean insertSkill(int idGiocatore, ArrayList<Integer> skill) {
        PreparedStatement caricaSkill;
        try{
            caricaSkill = connection.prepareStatement("INSERT INTO Skill (id_giocatore, colpoditacco, colpoditesta, rovesciata, sforbiciata, dribbling, controllopalla)" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?);");

            caricaSkill.setInt(1, idGiocatore);

            for(int i=0; i<skill.size(); i++){
                caricaSkill.setInt(i+2, skill.get(i));
            }
            caricaSkill.executeUpdate();
            caricaSkill.close();
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Errore nel caricamento delle skill", "Errore", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public void updateSkill(int idGiocatore, ArrayList<Integer> skill){
        PreparedStatement caricaAbilita;

        try{
            caricaAbilita = connection.prepareStatement("UPDATE Skill " +
                    "SET "+
                    " colpoditacco = " + skill.get(0) +
                    " ,colpoditesta = " + skill.get(1) +
                    " ,rovesciata = " + skill.get(2) +
                    " ,sforbiciata = " + skill.get(3) +
                    " ,dribbling = " + skill.get(4) +
                    " ,controllopalla = " +skill.get(5) +
                    " WHERE id_giocatore = " + idGiocatore);

            caricaAbilita.executeUpdate();
            caricaAbilita.close();
            connection.close();
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Errore nel caricamento delle skill", "Errore", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}
