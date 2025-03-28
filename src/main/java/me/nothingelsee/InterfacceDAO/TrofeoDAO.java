package me.nothingelsee.InterfacceDAO;

import me.nothingelsee.Model.Giocatore;

import java.util.ArrayList;

public interface TrofeoDAO {
    public void getTrofei(Giocatore giocatore);
    public int addTrofeo(String nome, String tipo);

    void getTrofeiNome(ArrayList<String> trofeiNome);
}
