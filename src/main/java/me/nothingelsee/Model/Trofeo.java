package me.nothingelsee.Model;

public class Trofeo {
    private String nome;
    private String data;

    public Trofeo (String nome, String data){
        this.nome = nome;
        this.data = data;
    }

    public String getNome() {return nome;}
    public String getData() {return data;}

    public void setNome(String nome) {this.nome=nome;}
    public void setData(String data) {this.data=data;}
}
