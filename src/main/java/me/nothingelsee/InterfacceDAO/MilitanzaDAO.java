package me.nothingelsee.InterfacceDAO;

import me.nothingelsee.Model.Giocatore;
import me.nothingelsee.Model.Militanza;

public interface MilitanzaDAO {
    void getMilitanze(Giocatore giocatore);
    boolean insertMilitanza(int id_giocatore, Militanza militanza);
    boolean updateMilitanza(Militanza militanza);
    void deleteMilitanza(Militanza militanza);
}
