package me.nothingelsee.ImplementazioniPostgresDAO;

import me.nothingelsee.Database.ConnessioneDatabase;
import me.nothingelsee.InterfacceDAO.GiocatoreDAO;
import me.nothingelsee.Model.Giocatore;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

/**
 * The type Giocatore implementazione postgres dao.
 */
public class GiocatoreImplementazionePostgresDAO implements GiocatoreDAO {

    private Connection connection;

    /**
     * Instantiates a new Giocatore implementazione postgres dao.
     */
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
                select = "SELECT " +
                        "  G.id_giocatore," +
                        "  G.nome        AS nomeG," +
                        "  G.cognome," +
                        "  G.dataNascita," +
                        "  N.nome        AS nomeN," +
                        "  G.dataRitiro," +
                        "  G.piede," +
                        "  MAX(COALESCE(Stats.total_goals, 0)) AS stat" +
                        " FROM Giocatore AS G" +
                        "" +
                        " LEFT JOIN (" +
                        "  SELECT " +
                        "    M.id_giocatore," +
                        "    SUM(S.Goal) AS total_goals" +
                        "  FROM Militanza AS M" +
                        "    LEFT JOIN match      AS Ma ON Ma.id_militanza = M.id_militanza" +
                        "    LEFT JOIN Statistica AS S  ON S.id_partita    = Ma.id_partita" +
                        "  GROUP BY M.id_giocatore" +
                        ") AS Stats" +
                        "  ON Stats.id_giocatore = G.id_giocatore" +
                        "" +
                        " LEFT JOIN Appartiene   AS A ON A.id_giocatore = G.id_giocatore" +
                        " LEFT JOIN nazionalità  AS N ON N.nome          = A.nomenazionalità" +
                        "" +
                        " JOIN Posizione AS P ON P.id_giocatore = G.id_giocatore" +
                        " JOIN Ruolo     AS R ON R.id_ruolo     = P.id_ruolo" +
                         " WHERE " + filtri.get(0) + " " + filtri.get(1) + " " + filtri.get(2) + " " + filtri.get(3) +
                        " GROUP BY " +
                        "  G.id_giocatore," +
                        "  G.nome," +
                        "  G.cognome," +
                        "  G.dataNascita," +
                        "  N.nome," +
                        "  G.dataRitiro," +
                        "  G.piede" +
                        " ORDER BY stat " + filtri.get(5);
                break;
            case "Assist":
                select = "SELECT " +
                        "  G.id_giocatore," +
                        "  G.nome        AS nomeG," +
                        "  G.cognome," +
                        "  G.dataNascita," +
                        "  N.nome        AS nomeN," +
                        "  G.dataRitiro," +
                        "  G.piede," +
                        "  MAX(COALESCE(Stats.total_assist, 0)) AS stat" +
                        " FROM Giocatore AS G" +
                        "" +
                        " LEFT JOIN (" +
                        "  SELECT " +
                        "    M.id_giocatore," +
                        "    SUM(S.assist) AS total_assist" +
                        "  FROM Militanza AS M" +
                        "    LEFT JOIN match      AS Ma ON Ma.id_militanza = M.id_militanza" +
                        "    LEFT JOIN Statistica AS S  ON S.id_partita    = Ma.id_partita" +
                        "  GROUP BY M.id_giocatore" +
                        ") AS Stats" +
                        "  ON Stats.id_giocatore = G.id_giocatore" +
                        "" +
                        " LEFT JOIN Appartiene   AS A ON A.id_giocatore = G.id_giocatore" +
                        " LEFT JOIN nazionalità  AS N ON N.nome          = A.nomenazionalità" +
                        "" +
                        " JOIN Posizione AS P ON P.id_giocatore = G.id_giocatore" +
                        " JOIN Ruolo     AS R ON R.id_ruolo     = P.id_ruolo" +
                        " WHERE " + filtri.get(0) + " " + filtri.get(1) + " " + filtri.get(2) + " " + filtri.get(3) +
                        " GROUP BY " +
                        "  G.id_giocatore," +
                        "  G.nome," +
                        "  G.cognome," +
                        "  G.dataNascita," +
                        "  N.nome," +
                        "  G.dataRitiro," +
                        "  G.piede" +
                        " ORDER BY stat " + filtri.get(5);;
                break;
            case "Cartellini Rossi":
                select = "SELECT " +
                        "  G.id_giocatore," +
                        "  G.nome        AS nomeG," +
                        "  G.cognome," +
                        "  G.dataNascita," +
                        "  N.nome        AS nomeN," +
                        "  G.dataRitiro," +
                        "  G.piede," +
                        "  MAX(COALESCE(Stats.total_cr, 0)) AS stat" +
                        " FROM Giocatore AS G" +
                        "" +
                        " LEFT JOIN (" +
                        "  SELECT " +
                        "    M.id_giocatore," +
                        "    SUM(S.cartellinirossi) AS total_cr" +
                        "  FROM Militanza AS M" +
                        "    LEFT JOIN match      AS Ma ON Ma.id_militanza = M.id_militanza" +
                        "    LEFT JOIN Statistica AS S  ON S.id_partita    = Ma.id_partita" +
                        "  GROUP BY M.id_giocatore" +
                        ") AS Stats" +
                        "  ON Stats.id_giocatore = G.id_giocatore" +
                        "" +
                        " LEFT JOIN Appartiene   AS A ON A.id_giocatore = G.id_giocatore" +
                        " LEFT JOIN nazionalità  AS N ON N.nome          = A.nomenazionalità" +
                        "" +
                        " JOIN Posizione AS P ON P.id_giocatore = G.id_giocatore" +
                        " JOIN Ruolo     AS R ON R.id_ruolo     = P.id_ruolo" +
                        " WHERE " + filtri.get(0) + " " + filtri.get(1) + " " + filtri.get(2) + " " + filtri.get(3) +
                        " GROUP BY " +
                        "  G.id_giocatore," +
                        "  G.nome," +
                        "  G.cognome," +
                        "  G.dataNascita," +
                        "  N.nome," +
                        "  G.dataRitiro," +
                        "  G.piede" +
                        " ORDER BY stat " + filtri.get(5);;
                break;
            case "Cartellioni Gialli":
                select = "SELECT " +
                        "  G.id_giocatore," +
                        "  G.nome        AS nomeG," +
                        "  G.cognome," +
                        "  G.dataNascita," +
                        "  N.nome        AS nomeN," +
                        "  G.dataRitiro," +
                        "  G.piede," +
                        "  MAX(COALESCE(Stats.total_cg, 0)) AS stat" +
                        " FROM Giocatore AS G" +
                        "" +
                        " LEFT JOIN (" +
                        "  SELECT " +
                        "    M.id_giocatore," +
                        "    SUM(S.cartellinigialli) AS total_cg" +
                        "  FROM Militanza AS M" +
                        "    LEFT JOIN match      AS Ma ON Ma.id_militanza = M.id_militanza" +
                        "    LEFT JOIN Statistica AS S  ON S.id_partita    = Ma.id_partita" +
                        "  GROUP BY M.id_giocatore" +
                        ") AS Stats" +
                        "  ON Stats.id_giocatore = G.id_giocatore" +
                        "" +
                        " LEFT JOIN Appartiene   AS A ON A.id_giocatore = G.id_giocatore" +
                        " LEFT JOIN nazionalità  AS N ON N.nome          = A.nomenazionalità" +
                        "" +
                        " JOIN Posizione AS P ON P.id_giocatore = G.id_giocatore" +
                        " JOIN Ruolo     AS R ON R.id_ruolo     = P.id_ruolo" +
                        " WHERE " + filtri.get(0) + " " + filtri.get(1) + " " + filtri.get(2) + " " + filtri.get(3) +
                        " GROUP BY " +
                        "  G.id_giocatore," +
                        "  G.nome," +
                        "  G.cognome," +
                        "  G.dataNascita," +
                        "  N.nome," +
                        "  G.dataRitiro," +
                        "  G.piede" +
                        " ORDER BY stat " + filtri.get(5);
                break;
            case "Numero Parate":
                select = "SELECT " +
                        "  G.id_giocatore," +
                        "  G.nome        AS nomeG," +
                        "  G.cognome," +
                        "  G.dataNascita," +
                        "  N.nome        AS nomeN," +
                        "  G.dataRitiro," +
                        "  G.piede," +
                        "  MAX(COALESCE(Stats.total_np, 0)) AS stat" +
                        " FROM Giocatore AS G" +
                        "" +
                        " LEFT JOIN (" +
                        "  SELECT " +
                        "    M.id_giocatore," +
                        "    SUM(S.numParate) AS total_np" +
                        "  FROM Militanza AS M" +
                        "    LEFT JOIN match      AS Ma ON Ma.id_militanza = M.id_militanza" +
                        "    LEFT JOIN StatPortiere AS S  ON S.id_partita    = Ma.id_partita" +
                        "  GROUP BY M.id_giocatore" +
                        ") AS Stats" +
                        "  ON Stats.id_giocatore = G.id_giocatore" +
                        "" +
                        " LEFT JOIN Appartiene   AS A ON A.id_giocatore = G.id_giocatore" +
                        " LEFT JOIN nazionalità  AS N ON N.nome          = A.nomenazionalità" +
                        "" +
                        " JOIN Posizione AS P ON P.id_giocatore = G.id_giocatore" +
                        " JOIN Ruolo     AS R ON R.id_ruolo     = P.id_ruolo" +
                        " WHERE " + filtri.get(0) + " " + filtri.get(1) + " " + filtri.get(2) + " " + filtri.get(3) +
                        " GROUP BY " +
                        "  G.id_giocatore," +
                        "  G.nome," +
                        "  G.cognome," +
                        "  G.dataNascita," +
                        "  N.nome," +
                        "  G.dataRitiro," +
                        "  G.piede" +
                        " ORDER BY stat " + filtri.get(5);
                break;
            case "Numero Goal Subiti":
                select = "SELECT" +
                        "  G.id_giocatore," +
                        "  G.nome        AS nomeG," +
                        "  G.cognome," +
                        "  G.dataNascita," +
                        "  N.nome AS nomeN," +
                        "  G.dataRitiro," +
                        "  G.piede," +
                        "  MAX(COALESCE(Stats.total_gs, 0)) AS stat" +
                        " FROM Giocatore AS G" +
                        "" +
                        " LEFT JOIN (" +
                        "  SELECT " +
                        "    M.id_giocatore," +
                        "    SUM(S.goalsubiti) AS total_gs" +
                        "  FROM Militanza AS M" +
                        "    LEFT JOIN match      AS Ma ON Ma.id_militanza = M.id_militanza" +
                        "    LEFT JOIN StatPortiere AS S  ON S.id_partita    = Ma.id_partita" +
                        "  GROUP BY M.id_giocatore" +
                        ") AS Stats" +
                        "  ON Stats.id_giocatore = G.id_giocatore" +
                        "" +
                        " LEFT JOIN Appartiene   AS A ON A.id_giocatore = G.id_giocatore" +
                        " LEFT JOIN nazionalità  AS N ON N.nome          = A.nomenazionalità" +
                        "" +
                        " JOIN Posizione AS P ON P.id_giocatore = G.id_giocatore" +
                        " JOIN Ruolo     AS R ON R.id_ruolo     = P.id_ruolo" +
                        " WHERE " + filtri.get(0) + " " + filtri.get(1) + " " + filtri.get(2) + " " + filtri.get(3) +
                        " GROUP BY " +
                        "  G.id_giocatore," +
                        "  G.nome," +
                        "  G.cognome," +
                        "  G.dataNascita," +
                        "  N.nome," +
                        "  G.dataRitiro," +
                        "  G.piede" +
                        " ORDER BY stat " + filtri.get(5);
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
                    giocatoriAr.add(new Giocatore(rs.getInt("id_Giocatore"), rs.getString("nomeG"), rs.getString("cognome"), rs.getString("dataNascita"), rs.getString("nomeN"), rs.getString("dataRitiro"), rs.getString("piede")));
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
    public void insertGiocatore(Giocatore giocatore) {
        try {
            PreparedStatement insGio = connection.prepareStatement("INSERT INTO Giocatore(nome, cognome, datanascita, dataritiro, piede) VALUES (?,?,?,?," + "\'" + giocatore.getPiede().name() + "\')");
            insGio.setString(1, giocatore.getNome());
            insGio.setString(2, giocatore.getCognome());
            insGio.setDate(3, Date.valueOf(giocatore.getDataNascita()));
            if (giocatore.getDataRitiro() != null) insGio.setDate(4, Date.valueOf(giocatore.getDataRitiro()));
            else insGio.setDate(4, null);

            insGio.executeUpdate();

            insGio.close();
            insGio = connection.prepareStatement("SELECT currval(pg_get_serial_sequence('giocatore','id_giocatore'))");
            ResultSet rs = insGio.executeQuery();
            rs.next();
            giocatore.setId(rs.getInt(1));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateGiocatore(Giocatore giocatore) {
        try {
            PreparedStatement insGio = connection.prepareStatement("UPDATE Giocatore SET nome = ?, cognome = ?, datanascita = ?, dataritiro = ?, piede = \'" + giocatore.getPiede().toString() + "\' WHERE id_giocatore = ?");


            insGio.setString(1, giocatore.getNome());
            insGio.setString(2, giocatore.getCognome());
            insGio.setDate(3, Date.valueOf(giocatore.getDataNascita()));
            if (giocatore.getDataRitiro() != null) insGio.setDate(4, Date.valueOf(giocatore.getDataRitiro()));
            else insGio.setNull(4, Types.DATE);
            insGio.setInt(5, giocatore.getId());
            insGio.executeUpdate();

            insGio.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteGiocatore(Giocatore giocatore) {
        PreparedStatement delGiocatore = null;

        try {
            delGiocatore = connection.prepareStatement("DELETE FROM Giocatore WHERE id_giocatore = ?");

            delGiocatore.setInt(1, giocatore.getId());
            delGiocatore.executeUpdate();
            delGiocatore.close();
            connection.close();

            JOptionPane.showMessageDialog(null, "Giocatore " + giocatore.getNome() + " " + giocatore.getCognome() + " eliminato con successo!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
