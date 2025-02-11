package me.nothingelsee.GUI;

import me.nothingelsee.Controller.Controller;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

public class TestGetGiocatori {

    private static JFrame frame;
    private JTextField nomeGiocatore;
    private JButton cercaButton;
    private JTable giocatoriTable;
    private JPanel panel;

    private Controller controller;

    public TestGetGiocatori() {
        controller = new Controller();

        giocatoriTable.setModel(new DefaultTableModel(
                new Object[][] {
                },
                new String[] {
                        "Nome", "Cognome", "Data di Nascita'"
                }
        ));

        DefaultTableModel model = (DefaultTableModel) giocatoriTable.getModel();
        ArrayList<Integer> codiciGiocatore = new ArrayList<>();
        ArrayList<String> cognomiGiocatore = new ArrayList<>();
        ArrayList<Date> dataGiocatori = new ArrayList<>();

        cercaButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed (ActionEvent e) {
                if(nomeGiocatore.getText()!=null){
                    codiciGiocatore.clear();
                    cognomiGiocatore.clear();
                    dataGiocatori.clear();
                    model.setRowCount(0);
                    String nome = capitalizeFirstLetter(nomeGiocatore.getText());
                    controller.getGiocatoriByName(nome, codiciGiocatore, cognomiGiocatore, dataGiocatori);
                    if(codiciGiocatore!=null) {
                        for (int i = 0; i < codiciGiocatore.size(); i++){
                            model.addRow(new Object[]{nome, cognomiGiocatore.get(i), dataGiocatori.get(i)});
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Inserisci un giocatore prima!!");
                    }
                }
            }

        });




    }

    public static void main(String[] args) {
        frame = new JFrame("Cerca Giocatori");
        frame.setContentPane(new TestGetGiocatori().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public String capitalizeFirstLetter(String s){
        return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
    }
}
