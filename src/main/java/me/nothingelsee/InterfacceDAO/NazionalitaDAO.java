package me.nothingelsee.InterfacceDAO;

import me.nothingelsee.Model.Giocatore;

import java.util.ArrayList;

public interface NazionalitaDAO {
    public void getNazionalita(ArrayList<String> nazionalita);
    public void insertNazionalita(int idGiocatore, String nome);
    void updateNazionalita(int idGiocatore, String nome);
}
