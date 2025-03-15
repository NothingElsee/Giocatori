package me.nothingelsee.Model;

public class Trofeo {
    private String nome;
    private String data;
    private String squadra;
    private String tipo;

    public Trofeo (String nome, String data, String tipo) {
        this.nome = nome;
        this.data = data;
        this.tipo = tipo;
    }

    public Trofeo (String nome, String data,  String squadra, String tipo) {
        this.nome = nome;
        this.data = data;
        this.squadra = squadra;
        this.tipo = tipo;
    }

    public String getNome() {return nome;}
    public String getData() {return data;}
    public String getSquadra() {return squadra;}
    public String getTipo() {return tipo;}

    public void setNome(String nome) {this.nome=nome;}
    public void setData(String data) {this.data=data;}
    public void setSquadra(String squadra) {this.squadra=squadra;}
    public void setTipo(String tipo) {this.tipo=tipo;}
}
