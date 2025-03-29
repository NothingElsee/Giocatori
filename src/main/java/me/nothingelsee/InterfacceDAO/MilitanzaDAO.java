package me.nothingelsee.InterfacceDAO;

import me.nothingelsee.Model.Giocatore;
import me.nothingelsee.Model.Militanza;

public interface MilitanzaDAO {
    void getMilitanze(Giocatore giocatore);
    void insertMilitanza(int id_giocatore, Militanza militanza);
    void updateMilitanza(Militanza militanza);
    void deleteMilitanza(Militanza militanza);
}
