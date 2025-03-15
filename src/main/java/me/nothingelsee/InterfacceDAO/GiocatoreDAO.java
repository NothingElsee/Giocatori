package me.nothingelsee.InterfacceDAO;

import me.nothingelsee.Model.Giocatore;

import java.util.ArrayList;

public interface GiocatoreDAO {
    void getGiocatoriByFiltri(ArrayList<Giocatore> giocatoriAr, ArrayList<String> filtri);
}
