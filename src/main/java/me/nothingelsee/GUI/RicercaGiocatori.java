package me.nothingelsee.GUI;

import me.nothingelsee.Aesthetics.Estetica;
import me.nothingelsee.Controller.Controller;
import me.nothingelsee.ENUM.PIEDE;
import me.nothingelsee.ENUM.RUOLO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class RicercaGiocatori {

    private static JFrame frame;
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
    private Controller controller;
    private JPopupMenu popup;
    private JMenuItem visionaPopup;
    private JMenuItem modificaPopup;
    private JMenuItem annullaPopup;

    public RicercaGiocatori() {
        controller = new Controller();
        inizializzaComponenti();
        creaLayout();
        implementaListeners();
    }

    public void inizializzaComponenti() {

        //Crea PopupMenu
        popup = new JPopupMenu();
        visionaPopup = new JMenuItem("Visualizza");
        modificaPopup = new JMenuItem("Modifica");
        annullaPopup = new JMenuItem("Annulla");
        popup.add(visionaPopup);
        popup.add(annullaPopup);
        frame.add(popup);

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

        //BorderLayout e Margini MainPanel
        mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        mainPanel.setBackground(Estetica.mainBackgroundColor);

        //BorderLayout e Margini FilterPanel
        filterPanel = new JPanel(new GridBagLayout());
        filterPanel.setBackground(Estetica.filterBackgorundColor);
        TitledBorder titledBorder = BorderFactory.createTitledBorder("Filtri di Ricerca");
        titledBorder.setTitleFont(new Font("Arial", Font.BOLD, 14));
        filterPanel.setBorder(titledBorder);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        //Riga 0; Nome e Bottone Ricercaù
        gbc.gridx = 0;
        gbc.gridy = 0;
        filterPanel.add(new JLabel("Nome: "), gbc);

        gbc.gridx = 1;
        filterPanel.add(nomeGiocatore, gbc);

        gbc.gridx = 2;
        filterPanel.add(cercaButton, gbc);

        //Riga 1: Ruolo
        gbc.gridx = 0;
        gbc.gridy = 1;
        filterPanel.add(new JLabel("Ruolo: "), gbc);

        gbc.gridx = 1;
        filterPanel.add(ruoloComboBox, gbc);

        //Riga 2: Piede
        gbc.gridx = 0;
        gbc.gridy = 2;
        filterPanel.add(new JLabel("Piede: "), gbc);

        gbc.gridx = 1;
        filterPanel.add(piedeComboBox, gbc);

        //Riga 3: Squadre
        gbc.gridx = 0;
        gbc.gridy = 3;
        filterPanel.add(new JLabel("Squadra: "), gbc);

        gbc.gridx = 1;
        filterPanel.add(squadraComboBox, gbc);

        //Riga 4: Group By
        gbc.gridx = 0;
        gbc.gridy = 4;
        filterPanel.add(new JLabel("Raggruppa per: "), gbc);

        gbc.gridx = 1;
        filterPanel.add(groupByComboBox, gbc);

        //Riga 5: Resetta filtri bottone
        gbc.gridx = 0;
        gbc.gridy = 5;
        filterPanel.add(resettaFiltri, gbc);

        gbc.gridx = 1;
        filterPanel.add(visionaButton, gbc);
        visionaButton.setEnabled(false);

        //Aggiunge tabella in scroll pane
        JScrollPane scrollPane = new JScrollPane(giocatoriTable);
        mainPanel.add(filterPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
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
                controller.getGiocatoriByFiltri();
                if (!controller.getGiocatori().isEmpty()) {
                    for (int i = 0; i < controller.getGiocatori().size(); i++) {
                        model.addRow(new Object[]{controller.getGiocatori().get(i).getNome(), controller.getGiocatori().get(i).getCognome(), controller.getGiocatori().get(i).getDataNascita()});
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

                                                            } else if (e.getButton() == MouseEvent.BUTTON3) {


                                                                popup.show(giocatoriTable, e.getX(), e.getY());

                                                                int r = giocatoriTable.rowAtPoint(e.getPoint());
                                                                if (r >= 0 && r < giocatoriTable.getRowCount()) {
                                                                    giocatoriTable.setRowSelectionInterval(r, r);
                                                                    int row = giocatoriTable.getSelectedRow();
                                                                    controller.setGiocatoreCercato(controller.getGiocatori().get(row));
                                                                    visionaButton.setEnabled(true);
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
                                                       popup.hide();
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
    }

    public String capitalizeFirstLetter(String s) {
        if (s.equals("")) return "";
        return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
    }

    private void visualizzaGiocatore() {

        LeggiGiocatore giocatoreVis = new LeggiGiocatore(controller, frame);
        giocatoreVis.frame.setVisible(true);
    }

    private void creaFiltri(String nome) {

        controller.getFiltriRicerca().clear();

        if (nome == "") controller.addFiltri("G.nome LIKE '%'");
        else controller.addFiltri("G.nome LIKE " + nome);

        if (piedeComboBox.getSelectedItem().toString().equals("TUTTI")) controller.addFiltri("");
        else controller.addFiltri("AND G.piede = \'" + piedeComboBox.getSelectedItem().toString() + "\'");

        if (ruoloComboBox.getSelectedItem().toString().equals("TUTTI")) controller.addFiltri("");
        else controller.addFiltri("AND R.tipo = \'" + ruoloComboBox.getSelectedItem().toString() + "\'");

        if (squadraComboBox.getSelectedItem().toString().equals("TUTTE")) controller.addFiltri("");
        else controller.addFiltri("AND M.nomesquadra = \'" + squadraComboBox.getSelectedItem().toString() + "\'");

        controller.addFiltri(groupByComboBox.getSelectedItem().toString());
    }

    private void impostaTabella() {

        if(groupByComboBox.getSelectedItem().toString() == "Nessuno"){

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

    public static void main(String[] args) {
        frame = new JFrame("Cerca Giocatori");
        frame.setContentPane(new RicercaGiocatori().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(600, 500);
        frame.setLocationRelativeTo(null);

    }
}
