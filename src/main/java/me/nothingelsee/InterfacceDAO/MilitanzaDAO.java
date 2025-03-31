package me.nothingelsee.InterfacceDAO;

import me.nothingelsee.Model.Giocatore;
import me.nothingelsee.Model.Militanza;

/**
 * The interface Militanza dao.
 */
public interface MilitanzaDAO {
    /**
     * Gets militanze.
     *
     * @param giocatore the giocatore
     */
    void getMilitanze(Giocatore giocatore);

    /**
     * Insert militanza boolean.
     *
     * @param id_giocatore the id giocatore
     * @param militanza    the militanza
     * @return the boolean
     */
    boolean insertMilitanza(int id_giocatore, Militanza militanza);

    /**
     * Update militanza boolean.
     *
     * @param militanza the militanza
     * @return the boolean
     */
    boolean updateMilitanza(Militanza militanza);

    /**
     * Delete militanza.
     *
     * @param militanza the militanza
     */
    void deleteMilitanza(Militanza militanza);
}
