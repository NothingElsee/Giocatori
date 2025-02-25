package me.nothingelsee.ImplementazioniPostgresDAO;

import me.nothingelsee.Database.ConnessioneDatabase;
import me.nothingelsee.InterfacceDAO.SkillDAO;
import me.nothingelsee.Model.Giocatore;

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
        PreparedStatement leggiNomeSkill;

        try{
            leggiSkill = connection.prepareStatement(
                    "SELECT * FROM Skill WHERE id_giocatore = " + giocatore.getId()
            );

            leggiNomeSkill = connection.prepareStatement(
                    "SELECT column_name FROM information_schema.columns WHERE table_name   = 'skill'"
            );

            ResultSet rs = leggiSkill.executeQuery();
            ResultSet rs2 = leggiNomeSkill.executeQuery();

            rs.next();
            while(rs2.next()){
                giocatore.addSkill(rs2.getString(1), rs.getInt(rs2.getRow()));
            }

            rs.close();
            rs2.close();
            connection.close();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }
}
