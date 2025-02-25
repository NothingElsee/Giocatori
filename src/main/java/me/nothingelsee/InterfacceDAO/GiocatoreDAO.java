package me.nothingelsee.InterfacceDAO;

import me.nothingelsee.Model.Giocatore;

import java.util.ArrayList;

public interface GiocatoreDAO {
    void getGiocatoriByNome(String nome, ArrayList<Giocatore> giocatoriAr);
}
