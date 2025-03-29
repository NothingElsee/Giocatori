package me.nothingelsee.Model;

import java.util.ArrayList;

public class Militanza {

    private int id;
    private String dataInizio;
    private String dataFine;
    private Squadra squadra;
    private ArrayList<Partita> partite = new ArrayList<>();

    public Militanza (String dataInizio, String dataFine, Squadra squadra) {
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.squadra = squadra;
    }

    public Militanza(int id, String dataInizio, String dataFine, Squadra squadra){
        this.id = id;
        this.dataInizio = dataInizio;
        if(dataFine == null) this.dataFine = "Attuale";
        else this.dataFine = dataFine;
        this.squadra = squadra;
    }

    public int getId() {return id;}
    public String getDataInizio() {return dataInizio;}
    public String getDataFine() {return dataFine;}
    public Squadra getSquadra() {return squadra;}
    public ArrayList<Partita> getPartite() {return partite;}

    public void setId(int id) {this.id = id;}
    public void setPartite(ArrayList<Partita> partite) {this.partite = partite;}
    public void addPartita(Partita p){partite.add(p);}
}
