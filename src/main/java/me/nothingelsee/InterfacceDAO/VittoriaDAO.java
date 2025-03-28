package me.nothingelsee.InterfacceDAO;

import me.nothingelsee.Model.Giocatore;

public interface VittoriaDAO {
    public int addVittoriaIndividuale(int idTrofeo, int idGiocatore, String data);
    public int addVittoriaSquadra(int idTrofeo, String nomeSquadra, String data);
}
