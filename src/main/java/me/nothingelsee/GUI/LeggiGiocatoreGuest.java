package me.nothingelsee.GUI;

import me.nothingelsee.Aesthetics.Estetica;
import me.nothingelsee.Controller.Controller;
import me.nothingelsee.Model.Giocatore;
import me.nothingelsee.Model.Militanza;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.ArrayList;
public class LeggiGiocatoreGuest {

    private Controller controller;

    JFrame frame;
    private JFrame frameChiamante;
    private Estetica e;
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
    private JPanel mainPanel;
    private JLabel ruoliLabel;
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

    public LeggiGiocatoreGuest(Controller controller, JFrame frameChiamante) {
        this.controller = controller;
        inizializzaComponenti(frameChiamante);
        impostaBackground();
        caricaDati();
        implementaListeners();
    }

    public void inizializzaComponenti(JFrame frameChiamante) {
        this.frameChiamante = frameChiamante;
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
                LeggiTrofei trofeiVis = new LeggiTrofei(controller, frame, false);
            }
        });
    }

    private void caricaAbilità(Giocatore giocatore) {
        ArrayList<Integer> abilita = giocatore.getAbilita();

        velocitaLabel.setText(abilita.get(0).toString());
        tiroLabel.setText(abilita.get(1).toString());
        passaggioLabel.setText(abilita.get(2).toString());
        piedeDeboleLabel.setText(abilita.get(3).toString());
        restistenzaLabel.setText(abilita.get(4).toString());
        difesaLabel.setText(abilita.get(5).toString());
        punizioneLabel.setText(abilita.get(6).toString());
    }

    private void caricaSkill(Giocatore giocatore) {
        ArrayList<Integer> skill = giocatore.getSkill();

        taccoLabel.setText(skill.get(0).toString());
        rovesciataLabel.setText(skill.get(1).toString());
        testaLabel.setText(skill.get(2).toString());
        dribblingLabel.setText(skill.get(3).toString());
        sforbiciataLabel.setText(skill.get(4).toString());
        controlloLabel.setText(skill.get(5).toString());
    }

    private void visualizzaSquadra() {

        LeggiPartiteGuest partiteVis = new LeggiPartiteGuest(controller, frame);
        partiteVis.frame.setVisible(true);
    }
}