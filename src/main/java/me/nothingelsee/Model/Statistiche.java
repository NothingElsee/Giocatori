package me.nothingelsee.Model;

public class Statistiche {
    private int goal;
    private int assist;
    private int cartelliniRossi;
    private int cartelliniGialli;
    private int rigoriSegnati;
    private int goalSubiti;
    private int numParate;

    public Statistiche(int goal, int assist, int cartelliniRossi, int cartelliniGialli, int rigoriSegnati, int goalSubiti, int numParate) {
        this.goal = goal;
        this.assist = assist;
        this.cartelliniRossi = cartelliniRossi;
        this.cartelliniGialli = cartelliniGialli;
        this.rigoriSegnati = rigoriSegnati;
        this.goalSubiti = goalSubiti;
        this.numParate = numParate;
    }

    public int getGoal() {
        return goal;
    }

    public int getAssist() {
        return assist;
    }

    public int getCartelliniRossi() {
        return cartelliniRossi;
    }

    public int getCartelliniGialli() {
        return cartelliniGialli;
    }

    public int getRigoriSegnati() {
        return rigoriSegnati;
    }

    public int getGoalSubiti() {
        return goalSubiti;
    }

    public int getNumParate() {
        return numParate;
    }

    public void setGoal(int goal) {
        this.goal = goal;
    }

    public void setAssist(int assist) {
        this.assist = assist;
    }

    public void setCartelliniRossi(int cartelliniRossi) {
        this.cartelliniRossi = cartelliniRossi;
    }

    public void setCartelliniGialli(int cartelliniGialli) {
        this.cartelliniGialli = cartelliniGialli;
    }

    public void setRigoriSegnati(int rigoriSegnati) {
        this.rigoriSegnati = rigoriSegnati;
    }

    public void setGoalSubiti(int goalSubiti) {
        this.goalSubiti = goalSubiti;
    }

    public void setNumParate(int numParate) {
        this.numParate = numParate;
    }
}
