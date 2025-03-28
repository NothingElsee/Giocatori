package me.nothingelsee.GUI;

import me.nothingelsee.Aesthetics.Estetica;
import me.nothingelsee.Controller.Controller;
import me.nothingelsee.Model.Militanza;
import me.nothingelsee.Model.Partita;
import me.nothingelsee.Model.Squadra;
import me.nothingelsee.Model.Statistiche;
import raven.datetime.DatePicker;
import raven.datetime.DateSelectionAble;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class LeggiPartiteAdmin {

    private Controller controller;
    private JFrame frameChiamante;
    JFrame frame;
    private JPanel mainPanel;
    private JPanel topPanel;
    private JLabel nomeGiocatoreLabel;
    private JComboBox nomeSquadraText;
    private JFormattedTextField dataInizioText;
    private JFormattedTextField dataFineText;
    private JPanel middlePanel;
    private JScrollPane partiteScrollPane;
    private JTable partiteTable;
    private JPanel infoPartita;
    private JTextField goalText;
    private JTextField assistText;
    private JTextField rossiText;
    private JTextField rigoriSegnatiText;
    private JTextField numeroParateText;
    private JTextField goalSubitiText;
    private JTextField gialliText;
    private JPanel bottomPanel;
    private JButton selezionaButton;
    private JButton chiudiButton;
    private JButton caricaMilitanza;
    private JPanel paritePanel;
    private JButton aggiungiPartitaButton;
    private JTextField goalCasaText;
    private JTextField goalTrafertText;
    private JComboBox nomeAvversarioText;
    private JFormattedTextField dataPartitaText;
    private JComboBox tipoPartitaBox;
    private JButton modificaButton;
    private JButton resetButton;
    private JButton eliminaButton;
    private DatePicker dataInizioPicker;
    private DatePicker dataFinePicker;
    private DatePicker dataPartitaPicker;
    private JPopupMenu popupMenu;
    private JMenuItem eliminaPopup;
    private JMenuItem modificaPopup;
    private JMenuItem selezionaItem;
    private JMenuItem annullaItem;
    private Militanza militanza = null;

    public LeggiPartiteAdmin(JFrame frame, Controller controller) {

        inizializzaComponenti(frame, controller);
        impostaEstetica();
        implementaListeners();
    }

    private void inizializzaComponenti(JFrame frame, Controller controller) {

        this.controller = controller;
        militanza = controller.getMilitanzaCercata();
        frameChiamante = frame;
        frame = new JFrame("Modifica Militanza");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        popupMenu = new JPopupMenu("Partite");
        eliminaPopup = new JMenuItem("Elimina");
        modificaPopup = new JMenuItem("Modifica");
        selezionaItem = new JMenuItem("Seleziona");
        annullaItem = new JMenuItem("Annulla");

        popupMenu.add(eliminaPopup);
        popupMenu.add(modificaPopup);
        popupMenu.addSeparator();
        popupMenu.add(annullaItem);

        nomeGiocatoreLabel.setText(controller.getGiocatoreCercato().getNome());

        for (String s : controller.getSquadreNomi()) {
            nomeSquadraText.addItem(s);
            nomeAvversarioText.addItem(s);
        }

        modificaButton.setEnabled(false);
        eliminaButton.setEnabled(false);
        selezionaButton.setEnabled(false);
        aggiungiPartitaButton.setEnabled(false);
        infoPartita.setVisible(false);

        dataInizioPicker = new DatePicker();
        dataFinePicker = new DatePicker();
        dataPartitaPicker = new DatePicker();

        dataInizioPicker.setEditor(dataInizioText);
        dataFinePicker.setEditor(dataFineText);
        dataPartitaPicker.setEditor(dataPartitaText);

        dataInizioPicker.setDateSelectionMode(DatePicker.DateSelectionMode.SINGLE_DATE_SELECTED);
        dataFinePicker.setDateSelectionMode(DatePicker.DateSelectionMode.SINGLE_DATE_SELECTED);
        dataPartitaPicker.setDateSelectionMode(DatePicker.DateSelectionMode.SINGLE_DATE_SELECTED);

        partiteTable.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{
                        "Avversario", "Data"
                }));

        frame.setVisible(true);
    }

    private void impostaEstetica() {

        mainPanel.setBackground(Estetica.mainBackgroundColor);
        topPanel.setBackground(Estetica.filterBackgorundColor);
        middlePanel.setBackground(Estetica.filterBackgorundColor);
        Estetica.setButtonColor(selezionaButton);
        Estetica.setButtonColor(chiudiButton);
        Estetica.setButtonColor(caricaMilitanza);
        Estetica.setButtonColor(aggiungiPartitaButton);
        Estetica.setButtonColor(modificaButton);
        Estetica.setButtonColor(resetButton);
        Estetica.setMenuItemColor(modificaPopup);
        Estetica.setMenuItemColor(selezionaItem);
        Estetica.setMenuItemColor(annullaItem);
        eliminaPopup.setBackground(Color.RED);
        Estetica.setHeaderTable(partiteTable);
    }

    private void implementaListeners() {
        caricaMilitanza.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (militanza == null) creaMilitanza();

                frame.dispose();
                frameChiamante.setVisible(true);
            }
        });

        aggiungiPartitaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkAllCampi()) {

                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

                    if (militanza == null) creaMilitanza();

                    Statistiche stat = getStatistiche();
                    String squadraCasa = "";
                    String squadraTrasferta = "";

                    if (tipoPartitaBox.getSelectedItem().toString().equals("CASA")) {
                        squadraCasa = nomeSquadraText.getSelectedItem().toString();
                        squadraTrasferta = nomeAvversarioText.getSelectedItem().toString();
                    } else {
                        squadraCasa = nomeAvversarioText.getSelectedItem().toString();
                        squadraTrasferta = nomeSquadraText.getSelectedItem().toString();
                    }

                    militanza.addPartita(new Partita(-1, squadraCasa, squadraTrasferta, Integer.getInteger(goalCasaText.getText()), Integer.getInteger(goalTrafertText.getText()),
                            dtf.format(dataPartitaPicker.getSelectedDate()), stat));

                    DefaultTableModel model = (DefaultTableModel) partiteTable.getModel();
                    model.addRow(new Object[]{nomeAvversarioText.getSelectedItem().toString(), dtf.format(dataPartitaPicker.getSelectedDate())});

                    controller.setMilitanzaCercata(militanza);
                }
            }
        });

        modificaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = partiteTable.getSelectedRow();
                Partita p = militanza.getPartite().get(index);
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

                if (checkAllCampi()) {
                    Statistiche stat = getStatistiche();
                    String squadraCasa = "";
                    String squadraTrasferta = "";

                    if (tipoPartitaBox.getSelectedItem().toString().equals("CASA")) {
                        squadraCasa = nomeSquadraText.getSelectedItem().toString();
                        squadraTrasferta = nomeAvversarioText.getSelectedItem().toString();
                    } else {
                        squadraCasa = nomeAvversarioText.getSelectedItem().toString();
                        squadraTrasferta = nomeSquadraText.getSelectedItem().toString();
                    }

                    p.setSquadraCasa(squadraCasa);
                    p.setSquadraTrasferta(squadraTrasferta);
                    p.setGoalCasa(Integer.getInteger(goalCasaText.getText()));
                    p.setGoalTrasferta(Integer.getInteger(goalTrafertText.getText()));
                    p.setData(dtf.format(dataPartitaPicker.getSelectedDate()));
                    p.setStat(stat);

                    modificaButton.setEnabled(false);
                    eliminaButton.setEnabled(false);
                    selezionaButton.setEnabled(false);
                    partiteTable.clearSelection();
                }
            }
        });

        eliminaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = partiteTable.getSelectedRow();
                militanza.getPartite().remove(index);

                eliminaButton.setEnabled(false);
                modificaButton.setEnabled(false);
                selezionaButton.setEnabled(false);
            }
    });

        eliminaPopup.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               int index = partiteTable.getSelectedRow();
               militanza.getPartite().remove(index);

               eliminaButton.setEnabled(false);
               modificaButton.setEnabled(false);
               selezionaButton.setEnabled(false);
           }
        });

        dataInizioPicker.setDateSelectionAble(new

    DateSelectionAble() {
        @Override
        public boolean isDateSelectedAble (LocalDate localDate){
            return !localDate.isAfter(LocalDate.now());
        }
    });
        dataFinePicker.setDateSelectionAble(new

    DateSelectionAble() {
        @Override
        public boolean isDateSelectedAble (LocalDate localDate){
            return !localDate.isAfter(LocalDate.now());
        }
    });
        dataPartitaPicker.setDateSelectionAble(new

    DateSelectionAble() {
        @Override
        public boolean isDateSelectedAble (LocalDate localDate){
            return !localDate.isAfter(LocalDate.now());
        }
    });

        partiteTable.addMouseListener(new

    MouseAdapter() {

        @Override
        public void mouseClicked (MouseEvent e){
            if (e.getButton() == MouseEvent.BUTTON1) {

                if (partiteTable.getSelectedRow() >= 0 && partiteTable.getSelectedRow() < partiteTable.getRowCount()) {

                    selezionaButton.setEnabled(true);
                    eliminaButton.setEnabled(true);
                    modificaButton.setEnabled(true);
                }

            } else if (e.getButton() == MouseEvent.BUTTON3) {

                int row = partiteTable.rowAtPoint(e.getPoint());
                if (row >= 0 && row < partiteTable.getRowCount()) {

                    popupMenu.show(partiteTable, e.getX(), e.getY());

                    partiteTable.setRowSelectionInterval(row, row);
                    selezionaButton.setEnabled(true);
                    eliminaButton.setEnabled(true);
                    modificaButton.setEnabled(true);
                }
            }
        }
    });

        resetButton.addActionListener(new

    ActionListener() {
        @Override
        public void actionPerformed (ActionEvent e){
            resetInfoPartita();
        }
    });

        selezionaItem.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed (ActionEvent e){

            caricaInfoPartita();
        }
    });

        annullaItem.addActionListener(new

    ActionListener() {

        @Override
        public void actionPerformed (ActionEvent e){
            popupMenu.hide();
        }
    });

        selezionaButton.addActionListener(new

    ActionListener() {
        @Override
        public void actionPerformed (ActionEvent e){

            caricaInfoPartita();
        }
    });

        chiudiButton.addActionListener(new

    ActionListener() {
        @Override
        public void actionPerformed (ActionEvent e){
            frame.dispose();
            frameChiamante.setVisible(true);
        }
    });
}

private void resetInfoPartita() {

    goalCasaText.setText("0");
    goalTrafertText.setText("0");
    goalText.setText("0");
    assistText.setText("0");
    rossiText.setText("0");
    gialliText.setText("0");
    rigoriSegnatiText.setText("0");
    numeroParateText.setText("0");
    goalSubitiText.setText("0");
}

private void creaMilitanza() {
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    if (checkMilCampi()) {
        LocalDate ldI = dataInizioPicker.getSelectedDate();
        LocalDate ldF = dataFinePicker.getSelectedDate();
        militanza = new Militanza(-1, dtf.format(ldI), dtf.format(ldF), new Squadra(nomeSquadraText.getSelectedItem().toString()));
        militanza.setPartite(new ArrayList<Partita>());

        controller.getGiocatoreCercato().addMilitanza(militanza);
    }
}

private Statistiche getStatistiche() {
    return new Statistiche(Integer.getInteger(goalText.getText()), Integer.getInteger(assistText.getText()), Integer.getInteger(rossiText.getText()),
            Integer.getInteger(gialliText.getText()), Integer.getInteger(rigoriSegnatiText.getText()), Integer.getInteger(goalSubitiText.getText()), Integer.getInteger(numeroParateText.getText()));
}

private void caricaInfoPartita() {
    int index = partiteTable.getSelectedRow();

    if (index >= 0) {
        Partita p = militanza.getPartite().get(index);

        String avversario = p.getSquadraCasa().equals(militanza.getSquadra().getNome()) ? p.getSquadraCasa() : p.getSquadraTrasferta();
        nomeAvversarioText.setSelectedItem(avversario);
        LocalDate data = LocalDate.parse(p.getData());
        dataPartitaPicker.setSelectedDate(data);
        int tipo = p.getSquadraCasa().equals(militanza.getSquadra().getNome()) ? 0 : 1;
        tipoPartitaBox.setSelectedIndex(tipo);

        goalCasaText.setText(Integer.toString(p.getGoalCasa()));
        goalTrafertText.setText(Integer.toString(p.getGoalTrasferta()));
        goalText.setText(Integer.toString(p.getStat().getGoal()));
        assistText.setText(Integer.toString(p.getStat().getAssist()));
        rossiText.setText(Integer.toString(p.getStat().getCartelliniRossi()));
        gialliText.setText(Integer.toString(p.getStat().getCartelliniGialli()));
        rigoriSegnatiText.setText(Integer.toString(p.getStat().getRigoriSegnati()));
        numeroParateText.setText(Integer.toString(p.getStat().getNumParate()));
        goalSubitiText.setText(Integer.toString(p.getStat().getGoalSubiti()));

        modificaButton.setEnabled(true);
    }

}

private boolean checkAllCampi() {

    if (nomeSquadraText.getSelectedItem().equals("") || !dataInizioPicker.isDateSelected() || !dataFinePicker.isDateSelected() ||
            !dataPartitaPicker.isDateSelected() || nomeAvversarioText.getSelectedItem().equals("")) {
        JOptionPane.showMessageDialog(frame, "Campi della Militanza non validi o mancanti", "Errore", JOptionPane.ERROR_MESSAGE);
        return false;
    }

    return true;
}

private boolean checkMilCampi() {
    if (nomeSquadraText.getSelectedItem().equals("") || !dataInizioPicker.isDateSelected() || !dataFinePicker.isDateSelected()) {
        JOptionPane.showMessageDialog(frame, "Campi della Militanza non validi o mancanti", "Errore", JOptionPane.ERROR_MESSAGE);
        return false;
    }

    return true;
}


}
