package me.nothingelsee.InterfacceDAO;

import me.nothingelsee.Model.Giocatore;

import java.util.ArrayList;

public interface GiocatoreDAO {
    ArrayList<Integer> getGiocatoriByFiltri(ArrayList<Giocatore> giocatoriAr, ArrayList<String> filtri);
    void caricaGiocatore(Giocatore giocatore);
    void deleteGiocatore(Giocatore giocatore);
}
