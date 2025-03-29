package me.nothingelsee.InterfacceDAO;

import me.nothingelsee.Model.Giocatore;
import me.nothingelsee.Model.Trofeo;

import java.util.ArrayList;

public interface TrofeoDAO {
    public void getTrofei(Giocatore giocatore);
    public void insertTrofeo(Trofeo trofeo);

    void getTrofeiNome(ArrayList<String> trofeiNome);
}
