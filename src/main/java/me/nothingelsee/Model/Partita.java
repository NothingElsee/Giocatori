package me.nothingelsee.Model;

import me.nothingelsee.ENUM.COMPETIZIONE;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The type Partita.
 */
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

    /**
     * Instantiates a new Partita.
     *
     * @param squadraCasa      the squadra casa
     * @param squadraTrasferta the squadra trasferta
     * @param goalCasa         the goal casa
     * @param goalTrasferta    the goal trasferta
     * @param data             the data
     * @param stat             the stat
     */
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

    /**
     * Instantiates a new Partita.
     *
     * @param id_partita       the id partita
     * @param squadraCasa      the squadra casa
     * @param squadraTrasferta the squadra trasferta
     * @param goalCasa         the goal casa
     * @param goalTrasferta    the goal trasferta
     * @param data             the data
     * @param comp             the comp
     * @param nomeCompetizione the nome competizione
     */
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

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id_partita;
    }

    /**
     * Gets squadra casa.
     *
     * @return the squadra casa
     */
    public String getSquadraCasa() {
        return squadraCasa;
    }

    /**
     * Gets squadra trasferta.
     *
     * @return the squadra trasferta
     */
    public String getSquadraTrasferta() {
        return squadraTrasferta;
    }

    /**
     * Gets goal casa.
     *
     * @return the goal casa
     */
    public int getGoalCasa() {
        return goalCasa;
    }

    /**
     * Gets goal trasferta.
     *
     * @return the goal trasferta
     */
    public int getGoalTrasferta() {
        return goalTrasferta;
    }

    /**
     * Gets data as string.
     *
     * @return the data as string
     */
    public String getDataAsString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return formatter.format(data).toString();
    }

    /**
     * Gets data.
     *
     * @return the data
     */
    public LocalDate getData() {
        return data;
    }

    /**
     * Gets competizione.
     *
     * @return the competizione
     */
    public String getCompetizione() {
        return competizione.toString();
    }

    /**
     * Gets stat.
     *
     * @return the stat
     */
    public Statistiche getStat() {
        return stat;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id_partita = id;
    }

    /**
     * Sets squadra casa.
     *
     * @param squadraCasa the squadra casa
     */
    public void setSquadraCasa(String squadraCasa) {
        this.squadraCasa = squadraCasa;
    }

    /**
     * Sets squadra trasferta.
     *
     * @param squadraTrasferta the squadra trasferta
     */
    public void setSquadraTrasferta(String squadraTrasferta) {
        this.squadraTrasferta = squadraTrasferta;
    }

    /**
     * Sets goal casa.
     *
     * @param goalCasa the goal casa
     */
    public void setGoalCasa(int goalCasa) {
        this.goalCasa = goalCasa;
    }

    /**
     * Sets goal trasferta.
     *
     * @param goalTrasferta the goal trasferta
     */
    public void setGoalTrasferta(int goalTrasferta) {
        this.goalTrasferta = goalTrasferta;
    }

    /**
     * Sets data.
     *
     * @param data the data
     */
    public void setData(LocalDate data) {
        this.data = data;
    }

    /**
     * Sets data as string.
     *
     * @param data the data
     */
    public void setDataAsString(String data) {
        this.data = LocalDate.parse(data);
    }

    /**
     * Sets competizione.
     *
     * @param competizione the competizione
     */
    public void setCompetizione(COMPETIZIONE competizione) {
        this.competizione = competizione;
    }

    /**
     * Sets stat.
     *
     * @param stat the stat
     */
    public void setStat(Statistiche stat) {
        this.stat = stat;
    }
}
