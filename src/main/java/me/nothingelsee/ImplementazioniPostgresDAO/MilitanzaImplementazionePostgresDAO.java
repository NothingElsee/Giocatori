package me.nothingelsee.ImplementazioniPostgresDAO;

import me.nothingelsee.Database.ConnessioneDatabase;
import me.nothingelsee.InterfacceDAO.MilitanzaDAO;
import me.nothingelsee.Model.Giocatore;
import me.nothingelsee.Model.Militanza;
import me.nothingelsee.Model.Squadra;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

/**
 * The type Militanza implementazione postgres dao.
 */
public class MilitanzaImplementazionePostgresDAO implements MilitanzaDAO {

    private Connection connection;

    /**
     * Instantiates a new Militanza implementazione postgres dao.
     */
    public MilitanzaImplementazionePostgresDAO() {
        try {
            connection = ConnessioneDatabase.getInstance().getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getMilitanze(Giocatore giocatore) {
        PreparedStatement leggiMilitanze;

        try {

            leggiMilitanze = connection.prepareStatement(
                    "SELECT M.id_militanza, M.datainizio, M.datafine, S.nome, S.nomenazione FROM GIOCATORE AS G JOIN Militanza AS M ON G.id_giocatore = M.id_giocatore" +
                            " JOIN Squadra AS S ON M.nomesquadra = S.nome WHERE G.id_giocatore = " + giocatore.getId() + " ORDER BY M.datafine DESC"
            );

            ResultSet rs = leggiMilitanze.executeQuery();
            giocatore.clearMilitanze();
            while (rs.next()) {
                giocatore.addMilitanza(new Militanza(rs.getInt("id_militanza"), rs.getString("datainizio"), rs.getString("datafine"), new Squadra(rs.getString("nome"), rs.getString("nomenazione"))));
            }
            rs.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean insertMilitanza(int id_giocatore, Militanza militanza) {
        PreparedStatement caricaMilitanza = null;
        PreparedStatement leggiSquadra = null;

        try {
            leggiSquadra = connection.prepareStatement("Select * FROM Squadra WHERE nome = ?");
            leggiSquadra.setString(1, militanza.getSquadra().getNome());
            ResultSet rs2 = leggiSquadra.executeQuery();
            if (!rs2.next()) {
                PreparedStatement caricaSquadra = connection.prepareStatement("INSERT INTO Squadra(nome) VALUES (?)");
                caricaSquadra.setString(1, militanza.getSquadra().getNome());
                caricaSquadra.executeUpdate();
                caricaSquadra.close();
                rs2.close();
            }
            leggiSquadra.close();

            caricaMilitanza = connection.prepareStatement("INSERT INTO Militanza(id_giocatore, nomesquadra, datainizio, datafine) VALUES (?,?,?,?)");
            caricaMilitanza.setInt(1, id_giocatore);
            caricaMilitanza.setString(2, militanza.getSquadra().getNome());
            caricaMilitanza.setDate(3, Date.valueOf(militanza.getDataInizio()));
            if (militanza.getDataFine() == null) caricaMilitanza.setNull(4, Types.DATE);
            else caricaMilitanza.setDate(4, Date.valueOf(militanza.getDataFine()));
            caricaMilitanza.executeUpdate();

            PreparedStatement mil = connection.prepareStatement("SELECT currval(pg_get_serial_sequence('militanza','id_militanza'))");
            ResultSet rs = mil.executeQuery();
            rs.next();
            militanza.setId(rs.getInt(1));
            rs.close();
            caricaMilitanza.close();
            connection.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Errore durante il caricamento delle militanze", "Errore", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean updateMilitanza(Militanza militanza) {
        PreparedStatement caricaMilitanza = null;

        try {
            caricaMilitanza = connection.prepareStatement("UPDATE Militanza SET nomesquadra = ?, datainizio = ?, datafine = ? WHERE id_militanza = ?");
            caricaMilitanza.setString(1, militanza.getSquadra().getNome());
            caricaMilitanza.setDate(2, Date.valueOf(militanza.getDataInizio()));
            if (militanza.getDataFine() == null) caricaMilitanza.setNull(3, Types.DATE);
            else caricaMilitanza.setDate(3, Date.valueOf(militanza.getDataFine()));
            caricaMilitanza.setInt(4, militanza.getId());
            caricaMilitanza.executeUpdate();

            caricaMilitanza.close();
            connection.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Errore durante la modifica della militanza", "Errore", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public void deleteMilitanza(Militanza militanza) {
        PreparedStatement caricaMilitanza = null;

        try {
            caricaMilitanza = connection.prepareStatement("DELETE FROM militanza WHERE id_militanza = ?");
            caricaMilitanza.setInt(1, militanza.getId());
            caricaMilitanza.executeUpdate();

            caricaMilitanza.close();
            connection.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Errore durante l'eliminazione della militanza", "Errore", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}
