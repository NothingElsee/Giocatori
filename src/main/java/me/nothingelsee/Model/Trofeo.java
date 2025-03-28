package me.nothingelsee.Model;

public class Trofeo {
    private int idTrofeo;
    private String nome;
    private String data;
    private String squadra;
    private String tipo;

    public Trofeo (int idTrofeo, String nome, String data, String tipo) {
        this.idTrofeo = idTrofeo;
        this.nome = nome;
        this.data = data;
        this.tipo = tipo;
    }

    public Trofeo (int idTrofeo, String nome, String data,  String squadra, String tipo) {
        this.idTrofeo = idTrofeo;
        this.nome = nome;
        this.data = data;
        this.squadra = squadra;
        this.tipo = tipo;
    }

    public int getId() {return idTrofeo;}
    public String getNome() {return nome;}
    public String getData() {return data;}
    public String getSquadra() {return squadra;}
    public String getTipo() {return tipo;}

    public void setId(int idTrofeo) {this.idTrofeo = idTrofeo;}
    public void setNome(String nome) {this.nome=nome;}
    public void setData(String data) {this.data=data;}
    public void setSquadra(String squadra) {this.squadra=squadra;}
    public void setTipo(String tipo) {this.tipo=tipo;}
}
