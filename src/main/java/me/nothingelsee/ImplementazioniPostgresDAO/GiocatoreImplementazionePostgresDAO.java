package me.nothingelsee.ImplementazioniPostgresDAO;

import me.nothingelsee.Database.ConnessioneDatabase;
import me.nothingelsee.ENUM.PIEDE;
import me.nothingelsee.InterfacceDAO.GiocatoreDAO;
import me.nothingelsee.Model.Giocatore;

import javax.swing.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class GiocatoreImplementazionePostgresDAO implements GiocatoreDAO {

    private Connection connection;

    public GiocatoreImplementazionePostgresDAO() {
        try {
            connection = ConnessioneDatabase.getInstance().getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Integer> getGiocatoriByFiltri(ArrayList<Giocatore> giocatoriAr, ArrayList<String> filtri) {

        String select = null;
        PreparedStatement leggiNomi;
        ArrayList<Integer> stat = new ArrayList<>();

        switch (filtri.get(4)) {

            case "Nessuno":
                select = "Select DISTINCT  G.id_giocatore, G.nome AS nomeG, G.cognome, G.dataNascita, N.nome AS nomeN, G.dataRitiro, G.piede FROM Giocatore AS G LEFT JOIN Posizione AS P ON P.id_giocatore = G.id_giocatore LEFT JOIN Ruolo AS R ON R.id_ruolo = P.id_ruolo " + "LEFT JOIN Militanza AS M ON G.id_giocatore = M.id_giocatore LEFT JOIN Appartiene AS A ON A.id_giocatore=G.id_giocatore LEFT JOIN nazionalità AS N ON N.nome = A.nomenazionalità " + "WHERE " + filtri.get(0) + " " + filtri.get(1) + " " + filtri.get(2) + " " + filtri.get(3) + " " + "ORDER BY G.Nome " + filtri.get(5) + ", G.Cognome " + filtri.get(5);
                break;
            case "Goal":
                select = "Select G.id_giocatore, G.nome, G.cognome, G.dataNascita, G.dataRitiro, G.piede, COALESCE(SUM(s.Goal), 0) AS stat FROM Giocatore AS G JOIN Posizione AS P ON P.id_giocatore = G.id_giocatore JOIN Ruolo AS R ON R.id_ruolo = P.id_ruolo " + "JOIN Militanza AS M ON G.id_giocatore = M.id_giocatore " + "LEFT JOIN match as Ma ON Ma.id_militanza = M.id_militanza " + "LEFT JOIN Statistica AS S ON S.id_partita = Ma.id_partita LEFT JOIN Appartiene AS A ON A.id_giocatore=G.id_giocatore LEFT JOIN nazionalità AS N ON N.nome = A.nomenazionalità " + "WHERE " + filtri.get(0) + " " + filtri.get(1) + " " + filtri.get(2) + " " + filtri.get(3) + " GROUP BY G.id_giocatore, G.nome, G.cognome " + "ORDER BY stat " + filtri.get(5);
                break;
            case "Assist":
                select = "Select G.id_giocatore, G.nome, G.cognome, G.dataNascita, G.dataRitiro, G.piede, COALESCE(SUM(s.Assist), 0) AS stat FROM Giocatore AS G JOIN Posizione AS P ON P.id_giocatore = G.id_giocatore JOIN Ruolo AS R ON R.id_ruolo = P.id_ruolo " + "JOIN Militanza AS M ON G.id_giocatore = M.id_giocatore " + "LEFT JOIN match as Ma ON Ma.id_militanza = M.id_militanza " + "LEFT JOIN Statistica AS S ON S.id_partita = Ma.id_partita LEFT JOIN Appartiene AS A ON A.id_giocatore=G.id_giocatore LEFT JOIN nazionalità AS N ON N.nome = A.nomenazionalità " + "WHERE " + filtri.get(0) + " " + filtri.get(1) + " " + filtri.get(2) + " " + filtri.get(3) + " GROUP BY G.id_giocatore, G.nome, G.cognome " + "ORDER BY stat " + filtri.get(5);
                break;
            case "Cartellini Rossi":
                select = "Select G.id_giocatore, G.nome, G.cognome, G.dataNascita, G.dataRitiro, G.piede, COALESCE(SUM(s.CartelliniRossi), 0) AS stat FROM Giocatore AS G JOIN Posizione AS P ON P.id_giocatore = G.id_giocatore JOIN Ruolo AS R ON R.id_ruolo = P.id_ruolo " + "JOIN Militanza AS M ON G.id_giocatore = M.id_giocatore " + "LEFT JOIN match as Ma ON Ma.id_militanza = M.id_militanza " + "LEFT JOIN Statistica AS S ON S.id_partita = Ma.id_partita LEFT JOIN Appartiene AS A ON A.id_giocatore=G.id_giocatore LEFT JOIN nazionalità AS N ON N.nome = A.nomenazionalità " + "WHERE " + filtri.get(0) + " " + filtri.get(1) + " " + filtri.get(2) + " " + filtri.get(3) + " GROUP BY G.id_giocatore, G.nome, G.cognome " + "ORDER BY stat " + filtri.get(5);
                break;
            case "Cartellioni Gialli":
                select = "Select G.id_giocatore, G.nome, G.cognome, G.dataNascita, G.dataRitiro, G.piede, COALESCE(SUM(s.CartelliniGialli), 0) AS stat FROM Giocatore AS G JOIN Posizione AS P ON P.id_giocatore = G.id_giocatore JOIN Ruolo AS R ON R.id_ruolo = P.id_ruolo " + "JOIN Militanza AS M ON G.id_giocatore = M.id_giocatore " + "LEFT JOIN match as Ma ON Ma.id_militanza = M.id_militanza " + "LEFT JOIN Statistica AS S ON S.id_partita = Ma.id_partita LEFT JOIN Appartiene AS A ON A.id_giocatore=G.id_giocatore LEFT JOIN nazionalità AS N ON N.nome = A.nomenazionalità " + "WHERE " + filtri.get(0) + " " + filtri.get(1) + " " + filtri.get(2) + " " + filtri.get(3) + " GROUP BY G.id_giocatore, G.nome, G.cognome " + "ORDER BY stat " + filtri.get(5);
                break;
            case "Numero Parate":
                select = "Select G.id_giocatore, G.nome, G.cognome, G.dataNascita, G.dataRitiro, G.piede, COALESCE(SUM(s.NumParate), 0) AS stat FROM Giocatore AS G JOIN Posizione AS P ON P.id_giocatore = G.id_giocatore JOIN Ruolo AS R ON R.id_ruolo = P.id_ruolo " + "JOIN Militanza AS M ON G.id_giocatore = M.id_giocatore " + "LEFT JOIN match as Ma ON Ma.id_militanza = M.id_militanza " + "LEFT JOIN StatPortiere AS S ON S.id_partita = Ma.id_partita LEFT JOIN Appartiene AS A ON A.id_giocatore=G.id_giocatore LEFT JOIN nazionalità AS N ON N.nome = A.nomenazionalità " + "WHERE " + filtri.get(0) + " " + filtri.get(1) + " " + filtri.get(2) + " " + filtri.get(3) + " GROUP BY G.id_giocatore, G.nome, G.cognome " + "ORDER BY stat " + filtri.get(5);
                break;
            case "Numero Goal Subiti":
                select = "Select G.id_giocatore, G.nome, G.cognome, G.dataNascita, G.dataRitiro, G.piede, COALESCE(SUM(s.GoalSubiti), 0) AS stat FROM Giocatore AS G JOIN Posizione AS P ON P.id_giocatore = G.id_giocatore JOIN Ruolo AS R ON R.id_ruolo = P.id_ruolo " + "JOIN Militanza AS M ON G.id_giocatore = M.id_giocatore " + "LEFT JOIN match as Ma ON Ma.id_militanza = M.id_militanza " + "LEFT JOIN StatPortiere AS S ON S.id_partita = Ma.id_partita LEFT JOIN Appartiene AS A ON A.id_giocatore=G.id_giocatore LEFT JOIN nazionalità AS N ON N.nome = A.nomenazionalità " + "WHERE " + filtri.get(0) + " " + filtri.get(1) + " " + filtri.get(2) + " " + filtri.get(3) + " GROUP BY G.id_giocatore, G.nome, G.cognome " + "ORDER BY stat " + filtri.get(5);
                break;
        }

        try {
            leggiNomi = connection.prepareStatement(select);

            ResultSet rs = leggiNomi.executeQuery();

            if (filtri.get(4).equals("Nessuno")) {
                while (rs.next()) {
                    giocatoriAr.add(new Giocatore(rs.getInt("id_Giocatore"), rs.getString("nomeG"), rs.getString("cognome"), rs.getString("dataNascita"), rs.getString("nomeN"), rs.getString("dataRitiro"), rs.getString("piede")));
                }
                stat = null;
            } else {
                while (rs.next()) {
                    giocatoriAr.add(new Giocatore(rs.getInt("id_Giocatore"), rs.getString("G.nome"), rs.getString("cognome"), rs.getString("dataNascita"), rs.getString("nomeN"), rs.getString("dataRitiro"), rs.getString("piede")));
                    stat.add(rs.getInt("stat"));
                }
            }
            rs.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return stat;
    }

    @Override
    public void insertGiocatore(Giocatore giocatore){
        try{
            PreparedStatement insGio = connection.prepareStatement("INSERT INTO Giocatore(nome, cognome, datanascita, dataritiro, piede) VALUES (?,?,?,?," + "\'" + giocatore.getPiede().name() + "\')");
            insGio.setString(1, giocatore.getNome());
            insGio.setString(2, giocatore.getCognome());
            insGio.setDate(3, Date.valueOf(giocatore.getDataNascita()));
            if(giocatore.getDataRitiro() != null) insGio.setDate(4, Date.valueOf(giocatore.getDataRitiro()));
            else insGio.setDate(4, null);

            insGio.executeUpdate();

            insGio.close();
            insGio = connection.prepareStatement("SELECT currval(pg_get_serial_sequence('giocatore','id_giocatore'))");
            ResultSet rs = insGio.executeQuery();
            rs.next();
            giocatore.setId(rs.getInt(1));

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void updateGiocatore(Giocatore giocatore){
        try{
            PreparedStatement insGio = connection.prepareStatement("UPDATE Giocatore SET nome = ?, cognome = ?, datanascita = ?, dataritiro = ?, piede = \'" + giocatore.getPiede().toString() +"\' WHERE id_giocatore = ?");


            insGio.setString(1, giocatore.getNome());
            insGio.setString(2, giocatore.getCognome());
            insGio.setDate(3, Date.valueOf(giocatore.getDataNascita()));
            if(giocatore.getDataRitiro() != null) insGio.setDate(4, Date.valueOf(giocatore.getDataRitiro()));
            else insGio.setNull(4, Types.DATE);
            insGio.setInt(5, giocatore.getId());
            insGio.executeUpdate();

            insGio.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void deleteGiocatore(Giocatore giocatore){
        PreparedStatement delGiocatore = null;

        try{
            delGiocatore = connection.prepareStatement("DELETE FROM Giocatore WHERE id_giocatore = ?");

            delGiocatore.setInt(1, giocatore.getId());
            delGiocatore.executeUpdate();
            delGiocatore.close();
            connection.close();

            JOptionPane.showMessageDialog(null, "Giocatore " + giocatore.getNome() + " " + giocatore.getCognome() + " eliminato con successo!");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
