package me.nothingelsee.InterfacceDAO;

import me.nothingelsee.Model.Giocatore;

import java.util.ArrayList;

/**
 * The interface Skill dao.
 */
public interface SkillDAO {
    /**
     * Gets skills.
     *
     * @param giocatore the giocatore
     */
    void getSkills(Giocatore giocatore);

    /**
     * Insert skill boolean.
     *
     * @param idGiocatore the id giocatore
     * @param skill       the skill
     * @return the boolean
     */
    boolean insertSkill(int idGiocatore, ArrayList<Integer> skill);

    /**
     * Update skill.
     *
     * @param idGiocatore the id giocatore
     * @param skill       the skill
     */
    void updateSkill(int idGiocatore, ArrayList<Integer> skill);
}
