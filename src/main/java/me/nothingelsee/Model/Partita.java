package me.nothingelsee.Model;

import me.nothingelsee.ENUM.COMPETIZIONE;

public class Partita {

    private String squadraCasa;
    private String squadraTrasferta;
    private int goalCasa;
    private int goalTrasferta;
    private String data;
    private COMPETIZIONE competizine;
    private Statistiche stat;

    public Partita(String squadraCasa, String squadraTrasferta, int goalCasa, int goalTrasferta, String data){
        this.squadraCasa = squadraCasa;
        this.squadraTrasferta = squadraTrasferta;
        this.goalCasa = goalCasa;
        this.goalTrasferta = goalTrasferta;
        this.data = data;
    }

    public String getSquadraCasa(){return squadraCasa;}
    public String getSquadraTrasferta(){return squadraTrasferta;}
    public int getGoalCasa(){return goalCasa;}
    public int getGoalTrasferta(){return goalTrasferta;}
    public String getData(){return data;}
    public String getCompetizione() {return competizine.toString();}
    public Statistiche getStat(){return stat;}

    public void setSquadraCasa (String squadraCasa){
        this.squadraCasa = squadraCasa;
    }
    public void setSquadraTrasferta (String squadraTrasferta){
        this.squadraTrasferta = squadraTrasferta;
    }
    public void setGoalCasa (int goalCasa){
        this.goalCasa = goalCasa;
    }
    public void setGoalTrasferta (int goalTrasferta){
        this.goalTrasferta = goalTrasferta;
    }
    public void setData (String data){
        this.data = data;
    }
    public void setCompetizine(COMPETIZIONE competizine){ this.competizine=competizine;}
    public void setStat (Statistiche stat){this.stat=stat;}
}
