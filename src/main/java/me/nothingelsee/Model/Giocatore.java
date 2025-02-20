package me.nothingelsee.Model;

import me.nothingelsee.ENUM.Piede;
import me.nothingelsee.ENUM.Ruoli;

import java.util.ArrayList;
import java.util.HashMap;

public class Giocatore {

    private int id;
    private String nome;
    private String cognome;
    private String dataNascita;
    private String dataRitiro;
    private Piede piede;
    private HashMap<String, Integer> skill = new HashMap<>();
    private HashMap<String, Integer> abilita = new HashMap<>();
    private ArrayList<Enum> ruoli = new ArrayList<>();
    private int trofeiVinti;

    public Giocatore() {}

    public Giocatore(int id, String nome, String cognome, String dataNascita, String dataRitiro, String piede) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.dataNascita = dataNascita;
        this.dataRitiro = dataRitiro;
        this.piede = Piede.valueOf(piede);
    }

    public int getId() {return id;}
    public String getNome() {return nome;}
    public String getCognome() {return cognome;}
    public String getDataNascita() {return dataNascita;}
    public String getDataRitiro() {return dataRitiro;}
    public Piede getPiede() {return piede;}
    public int getTrofeiVinti() {return trofeiVinti;}

    public HashMap<String, Integer> getAbilità() {return abilita;}
    public void aggiungiAbilita(String nome, Integer valore){
        abilita.put(nome, valore);
    };

    public HashMap<String, Integer> getSkill() {return skill;}
    public void aggiungiSkill(String nome, Integer valore){
        skill.put(nome, valore);
    }

    public ArrayList<Enum> getRuoli() {return ruoli;}

    public void aggiungiRuoli(String nome){
        ruoli.add(Ruoli.valueOf(nome));
    }

    public String getRuoliString(){
        String ruoliString;

        ruoliString = "";
        System.out.println(ruoliString);
        for (int i=0; i<ruoli.size(); i++) ruoliString += ruoli.get(i).toString() + " ";

        System.out.println(ruoliString);

        return ruoliString;
    }

    public void clearRuoli (){
        ruoli.clear();
    }

    public void setTrofeiVinti(int trofeiVinti) {
        this.trofeiVinti = trofeiVinti;
    }
}
