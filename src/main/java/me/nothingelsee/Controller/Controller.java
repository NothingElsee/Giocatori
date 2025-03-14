package me.nothingelsee.Controller;

import me.nothingelsee.ImplementazioniPostgresDAO.*;
import me.nothingelsee.InterfacceDAO.*;
import me.nothingelsee.Model.Giocatore;
import me.nothingelsee.Model.Militanza;
import me.nothingelsee.Model.Partita;

import java.util.ArrayList;

public class Controller {

    private ArrayList<Giocatore> giocatori = new ArrayList<>();
    private Giocatore giocatoreCercato;
    private Militanza militanzaCercata;

    public Controller() {
    }

    public Giocatore getGiocatoreCercato() {return giocatoreCercato;}

    public ArrayList<Giocatore> getGiocatori() {return giocatori;}

    public Militanza getMilitanzaCercata() {return militanzaCercata;}

    public void getGiocatoriByName(String nome) {
        GiocatoreDAO gioDAO = new GiocatoreImplementazionePostgresDAO();
        gioDAO.getGiocatoriByNome(nome,giocatori);
    }

    public void getAbilita(Giocatore giocatore){
        AbilitaDAO ablDAO = new AbilitaImplementazionePostgresDAO();
        ablDAO.getAbilities(giocatore);
    }

    public void getRuoli(Giocatore giocatore){
        RuoloDAO rloDAO = new RuoloImplementazionePostgresDAO();
        rloDAO.getRuoli(giocatore);
    }

    public void getSkill(Giocatore giocatore){
        SkillDAO sklDAO = new SkillImplementazionePostgresDAO();
        sklDAO.getSkills(giocatore);
    }

    public void getMilitanze(Giocatore giocatore){
        MilitanzaDAO milDAO = new MilitanzaImplementazionePostgresDAO();
        milDAO.getMilitanze(giocatore);
    }

    public void getPartite(Militanza militanza){
        PartitaDAO prtDAO = new PartitaImplementazionePostgresDAO();
        prtDAO.getPartite(militanza);
    }

    public void getStat(Partita partita){
        StatisticaDAO stDAO = new StatisticaImplementazionePostgresDAO();
        stDAO.getStat(partita);
    }

    public ArrayList<Militanza> getMilitanzeDaGiocatore(){ return giocatoreCercato.getMilitanze();}

    public void setGiocatoreCercato(Giocatore giocatoreCercato) { this.giocatoreCercato = giocatoreCercato;}

    public void setMilitanzaCercata(Militanza militanzaCercata) { this.militanzaCercata = militanzaCercata;}

    public boolean isIn(String username, String password) {
        AccountImplementazionePostgresDAO adao = new AccountImplementazionePostgresDAO();
        return adao.isIn(username,password);
    }
}
