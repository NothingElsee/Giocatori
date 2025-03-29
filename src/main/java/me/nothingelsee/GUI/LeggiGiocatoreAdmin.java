package me.nothingelsee.GUI;

import me.nothingelsee.Aesthetics.Estetica;
import me.nothingelsee.Controller.Controller;
import me.nothingelsee.ENUM.PIEDE;
import me.nothingelsee.ENUM.RUOLO;
import me.nothingelsee.Model.Giocatore;
import me.nothingelsee.Model.Militanza;
import raven.datetime.DatePicker;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class LeggiGiocatoreAdmin {

    private Controller controller;
    JFrame frame;
    private JFrame frameChiamante;
    private JTable storicoSquadreTable;
    private JTextField nomeText;
    private JTextField cognomeText;
    private JFormattedTextField dataNascitaText;
    private JFormattedTextField dataRitiroText;
    private JTextField trofeiVintiText;
    private JTextField velocitaText;
    private JTextField tiroText;
    private JTextField passaggioText;
    private JTextField piedeDeboleText;
    private JTextField restistenzaText;
    private JTextField difesaText;
    private JTextField punizioneText;
    private JTextField taccoText;
    private JTextField rovesciataText;
    private JTextField testaText;
    private JTextField dribblingText;
    private JTextField sforbiciataText;
    private JTextField controlloText;
    private JPanel mainPanel;
    private JButton chiudiButton;
    private JPanel generaliPanel;
    private JPanel abilitaPanel;
    private JPanel skillPanel;
    private JPanel squadrePanel;
    private JPanel buttonPanel;
    private JPanel topPanel;
    private JPanel middlePanel;
    private JScrollPane squadreScrollPanel;
    private JButton trofeiButton;
    private JComboBox nazionalitaBox;
    private JComboBox piedeBox;
    private JList ruoliList;
    private JPanel squadraPanel;
    private JButton aggiungiSquadra;
    private JButton modificaButton;
    private JButton caricaButton;
    private JButton eliminaButton;
    private JButton eliminaMilitanzaButton;
    private JPopupMenu popupSquadre;
    private JMenuItem eliminaPopup;
    private JMenuItem modificaPopup;
    private JMenuItem annullaPopupSquadre;
    private DatePicker dataNascita;
    private DatePicker dataRitiro;
    private Giocatore giocatore = null;

    public LeggiGiocatoreAdmin(Controller controller, JFrame frameChiamante) {
        inizializzaComponenti(frameChiamante, controller);
        impostaEstetica();
        implementaListeners();
    }

    public void inizializzaComponenti(JFrame frameChiamante, Controller controller) {
        this.controller = controller;
        this.frameChiamante = frameChiamante;
        this.giocatore = controller.getGiocatoreCercato();
        frame = new JFrame("Informazioni Giocatore");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setSize(1000, 600);

        popupSquadre = new JPopupMenu("Squadre");
        modificaPopup = new JMenuItem("Modifica");
        eliminaPopup = new JMenuItem("Elimina");
        annullaPopupSquadre = new JMenuItem("Annulla");

        popupSquadre.add(eliminaPopup);
        popupSquadre.add(modificaPopup);
        popupSquadre.addSeparator();
        popupSquadre.add(annullaPopupSquadre);

        dataNascita = new DatePicker();
        dataRitiro = new DatePicker();

        dataNascita.setEditor(dataNascitaText);
        dataRitiro.setEditor(dataRitiroText);
        eliminaMilitanzaButton.setEnabled(false);
        modificaButton.setEnabled(false);
        eliminaButton.setEnabled(false);
        dataNascita.setDateSelectionMode(DatePicker.DateSelectionMode.SINGLE_DATE_SELECTED);
        dataRitiro.setDateSelectionMode(DatePicker.DateSelectionMode.SINGLE_DATE_SELECTED);

        ArrayList<String> nazioni = new ArrayList<>();
        controller.getNazionalita(nazioni);
        for (String s : nazioni) {
            nazionalitaBox.addItem(s);
        }

        DefaultListModel listModel = (DefaultListModel) ruoliList.getModel();
        for (RUOLO r : RUOLO.values()) {
            listModel.addElement(r.toString());
        }
        ruoliList.setModel(listModel);

        for (PIEDE p : PIEDE.values()) {
            piedeBox.addItem(p);
        }

        storicoSquadreTable.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{
                        "Nome", "Nazionalità", "Data Inizio", "Data Fine"
                }
        ));
        storicoSquadreTable.setFillsViewportHeight(true);

        if (giocatore != null) {
            caricaDati();
            eliminaButton.setEnabled(true);
        }

        frame.setVisible(true);
    }

    public void impostaEstetica() {

        mainPanel.setBackground(Estetica.mainBackgroundColor);
        generaliPanel.setBackground(Estetica.filterBackgorundColor);
        abilitaPanel.setBackground(Estetica.filterBackgorundColor);
        skillPanel.setBackground(Estetica.filterBackgorundColor);
        squadreScrollPanel.setBackground(Estetica.filterBackgorundColor);
        buttonPanel.setBackground(Estetica.filterBackgorundColor);

        Estetica.setMenuItemColor(modificaPopup);
        Estetica.setMenuItemColor(annullaPopupSquadre);
        Estetica.setButtonColor(trofeiButton);
        Estetica.setButtonColor(chiudiButton);
        Estetica.setButtonColor(modificaButton);
        Estetica.setButtonColor(aggiungiSquadra);

        Estetica.setHeaderTable(storicoSquadreTable);
    }


    private void implementaListeners() {

        storicoSquadreTable.addMouseListener(new MouseAdapter() {


            @Override
            public void mouseClicked(MouseEvent e) {

                super.mouseClicked(e);

                if (e.getButton() == MouseEvent.BUTTON1) {

                    int row = storicoSquadreTable.getSelectedRow();
                    if (row >= 0 && row < storicoSquadreTable.getRowCount()) {
                        controller.setMilitanzaCercata(controller.getMilitanzeDaGiocatore().get(row));
                        modificaButton.setEnabled(true);
                        eliminaMilitanzaButton.setEnabled(true);
                    }

                } else if (e.getButton() == MouseEvent.BUTTON3) {

                    popupSquadre.show(storicoSquadreTable, e.getX(), e.getY());

                    int r = storicoSquadreTable.rowAtPoint(e.getPoint());
                    if (r >= 0 && r < storicoSquadreTable.getRowCount()) {
                        storicoSquadreTable.setRowSelectionInterval(r, r);
                        int row = storicoSquadreTable.getSelectedRow();
                        controller.setMilitanzaCercata(controller.getMilitanzeDaGiocatore().get(row));
                        modificaButton.setEnabled(true);
                        eliminaMilitanzaButton.setEnabled(true);
                    } else {
                        storicoSquadreTable.clearSelection();
                    }
                }
            }
        });

        aggiungiSquadra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                creaGiocatore();
                if (giocatore != null) {
                    controller.setMilitanzaCercata(null);
                    visualizzaSquadra();
                }
            }
        });
        modificaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                creaGiocatore();
                if(giocatore != null)visualizzaSquadra();
            }
        });

        eliminaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.deleteGiocatore(giocatore);
                controller.setGiocatoreCercato(null);
            }
        });

        eliminaPopup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.deleteMilitanza(giocatore.getMilitanze().get(storicoSquadreTable.getSelectedRow()));
                giocatore.getMilitanze().remove(storicoSquadreTable.getSelectedRow());
            }
        });

        eliminaMilitanzaButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               controller.deleteMilitanza(controller.getMilitanzaCercata());
               eliminaMilitanzaButton.setEnabled(false);
               modificaButton.setEnabled(false);
               aggiornaTable();
           }
        });

        caricaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(giocatore == null) creaGiocatore();
                else controller.updateGiocatore(giocatore);
            }
        });

        chiudiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                frameChiamante.setVisible(true);
            }
        });

        trofeiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                creaGiocatore();
                LeggiTrofei trofeiVis = new LeggiTrofei(controller, frame, true);
                frame.setVisible(false);
            }
        });

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                super.windowClosed(e);
                frameChiamante.setVisible(true);
            }
        });
    }

    private void aggiornaTable() {
        DefaultTableModel model = (DefaultTableModel) storicoSquadreTable.getModel();
        ArrayList<Militanza> militanze = giocatore.getMilitanze();
        for (Militanza m : militanze) {
            model.addRow(new Object[]{m.getSquadra().getNome(), m.getSquadra().getNazionalita(), m.getDataInizio(), m.getDataFine()});
        }
    }

    public void caricaDati() {

        nomeText.setText(giocatore.getNome());
        cognomeText.setText(giocatore.getCognome());
        dataNascita.setSelectedDate(LocalDate.parse(giocatore.getDataNascita()));
        nazionalitaBox.setSelectedItem(giocatore.getNazionalita());
        dataRitiro.setSelectedDate(LocalDate.parse(giocatore.getDataRitiro()));
        piedeBox.setSelectedItem(giocatore.getPiede().toString());
        controller.getAbilita(giocatore);
        controller.getSkill(giocatore);
        controller.getRuoli(giocatore);
        controller.getTrofei(giocatore);
        caricaAbilita(giocatore);
        caricaSkill(giocatore);
        DefaultListModel modelList = (DefaultListModel) ruoliList.getModel();
        modelList.addElement(giocatore.getRuoli());
        trofeiVintiText.setText(String.valueOf(giocatore.getNumTrofei()));

        controller.getMilitanze(giocatore);
        DefaultTableModel model = (DefaultTableModel) storicoSquadreTable.getModel();
        ArrayList<Militanza> militanze = controller.getMilitanzeDaGiocatore();
        for (Militanza m : militanze) {
            model.addRow(new Object[]{m.getSquadra().getNome(), m.getSquadra().getNazionalita(), m.getDataInizio(), m.getDataFine()});
        }
    }

    private void caricaAbilita(Giocatore giocatore) {
        ArrayList<Integer> abilita = giocatore.getAbilita();

        velocitaText.setText(abilita.get(0).toString());
        tiroText.setText(abilita.get(1).toString());
        passaggioText.setText(abilita.get(2).toString());
        piedeDeboleText.setText(abilita.get(3).toString());
        restistenzaText.setText(abilita.get(4).toString());
        difesaText.setText(abilita.get(5).toString());
        punizioneText.setText(abilita.get(6).toString());
    }

    private void caricaSkill(Giocatore giocatore) {
        ArrayList<Integer> skill = giocatore.getSkill();

        taccoText.setText(skill.get(0).toString());
        rovesciataText.setText(skill.get(1).toString());
        testaText.setText(skill.get(2).toString());
        dribblingText.setText(skill.get(3).toString());
        sforbiciataText.setText(skill.get(4).toString());
        controlloText.setText(skill.get(5).toString());
    }

    private void visualizzaSquadra() {

        LeggiPartiteAdmin partiteVis = new LeggiPartiteAdmin(frame, controller);
    }

    private boolean checkGenerali() {

        if (nomeText.getText().isEmpty() || cognomeText.getText().isEmpty() || !dataNascita.isDateSelected() ||
                nazionalitaBox.getSelectedItem().equals("") || piedeBox.getSelectedItem().equals("")) {

            JOptionPane.showMessageDialog(null, "Dati giocatore mancanti");
            return false;
        }

        return true;
    }

    private boolean checkSkills() {
        if (taccoText.getText().isEmpty() || rovesciataText.getText().isEmpty() || testaText.getText().isEmpty() || dribblingText.getText().isEmpty() ||
                sforbiciataText.getText().isEmpty() || controlloText.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Skill giocatore mancanti");
            return false;
        }
        return true;
    }

    private boolean checkAbilita() {
        if (velocitaText.getText().isEmpty() || tiroText.getText().isEmpty() || passaggioText.getText().isEmpty() ||
                piedeDeboleText.getText().isEmpty() || restistenzaText.getText().isEmpty() || difesaText.getText().isEmpty() || punizioneText.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Abilità giocatore mancanti");
            return false;
        }
        return true;
    }

    private boolean checkRuoli() {
        if (ruoliList.getSelectedValuesList().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ruoli giocatore mancanti");
            return false;
        }
        return true;
    }

    private void creaGiocatore() {
        if (checkGenerali() && checkSkills() && checkAbilita() && checkRuoli()) {
            ArrayList<Integer> skills = new ArrayList<>();
            ArrayList<Integer> abilita = new ArrayList<>();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            skills.add(Integer.valueOf(taccoText.getText()));
            skills.add(Integer.valueOf(rovesciataText.getText()));
            skills.add(Integer.valueOf(testaText.getText()));
            skills.add(Integer.valueOf(dribblingText.getText()));
            skills.add(Integer.valueOf(sforbiciataText.getText()));
            skills.add(Integer.valueOf(controlloText.getText()));

            abilita.add(Integer.valueOf(velocitaText.getText()));
            abilita.add(Integer.valueOf(tiroText.getText()));
            abilita.add(Integer.valueOf(passaggioText.getText()));
            abilita.add(Integer.valueOf(piedeDeboleText.getText()));
            abilita.add(Integer.valueOf(restistenzaText.getText()));
            abilita.add(Integer.valueOf(difesaText.getText()));
            abilita.add(Integer.valueOf(punizioneText.getText()));

            ArrayList<RUOLO> ruoli = new ArrayList<>();
            ruoli.addAll((List<RUOLO>) ruoliList.getSelectedValuesList());

            if (giocatore == null) {
                giocatore = new Giocatore(nomeText.getText(), cognomeText.getText(), dtf.format(dataNascita.getSelectedDate()), nazionalitaBox.getSelectedItem().toString(),
                        dtf.format(dataRitiro.getSelectedDate()), (PIEDE) piedeBox.getSelectedItem(), skills, abilita, ruoli);
                controller.caricaGiocatore(giocatore);
            } else {
                giocatore.setNome(nomeText.getText());
                giocatore.setCognome(cognomeText.getText());
                giocatore.setDataNascita(dtf.format(dataNascita.getSelectedDate()));
                giocatore.setNazionalita(nazionalitaBox.getSelectedItem().toString());
                giocatore.setDataRitiro(dtf.format(dataRitiro.getSelectedDate()));
                giocatore.setPiede(PIEDE.valueOf(piedeDeboleText.getText()));
                System.out.println(piedeDeboleText.getText());
                giocatore.setSkills(skills);
                giocatore.setAbilita(abilita);
                giocatore.setRuoli(ruoli);
            }
            controller.setGiocatoreCercato(giocatore);
        } else {
            JOptionPane.showMessageDialog(null, "Attributi giocatore mancanti o errati!", "Errore", JOptionPane.ERROR_MESSAGE);
        }
    }
}