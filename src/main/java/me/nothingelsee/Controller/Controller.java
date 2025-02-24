package me.nothingelsee.Controller;

import me.nothingelsee.ImplementazioniPostgresDAO.GiocatoreImplementazionePostgresDAO;
import me.nothingelsee.ImplementazioniPostgresDAO.MilitanzaImplementazionePostgresDAO;
import me.nothingelsee.InterfacceDAO.GiocatoreDAO;
import me.nothingelsee.InterfacceDAO.MilitanzaDAO;
import me.nothingelsee.Model.Giocatore;

import java.util.ArrayList;
import java.util.Date;

public class Controller {
    public Controller() {

    }

    public void getGiocatoriByName(String nome, ArrayList<Giocatore> giocatoriAr) {
        GiocatoreDAO gioDAO = new GiocatoreImplementazionePostgresDAO();
        gioDAO.getGiocatoriByNome(nome,giocatoriAr);
    }

    public void getAbilita(Giocatore giocatore){
        GiocatoreDAO gioDAO = new GiocatoreImplementazionePostgresDAO();
        gioDAO.getAbilita(giocatore);
    }

    public void getRuoli(Giocatore giocatore){
        GiocatoreDAO gioDAO = new GiocatoreImplementazionePostgresDAO();
        gioDAO.getRuoli(giocatore);
    }

    public void getSkill(Giocatore giocatore){
        GiocatoreDAO gioDAO = new GiocatoreImplementazionePostgresDAO();
        gioDAO.getSkill(giocatore);
    }

    public void getMilitanze(Giocatore giocatore){
        MilitanzaDAO milDAO = new MilitanzaImplementazionePostgresDAO();
        milDAO.getMilitanze(giocatore);
    }
}
