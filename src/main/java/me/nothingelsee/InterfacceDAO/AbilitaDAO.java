package me.nothingelsee.InterfacceDAO;

import me.nothingelsee.Model.Giocatore;

import java.util.ArrayList;

public interface AbilitaDAO {
    void getAbilities(Giocatore giocatore);
    void insertAbilita(int idGiocatore, ArrayList<Integer> abilita);
    void updateAbilita(int idGiocatore, ArrayList<Integer> abilita);
}
