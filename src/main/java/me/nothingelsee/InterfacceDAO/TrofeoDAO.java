package me.nothingelsee.InterfacceDAO;

import me.nothingelsee.Model.Giocatore;
import me.nothingelsee.Model.Trofeo;

import java.util.ArrayList;

public interface TrofeoDAO {
    public void getTrofei(Giocatore giocatore);
    public void caricaTrofeo(Giocatore giocatore);

    void getTrofeiNome(ArrayList<String> trofeiNome);

    void deleteTrofeo(Trofeo trofeo);
}
