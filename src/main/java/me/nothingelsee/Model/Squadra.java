package me.nothingelsee.Model;

/**
 * The type Squadra.
 */
public class Squadra {

    private String nome;
    private String nazionalita;

    /**
     * Instantiates a new Squadra.
     *
     * @param nome the nome
     */
    public Squadra(String nome) {
        this.nome = nome;
    }

    /**
     * Instantiates a new Squadra.
     *
     * @param nome        the nome
     * @param nazionalita the nazionalita
     */
    public Squadra(String nome, String nazionalita) {
        this.nome = nome;
        this.nazionalita = nazionalita;
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
     * Gets nazionalita.
     *
     * @return the nazionalita
     */
    public String getNazionalita() {
        return nazionalita;
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
     * Sets nazionalita.
     *
     * @param nazionalita the nazionalita
     */
    public void setNazionalita(String nazionalita) {
        this.nazionalita = nazionalita;
    }
}
