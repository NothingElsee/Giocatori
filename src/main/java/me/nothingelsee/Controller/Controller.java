package me.nothingelsee.Controller;

import me.nothingelsee.ENUM.RUOLO;
import me.nothingelsee.ImplementazioniPostgresDAO.*;
import me.nothingelsee.InterfacceDAO.*;
import me.nothingelsee.Model.*;

import javax.naming.PartialResultException;
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
    public void setTrofeoCercato(Trofeo trofeo) {
        trofeoCercato = trofeo;
    }

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

    public void caricaGiocatore(Giocatore giocatore) {

        GiocatoreDAO giocatoreDAO = new GiocatoreImplementazionePostgresDAO();
        giocatoreDAO.insertGiocatore(giocatore);
    }
    public void updateGiocatore(Giocatore giocatore) {
        GiocatoreDAO giDAO = new GiocatoreImplementazionePostgresDAO();
        giDAO.updateGiocatore(giocatore);
    }
    public void deleteGiocatore(Giocatore giocatore) {
        GiocatoreDAO gioDAO = new GiocatoreImplementazionePostgresDAO();
        gioDAO.deleteGiocatore(giocatore);
    }

    public void caricaRuoli(int id, ArrayList<RUOLO> ruoli) {
        PosizioneDAO psDAO = new PosizioneImplementazionePostgresDAO();
        psDAO.insertPosizione(id, ruoli);
    }
    public void updateRuoli(int id, ArrayList<RUOLO> ruoli) {
        PosizioneDAO psDAO = new PosizioneImplementazionePostgresDAO();
        psDAO.updatePosizione(id, ruoli);
    }

    public void caricaSkill(ArrayList<Integer> skill) {
        SkillDAO sDAO = new SkillImplementazionePostgresDAO();
        sDAO.insertSkill(skill);
    }
    public void updateSkill(int id, ArrayList<Integer> skill) {
        SkillDAO sDAO = new SkillImplementazionePostgresDAO();
        sDAO.updateSkill(id, skill);
    }

    public void caricaAbilita(ArrayList<Integer> abilita) {
        AbilitaDAO aDAO = new AbilitaImplementazionePostgresDAO();
        aDAO.insertAbilita(abilita);
    }
    public void updateAbilita(int id, ArrayList<Integer> skill) {
        AbilitaDAO aDAO = new AbilitaImplementazionePostgresDAO();
        aDAO.updateAbilita(id, skill);
    }

    public void caricaNazionalita(int id, String nazionalita) {
        NazionalitaDAO naDAO = new NazionalitaImplementazionePostgresDAO();
        naDAO.insertNazionalita(id, nazionalita);
    }
    public void updateNazionalita(int id, String nazionalita) {
        NazionalitaDAO naDAO = new NazionalitaImplementazionePostgresDAO();
        naDAO.updateNazionalita(id, nazionalita);
    }

    public void caricaSquadra(Squadra squadra) {
        SquadraDAO sDAO = new SquadraImplementazionePostgresDAO();
        sDAO.insertSquadra(squadra);
    }

    public void caricaMilitanza(int id, Militanza militanza) {
        MilitanzaDAO mDAO = new MilitanzaImplementazionePostgresDAO();
        mDAO.insertMilitanza( id, militanza);
    }
    public void upgrateMilitanza(Militanza militanza) {
        MilitanzaDAO mDAO = new MilitanzaImplementazionePostgresDAO();
        mDAO.updateMilitanza(militanza);
    }
    public void deleteMilitanza(Militanza militanza) {
        MilitanzaDAO mDAO = new MilitanzaImplementazionePostgresDAO();
        mDAO.deleteMilitanza(militanza);
    }

    public void caricaTrofeo(Trofeo trofeo) {
        if(trofeo.getId()==-1) {
            TrofeoDAO tDAO = new TrofeoImplementazionePostgresDAO();
            tDAO.insertTrofeo(trofeo);
        }
    }
    public void caricaVittoriaIndividuale(int id, Trofeo trofeo) {
        caricaTrofeo(trofeo);
        VittoriaDAO vitDAO = new VittoriaImplementazionePostgresDAO();
        vitDAO.insertVittoriaIndividuale(id, trofeo);
    }
    public void caricaVittoriaSquadra(Trofeo trofeo) {
        caricaTrofeo(trofeo);
        VittoriaDAO vitDAO = new VittoriaImplementazionePostgresDAO();
        vitDAO.insertVittoriaSquadra(trofeo);
    }
    public void deleteVittoria(Trofeo trofeo) {
        VittoriaDAO vitDAO = new VittoriaImplementazionePostgresDAO();
        vitDAO.deleteVittoria(trofeo);
    }

    public void caricaPartita(int id, Partita partita) {
        PartitaDAO pDAO = new PartitaImplementazionePostgresDAO();
        pDAO.insertPartita(id, partita);
    }
    public void updatePartita(Partita partita) {
        PartitaDAO pDAO = new PartitaImplementazionePostgresDAO();
        pDAO.updatePartita(partita);
    }
    public void deletePartita(Partita partita) {
        PartitaDAO pDAO = new PartitaImplementazionePostgresDAO();
        pDAO.deletePartita(partita);
    }

    public void caricaPartecipazione(String casa, String trasferta) {
        PartecipazioneDAO pDAO = new PartecipazioneImplementazionePostgresDAO();
        pDAO.insertPartecipazione(casa, trasferta);
    }

    public void caricaStatistica(Statistiche statistiche) {
        StatisticaDAO sDAO = new StatisticaImplementazionePostgresDAO();
        sDAO.insertStatistiche(statistiche);
    }
    public void updateStatistica(int idPartita, Statistiche statistiche) {
        StatisticaDAO sDAO = new StatisticaImplementazionePostgresDAO();
        sDAO.updateStatistiche(idPartita, statistiche);
    }

    public void caricaStatisticaPor(Statistiche statistiche) {
        StatisticaDAO sDAO = new StatisticaImplementazionePostgresDAO();
        sDAO.insertStatistichePor(statistiche);
    }
    public void updateStatisticaPor(int idPartita, Statistiche statistiche) {
        StatisticaDAO sDAO = new StatisticaImplementazionePostgresDAO();
        sDAO.updateStatistichePor(idPartita, statistiche);
    }

}
