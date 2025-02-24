package me.nothingelsee.Model;

import java.util.ArrayList;

public class Squadra {

    private String nome;
    private String nazionalita;
    private ArrayList<Partita> partite = new ArrayList<>();
    private ArrayList<Trofeo> trofei = new ArrayList<>();

    public Squadra (String nome, String nazionalita){
        this.nome = nome;
        this.nazionalita = nazionalita;
    }

    public String getNome() {return nome;}
    public String getNazionalita() {return nazionalita;}
    public ArrayList<Partita> getPartite() {return partite;}
    public ArrayList<Trofeo> getTrofei() {return trofei;}

    public void setNome(String nome) {this.nome = nome;}
    public void setNazionalita(String nazionalita) {this.nazionalita = nazionalita;}
    public void addPartita(Partita partita){partite.add(partita);}
    public void addTrofeo(Trofeo trofeo){trofei.add(trofeo);}
}
