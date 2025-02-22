package me.nothingelsee.Model;

import java.util.ArrayList;

public class Squadra {

    private String nome;
    private String nazionalita;
    private String dataInizio;
    private String dataFine;
    private ArrayList<Partita> partite = new ArrayList<>();
    private ArrayList<Trofeo> trofei = new ArrayList<>();

    public Squadra (String nome, String nazionalita, String dataInizio, String dataFine){
        this.nome = nome;
        this.nazionalita = nazionalita;
        this.dataInizio = dataInizio;
        if(dataFine == null) this.dataFine = "Attuale";
        else this.dataFine = dataFine;
    }

    public String getNome() {return nome;}
    public String getNazionalita() {return nazionalita;}
    public String getDataInizio() {return dataInizio;}
    public String getDataFine() {return dataFine;}
    public ArrayList<Partita> getPartite() {return partite;}
    public ArrayList<Trofeo> getTrofei() {return trofei;}

    public void setNome(String nome) {this.nome = nome;}
    public void setNazionalita(String nazionalita) {this.nazionalita = nazionalita;}
    public void addPartita(Partita partita){partite.add(partita);}
    public void addTrofeo(Trofeo trofeo){trofei.add(trofeo);}
}
