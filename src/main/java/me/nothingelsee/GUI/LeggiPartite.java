package me.nothingelsee.GUI;

import me.nothingelsee.Controller.Controller;
import me.nothingelsee.Model.Partita;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LeggiPartite {

    JFrame frame;
    private JPanel panel1;
    private JLabel nomeGiocatoreLabel;
    private JLabel nomeSquadraLabel;
    private JLabel datInizioLabel;
    private JLabel dataFineLabel;
    private JTable partiteTable;
    private JPanel risultatoPanel;
    private JLabel risultatoLabel;
    private JLabel numeroParateLabel;
    private JLabel goalSubitiLabel;
    private JLabel goalLabel;
    private JLabel assistLabel;
    private JLabel rossiLabel;
    private JLabel gialliLabel;
    private JButton chiudiButton;
    private JButton selezionaButton;
    private JLabel rigoriSegnatiLabel;

    public LeggiPartite(Controller controller, JFrame frameChiamante) {

        frame = new JFrame( "Statistiche Partite");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(frameChiamante);
        frame.setSize(1000, 600);

        nomeGiocatoreLabel.setText(controller.getGiocatoreCercato().getNome() + " " + controller.getGiocatoreCercato().getCognome());
        nomeSquadraLabel.setText(String.valueOf(controller.getMilitanzaCercata().getSquadra().getNome()));
        datInizioLabel.setText("Data Inizio: " + controller.getMilitanzaCercata().getDataInizio());
        dataFineLabel.setText("Data Fine: " + controller.getMilitanzaCercata().getDataFine());

        partiteTable.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{
                        "Avversario", "Data"
        }));


        controller.getPartite(controller.getMilitanzaCercata());
        DefaultTableModel model = (DefaultTableModel) partiteTable.getModel();
        ArrayList<Partita> partite = controller.getMilitanzaCercata().getPartite();
        String avversario = "";
        for(Partita p : partite){
            avversario = p.getSquadraCasa().equals(controller.getMilitanzaCercata().getSquadra()) ? p.getSquadraTrasferta() : p.getSquadraCasa();
            model.addRow(new Object[]{avversario ,p.getData()});
        }

        selezionaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(partiteTable.getSelectedRow()>=0 && partiteTable.getSelectedRow()<partite.size()){

                    Partita p = partite.get(partiteTable.getSelectedRow());

                    controller.getStat(p);

                    risultatoLabel.setText(p.getSquadraCasa() + " " + p.getGoalCasa()+"-"+p.getGoalTrasferta() + " " + p.getSquadraTrasferta());
                    goalLabel.setText("Goal: " + String.valueOf(p.getStat().getGoal()));
                    assistLabel.setText("Assist: " + String.valueOf(p.getStat().getAssist()));
                    rossiLabel.setText("Cartellini Rossi: " + String.valueOf(p.getStat().getCartelliniRossi()));
                    gialliLabel.setText("Cartellini Gialli: " + String.valueOf(p.getStat().getCartelliniGialli()));
                    rigoriSegnatiLabel.setText("Rigori Segnati: " + String.valueOf(p.getStat().getRigoriSegnati()));

                    numeroParateLabel.setText("Numero Parate: " + String.valueOf(p.getStat().getNumParate()));
                    goalSubitiLabel.setText("Goal Subiti: " + String.valueOf(p.getStat().getGoalSubiti()));

                }
            }
        });

        chiudiButton.addActionListener(new

            ActionListener() {
                @Override
                public void actionPerformed (ActionEvent e){
                    frame.dispose();
                }
            });
    }
}
