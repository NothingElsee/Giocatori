package me.nothingelsee.InterfacceDAO;

import me.nothingelsee.ENUM.RUOLO;
import me.nothingelsee.Model.Giocatore;

import java.util.ArrayList;

public interface PosizioneDAO {
    void insertPosizione(int idGiocatore, ArrayList<RUOLO> ruoli);
    void updatePosizione(int idGiocatore, ArrayList<RUOLO> ruoli);
}
