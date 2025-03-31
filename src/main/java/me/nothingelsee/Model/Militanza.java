package me.nothingelsee.Model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * The type Militanza.
 */
public class Militanza {

    private int id;
    private LocalDate dataInizio;
    private LocalDate dataFine;
    private Squadra squadra;
    private ArrayList<Partita> partite = new ArrayList<>();

    /**
     * Instantiates a new Militanza.
     *
     * @param dataInizio the data inizio
     * @param dataFine   the data fine
     * @param squadra    the squadra
     */
    public Militanza(String dataInizio, String dataFine, Squadra squadra) {
        this.dataInizio = LocalDate.parse(dataInizio);
        if (dataFine == null) this.dataFine = null;
        else this.dataFine = LocalDate.parse(dataFine);
        this.squadra = squadra;
    }

    /**
     * Instantiates a new Militanza.
     *
     * @param id         the id
     * @param dataInizio the data inizio
     * @param dataFine   the data fine
     * @param squadra    the squadra
     */
    public Militanza(int id, String dataInizio, String dataFine, Squadra squadra) {
        this.id = id;
        this.dataInizio = LocalDate.parse(dataInizio);
        if (dataFine == null) this.dataFine = null;
        else this.dataFine = LocalDate.parse(dataFine);
        this.squadra = squadra;
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
     * Gets data inizio as string.
     *
     * @return the data inizio as string
     */
    public String getDataInizioAsString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return formatter.format(dataInizio).toString();
    }

    /**
     * Gets data fine as string.
     *
     * @return the data fine as string
     */
    public String getDataFineAsString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        if (dataFine == null) return "Attuale";
        else return formatter.format(dataFine).toString();
    }

    /**
     * Gets squadra.
     *
     * @return the squadra
     */
    public Squadra getSquadra() {
        return squadra;
    }

    /**
     * Gets partite.
     *
     * @return the partite
     */
    public ArrayList<Partita> getPartite() {
        return partite;
    }

    /**
     * Gets data inizio.
     *
     * @return the data inizio
     */
    public LocalDate getDataInizio() {
        return dataInizio;
    }

    /**
     * Gets data fine.
     *
     * @return the data fine
     */
    public LocalDate getDataFine() {
        return dataFine;
    }

    /**
     * Sets data inizio.
     *
     * @param dataInizio the data inizio
     */
    public void setDataInizio(LocalDate dataInizio) {
        this.dataInizio = dataInizio;
    }

    /**
     * Sets data fine.
     *
     * @param dataFine the data fine
     */
    public void setDataFine(LocalDate dataFine) {
        if (dataFine == null) this.dataFine = null;
        else this.dataFine = dataFine;
    }

    /**
     * Sets data inizio.
     *
     * @param dataInizio the data inizio
     */
    public void setDataInizio(String dataInizio) {
        this.dataInizio = LocalDate.parse(dataInizio);
    }

    /**
     * Sets data fine.
     *
     * @param dataFine the data fine
     */
    public void setDataFine(String dataFine) {
        if (dataFine == null) this.dataFine = null;
        else this.dataFine = LocalDate.parse(dataFine);
    }

    /**
     * Sets squadra.
     *
     * @param squadra the squadra
     */
    public void setSquadra(Squadra squadra) {
        this.squadra = squadra;
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
     * Sets partite.
     *
     * @param partite the partite
     */
    public void setPartite(ArrayList<Partita> partite) {
        this.partite = partite;
    }

    /**
     * Add partita.
     *
     * @param p the p
     */
    public void addPartita(Partita p) {
        partite.add(p);
    }
}
