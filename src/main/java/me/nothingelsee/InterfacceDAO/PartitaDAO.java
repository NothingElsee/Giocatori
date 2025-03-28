package me.nothingelsee.InterfacceDAO;

import me.nothingelsee.Model.Giocatore;
import me.nothingelsee.Model.Militanza;

public interface PartitaDAO {
    void getPartite(Militanza militanza);
    void caricaPartita(Giocatore giocatore);
}
