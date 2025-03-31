package me.nothingelsee.InterfacceDAO;

import me.nothingelsee.Model.Giocatore;
import me.nothingelsee.Model.Militanza;
import me.nothingelsee.Model.Partita;

/**
 * The interface Partita dao.
 */
public interface PartitaDAO {
    /**
     * Gets partite.
     *
     * @param militanza the militanza
     */
    void getPartite(Militanza militanza);

    /**
     * Insert partita.
     *
     * @param idMilitanza the id militanza
     * @param p           the p
     */
    void insertPartita(int idMilitanza, Partita p);

    /**
     * Update partita.
     *
     * @param p the p
     */
    void updatePartita(Partita p);

    /**
     * Delete partita.
     *
     * @param p the p
     */
    void deletePartita(Partita p);
}
