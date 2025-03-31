package me.nothingelsee.Model;

import me.nothingelsee.ENUM.PIEDE;
import me.nothingelsee.ENUM.RUOLO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * The type Giocatore.
 */
public class Giocatore {

    private int id;
    private String nome;
    private String cognome;
    private LocalDate dataNascita;
    private String nazionalita;
    private LocalDate dataRitiro;
    private PIEDE piede;
    private ArrayList<RUOLO> ruoli;
    private ArrayList<Trofeo> trofei;
    private ArrayList<Integer> abilita;
    private ArrayList<Integer> skill;
    private ArrayList<Militanza> militanze = new ArrayList<>();

    /**
     * Instantiates a new Giocatore.
     *
     * @param nome        the nome
     * @param cognome     the cognome
     * @param dataNascita the data nascita
     * @param nazionalita the nazionalita
     * @param dataRitiro  the data ritiro
     * @param piede       the piede
     * @param skills      the skills
     * @param abilita     the abilita
     * @param ruoli       the ruoli
     */
    public Giocatore(String nome, String cognome, String dataNascita, String nazionalita, String dataRitiro, PIEDE piede, ArrayList<Integer> skills, ArrayList<Integer> abilita, ArrayList<RUOLO> ruoli) {
        id = -1;
        this.nome = nome;
        this.cognome = cognome;
        this.dataNascita = LocalDate.parse(dataNascita);
        this.nazionalita = nazionalita;
        if (dataRitiro == null) this.dataRitiro = null;
        else this.dataRitiro = LocalDate.parse(dataRitiro);
        this.piede = piede;
        this.ruoli = ruoli;
        this.skill = skills;
        this.abilita = abilita;
    }

    /**
     * Instantiates a new Giocatore.
     *
     * @param id          the id
     * @param nome        the nome
     * @param cognome     the cognome
     * @param dataNascita the data nascita
     * @param nazionalita the nazionalita
     * @param dataRitiro  the data ritiro
     * @param piede       the piede
     */
    public Giocatore(int id, String nome, String cognome, String dataNascita, String nazionalita, String dataRitiro, String piede) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.dataNascita = LocalDate.parse(dataNascita);
        this.nazionalita = nazionalita;
        if (dataRitiro == null) this.dataRitiro = null;
        else this.dataRitiro = LocalDate.parse(dataRitiro);
        this.piede = PIEDE.valueOf(piede);
        ruoli = new ArrayList<>();
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Gets nome.
     *
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * Gets cognome.
     *
     * @return the cognome
     */
    public String getCognome() {
        return cognome;
    }

    /**
     * Gets data nascita as string.
     *
     * @return the data nascita as string
     */
    public String getDataNascitaAsString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return formatter.format(dataNascita).toString();
    }

    /**
     * Gets data ritiro as string.
     *
     * @return the data ritiro as string
     */
    public String getDataRitiroAsString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        if (dataRitiro == null) return "Non Ritirato";
        else return formatter.format(dataRitiro).toString();
    }

    /**
     * Gets data nascita.
     *
     * @return the data nascita
     */
    public LocalDate getDataNascita() {
        return dataNascita;
    }

    /**
     * Gets data ritiro.
     *
     * @return the data ritiro
     */
    public LocalDate getDataRitiro() {
        return dataRitiro;
    }

    /**
     * Gets nazionalita.
     *
     * @return the nazionalita
     */
    public String getNazionalita() {
        return nazionalita;
    }

    /**
     * Gets piede.
     *
     * @return the piede
     */
    public PIEDE getPiede() {
        return piede;
    }

    /**
     * Gets trofei.
     *
     * @return the trofei
     */
    public ArrayList<Trofeo> getTrofei() {
        return trofei;
    }

    /**
     * Gets ruoli.
     *
     * @return the ruoli
     */
    public ArrayList<RUOLO> getRuoli() {
        return ruoli;
    }

    /**
     * Get ruoli indici int [ ].
     *
     * @return the int [ ]
     */
    public int[] getRuoliIndici() {
        int[] indici = new int[ruoli.size()];
        for (int i = 0; i < ruoli.size(); i++) {
            indici[i] = ruoli.get(i).ordinal();
        }
        return indici;
    }

    /**
     * Gets skill.
     *
     * @return the skill
     */
    public ArrayList<Integer> getSkill() {
        return skill;
    }

    /**
     * Gets abilita.
     *
     * @return the abilita
     */
    public ArrayList<Integer> getAbilita() {
        return abilita;
    }

    /**
     * Gets militanze.
     *
     * @return the militanze
     */
    public ArrayList<Militanza> getMilitanze() {
        return militanze;
    }

    /**
     * Gets num trofei.
     *
     * @return the num trofei
     */
    public int getNumTrofei() {
        return trofei.size();
    }

    /**
     * Gets ruoli string.
     *
     * @return the ruoli string
     */
    public String getRuoliString() {
        String ruoliString;

        ruoliString = "";
        for (RUOLO ruolo : ruoli) ruoliString += ruolo.toString() + " ";

        return ruoliString;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Sets nome.
     *
     * @param nome the nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Sets cognome.
     *
     * @param cognome the cognome
     */
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    /**
     * Sets data nascita as string.
     *
     * @param dataNascita the data nascita
     */
    public void setDataNascitaAsString(String dataNascita) {
        this.dataNascita = LocalDate.parse(dataNascita);
    }

    /**
     * Sets data ritiro as string.
     *
     * @param dataRitiro the data ritiro
     */
    public void setDataRitiroAsString(String dataRitiro) {
        if (dataRitiro == null) this.dataRitiro = null;
        else this.dataRitiro = LocalDate.parse(dataRitiro);
    }

    /**
     * Sets data nascita.
     *
     * @param dataNascita the data nascita
     */
    public void setDataNascita(LocalDate dataNascita) {
        this.dataNascita = dataNascita;
    }

    /**
     * Sets data ritiro.
     *
     * @param dataRitiro the data ritiro
     */
    public void setDataRitiro(LocalDate dataRitiro) {
        if (dataRitiro == null) this.dataRitiro = null;
        else this.dataRitiro = dataRitiro;
    }

    /**
     * Sets nazionalita.
     *
     * @param nazionalita the nazionalita
     */
    public void setNazionalita(String nazionalita) {
        this.nazionalita = nazionalita;
    }

    /**
     * Sets piede.
     *
     * @param piede the piede
     */
    public void setPiede(PIEDE piede) {
        this.piede = piede;
    }

    /**
     * Sets skills.
     *
     * @param skills the skills
     */
    public void setSkills(ArrayList<Integer> skills) {
        this.skill = skills;
    }

    /**
     * Sets abilita.
     *
     * @param abilita the abilita
     */
    public void setAbilita(ArrayList<Integer> abilita) {
        this.abilita = abilita;
    }

    /**
     * Sets ruoli.
     *
     * @param ruoli the ruoli
     */
    public void setRuoli(ArrayList<RUOLO> ruoli) {
        this.ruoli = ruoli;
    }

    /**
     * Add militanza.
     *
     * @param militanza the militanza
     */
    public void addMilitanza(Militanza militanza) {
        militanze.add(militanza);
    }

    /**
     * Sets trofei.
     *
     * @param trofei the trofei
     */
    public void setTrofei(ArrayList<Trofeo> trofei) {
        this.trofei = trofei;
    }

    /**
     * Clear militanze.
     */
    public void clearMilitanze() {
        militanze.clear();
    }
}
