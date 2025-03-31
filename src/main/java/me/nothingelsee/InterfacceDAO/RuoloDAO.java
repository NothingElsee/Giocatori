package me.nothingelsee.InterfacceDAO;

import me.nothingelsee.Model.Giocatore;

/**
 * The interface Ruolo dao.
 */
public interface RuoloDAO {
    /**
     * Gets ruoli.
     *
     * @param giocatore the giocatore
     */
    void getRuoli(Giocatore giocatore);
}
