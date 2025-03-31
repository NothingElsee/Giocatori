package me.nothingelsee.InterfacceDAO;

import me.nothingelsee.Model.Giocatore;
import me.nothingelsee.Model.Squadra;

import java.util.ArrayList;

/**
 * The interface Squadra dao.
 */
public interface SquadraDAO {
    /**
     * Gets squadre nomi.
     *
     * @return the squadre nomi
     */
    public ArrayList<String> getSquadreNomi();

    /**
     * Insert squadra.
     *
     * @param squadra the squadra
     */
    void insertSquadra(Squadra squadra);

    /**
     * Gets squadre giocatore.
     *
     * @param id the id
     * @return the squadre giocatore
     */
    ArrayList<String> getSquadreGiocatore(int id);
}
