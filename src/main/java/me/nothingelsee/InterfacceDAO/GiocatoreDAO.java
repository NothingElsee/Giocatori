package me.nothingelsee.InterfacceDAO;

import java.util.ArrayList;
import java.util.Date;

public interface GicatoreDAO {
    void getGiocatoriByNome(String nome,ArrayList<Integer> codiciGiocatori, ArrayList<String> cognomiGiocatori, ArrayList<Date> dateGiocatori);
}
