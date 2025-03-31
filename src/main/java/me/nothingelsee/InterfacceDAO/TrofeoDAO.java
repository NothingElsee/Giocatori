package me.nothingelsee.InterfacceDAO;

import me.nothingelsee.Model.Giocatore;
import me.nothingelsee.Model.Trofeo;

import java.util.ArrayList;

/**
 * The interface Trofeo dao.
 */
public interface TrofeoDAO {
    /**
     * Gets trofei.
     *
     * @param giocatore the giocatore
     */
    public void getTrofei(Giocatore giocatore);

    /**
     * Insert trofeo.
     *
     * @param trofeo the trofeo
     */
    public void insertTrofeo(Trofeo trofeo);

    /**
     * Update trofeo.
     *
     * @param trofeo the trofeo
     */
    void updateTrofeo(Trofeo trofeo);

    /**
     * Gets trofei nome.
     *
     * @param trofeiNome the trofei nome
     */
    void getTrofeiNome(ArrayList<String> trofeiNome);
}
