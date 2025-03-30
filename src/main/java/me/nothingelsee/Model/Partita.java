package me.nothingelsee.Model;

import me.nothingelsee.ENUM.COMPETIZIONE;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Partita {

    private int id_partita;
    private String squadraCasa;
    private String squadraTrasferta;
    private int goalCasa;
    private int goalTrasferta;
    private LocalDate data;
    private COMPETIZIONE competizione;
    private String nomeCompetizione;
    private Statistiche stat;

    public Partita(String squadraCasa, String squadraTrasferta, int goalCasa, int goalTrasferta, String data, Statistiche stat) {
        this.squadraCasa = squadraCasa;
        this.squadraTrasferta = squadraTrasferta;
        this.goalCasa = goalCasa;
        this.goalTrasferta = goalTrasferta;
        this.data = LocalDate.parse(data);
        this.stat = stat;
        competizione = null;
        nomeCompetizione = null;
    }

    public Partita(int id_partita, String squadraCasa, String squadraTrasferta, int goalCasa, int goalTrasferta, String data, String comp, String nomeCompetizione) {
        this.id_partita = id_partita;
        this.squadraCasa = squadraCasa;
        this.squadraTrasferta = squadraTrasferta;
        this.goalCasa = goalCasa;
        this.goalTrasferta = goalTrasferta;
        this.data = LocalDate.parse(data);
        if (comp != null) this.competizione = COMPETIZIONE.valueOf(comp);
        else comp = "GENERICA";
        this.nomeCompetizione = nomeCompetizione;
    }

    public int getId() {
        return id_partita;
    }

    public String getSquadraCasa() {
        return squadraCasa;
    }

    public String getSquadraTrasferta() {
        return squadraTrasferta;
    }

    public int getGoalCasa() {
        return goalCasa;
    }

    public int getGoalTrasferta() {
        return goalTrasferta;
    }

    public String getDataAsString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return formatter.format(data).toString();
    }

    public LocalDate getData() {
        return data;
    }

    public String getCompetizione() {
        return competizione.toString();
    }

    public Statistiche getStat() {
        return stat;
    }

    public void setId(int id) {
        this.id_partita = id;
    }

    public void setSquadraCasa(String squadraCasa) {
        this.squadraCasa = squadraCasa;
    }

    public void setSquadraTrasferta(String squadraTrasferta) {
        this.squadraTrasferta = squadraTrasferta;
    }

    public void setGoalCasa(int goalCasa) {
        this.goalCasa = goalCasa;
    }

    public void setGoalTrasferta(int goalTrasferta) {
        this.goalTrasferta = goalTrasferta;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public void setDataAsString(String data) {
        this.data = LocalDate.parse(data);
    }

    public void setCompetizine(COMPETIZIONE competizine) {
        this.competizione = competizine;
    }

    public void setStat(Statistiche stat) {
        this.stat = stat;
    }
}
