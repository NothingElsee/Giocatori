package me.nothingelsee.InterfacceDAO;

import me.nothingelsee.ENUM.RUOLO;
import me.nothingelsee.Model.Giocatore;

import java.util.ArrayList;

public interface PosizioneDAO {
    boolean insertPosizione(int idGiocatore, ArrayList<RUOLO> ruoli);
    boolean updatePosizione(int idGiocatore, ArrayList<RUOLO> ruoli);
}
