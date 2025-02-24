package me.nothingelsee.ImplementazioniPostgresDAO;

import me.nothingelsee.Database.ConnessioneDatabase;
import me.nothingelsee.InterfacceDAO.GiocatoreDAO;
import me.nothingelsee.Model.Giocatore;
import me.nothingelsee.Model.Squadra;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GiocatoreImplementazionePostgresDAO implements GiocatoreDAO {

    private Connection connection;

    public GiocatoreImplementazionePostgresDAO() {
        try{
            connection = ConnessioneDatabase.getInstance().getConnection();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void getGiocatoriByNome(String nome, ArrayList<Giocatore> giocatoriAr) {

        PreparedStatement leggiNomi;

        if(nome.equals("")) {
            try{
                leggiNomi = connection.prepareStatement(
                        "Select  id_giocatore, nome, cognome, dataNascita, dataRitiro, piede FROM Giocatore"
                );

                ResultSet rs = leggiNomi.executeQuery();
                while(rs.next()){
                    giocatoriAr.add(new Giocatore(rs.getInt("id_Giocatore"), rs.getString("nome"), rs.getString("cognome"),
                            rs.getString("dataNascita"), rs.getString("dataRitiro"), rs.getString("piede")));
                }
                rs.close();
                connection.close();
            } catch (SQLException e){
                e.printStackTrace();
            }
        } else {
            try{
                leggiNomi = connection.prepareStatement(
                        "Select  id_giocatore, cognome, dataNascita, dataRitiro, piede FROM Giocatore WHERE nome='"+nome+"'"
                );
                ResultSet rs = leggiNomi.executeQuery();
                while(rs.next()){
                    giocatoriAr.add(new Giocatore(rs.getInt("id_Giocatore"), nome, rs.getString("cognome"),
                            rs.getString("dataNascita"), rs.getString("dataRitiro"), rs.getString("piede")));
                }
                rs.close();
                connection.close();
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void getAbilita(Giocatore giocatore) {
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
    public void getSkill(Giocatore giocatore) {
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

    @Override
    public void getRuoli(Giocatore giocatore) {
        PreparedStatement leggiRuoli;

        try{
            leggiRuoli = connection.prepareStatement(
                    "SELECT tipo FROM Ruolo AS R JOIN Posizione AS P ON R.id_ruolo = P.id_ruolo JOIN Giocatore AS G ON G.id_giocatore = P.id_giocatore " +
                            "WHERE G.id_giocatore = " + giocatore.getId()
            );
            ResultSet rs = leggiRuoli.executeQuery();
            giocatore.clearRuoli();
            while(rs.next()){
                giocatore.addRuolo(rs.getString(1));
            }
            rs.close();
            connection.close();
        } catch(SQLException e ){
            e.printStackTrace();
        }
    }
}
