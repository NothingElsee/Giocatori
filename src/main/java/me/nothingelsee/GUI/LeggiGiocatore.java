package me.nothingelsee.GUI;

import me.nothingelsee.Controller.Controller;
import me.nothingelsee.Model.Giocatore;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class LeggiGiocatore {

    JFrame frame;
    private JTable storicoPartiteTable;
    private JLabel nomeLabel;
    private JLabel cognomeLabel;
    private JLabel dataNascitaLabel;
    private JLabel dataRitiroLabel;
    private JLabel piedeLabel;
    private JLabel trofeiVintiLabel;
    private JLabel velocitaLabel;
    private JLabel tiroLabel;
    private JLabel passaggioLabel;
    private JLabel piedeDeboleLabel;
    private JLabel restistenzaLabel;
    private JLabel difesaLabel;
    private JLabel punizioneLabel;
    private JLabel taccoLabel;
    private JLabel rovesciataLabel;
    private JLabel testaLabel;
    private JLabel dribblingLabel;
    private JLabel sforbiciataLabel;
    private JLabel controlloLabel;
    private JPanel panel;
    private JLabel ruoliLabel;
    private JButton chiudiButton;


    public LeggiGiocatore(Controller controller, Frame frameChiamante, Giocatore giocatore) {

        frame = new JFrame("Visualizza Giocatore");
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(frameChiamante);
        frame.setSize(1000, 600);


        nomeLabel.setText(giocatore.getNome());
        cognomeLabel.setText(giocatore.getCognome());
        dataNascitaLabel.setText(giocatore.getDataNascita());
        dataRitiroLabel.setText(giocatore.getDataRitiro());
        piedeLabel.setText(giocatore.getPiede().toString());
        controller.getAbilita(giocatore);
        controller.getSkill(giocatore);
        controller.getRuoli(giocatore);
        caricaAbilità(giocatore);
        caricaSkill(giocatore);
        ruoliLabel.setText(giocatore.getRuoliString());

        chiudiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                frame.dispose();
            }
        });

    }

    private void caricaAbilità(Giocatore giocatore) {
        HashMap<String, Integer> abilita =  giocatore.getAbilita();

        velocitaLabel.setText(String.valueOf(abilita.get("velocità")));
        tiroLabel.setText(abilita.get("tiro").toString());
        passaggioLabel.setText(String.valueOf(abilita.get("passaggio")));
        piedeDeboleLabel.setText(String.valueOf(abilita.get("piededebole")));
        restistenzaLabel.setText(String.valueOf(abilita.get("resistenza")));
        difesaLabel.setText(String.valueOf(abilita.get("difesa")));
        punizioneLabel.setText(String.valueOf(abilita.get("tirosupunizione")));
    }

    private void caricaSkill(Giocatore giocatore) {
        HashMap<String, Integer> skill = giocatore.getSkill();

        taccoLabel.setText(String.valueOf(skill.get("colpoditacco")));
        rovesciataLabel.setText(String.valueOf(skill.get("rovesciata")));
        testaLabel.setText(String.valueOf(skill.get("colpoditesta")));
        dribblingLabel.setText(String.valueOf(skill.get("dribbling")));
        sforbiciataLabel.setText(String.valueOf(skill.get("sforbiciata")));
        controlloLabel.setText(String.valueOf(skill.get("controllopalla")));
    }
}