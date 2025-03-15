package me.nothingelsee.GUI;

import me.nothingelsee.Aesthetics.Estetica;
import me.nothingelsee.Controller.Controller;
import me.nothingelsee.Model.Partita;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class LeggiPartite {

    JFrame frame;
    private Controller controller;
    private JPanel mainPanel;
    private JLabel nomeGiocatoreLabel;
    private JLabel nomeSquadraLabel;
    private JLabel datInizioLabel;
    private JLabel dataFineLabel;
    private JTable partiteTable;
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
    private JPanel topPanel;
    private JPanel middlePanel;
    private JPanel bottomPanel;
    private JPanel infoPartita;
    private JScrollPane partiteScrollPane;
    private ArrayList<Partita> partite;
    private JPopupMenu popupMenu;
    private JMenuItem selezionaItem;
    private JMenuItem annullaItem;


    public LeggiPartite(Controller controller, JFrame frameChiamante) {

        inizializzaComponenti(controller, frameChiamante);
        impostaBackground();
        caricaDati();
        implementaListeners();
    }

    private void inizializzaComponenti(Controller controller, JFrame frameChiamante) {

        this.controller = controller;
        frame = new JFrame("Statistiche Partite");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setSize(1000, 600);

        popupMenu = new JPopupMenu("Partite");
        selezionaItem = new JMenuItem("Seleziona");
        annullaItem = new JMenuItem("Annulla");

        popupMenu.add(selezionaItem);
        popupMenu.add(annullaItem);

        partiteTable.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{
                        "Avversario", "Data"
                }));

        selezionaButton.setEnabled(false);
    }

    private void impostaBackground() {

        mainPanel.setBackground(Estetica.mainBackgroundColor);
        topPanel.setBackground(Estetica.filterBackgorundColor);
        middlePanel.setBackground(Estetica.filterBackgorundColor);
        Estetica.setMenuItemColor(selezionaItem);
        Estetica.setMenuItemColor(annullaItem);
        Estetica.setButtonColor(selezionaButton);
        Estetica.setButtonColor(chiudiButton);
        Estetica.setHeaderTable(partiteTable);
    }

    private void caricaDati() {

        nomeGiocatoreLabel.setText(controller.getGiocatoreCercato().getNome() + " " + controller.getGiocatoreCercato().getCognome());
        nomeSquadraLabel.setText(String.valueOf(controller.getMilitanzaCercata().getSquadra().getNome()));
        datInizioLabel.setText("Data Inizio: " + controller.getMilitanzaCercata().getDataInizio());
        dataFineLabel.setText("Data Fine: " + controller.getMilitanzaCercata().getDataFine());

        controller.getPartite(controller.getMilitanzaCercata());
        DefaultTableModel model = (DefaultTableModel) partiteTable.getModel();
        partite = controller.getMilitanzaCercata().getPartite();
        String avversario = "";
        for (Partita p : partite) {
            avversario = p.getSquadraCasa().equals(controller.getMilitanzaCercata().getSquadra()) ? p.getSquadraTrasferta() : p.getSquadraCasa();
            model.addRow(new Object[]{avversario, p.getData()});
        }
    }

    private void implementaListeners() {

        partiteTable.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked (MouseEvent e){

                if (e.getButton() == MouseEvent.BUTTON1) {

                    if(partiteTable.getSelectedRow() >= 0 && partiteTable.getSelectedRow() < partiteTable.getRowCount()) {

                        selezionaButton.setEnabled(true);
                    }

                } else if (e.getButton() == MouseEvent.BUTTON3) {

                    int row = partiteTable.rowAtPoint(e.getPoint());
                    if(row >= 0 && row < partiteTable.getRowCount()) {

                        popupMenu.show(partiteTable, e.getX(), e.getY());

                        partiteTable.setRowSelectionInterval(row, row);
                        selezionaButton.setEnabled(true);
                    }
                }
            }
        });

        selezionaItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                caricaInfoPartita();
            }
        });

        annullaItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                popupMenu.hide();
            }
        });

        selezionaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                caricaInfoPartita();
            }
        });

        chiudiButton.addActionListener(new

                                               ActionListener() {
                                                   @Override
                                                   public void actionPerformed(ActionEvent e) {
                                                       frame.dispose();
                                                   }
                                               });

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                super.windowClosed(e);
                controller.getMilitanzaCercata().getPartite().clear();
            }
        });
    }

    private void caricaInfoPartita() {

        Partita p = partite.get(partiteTable.getSelectedRow());

        controller.getStat(p);

        risultatoLabel.setText(p.getSquadraCasa() + " " + p.getGoalCasa() + "-" + p.getGoalTrasferta() + " " + p.getSquadraTrasferta());
        goalLabel.setText(String.valueOf(p.getStat().getGoal()));
        assistLabel.setText(String.valueOf(p.getStat().getAssist()));
        rossiLabel.setText(String.valueOf(p.getStat().getCartelliniRossi()));
        gialliLabel.setText(String.valueOf(p.getStat().getCartelliniGialli()));
        rigoriSegnatiLabel.setText(String.valueOf(p.getStat().getRigoriSegnati()));

        numeroParateLabel.setText(String.valueOf(p.getStat().getNumParate()));
        goalSubitiLabel.setText(String.valueOf(p.getStat().getGoalSubiti()));
    }
}
