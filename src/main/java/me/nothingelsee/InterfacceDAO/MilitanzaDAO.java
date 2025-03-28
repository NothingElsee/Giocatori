package me.nothingelsee.InterfacceDAO;

import me.nothingelsee.Model.Giocatore;
import me.nothingelsee.Model.Militanza;

public interface MilitanzaDAO {
    void getMilitanze(Giocatore giocatore);
    void caricaMilitanta(Giocatore giocatore);
}
