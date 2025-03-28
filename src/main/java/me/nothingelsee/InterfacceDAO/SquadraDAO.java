package me.nothingelsee.InterfacceDAO;

import me.nothingelsee.Model.Giocatore;
import me.nothingelsee.Model.Squadra;

import java.util.ArrayList;

public interface SquadraDAO {
    public ArrayList<String> getSquadreNomi();
    void caricaSquadra(Giocatore gicoatore);
}
