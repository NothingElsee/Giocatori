package me.nothingelsee.InterfacceDAO;

import me.nothingelsee.Model.Partita;
import me.nothingelsee.Model.Statistiche;

/**
 * The interface Statistica dao.
 */
public interface StatisticaDAO {
    /**
     * Gets stat.
     *
     * @param partita the partita
     */
    void getStat(Partita partita);

    /**
     * Insert statistiche.
     *
     * @param idPartita   the id partita
     * @param statistiche the statistiche
     */
    void insertStatistiche(int idPartita, Statistiche statistiche);

    /**
     * Update statistiche.
     *
     * @param idPartita   the id partita
     * @param statistiche the statistiche
     */
    void updateStatistiche(int idPartita, Statistiche statistiche);

    /**
     * Insert statistiche por.
     *
     * @param idPartita   the id partita
     * @param statistiche the statistiche
     */
    void insertStatistichePor(int idPartita, Statistiche statistiche);

    /**
     * Update statistiche por.
     *
     * @param idPartita   the id partita
     * @param statistiche the statistiche
     */
    void updateStatistichePor(int idPartita, Statistiche statistiche);
}
