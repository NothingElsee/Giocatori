package me.nothingelsee.Model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Militanza {

    private int id;
    private LocalDate dataInizio;
    private LocalDate dataFine;
    private Squadra squadra;
    private ArrayList<Partita> partite = new ArrayList<>();

    public Militanza(String dataInizio, String dataFine, Squadra squadra) {
        this.dataInizio = LocalDate.parse(dataInizio);
        if (dataFine == null) this.dataFine = null;
        else this.dataFine = LocalDate.parse(dataFine);
        this.squadra = squadra;
    }

    public Militanza(int id, String dataInizio, String dataFine, Squadra squadra) {
        this.id = id;
        this.dataInizio = LocalDate.parse(dataInizio);
        if (dataFine == null) this.dataFine = null;
        else this.dataFine = LocalDate.parse(dataFine);
        this.squadra = squadra;
    }

    public int getId() {
        return id;
    }

    public String getDataInizioAsString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return formatter.format(dataInizio).toString();
    }

    public String getDataFineAsString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        if (dataFine == null) return "Attuale";
        else return formatter.format(dataFine).toString();
    }

    public Squadra getSquadra() {
        return squadra;
    }

    public ArrayList<Partita> getPartite() {
        return partite;
    }

    public LocalDate getDataInizio() {
        return dataInizio;
    }

    public LocalDate getDataFine() {
        return dataFine;
    }

    public void setDataInizio(LocalDate dataInizio) {
        this.dataInizio = dataInizio;
    }

    public void setDataFine(LocalDate dataFine) {
        if (dataFine == null) this.dataFine = null;
        else this.dataFine = dataFine;
    }

    public void setDataInizio(String dataInizio) {
        this.dataInizio = LocalDate.parse(dataInizio);
    }

    public void setDataFine(String dataFine) {
        if (dataFine == null) this.dataFine = null;
        else this.dataFine = LocalDate.parse(dataFine);
    }

    public void setSquadra(Squadra squadra) {
        this.squadra = squadra;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPartite(ArrayList<Partita> partite) {
        this.partite = partite;
    }

    public void addPartita(Partita p) {
        partite.add(p);
    }
}
