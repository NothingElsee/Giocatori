package me.nothingelsee.Model;

import me.nothingelsee.ENUM.PIEDE;
import me.nothingelsee.ENUM.RUOLO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

public class Giocatore {

    private int id;
    private String nome;
    private String cognome;
    private LocalDate dataNascita;
    private String nazionalita;
    private LocalDate dataRitiro;
    private PIEDE piede;
    private ArrayList<RUOLO> ruoli;
    private ArrayList<Trofeo> trofei = new ArrayList<>();
    private ArrayList<Integer> abilita;
    private ArrayList<Integer> skill;
    private ArrayList<Militanza> militanze = new ArrayList<>();

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

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getDataNascitaAsString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return formatter.format(dataNascita).toString();
    }

    public String getDataRitiroAsString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        if (dataRitiro == null) return "Non Ritirato";
        else return formatter.format(dataRitiro).toString();
    }

    public LocalDate getDataNascita() {
        return dataNascita;
    }

    public LocalDate getDataRitiro() {
        return dataRitiro;
    }

    public String getNazionalita() {
        return nazionalita;
    }

    public PIEDE getPiede() {
        return piede;
    }

    public ArrayList<Trofeo> getTrofei() {
        return trofei;
    }

    public int getTrofeiVinti() {
        return trofei.size();
    }

    public ArrayList<RUOLO> getRuoli() {
        return ruoli;
    }
    public int[] getRuoliIndici() {
        int[] indici = new int[ruoli.size()];
        for (int i = 0; i < ruoli.size(); i++) {
            indici[i] = ruoli.get(i).ordinal();
        }
        return indici;
    }

    public ArrayList<Integer> getSkill() {
        return skill;
    }

    public ArrayList<Integer> getAbilita() {
        return abilita;
    }

    public ArrayList<Militanza> getMilitanze() {
        return militanze;
    }

    public int getNumTrofei() {
        return trofei.size();
    }

    public String getRuoliString() {
        String ruoliString;

        ruoliString = "";
        for (RUOLO ruolo : ruoli) ruoliString += ruolo.toString() + " ";

        return ruoliString;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public void setDataNascitaAsString(String dataNascita) {
        this.dataNascita = LocalDate.parse(dataNascita);
    }

    public void setDataRitiroAsString(String dataRitiro) {
        if (dataRitiro == null) this.dataRitiro = null;
        else this.dataRitiro = LocalDate.parse(dataRitiro);
    }

    public void setDataNascita(LocalDate dataNascita) {
        this.dataNascita = dataNascita;
    }

    public void setDataRitiro(LocalDate dataRitiro) {
        if (dataRitiro == null) this.dataRitiro = null;
        else this.dataRitiro = dataRitiro;
    }

    public void setNazionalita(String nazionalita) {
        this.nazionalita = nazionalita;
    }

    public void setPiede(PIEDE piede) {
        this.piede = piede;
    }

    public void setSkills(ArrayList<Integer> skills) {
        this.skill = skills;
    }

    public void setAbilita(ArrayList<Integer> abilita) {
        this.abilita = abilita;
    }

    public void setRuoli(ArrayList<RUOLO> ruoli) {
        this.ruoli = ruoli;
    }

    public void addAbilita(Integer valore) {
        abilita.add(valore);
    }

    public void addSkill(Integer valore) {
        skill.add(valore);
    }

    public void addRuolo(String nome) {
        ruoli.add(RUOLO.valueOf(nome));
    }

    public void addTrofeo(Trofeo trofeo) {
        trofei.add(trofeo);
    }

    public void addMilitanza(Militanza militanza) {
        militanze.add(militanza);
    }

    public void clearRuoli() {
        ruoli.clear();
    }

    public void clearTrofei() {
        trofei.clear();
    }

    public void clearMilitanze() {
        militanze.clear();
    }

}
