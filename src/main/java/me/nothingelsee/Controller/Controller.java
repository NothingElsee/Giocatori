package me.nothingelsee.Controller;

import me.nothingelsee.ImplementazioniPostgresDAO.*;
import me.nothingelsee.InterfacceDAO.*;
import me.nothingelsee.Model.Giocatore;
import me.nothingelsee.Model.Militanza;
import me.nothingelsee.Model.Partita;
import me.nothingelsee.Model.Trofeo;

import java.util.ArrayList;

public class Controller {

    private ArrayList<Giocatore> giocatori = new ArrayList<>();
    private Giocatore giocatoreCercato;
    private Militanza militanzaCercata;
    private ArrayList<String> filtriRicerca = new ArrayList<>();
    private Trofeo trofeoCercato = null;

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

    public Trofeo getTrofeoCercato() {
        return trofeoCercato;
    }

    public ArrayList<String> getFiltriRicerca() {return filtriRicerca;}

    public void addFiltri(String filtro) { filtriRicerca.add(filtro);}

    public void setGiocatoreCercato(Giocatore giocatoreCercato) { this.giocatoreCercato = giocatoreCercato;}

    public void setMilitanzaCercata(Militanza militanzaCercata) { this.militanzaCercata = militanzaCercata;}

    public boolean isIn(String username, String password) {
        AccountDAO accountDAO = new AccountImplementazionePostgresDAO();
        return accountDAO.isIn(username, password);
    }

    public void getTrofeiNome(ArrayList<String> trofeiNome) {
        TrofeoDAO trDAO = new TrofeoImplementazionePostgresDAO();
        trDAO.getTrofeiNome(trofeiNome);
    }

    public void getNazionalita(ArrayList<String> nazionalita) {
        NazionalitaDAO naDAO = new NazionalitaImplementazionePostgresDAO();
        naDAO.getNazionalita(nazionalita);
    }

    private void caricaGiocatore(Giocatore giocatore) {

        GiocatoreDAO giocatoreDAO = new GiocatoreImplementazionePostgresDAO();
        giocatoreDAO.caricaGiocatore(giocatore);
    }
    private void caricaRuoli(Giocatore giocatore) {
        PosizioneDAO psDAO = new PosizioneImplementazionePostgresDAO();
        psDAO.caricaPosizione(giocatoreCercato);
    }
    private void caricaSkill(Giocatore giocatore) {
        SkillDAO sDAO = new SkillImplementazionePostgresDAO();
        sDAO.caricaSkill(giocatore);
    }
    private void caricaAbilta(Giocatore giocatore) {
        AbilitaDAO aDAO = new AbilitaImplementazionePostgresDAO();
        aDAO.caricaAbilita(giocatore);
    }
    private void caricaNazionalita(Giocatore giocatore) {
        NazionalitaDAO naDAO = new NazionalitaImplementazionePostgresDAO();
        naDAO.caricaNazionalita(giocatore);
    }
    private void caricaSquadra(Giocatore giocatore) {
        SquadraDAO sDAO = new SquadraImplementazionePostgresDAO();
        sDAO.caricaSquadra(giocatore);
    }
    private void caricaMilitanza(Giocatore giocatore) {
        MilitanzaDAO mDAO = new MilitanzaImplementazionePostgresDAO();
        mDAO.caricaMilitanta(giocatore);
    }
    private void caricaTrofeo(Giocatore giocatore) {
        TrofeoDAO tDAO = new TrofeoImplementazionePostgresDAO();
        tDAO.caricaTrofeo(giocatore);
        VittoriaDAO vitDAO = new VittoriaImplementazionePostgresDAO();
        vitDAO.caricaVittoria(giocatore);
    }
    private void caricaPartita(Giocatore giocatore) {
        PartitaDAO pDAO = new PartitaImplementazionePostgresDAO();
        pDAO.caricaPartita(giocatore);
    }

    public void deleteGiocatore(Giocatore giocatore) {
        GiocatoreDAO gioDAO = new GiocatoreImplementazionePostgresDAO();
        gioDAO.deleteGiocatore(giocatore);
    }

    public void deleteTrofeo(Trofeo trofeo) {
        TrofeoDAO trDAO = new TrofeoImplementazionePostgresDAO();
        trDAO.deleteTrofeo(trofeo);
    }

    public void setTrofeoCercato(Trofeo trofeo) {
        trofeoCercato = trofeo;
    }

    public void caricaTutto(Giocatore giocatoreCercato) {
        caricaGiocatore(giocatoreCercato);
        caricaRuoli(giocatoreCercato);
        caricaSkill(giocatoreCercato);
        caricaAbilta(giocatoreCercato);
        caricaNazionalita(giocatoreCercato);
        caricaSquadra(giocatoreCercato);
        caricaMilitanza(giocatoreCercato);
        caricaTrofeo(giocatoreCercato);
        caricaPartita(giocatoreCercato);
    }
}
