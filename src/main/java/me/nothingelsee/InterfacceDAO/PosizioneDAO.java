package me.nothingelsee.InterfacceDAO;

import me.nothingelsee.ENUM.RUOLO;
import me.nothingelsee.Model.Giocatore;

import java.util.ArrayList;

/**
 * The interface Posizione dao.
 */
public interface PosizioneDAO {
    /**
     * Insert posizione boolean.
     *
     * @param idGiocatore the id giocatore
     * @param ruoli       the ruoli
     * @return the boolean
     */
    boolean insertPosizione(int idGiocatore, ArrayList<RUOLO> ruoli);

    /**
     * Update posizione boolean.
     *
     * @param idGiocatore the id giocatore
     * @param ruoli       the ruoli
     * @return the boolean
     */
    boolean updatePosizione(int idGiocatore, ArrayList<RUOLO> ruoli);
}
