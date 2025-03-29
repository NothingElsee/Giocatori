package me.nothingelsee.InterfacceDAO;

import me.nothingelsee.Model.Giocatore;

import java.util.ArrayList;

public interface SkillDAO {
    void getSkills(Giocatore giocatore);
    void insertSkill(ArrayList<Integer> skill);
    void updateSkill(int idGiocatore, ArrayList<Integer> skill);
}
