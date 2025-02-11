package me.nothingelsee.Controller;

import me.nothingelsee.ImplementazioniPostgresDAO.GiocatoreImplementazionePostgresDAO;
import me.nothingelsee.InterfacceDAO.GiocatoreDAO;

import java.util.ArrayList;
import java.util.Date;

public class Controller {
    public Controller() {

    }

public void getGiocatoriByName(String nome, ArrayList<Integer> codiciGiocatori, ArrayList<String> cognomiGiocatore, ArrayList<Date> dateGiocatori){
    GiocatoreDAO gioDAO = new GiocatoreImplementazionePostgresDAO();
    gioDAO.getGiocatoriByNome(nome,codiciGiocatori, cognomiGiocatore, dateGiocatori);
}
}
