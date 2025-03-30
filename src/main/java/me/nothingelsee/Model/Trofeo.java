package me.nothingelsee.Model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Trofeo {
    private int idTrofeo;
    private String nome;
    private LocalDate data;
    private String squadra;
    private String tipo;

    public Trofeo(String nome, String data, String tipo, String squadra) {
        this.nome = nome;
        this.data = LocalDate.parse(data);
        this.tipo = tipo;
        this.squadra = squadra;
    }

    public Trofeo(int idTrofeo, String nome, String data, String squadra, String tipo) {
        this.idTrofeo = idTrofeo;
        this.nome = nome;
        this.data = LocalDate.parse(data);
        this.squadra = squadra;
        this.tipo = tipo;
    }

    public int getId() {
        return idTrofeo;
    }

    public String getNome() {
        return nome;
    }

    public String getDataAsString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return formatter.format(data);
    }

    public LocalDate getData() {
        return data;
    }

    public String getSquadra() {
        return squadra;
    }

    public String getTipo() {
        return tipo;
    }

    public void setId(int idTrofeo) {
        this.idTrofeo = idTrofeo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public void setDataAsString(String data) {
        this.data = LocalDate.parse(data);
    }

    public void setSquadra(String squadra) {
        this.squadra = squadra;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
