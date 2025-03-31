package me.nothingelsee.InterfacceDAO;

import me.nothingelsee.Model.Giocatore;

import java.util.ArrayList;

/**
 * The interface Giocatore dao.
 */
public interface GiocatoreDAO {
    /**
     * Gets giocatori by filtri.
     *
     * @param giocatoriAr the giocatori ar
     * @param filtri      the filtri
     * @return the giocatori by filtri
     */
    ArrayList<Integer> getGiocatoriByFiltri(ArrayList<Giocatore> giocatoriAr, ArrayList<String> filtri);

    /**
     * Insert giocatore.
     *
     * @param giocatore the giocatore
     */
    void insertGiocatore(Giocatore giocatore);

    /**
     * Update giocatore.
     *
     * @param giocatore the giocatore
     */
    void updateGiocatore(Giocatore giocatore);

    /**
     * Delete giocatore.
     *
     * @param giocatore the giocatore
     */
    void deleteGiocatore(Giocatore giocatore);
}
