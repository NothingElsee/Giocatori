package me.nothingelsee.InterfacceDAO;

import me.nothingelsee.Model.Partita;
import me.nothingelsee.Model.Statistiche;

public interface StatisticaDAO {
    void getStat(Partita partita);
    void insertStatistiche(Statistiche statistiche);
    void updateStatistiche(int idPartita, Statistiche statistiche);
    void insertStatistichePor(Statistiche statistiche);
    void updateStatistichePor(int idPartita, Statistiche statistiche);
}
