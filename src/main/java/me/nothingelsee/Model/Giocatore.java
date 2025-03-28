package me.nothingelsee.Model;

import me.nothingelsee.ENUM.PIEDE;
import me.nothingelsee.ENUM.RUOLO;

import java.util.ArrayList;
import java.util.HashMap;

public class Giocatore {

    private int id;
    private String nome;
    private String cognome;
    private String dataNascita;
    private String nazionalita;
    private String dataRitiro;
    private PIEDE piede;
    private HashMap<String, Integer> skill = new HashMap<>();
    private HashMap<String, Integer> abilita = new HashMap<>();
    private ArrayList<RUOLO> ruoli;
    private ArrayList<Trofeo> trofei = new ArrayList<>();
    private ArrayList<Integer> abilitaI;
    private ArrayList<Integer> skillI;
    private ArrayList<Militanza> militanze = new ArrayList<>();

    public Giocatore(String nome, String cognome, String dataNascita, String nazionalita, String dataRitiro, PIEDE piede, ArrayList<Integer> skills, ArrayList<Integer> abilita, ArrayList<RUOLO> ruoli) {
        id = -1;
        this.nome = nome;
        this.cognome = cognome;
        this.dataNascita = dataNascita;
        this.nazionalita = nazionalita;
        this.dataRitiro = dataRitiro;
        this.piede = piede;
        this.ruoli = ruoli;
        this.skillI = skills;
        this.abilitaI = abilita;
    }

    public Giocatore(int id, String nome, String cognome, String dataNascita, String dataRitiro, String piede) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.dataNascita = dataNascita;
        if(dataRitiro == null) dataRitiro = "Non Ritirato";
        this.dataRitiro = dataRitiro;
        this.piede = PIEDE.valueOf(piede);
        ruoli = new ArrayList<>();
    }

    public int getId() {return id;}
    public String getNome() {return nome;}
    public String getCognome() {return cognome;}
    public String getDataNascita() {return dataNascita;}
    public String getDataRitiro() {return dataRitiro;}
    public String getNazionalita() {return nazionalita;}
    public PIEDE getPiede() {return piede;}
    public ArrayList<Trofeo> getTrofei() {return trofei;}
    public int getTrofeiVinti() {return trofei.size();}
    public ArrayList<RUOLO> getRuoli() {return ruoli;}
    public HashMap<String, Integer> getAbilita() {return abilita;}
    public HashMap<String, Integer> getSkill() {return skill;}
    public ArrayList<Militanza> getMilitanze() {return militanze;}
    public int getNumTrofei() {return trofei.size();}
    public String getRuoliString(){
        String ruoliString;

        ruoliString = "";
        for (RUOLO ruolo : ruoli) ruoliString += ruolo.toString() + " ";

        return ruoliString;
    }

    public void setId(int id) {this.id = id;}
    public void setNome(String nome) {this.nome = nome;}
    public void setCognome(String cognome) {this.cognome = cognome;}
    public void setDataNascita(String dataNascita) {this.dataNascita = dataNascita;}
    public void setDataRitiro (String dataRitiro) {this.dataRitiro = dataRitiro;}
    public void setNazionalita (String nazionalita) {this.nazionalita = nazionalita;}
    public void setPiede(PIEDE piede) {this.piede = piede;}
    public void setSkills(ArrayList<Integer> skills) {this.skillI = skills;}
    public void setAbilita(ArrayList<Integer> abilita) {this.abilitaI = abilita;}
    public void setRuoli(ArrayList<RUOLO> ruoli) {this.ruoli = ruoli;}

    public void addAbilita(String nome, Integer valore){
        abilita.put(nome, valore);
    }
    public void addSkill(String nome, Integer valore){
        skill.put(nome, valore);
    }
    public void addRuolo(String nome){
        ruoli.add(RUOLO.valueOf(nome));
    }
    public void addTrofeo(Trofeo trofeo) {trofei.add(trofeo);}
    public void addMilitanza(Militanza militanza) {militanze.add(militanza);}

    public void clearRuoli() {ruoli.clear();}
    public void clearTrofei() {trofei.clear();}
    public void clearMilitanze() {militanze.clear();}


}
