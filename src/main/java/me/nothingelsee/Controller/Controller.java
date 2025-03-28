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
    private ArrayList<String> filtriRicerca = new ArrayList<>();

    public Controller() {
    }

    public Giocatore getGiocatoreCercato() {return giocatoreCercato;}

    public ArrayList<Giocatore> getGiocatori() {return giocatori;}

    public Militanza getMilitanzaCercata() {return militanzaCercata;}

    public ArrayList<Integer> getGiocatoriByFiltri() {
        GiocatoreDAO gioDAO = new GiocatoreImplementazionePostgresDAO();
        return gioDAO.getGiocatoriByFiltri(giocatori, filtriRicerca);
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

    public ArrayList<String> getSquadreNomi() {
        SquadraDAO sqDAO = new SquadraImplementazionePostgresDAO();
        return sqDAO.getSquadreNomi();
    }

    public void getTrofei(Giocatore giocatore) {

        TrofeoDAO tDAO = new TrofeoImplementazionePostgresDAO();
        tDAO.getTrofei(giocatore);
    }

    public ArrayList<String> getFiltriRicerca() {return filtriRicerca;}

    public void addFiltri(String filtro) { filtriRicerca.add(filtro);}

    public void setGiocatoreCercato(Giocatore giocatoreCercato) { this.giocatoreCercato = giocatoreCercato;}

    public void setMilitanzaCercata(Militanza militanzaCercata) { this.militanzaCercata = militanzaCercata;}

    public boolean isIn(String username, String password) {
        AccountDAO accountDAO = new AccountImplementazionePostgresDAO();
        return accountDAO.isIn(username, password);
    }

    public int addTrofeo(String nomeTrofeo, String tipoTrofeo) {
        TrofeoDAO trDAO = new TrofeoImplementazionePostgresDAO();
        return trDAO.addTrofeo(nomeTrofeo, tipoTrofeo);
    }

    public int addVittoriaIndividuale(int idTrofeo, int idGiocatore, String data) {
        VittoriaDAO vitDAO = new VittoriaImplementazionePostgresDAO();
        return vitDAO.addVittoriaIndividuale(idTrofeo, idGiocatore, data);
    }

    public int addVittoriaSquadra(int idTrofeo, String nomeSquadra, String data) {
        VittoriaDAO vitDAO = new VittoriaImplementazionePostgresDAO();
        return vitDAO.addVittoriaSquadra(idTrofeo, nomeSquadra, data);
    }

    public void getTrofeiNome(ArrayList<String> trofeiNome) {
        TrofeoDAO trDAO = new TrofeoImplementazionePostgresDAO();
        trDAO.getTrofeiNome(trofeiNome);
    }

    public void getNazionalita(ArrayList<String> nazionalita) {
        NazionalitaDAO naDAO = new NazionalitaImplementazionePostgresDAO();
        naDAO.getNazionalita(nazionalita);
    }

    public void caricaGiocatore(Giocatore giocatore) {

        GiocatoreDAO giocatoreDAO = new GiocatoreImplementazionePostgresDAO();
        giocatoreDAO.caricaGiocatore(giocatore);
    }
}
