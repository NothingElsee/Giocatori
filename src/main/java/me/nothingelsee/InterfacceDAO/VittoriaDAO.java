package me.nothingelsee.InterfacceDAO;

import me.nothingelsee.Model.Giocatore;
import me.nothingelsee.Model.Trofeo;

/**
 * The interface Vittoria dao.
 */
public interface VittoriaDAO {
    /**
     * Insert vittoria individuale.
     *
     * @param idGiocatore the id giocatore
     * @param trofeo      the trofeo
     */
    void insertVittoriaIndividuale(int idGiocatore, Trofeo trofeo);

    /**
     * Insert vittoria squadra.
     *
     * @param idGiocatore the id giocatore
     * @param trofeo      the trofeo
     */
    void insertVittoriaSquadra(int idGiocatore, Trofeo trofeo);

    /**
     * Delete vittoria.
     *
     * @param trofeo the trofeo
     */
    void deleteVittoria(Trofeo trofeo);

}
