package me.nothingelsee.GUI;

import me.nothingelsee.Aesthetics.Estetica;
import me.nothingelsee.Controller.Controller;
import me.nothingelsee.Model.Giocatore;
import me.nothingelsee.Model.Militanza;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;

public class LeggiGiocatoreAdmin {

    private Controller controller;
    JFrame frame;
    private Estetica e;
    private JTable storicoSquadreTable;
    private JTextField nomeLabel;
    private JTextField cognomeLabel;
    private JTextField dataNascitaLabel;
    private JTextField dataRitiroLabel;
    private JTextField piedeLabel;
    private JTextField trofeiVintiLabel;
    private JTextField velocitaLabel;
    private JTextField tiroLabel;
    private JTextField passaggioLabel;
    private JTextField piedeDeboleLabel;
    private JTextField restistenzaLabel;
    private JTextField difesaLabel;
    private JTextField punizioneLabel;
    private JTextField taccoLabel;
    private JTextField rovesciataLabel;
    private JTextField testaLabel;
    private JTextField dribblingLabel;
    private JTextField sforbiciataLabel;
    private JTextField controlloLabel;
    private JPanel mainPanel;
    private JTextField ruoliLabel;
    private JButton chiudiButton;
    private JButton apriButton;
    private JPanel generaliPanel;
    private JPanel abilitaPanel;
    private JPanel skillPanel;
    private JPanel squadrePanel;
    private JPanel buttonPanel;
    private JPanel topPanel;
    private JPanel middlePanel;
    private JScrollPane squadreScrollPanel;
    private JButton trofeiButton;
    private JPopupMenu popupSquadre;
    private JMenuItem visionaPopupSquadre;
    private JMenuItem annullaPopupSquadre;

    public LeggiGiocatoreAdmin(Controller controller, JFrame frameChiamante) {
        this.controller = controller;
        inizializzaComponenti(frameChiamante);
        impostaBackground();
        caricaDati();
        implementaListeners();
    }

    public void inizializzaComponenti(JFrame frameChiamante) {
        frame = new JFrame("Informazioni Giocatore");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setSize(1000, 600);

        popupSquadre = new JPopupMenu("Squadre");
        visionaPopupSquadre = new JMenuItem("Visualizza");
        annullaPopupSquadre = new JMenuItem("Annulla");

        popupSquadre.add(visionaPopupSquadre);
        popupSquadre.add(annullaPopupSquadre);

        Estetica.setMenuItemColor(visionaPopupSquadre);
        Estetica.setMenuItemColor(annullaPopupSquadre);

        Estetica.setButtonColor(trofeiButton);
        Estetica.setButtonColor(apriButton);
        Estetica.setButtonColor(chiudiButton);
        apriButton.setEnabled(false);

        storicoSquadreTable.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{
                        "Nome", "Nazionalità", "Data Inizio", "Data Fine"
                }
        ));
        storicoSquadreTable.setFillsViewportHeight(true);

        Estetica.setHeaderTable(storicoSquadreTable);
    }

    public void impostaBackground() {

        //BorderLayout e Margini MainPanel
        mainPanel.setBackground(Estetica.mainBackgroundColor);
        //BorderLayout e Margini GeneraliPanel
        generaliPanel.setBackground(Estetica.filterBackgorundColor);
        //BorderLayout e Margini AbilitaPanel
        abilitaPanel.setBackground(Estetica.filterBackgorundColor);
        //BorderLayout e Margini SkillPanel
        skillPanel.setBackground(Estetica.filterBackgorundColor);

        squadreScrollPanel.setBackground(Estetica.filterBackgorundColor);

        buttonPanel.setBackground(Estetica.filterBackgorundColor);


    }

    public void caricaDati() {

        nomeLabel.setText(controller.getGiocatoreCercato().getNome());
        cognomeLabel.setText(controller.getGiocatoreCercato().getCognome());
        dataNascitaLabel.setText(controller.getGiocatoreCercato().getDataNascita());
        dataRitiroLabel.setText(controller.getGiocatoreCercato().getDataRitiro());
        piedeLabel.setText(controller.getGiocatoreCercato().getPiede().toString());
        controller.getAbilita(controller.getGiocatoreCercato());
        controller.getSkill(controller.getGiocatoreCercato());
        controller.getRuoli(controller.getGiocatoreCercato());
        controller.getTrofei(controller.getGiocatoreCercato());
        caricaAbilità(controller.getGiocatoreCercato());
        caricaSkill(controller.getGiocatoreCercato());
        ruoliLabel.setText(controller.getGiocatoreCercato().getRuoliString());
        trofeiVintiLabel.setText(String.valueOf(controller.getGiocatoreCercato().getNumTrofei()));

        controller.getMilitanze(controller.getGiocatoreCercato());
        DefaultTableModel model = (DefaultTableModel) storicoSquadreTable.getModel();
        ArrayList<Militanza> militanze = controller.getMilitanzeDaGiocatore();
        for (Militanza m : militanze) {
            model.addRow(new Object[]{m.getSquadra().getNome(), m.getSquadra().getNazionalita(), m.getDataInizio(), m.getDataFine()});
        }
    }

    public void implementaListeners() {

        chiudiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        storicoSquadreTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                super.mouseClicked(e);

                if (e.getButton() == MouseEvent.BUTTON1) {

                    int row = storicoSquadreTable.getSelectedRow();
                    controller.setMilitanzaCercata(controller.getMilitanzeDaGiocatore().get(row));
                    apriButton.setEnabled(true);

                } else if (e.getButton() == MouseEvent.BUTTON3) {

                    popupSquadre.show(storicoSquadreTable, e.getX(), e.getY());

                    int r = storicoSquadreTable.rowAtPoint(e.getPoint());
                    if (r >= 0 && r < storicoSquadreTable.getRowCount()) {
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

        trofeiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LeggiTrofeiGuest trofeiVis = new LeggiTrofeiGuest(controller, frame);
            }
        });

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                super.windowClosed(e);
            }
        });
    }

    private void caricaAbilità(Giocatore giocatore) {
        HashMap<String, Integer> abilita = giocatore.getAbilita();

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

    private void visualizzaSquadra() {

        LeggiPartiteGuest partiteVis = new LeggiPartiteGuest(controller, frame);
        partiteVis.frame.setVisible(true);
    }
}