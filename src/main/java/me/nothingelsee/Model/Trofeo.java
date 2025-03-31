package me.nothingelsee.Model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The type Trofeo.
 */
public class Trofeo {
    private int idTrofeo;
    private String nome;
    private LocalDate data;
    private String squadra;
    private String tipo;

    /**
     * Instantiates a new Trofeo.
     *
     * @param nome    the nome
     * @param data    the data
     * @param tipo    the tipo
     * @param squadra the squadra
     */
    public Trofeo(String nome, String data, String tipo, String squadra) {
        this.nome = nome;
        this.data = LocalDate.parse(data);
        this.tipo = tipo;
        this.squadra = squadra;
    }

    /**
     * Instantiates a new Trofeo.
     *
     * @param idTrofeo the id trofeo
     * @param nome     the nome
     * @param data     the data
     * @param squadra  the squadra
     * @param tipo     the tipo
     */
    public Trofeo(int idTrofeo, String nome, String data, String squadra, String tipo) {
        this.idTrofeo = idTrofeo;
        this.nome = nome;
        this.data = LocalDate.parse(data);
        this.squadra = squadra;
        this.tipo = tipo;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return idTrofeo;
    }

    /**
     * Gets nome.
     *
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * Gets data as string.
     *
     * @return the data as string
     */
    public String getDataAsString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return formatter.format(data);
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
     * Gets squadra.
     *
     * @return the squadra
     */
    public String getSquadra() {
        return squadra;
    }

    /**
     * Gets tipo.
     *
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Sets id.
     *
     * @param idTrofeo the id trofeo
     */
    public void setId(int idTrofeo) {
        this.idTrofeo = idTrofeo;
    }

    /**
     * Sets nome.
     *
     * @param nome the nome
     */
    public void setNome(String nome) {
        this.nome = nome;
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
     * Sets squadra.
     *
     * @param squadra the squadra
     */
    public void setSquadra(String squadra) {
        this.squadra = squadra;
    }

    /**
     * Sets tipo.
     *
     * @param tipo the tipo
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
