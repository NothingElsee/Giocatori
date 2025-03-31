package me.nothingelsee.InterfacceDAO;

import me.nothingelsee.Model.Giocatore;

import java.util.ArrayList;

/**
 * The interface Abilita dao.
 */
public interface AbilitaDAO {
    /**
     * Gets abilities.
     *
     * @param giocatore the giocatore
     */
    void getAbilities(Giocatore giocatore);

    /**
     * Insert abilita.
     *
     * @param idGiocatore the id giocatore
     * @param abilita     the abilita
     */
    void insertAbilita(int idGiocatore, ArrayList<Integer> abilita);

    /**
     * Update abilita.
     *
     * @param idGiocatore the id giocatore
     * @param abilita     the abilita
     */
    void updateAbilita(int idGiocatore, ArrayList<Integer> abilita);
}
