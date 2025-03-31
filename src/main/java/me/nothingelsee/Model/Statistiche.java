package me.nothingelsee.Model;

/**
 * The type Statistiche.
 */
public class Statistiche {
    private int goal;
    private int assist;
    private int cartelliniRossi;
    private int cartelliniGialli;
    private int rigoriSegnati;
    private int goalSubiti;
    private int numParate;

    /**
     * Instantiates a new Statistiche.
     *
     * @param goal             the goal
     * @param assist           the assist
     * @param cartelliniRossi  the cartellini rossi
     * @param cartelliniGialli the cartellini gialli
     * @param rigoriSegnati    the rigori segnati
     * @param goalSubiti       the goal subiti
     * @param numParate        the num parate
     */
    public Statistiche(int goal, int assist, int cartelliniRossi, int cartelliniGialli, int rigoriSegnati, int goalSubiti, int numParate) {
        this.goal = goal;
        this.assist = assist;
        this.cartelliniRossi = cartelliniRossi;
        this.cartelliniGialli = cartelliniGialli;
        this.rigoriSegnati = rigoriSegnati;
        this.goalSubiti = goalSubiti;
        this.numParate = numParate;
    }

    /**
     * Gets goal.
     *
     * @return the goal
     */
    public int getGoal() {
        return goal;
    }

    /**
     * Gets assist.
     *
     * @return the assist
     */
    public int getAssist() {
        return assist;
    }

    /**
     * Gets cartellini rossi.
     *
     * @return the cartellini rossi
     */
    public int getCartelliniRossi() {
        return cartelliniRossi;
    }

    /**
     * Gets cartellini gialli.
     *
     * @return the cartellini gialli
     */
    public int getCartelliniGialli() {
        return cartelliniGialli;
    }

    /**
     * Gets rigori segnati.
     *
     * @return the rigori segnati
     */
    public int getRigoriSegnati() {
        return rigoriSegnati;
    }

    /**
     * Gets goal subiti.
     *
     * @return the goal subiti
     */
    public int getGoalSubiti() {
        return goalSubiti;
    }

    /**
     * Gets num parate.
     *
     * @return the num parate
     */
    public int getNumParate() {
        return numParate;
    }

    /**
     * Sets goal.
     *
     * @param goal the goal
     */
    public void setGoal(int goal) {
        this.goal = goal;
    }

    /**
     * Sets assist.
     *
     * @param assist the assist
     */
    public void setAssist(int assist) {
        this.assist = assist;
    }

    /**
     * Sets cartellini rossi.
     *
     * @param cartelliniRossi the cartellini rossi
     */
    public void setCartelliniRossi(int cartelliniRossi) {
        this.cartelliniRossi = cartelliniRossi;
    }

    /**
     * Sets cartellini gialli.
     *
     * @param cartelliniGialli the cartellini gialli
     */
    public void setCartelliniGialli(int cartelliniGialli) {
        this.cartelliniGialli = cartelliniGialli;
    }

    /**
     * Sets rigori segnati.
     *
     * @param rigoriSegnati the rigori segnati
     */
    public void setRigoriSegnati(int rigoriSegnati) {
        this.rigoriSegnati = rigoriSegnati;
    }

    /**
     * Sets goal subiti.
     *
     * @param goalSubiti the goal subiti
     */
    public void setGoalSubiti(int goalSubiti) {
        this.goalSubiti = goalSubiti;
    }

    /**
     * Sets num parate.
     *
     * @param numParate the num parate
     */
    public void setNumParate(int numParate) {
        this.numParate = numParate;
    }
}
