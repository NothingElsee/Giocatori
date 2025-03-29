package me.nothingelsee.InterfacceDAO;

import me.nothingelsee.Model.Giocatore;
import me.nothingelsee.Model.Militanza;
import me.nothingelsee.Model.Partita;

public interface PartitaDAO {
    void getPartite(Militanza militanza);
    void insertPartita(int idMilitanza, Partita p);
    void updatePartita(Partita p);
    void deletePartita(Partita p);
}
