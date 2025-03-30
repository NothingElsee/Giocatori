package me.nothingelsee.InterfacceDAO;

import me.nothingelsee.Model.Giocatore;
import me.nothingelsee.Model.Trofeo;

public interface VittoriaDAO {
    void insertVittoriaIndividuale(int idGiocatore, Trofeo trofeo);
    void insertVittoriaSquadra(int idGiocatore, Trofeo trofeo);
    void deleteVittoria(Trofeo trofeo);

}
