package me.nothingelsee.GUI;

import me.nothingelsee.Aesthetics.Estetica;
import me.nothingelsee.Controller.Controller;
import me.nothingelsee.ENUM.PIEDE;
import me.nothingelsee.ENUM.RUOLO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class RicercaGiocatori {

    private static JFrame frame;
    private JFrame frameChiamante;
    private JTextField nomeGiocatore;
    private JButton cercaButton;
    private JTable giocatoriTable;
    private JPanel mainPanel;
    private JButton visionaButton;
    private JPanel filterPanel;
    private JComboBox ruoloComboBox;
    private JComboBox piedeComboBox;
    private JComboBox squadraComboBox;
    private JComboBox groupByComboBox;
    private JButton resettaFiltri;
    private JLabel nomeLabel;
    private JLabel ruoloLabel;
    private JLabel piedeLabel;
    private JLabel squadraLabel;
    private JLabel raggruppaLabel;
    private JLabel ordinaLabel;
    private JComboBox ordinaComboBox;
    private JButton modificaButton;
    private JPanel buttonPanel;
    private JButton chiudiButton;
    private JButton aggiungiButton;
    private Controller controller;
    private JPopupMenu popup;
    private JMenuItem visionaPopup;
    private JMenuItem modificaPopup;
    private JMenuItem annullaPopup;
    private boolean isAdmin = false;

    public RicercaGiocatori(JFrame frameChiamante, boolean isAdmin) {
        controller = new Controller();
        frameChiamante.setVisible(false);
        inizializzaComponenti(isAdmin, frameChiamante);
        creaLayout();
        implementaListeners();
        frame.setVisible(true);
    }

    public void inizializzaComponenti(boolean isAdmin, JFrame frameChiamante) {

        this.isAdmin = isAdmin;
        this.frameChiamante = frameChiamante;
        frame = new JFrame("Cerca Giocatori");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setSize(600, 500);
        frame.setLocationRelativeTo(null);

        //Crea PopupMenu
        popup = new JPopupMenu();
        visionaPopup = new JMenuItem("Visualizza");
        annullaPopup = new JMenuItem("Annulla");

        //Aggiunge Modifica al Popup e il Bottone Modifica alla GUI
        if(isAdmin){
            modificaPopup = new JMenuItem("Modifica");
            popup.add(modificaPopup);
            popup.addSeparator();
            Estetica.setMenuItemColor(modificaPopup);

            Estetica.setButtonColor(modificaButton);
            Estetica.setButtonColor(aggiungiButton);

            modificaButton.setVisible(true);
            aggiungiButton.setVisible(true);
        }

        popup.add(visionaPopup);
        popup.add(annullaPopup);

        //Imposta colore bottoni e MenuItem
        Estetica.setButtonColor(cercaButton);
        Estetica.setButtonColor(visionaButton);
        Estetica.setButtonColor(resettaFiltri);
        Estetica.setMenuItemColor(visionaPopup);
        Estetica.setMenuItemColor(annullaPopup);

        //Aggiungi Ruoli nella ComboBox
        ruoloComboBox.addItem("TUTTI");
        for (RUOLO r : RUOLO.values()) {
            ruoloComboBox.addItem(r);
        }
        //Aggiungi Piedi nella ComboBox
        piedeComboBox.addItem("TUTTI");
        for (PIEDE p : PIEDE.values()) {
            piedeComboBox.addItem(p);
        }

        //Aggiungi Sqaudre nella ComboBox
        squadraComboBox.addItem("TUTTE");
        for (String s : controller.getSquadreNomi()) {
            squadraComboBox.addItem(s);
        }

        //Aggiungi opzioni raggruppamento
        groupByComboBox.addItem("Nessuno");
        groupByComboBox.addItem("Goal");
        groupByComboBox.addItem("Assist");
        groupByComboBox.addItem("Cartellini Rossi");
        groupByComboBox.addItem("Cartellini Gialli");
        groupByComboBox.addItem("Numero Parate");
        groupByComboBox.addItem("Numero Goal Subiti");

        giocatoriTable.setFillsViewportHeight(true);

        Estetica.setHeaderTable(giocatoriTable);
    }

    private void creaLayout() {

        mainPanel.setBackground(Estetica.mainBackgroundColor);
        filterPanel.setBackground(Estetica.filterBackgorundColor);
        buttonPanel.setBackground(Estetica.filterBackgorundColor);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        //Riga 0; Nome e Bottone Ricercaù
        gbc.gridx = 0;
        gbc.gridy = 0;
        filterPanel.add(nomeLabel, gbc);

        gbc.gridx = 1;
        filterPanel.add(nomeGiocatore, gbc);

        gbc.gridx = 2;
        filterPanel.add(cercaButton, gbc);

        //Riga 1: Ruolo
        gbc.gridx = 0;
        gbc.gridy = 1;
        filterPanel.add(ruoloLabel, gbc);

        gbc.gridx = 1;
        filterPanel.add(ruoloComboBox, gbc);

        //Riga 2: Piede
        gbc.gridx = 0;
        gbc.gridy = 2;
        filterPanel.add(piedeLabel, gbc);

        gbc.gridx = 1;
        filterPanel.add(piedeComboBox, gbc);

        //Riga 3: Squadre
        gbc.gridx = 0;
        gbc.gridy = 3;
        filterPanel.add(squadraLabel, gbc);

        gbc.gridx = 1;
        filterPanel.add(squadraComboBox, gbc);

        //Riga 4: Group By
        gbc.gridx = 0;
        gbc.gridy = 4;
        filterPanel.add(raggruppaLabel, gbc);

        gbc.gridx = 1;
        filterPanel.add(groupByComboBox, gbc);

        //Riga 5: Ordine
        gbc.gridx = 0;
        gbc.gridy = 5;
        filterPanel.add(ordinaLabel, gbc);

        gbc.gridx = 1;
        filterPanel.add(ordinaComboBox, gbc);

        //Riga 6: Resetta filtri bottone
        gbc.gridx = 0;
        gbc.gridy = 6;
        filterPanel.add(resettaFiltri, gbc);

        gbc.gridx = 1;
        filterPanel.add(visionaButton, gbc);

        if(isAdmin){
            gbc.gridx = 2;
            filterPanel.add(modificaButton, gbc);

            gbc.gridx = 3;
            filterPanel.add(aggiungiButton, gbc);
        }
    }

    public void implementaListeners() {

        cercaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                impostaTabella();
                DefaultTableModel model = (DefaultTableModel) giocatoriTable.getModel();
                model.setRowCount(0);
                String nome = capitalizeFirstLetter(nomeGiocatore.getText());
                creaFiltri(nome);
                controller.getGiocatori().clear();
                ArrayList<Integer> stat = controller.getGiocatoriByFiltri();
                if (!controller.getGiocatori().isEmpty()) {
                    if (stat == null) {
                        for (int i = 0; i < controller.getGiocatori().size(); i++) {
                            model.addRow(new Object[]{controller.getGiocatori().get(i).getNome(), controller.getGiocatori().get(i).getCognome(), controller.getGiocatori().get(i).getDataNascita()});
                        }
                    } else {
                        for (int i = 0; i < controller.getGiocatori().size(); i++) {
                            model.addRow(new Object[]{controller.getGiocatori().get(i).getNome(), controller.getGiocatori().get(i).getCognome(), controller.getGiocatori().get(i).getDataNascita(), stat.get(i).toString()});
                        }
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Non sono presenti giocatori con questi parametri!");
                }

            }
        });

        giocatoriTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if (giocatoriTable.getSelectedRow() != -1) {
                    super.mouseClicked(e);

                    if (e.getButton() == MouseEvent.BUTTON1) {

                        int row = giocatoriTable.getSelectedRow();
                        controller.setGiocatoreCercato(controller.getGiocatori().get(row));
                        visionaButton.setEnabled(true);
                        if(isAdmin) modificaButton.setEnabled(true);
                        if(isAdmin) aggiungiButton.setEnabled(false);

                    } else if (e.getButton() == MouseEvent.BUTTON3) {


                        popup.show(giocatoriTable, e.getX(), e.getY());

                        int r = giocatoriTable.rowAtPoint(e.getPoint());
                        if (r >= 0 && r < giocatoriTable.getRowCount()) {
                            giocatoriTable.setRowSelectionInterval(r, r);
                            int row = giocatoriTable.getSelectedRow();
                            controller.setGiocatoreCercato(controller.getGiocatori().get(row));
                            visionaButton.setEnabled(true);
                            if(isAdmin) modificaButton.setEnabled(true);
                            if(isAdmin) aggiungiButton.setEnabled(false);
                        } else {
                            giocatoriTable.clearSelection();
                        }

                    }
                }
            }
        });

        visionaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                visualizzaGiocatore();
                visionaButton.setEnabled(false);
                modificaButton.setEnabled(false);
                giocatoriTable.clearSelection();
            }
        });

        visionaPopup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                visualizzaGiocatore();
                visionaButton.setEnabled(false);
            }
        });

        annullaPopup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                giocatoriTable.clearSelection();
                modificaButton.setEnabled(false);
                aggiungiButton.setEnabled(true);
                visionaButton.setEnabled(false);
            }
        });

        resettaFiltri.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ruoloComboBox.setSelectedIndex(0);
                piedeComboBox.setSelectedIndex(0);
                squadraComboBox.setSelectedIndex(0);
                groupByComboBox.setSelectedIndex(0);
            }
        });

        chiudiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                frameChiamante.setVisible(true);
                frame.dispose();
            }
        });

        if(isAdmin) {
            modificaPopup.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    LeggiGiocatoreAdmin gioAdmin = new LeggiGiocatoreAdmin(controller, frame);
                    gioAdmin.frame.setVisible(true);
                    frame.setVisible(false);
                    modificaButton.setEnabled(false);
                    visionaButton.setEnabled(false);
                    aggiungiButton.setEnabled(true);
                    giocatoriTable.clearSelection();
                }
            });

            aggiungiButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    controller.setGiocatoreCercato(null);
                    frame.setVisible(false);
                    LeggiGiocatoreAdmin gioAdmin = new LeggiGiocatoreAdmin(controller, frame);
                }
            });

            modificaButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    LeggiGiocatoreAdmin gioAdmin = new LeggiGiocatoreAdmin(controller, frame);
                    frame.setVisible(false);
                    modificaButton.setEnabled(false);
                    visionaButton.setEnabled(false);
                    aggiungiButton.setEnabled(true);
                    giocatoriTable.clearSelection();
                }
            });
        }

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                super.windowClosed(e);
                frameChiamante.setVisible(true);
            }
        });
    }

    public String capitalizeFirstLetter(String s) {
        if (s.equals("")) return "";
        return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
    }

    private void visualizzaGiocatore() {

        LeggiGiocatoreGuest giocatoreVis = new LeggiGiocatoreGuest(controller, frame);
        giocatoreVis.frame.setVisible(true);
    }

    private void creaFiltri(String nome) {

        controller.getFiltriRicerca().clear();

        if (nome == "") controller.addFiltri("G.nome LIKE '%'");
        else controller.addFiltri("G.nome LIKE \'" + nome + "\'");

        if (piedeComboBox.getSelectedItem().toString().equals("TUTTI")) controller.addFiltri("");
        else controller.addFiltri("AND G.piede = \'" + piedeComboBox.getSelectedItem().toString() + "\'");

        if (ruoloComboBox.getSelectedItem().toString().equals("TUTTI")) controller.addFiltri("");
        else controller.addFiltri("AND R.tipo = \'" + ruoloComboBox.getSelectedItem().toString() + "\'");

        if (squadraComboBox.getSelectedItem().toString().equals("TUTTE")) controller.addFiltri("");
        else controller.addFiltri("AND M.nomesquadra = \'" + squadraComboBox.getSelectedItem().toString() + "\'");

        controller.addFiltri(groupByComboBox.getSelectedItem().toString());
        controller.addFiltri(ordinaComboBox.getSelectedItem().toString());
    }

    private void impostaTabella() {

        if (groupByComboBox.getSelectedItem().toString() == "Nessuno") {

            giocatoriTable.setModel(new DefaultTableModel(
                    new Object[][]{},
                    new String[]{
                            "Nome", "Cognome", "Data di Nascita"
                    }
            ));
        } else {

            giocatoriTable.setModel(new DefaultTableModel(
                    new Object[][]{},
                    new String[]{
                            "Nome", "Cognome", "Data di Nascita", groupByComboBox.getSelectedItem().toString()
                    }
            ));
        }

    }

    public void setIsAdmin(boolean isAdmin){ this.isAdmin = isAdmin;}

}
