package me.nothingelsee.Controller;

import me.nothingelsee.ENUM.RUOLO;
import me.nothingelsee.ImplementazioniPostgresDAO.*;
import me.nothingelsee.InterfacceDAO.*;
import me.nothingelsee.Model.*;

import java.util.ArrayList;

/**
 * The type Controller.
 */
public class Controller {

    private ArrayList<Giocatore> giocatori = new ArrayList<>();
    private Giocatore giocatoreCercato;
    private Militanza militanzaCercata;
    private ArrayList<String> filtriRicerca = new ArrayList<>();
    private Trofeo trofeoCercato = null;

    /**
     * Instantiates a new Controller.
     */
    public Controller() {
    }

    /**
     * Gets giocatore cercato.
     *
     * @return the giocatore cercato
     */
    public Giocatore getGiocatoreCercato() {
        return giocatoreCercato;
    }

    /**
     * Gets giocatori.
     *
     * @return the giocatori
     */
    public ArrayList<Giocatore> getGiocatori() {
        return giocatori;
    }

    /**
     * Gets militanza cercata.
     *
     * @return the militanza cercata
     */
    public Militanza getMilitanzaCercata() {
        return militanzaCercata;
    }

    /**
     * Gets giocatori by filtri.
     *
     * @return the giocatori by filtri
     */
    public ArrayList<Integer> getGiocatoriByFiltri() {
        GiocatoreDAO gioDAO = new GiocatoreImplementazionePostgresDAO();
        return gioDAO.getGiocatoriByFiltri(giocatori, filtriRicerca);
    }

    /**
     * Gets abilita.
     *
     * @param giocatore the giocatore
     */
    public void getAbilita(Giocatore giocatore) {
        AbilitaDAO ablDAO = new AbilitaImplementazionePostgresDAO();
        ablDAO.getAbilities(giocatore);
    }

    /**
     * Gets ruoli.
     *
     * @param giocatore the giocatore
     */
    public void getRuoli(Giocatore giocatore) {
        RuoloDAO rloDAO = new RuoloImplementazionePostgresDAO();
        rloDAO.getRuoli(giocatore);
    }

    /**
     * Gets skill.
     *
     * @param giocatore the giocatore
     */
    public void getSkill(Giocatore giocatore) {
        SkillDAO sklDAO = new SkillImplementazionePostgresDAO();
        sklDAO.getSkills(giocatore);
    }

    /**
     * Gets militanze.
     *
     * @param giocatore the giocatore
     */
    public void getMilitanze(Giocatore giocatore) {
        MilitanzaDAO milDAO = new MilitanzaImplementazionePostgresDAO();
        milDAO.getMilitanze(giocatore);
    }

    /**
     * Gets partite.
     *
     * @param militanza the militanza
     */
    public void getPartite(Militanza militanza) {
        PartitaDAO prtDAO = new PartitaImplementazionePostgresDAO();
        prtDAO.getPartite(militanza);
    }

    /**
     * Gets stat.
     *
     * @param partita the partita
     */
    public void getStat(Partita partita) {
        StatisticaDAO stDAO = new StatisticaImplementazionePostgresDAO();
        stDAO.getStat(partita);
    }

    /**
     * Gets militanze da giocatore.
     *
     * @return the militanze da giocatore
     */
    public ArrayList<Militanza> getMilitanzeDaGiocatore() {
        return giocatoreCercato.getMilitanze();
    }

    /**
     * Gets squadre nomi.
     *
     * @return the squadre nomi
     */
    public ArrayList<String> getSquadreNomi() {
        SquadraDAO sqDAO = new SquadraImplementazionePostgresDAO();
        return sqDAO.getSquadreNomi();
    }

    /**
     * Gets trofei.
     *
     * @param giocatore the giocatore
     */
    public void getTrofei(Giocatore giocatore) {
        TrofeoDAO tDAO = new TrofeoImplementazionePostgresDAO();
        tDAO.getTrofei(giocatore);
    }

    /**
     * Gets trofeo cercato.
     *
     * @return the trofeo cercato
     */
    public Trofeo getTrofeoCercato() {
        return trofeoCercato;
    }

    /**
     * Gets filtri ricerca.
     *
     * @return the filtri ricerca
     */
    public ArrayList<String> getFiltriRicerca() {
        return filtriRicerca;
    }

    /**
     * Add filtri.
     *
     * @param filtro the filtro
     */
    public void addFiltri(String filtro) {
        filtriRicerca.add(filtro);
    }

    /**
     * Sets giocatore cercato.
     *
     * @param giocatoreCercato the giocatore cercato
     */
    public void setGiocatoreCercato(Giocatore giocatoreCercato) {
        this.giocatoreCercato = giocatoreCercato;
    }

    /**
     * Sets militanza cercata.
     *
     * @param militanzaCercata the militanza cercata
     */
    public void setMilitanzaCercata(Militanza militanzaCercata) {
        this.militanzaCercata = militanzaCercata;
    }

    /**
     * Sets trofeo cercato.
     *
     * @param trofeo the trofeo
     */
    public void setTrofeoCercato(Trofeo trofeo) {
        trofeoCercato = trofeo;
    }

    /**
     * Is in boolean.
     *
     * @param username the username
     * @param password the password
     * @return the boolean
     */
    public boolean isIn(String username, String password) {
        AccountDAO accountDAO = new AccountImplementazionePostgresDAO();
        return accountDAO.isIn(username, password);
    }

    /**
     * Gets trofei nome.
     *
     * @param trofeiNome the trofei nome
     */
    public void getTrofeiNome(ArrayList<String> trofeiNome) {
        TrofeoDAO trDAO = new TrofeoImplementazionePostgresDAO();
        trDAO.getTrofeiNome(trofeiNome);
    }

    /**
     * Gets nazionalita.
     *
     * @param nazionalita the nazionalita
     */
    public void getNazionalita(ArrayList<String> nazionalita) {
        NazionalitaDAO naDAO = new NazionalitaImplementazionePostgresDAO();
        naDAO.getNazionalita(nazionalita);
    }

    /**
     * Carica giocatore.
     *
     * @param giocatore the giocatore
     */
    public void caricaGiocatore(Giocatore giocatore) {
        GiocatoreDAO giocatoreDAO = new GiocatoreImplementazionePostgresDAO();
        giocatoreDAO.insertGiocatore(giocatore);
    }

    /**
     * Update giocatore.
     *
     * @param giocatore the giocatore
     */
    public void updateGiocatore(Giocatore giocatore) {
        GiocatoreDAO giDAO = new GiocatoreImplementazionePostgresDAO();
        giDAO.updateGiocatore(giocatore);
    }

    /**
     * Delete giocatore.
     *
     * @param giocatore the giocatore
     */
    public void deleteGiocatore(Giocatore giocatore) {
        GiocatoreDAO gioDAO = new GiocatoreImplementazionePostgresDAO();
        gioDAO.deleteGiocatore(giocatore);
    }

    /**
     * Carica ruoli.
     *
     * @param id    the id
     * @param ruoli the ruoli
     */
    public void caricaRuoli(int id, ArrayList<RUOLO> ruoli) {
        PosizioneDAO psDAO = new PosizioneImplementazionePostgresDAO();
        psDAO.insertPosizione(id, ruoli);
    }

    /**
     * Update ruoli.
     *
     * @param id    the id
     * @param ruoli the ruoli
     */
    public void updateRuoli(int id, ArrayList<RUOLO> ruoli) {
        PosizioneDAO psDAO = new PosizioneImplementazionePostgresDAO();
        psDAO.updatePosizione(id, ruoli);
    }

    /**
     * Carica skill.
     *
     * @param idGiocatore the id giocatore
     * @param skill       the skill
     */
    public void caricaSkill(int idGiocatore, ArrayList<Integer> skill) {
        SkillDAO sDAO = new SkillImplementazionePostgresDAO();
        sDAO.insertSkill(idGiocatore, skill);
    }

    /**
     * Update skill.
     *
     * @param id    the id
     * @param skill the skill
     */
    public void updateSkill(int id, ArrayList<Integer> skill) {
        SkillDAO sDAO = new SkillImplementazionePostgresDAO();
        sDAO.updateSkill(id, skill);
    }

    /**
     * Carica abilita.
     *
     * @param idGiocatore the id giocatore
     * @param abilita     the abilita
     */
    public void caricaAbilita(int idGiocatore, ArrayList<Integer> abilita) {
        AbilitaDAO aDAO = new AbilitaImplementazionePostgresDAO();
        aDAO.insertAbilita(idGiocatore, abilita);
    }

    /**
     * Update abilita.
     *
     * @param id    the id
     * @param skill the skill
     */
    public void updateAbilita(int id, ArrayList<Integer> skill) {
        AbilitaDAO aDAO = new AbilitaImplementazionePostgresDAO();
        aDAO.updateAbilita(id, skill);
    }

    /**
     * Carica nazionalita.
     *
     * @param id          the id
     * @param nazionalita the nazionalita
     */
    public void caricaNazionalita(int id, String nazionalita) {
        NazionalitaDAO naDAO = new NazionalitaImplementazionePostgresDAO();
        naDAO.insertNazionalita(id, nazionalita);
    }

    /**
     * Update nazionalita.
     *
     * @param id          the id
     * @param nazionalita the nazionalita
     */
    public void updateNazionalita(int id, String nazionalita) {
        NazionalitaDAO naDAO = new NazionalitaImplementazionePostgresDAO();
        naDAO.updateNazionalita(id, nazionalita);
    }

    /**
     * Carica squadra.
     *
     * @param squadra the squadra
     */
    public void caricaSquadra(Squadra squadra) {
        SquadraDAO sDAO = new SquadraImplementazionePostgresDAO();
        sDAO.insertSquadra(squadra);
    }

    /**
     * Carica militanza boolean.
     *
     * @param id        the id
     * @param militanza the militanza
     * @return the boolean
     */
    public boolean caricaMilitanza(int id, Militanza militanza) {
        MilitanzaDAO mDAO = new MilitanzaImplementazionePostgresDAO();
        return mDAO.insertMilitanza(id, militanza);
    }

    /**
     * Upgrate militanza boolean.
     *
     * @param militanza the militanza
     * @return the boolean
     */
    public boolean upgrateMilitanza(Militanza militanza) {
        MilitanzaDAO mDAO = new MilitanzaImplementazionePostgresDAO();
        return mDAO.updateMilitanza(militanza);
    }

    /**
     * Delete militanza.
     *
     * @param militanza the militanza
     */
    public void deleteMilitanza(Militanza militanza) {
        MilitanzaDAO mDAO = new MilitanzaImplementazionePostgresDAO();
        mDAO.deleteMilitanza(militanza);
    }

    /**
     * Carica trofeo.
     *
     * @param trofeo the trofeo
     */
    public void caricaTrofeo(Trofeo trofeo) {
        if (trofeo.getId() == -1) {
            TrofeoDAO tDAO = new TrofeoImplementazionePostgresDAO();
            tDAO.insertTrofeo(trofeo);
        }
    }

    /**
     * Carica vittoria individuale.
     *
     * @param id     the id
     * @param trofeo the trofeo
     */
    public void caricaVittoriaIndividuale(int id, Trofeo trofeo) {
        caricaTrofeo(trofeo);
        VittoriaDAO vitDAO = new VittoriaImplementazionePostgresDAO();
        vitDAO.insertVittoriaIndividuale(id, trofeo);
    }

    /**
     * Carica vittoria squadra.
     *
     * @param idGiocatore the id giocatore
     * @param trofeo      the trofeo
     */
    public void caricaVittoriaSquadra(int idGiocatore, Trofeo trofeo) {
        caricaTrofeo(trofeo);
        VittoriaDAO vitDAO = new VittoriaImplementazionePostgresDAO();
        vitDAO.insertVittoriaSquadra(idGiocatore, trofeo);
    }

    /**
     * Delete vittoria.
     *
     * @param trofeo the trofeo
     */
    public void deleteVittoria(Trofeo trofeo) {
        VittoriaDAO vitDAO = new VittoriaImplementazionePostgresDAO();
        vitDAO.deleteVittoria(trofeo);
    }

    /**
     * Carica partita.
     *
     * @param id      the id
     * @param partita the partita
     */
    public void caricaPartita(int id, Partita partita) {
        PartitaDAO pDAO = new PartitaImplementazionePostgresDAO();
        pDAO.insertPartita(id, partita);
    }

    /**
     * Update partita.
     *
     * @param partita the partita
     */
    public void updatePartita(Partita partita) {
        PartitaDAO pDAO = new PartitaImplementazionePostgresDAO();
        pDAO.updatePartita(partita);
    }

    /**
     * Delete partita.
     *
     * @param partita the partita
     */
    public void deletePartita(Partita partita) {
        PartitaDAO pDAO = new PartitaImplementazionePostgresDAO();
        pDAO.deletePartita(partita);
    }

    /**
     * Carica partecipazione.
     *
     * @param casa      the casa
     * @param trasferta the trasferta
     */
    public void caricaPartecipazione(String casa, String trasferta) {
        PartecipazioneDAO pDAO = new PartecipazioneImplementazionePostgresDAO();
        pDAO.insertPartecipazione(casa, trasferta);
    }

    /**
     * Carica statistica.
     *
     * @param idPartita   the id partita
     * @param statistiche the statistiche
     */
    public void caricaStatistica(int idPartita, Statistiche statistiche) {
        StatisticaDAO sDAO = new StatisticaImplementazionePostgresDAO();
        sDAO.insertStatistiche(idPartita, statistiche);
    }

    /**
     * Update statistica.
     *
     * @param idPartita   the id partita
     * @param statistiche the statistiche
     */
    public void updateStatistica(int idPartita, Statistiche statistiche) {
        StatisticaDAO sDAO = new StatisticaImplementazionePostgresDAO();
        sDAO.updateStatistiche(idPartita, statistiche);
    }

    /**
     * Carica statistica por.
     *
     * @param idPartita   the id partita
     * @param statistiche the statistiche
     */
    public void caricaStatisticaPor(int idPartita, Statistiche statistiche) {
        StatisticaDAO sDAO = new StatisticaImplementazionePostgresDAO();
        sDAO.insertStatistichePor(idPartita, statistiche);
    }

    /**
     * Update statistica por.
     *
     * @param idPartita   the id partita
     * @param statistiche the statistiche
     */
    public void updateStatisticaPor(int idPartita, Statistiche statistiche) {
        StatisticaDAO sDAO = new StatisticaImplementazionePostgresDAO();
        sDAO.updateStatistichePor(idPartita, statistiche);
    }

    /**
     * Update trofeo.
     *
     * @param trofeo the trofeo
     */
    public void updateTrofeo(Trofeo trofeo) {
        TrofeoDAO tDAO = new TrofeoImplementazionePostgresDAO();
        tDAO.updateTrofeo(trofeo);
    }

    /**
     * Gets squadre giocatore.
     *
     * @param id the id
     * @return the squadre giocatore
     */
    public ArrayList<String> getSquadreGiocatore(int id) {
        SquadraDAO sDAO = new SquadraImplementazionePostgresDAO();
        return sDAO.getSquadreGiocatore(id);
    }
}
