package me.nothingelsee.InterfacceDAO;

import me.nothingelsee.Model.Giocatore;

import java.util.ArrayList;

/**
 * The interface Nazionalita dao.
 */
public interface NazionalitaDAO {
    /**
     * Gets nazionalita.
     *
     * @param nazionalita the nazionalita
     */
    public void getNazionalita(ArrayList<String> nazionalita);

    /**
     * Insert nazionalita.
     *
     * @param idGiocatore the id giocatore
     * @param nome        the nome
     */
    public void insertNazionalita(int idGiocatore, String nome);

    /**
     * Update nazionalita.
     *
     * @param idGiocatore the id giocatore
     * @param nome        the nome
     */
    void updateNazionalita(int idGiocatore, String nome);
}
