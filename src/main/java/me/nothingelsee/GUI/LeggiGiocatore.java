package me.nothingelsee.GUI;

import me.nothingelsee.Controller.Controller;
import me.nothingelsee.Model.Giocatore;
import me.nothingelsee.Model.Militanza;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;

public class LeggiGiocatore {

    private Controller controller;

    JFrame frame;
    private JTable storicoSquadreTable;
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
    private JButton apriButton;
    private JPopupMenu popupSquadre;

    public LeggiGiocatore(Controller controller, JFrame frameChiamante) {


        frame = new JFrame("Visualizza Giocatore");
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(frameChiamante);
        frame.setSize(1000, 600);

        popupSquadre = new JPopupMenu("Squadre");
        JMenuItem visionaPopupSquadre = new JMenuItem("Visualizza");
        JMenuItem annullaPopupSquadre = new JMenuItem("Annulla");

        apriButton.setEnabled(false);

        this.controller = controller;

        nomeLabel.setText(controller.getGiocatoreCercato().getNome());
        cognomeLabel.setText(controller.getGiocatoreCercato().getCognome());
        dataNascitaLabel.setText(controller.getGiocatoreCercato().getDataNascita());
        dataRitiroLabel.setText(controller.getGiocatoreCercato().getDataRitiro());
        piedeLabel.setText(controller.getGiocatoreCercato().getPiede().toString());
        controller.getAbilita(controller.getGiocatoreCercato());
        controller.getSkill(controller.getGiocatoreCercato());
        controller.getRuoli(controller.getGiocatoreCercato());
        caricaAbilità(controller.getGiocatoreCercato());
        caricaSkill(controller.getGiocatoreCercato());
        ruoliLabel.setText(controller.getGiocatoreCercato().getRuoliString());

        storicoSquadreTable.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{
                        "Nome", "Nazionalità", "Data Inizio", "Data Fine"
                }
        ));

        controller.getMilitanze(controller.getGiocatoreCercato());
        DefaultTableModel model = (DefaultTableModel) storicoSquadreTable.getModel();
        ArrayList<Militanza> militanze = controller.getMilitanzeDaGiocatore();
        for(Militanza m : militanze){
            model.addRow(new Object[]{m.getSquadra().getNome(), m.getSquadra().getNazionalita(), m.getDataInizio(), m.getDataFine()});
        }

        chiudiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                frame.dispose();
            }
        });

        storicoSquadreTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e){

                super.mouseClicked(e);

                if (e.getButton() == MouseEvent.BUTTON1) {

                    int row = storicoSquadreTable.getSelectedRow();
                    controller.setMilitanzaCercata(controller.getMilitanzeDaGiocatore().get(row));
                    apriButton.setEnabled(true);

                } else if (e.getButton() == MouseEvent.BUTTON3) {

                    popupSquadre.show(storicoSquadreTable, e.getX(), e.getY());

                    int r = storicoSquadreTable.rowAtPoint(e.getPoint());
                    if(r >= 0 && r < storicoSquadreTable.getRowCount()){
                        storicoSquadreTable.setRowSelectionInterval(r, r);
                        int row = storicoSquadreTable.getSelectedRow();
                        controller.setMilitanzaCercata(controller.getMilitanzeDaGiocatore().get(row));
                        apriButton.setEnabled(true);
                    } else {
                        storicoSquadreTable.clearSelection();
                    }

                }
            }
        });

        visionaPopupSquadre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                visualizzaSquadra();
            }
        });

        annullaPopupSquadre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                popupSquadre.hide();
            }
        });

        apriButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                visualizzaSquadra();
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

    private void visualizzaSquadra(){

        LeggiPartite partiteVis = new LeggiPartite(controller, frame);
        controller.setMilitanzaCercata(null);
        partiteVis.frame.setVisible(true);
    }
}