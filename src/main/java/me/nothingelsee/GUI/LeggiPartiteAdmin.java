package me.nothingelsee.GUI;

import me.nothingelsee.Aesthetics.Estetica;
import me.nothingelsee.Controller.Controller;
import me.nothingelsee.ENUM.RUOLO;
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

/**
 * The type Leggi partite admin.
 */
public class LeggiPartiteAdmin {

    private Controller controller;
    private JFrame frameChiamante;
    /**
     * The Frame.
     */
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
    private JButton eliminaButton1;
    private DatePicker dataInizioPicker;
    private DatePicker dataFinePicker;
    private DatePicker dataPartitaPicker;
    private JPopupMenu popupMenu;
    private JMenuItem eliminaPopup;
    private JMenuItem selezionaItem;
    private JMenuItem annullaItem;
    private Militanza militanza = null;

    /**
     * Instantiates a new Leggi partite admin.
     *
     * @param frameChiamante the frame chiamante
     * @param controller     the controller
     */
    public LeggiPartiteAdmin(JFrame frameChiamante, Controller controller) {

        inizializzaComponenti(frameChiamante, controller);
        impostaEstetica();
        implementaListeners();
    }

    private void inizializzaComponenti(JFrame frameChiamante, Controller controller) {

        this.controller = controller;
        militanza = controller.getMilitanzaCercata();
        this.frameChiamante = frameChiamante;
        frame = new JFrame("Modifica Militanza");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);

        popupMenu = new JPopupMenu("Partite");
        eliminaPopup = new JMenuItem("Elimina");
        selezionaItem = new JMenuItem("Seleziona");
        annullaItem = new JMenuItem("Annulla");

        popupMenu.add(eliminaPopup);
        popupMenu.add(selezionaItem);
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
        aggiungiPartitaButton.setEnabled(true);

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

        if (militanza != null) {
            nomeSquadraText.setSelectedItem(militanza.getSquadra().getNome());
            dataInizioPicker.setSelectedDate(militanza.getDataInizio());
            dataFinePicker.setSelectedDate(militanza.getDataFine());
            militanza.getPartite().clear();
            controller.getPartite(militanza);
            for (Partita p : militanza.getPartite()) {
                controller.getStat(p);
            }
            aggiornaTable();
        }

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
        Estetica.setMenuItemColor(selezionaItem);
        Estetica.setMenuItemColor(annullaItem);
        eliminaPopup.setBackground(Color.RED);
        Estetica.setHeaderTable(partiteTable);
    }

    private void implementaListeners() {
        caricaMilitanza.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (creaMilitanza()) {
                    frame.dispose();
                    frameChiamante.setVisible(true);
                }
            }
        });

        eliminaButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.deleteMilitanza(militanza);
                controller.getGiocatoreCercato().getMilitanze().remove(militanza);
                controller.setMilitanzaCercata(null);
                frame.dispose();
                frameChiamante.setVisible(true);
            }
        });

        aggiungiPartitaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkAllCampi()) {

                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

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

                    Partita p = new Partita(squadraCasa, squadraTrasferta, Integer.parseInt(goalCasaText.getText()), Integer.parseInt(goalTrafertText.getText()),
                            dtf.format(dataPartitaPicker.getSelectedDate()), stat);

                    controller.caricaPartita(militanza.getId(), p);
                    controller.caricaPartecipazione(squadraCasa, squadraTrasferta);
                    controller.caricaStatistica(p.getId(), p.getStat());
                    militanza.addPartita(p);
                    aggiornaTable();
                    controller.setMilitanzaCercata(militanza);

                    if (controller.getGiocatoreCercato().getRuoli().contains(RUOLO.GK)) {
                        controller.caricaStatisticaPor(p.getId(), stat);
                    } else
                        JOptionPane.showMessageDialog(frame, "Il giocatore non è un potiere!", "Errore", JOptionPane.ERROR_MESSAGE);

                    partiteTable.clearSelection();
                    resetInfoPartita();
                    aggiungiPartitaButton.setEnabled(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Errore dati paritita errati o mancanti", "Errore", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        modificaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = partiteTable.getSelectedRow();
                Partita p = militanza.getPartite().get(index);

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
                    p.setGoalCasa(Integer.parseInt(goalCasaText.getText()));
                    p.setGoalTrasferta(Integer.parseInt(goalTrafertText.getText()));
                    p.setData(dataPartitaPicker.getSelectedDate());
                    p.setStat(stat);

                    controller.updatePartita(p);
                    controller.updateStatistica(p.getId(), p.getStat());

                    if (controller.getGiocatoreCercato().getRuoli().contains(RUOLO.GK)) {
                        controller.updateStatisticaPor(p.getId(), p.getStat());
                    } else
                        JOptionPane.showMessageDialog(frame, "Il giocatore non è un potiere!", "Errore", JOptionPane.ERROR_MESSAGE);

                    modificaButton.setEnabled(false);
                    eliminaButton.setEnabled(false);
                    selezionaButton.setEnabled(false);
                    resetInfoPartita();
                    partiteTable.clearSelection();
                    aggiungiPartitaButton.setEnabled(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Dati Partita mancanti o non validi ", "Errore", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        eliminaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminaPartita();
            }
        });
        eliminaPopup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminaPartita();
            }
        });

        dataInizioPicker.setDateSelectionAble(new

                                                      DateSelectionAble() {
                                                          @Override
                                                          public boolean isDateSelectedAble(LocalDate localDate) {
                                                              return !localDate.isAfter(LocalDate.now());
                                                          }
                                                      });
        dataFinePicker.setDateSelectionAble(new

                                                    DateSelectionAble() {
                                                        @Override
                                                        public boolean isDateSelectedAble(LocalDate localDate) {
                                                            return !localDate.isAfter(LocalDate.now());
                                                        }
                                                    });
        dataPartitaPicker.setDateSelectionAble(new

                                                       DateSelectionAble() {
                                                           @Override
                                                           public boolean isDateSelectedAble(LocalDate localDate) {
                                                               return !localDate.isAfter(LocalDate.now());
                                                           }
                                                       });

        partiteTable.addMouseListener(new

                                              MouseAdapter() {

                                                  @Override
                                                  public void mouseClicked(MouseEvent e) {
                                                      if (e.getButton() == MouseEvent.BUTTON1) {

                                                          if (partiteTable.getSelectedRow() >= 0 && partiteTable.getSelectedRow() < partiteTable.getRowCount()) {

                                                              aggiungiPartitaButton.setEnabled(false);
                                                              selezionaButton.setEnabled(true);
                                                              eliminaButton.setEnabled(true);
                                                              modificaButton.setEnabled(true);
                                                          }

                                                      } else if (e.getButton() == MouseEvent.BUTTON3) {

                                                          int row = partiteTable.rowAtPoint(e.getPoint());
                                                          if (row >= 0 && row < partiteTable.getRowCount()) {

                                                              popupMenu.show(partiteTable, e.getX(), e.getY());

                                                              partiteTable.setRowSelectionInterval(row, row);
                                                              aggiungiPartitaButton.setEnabled(false);
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
                                                  public void actionPerformed(ActionEvent e) {
                                                      resetInfoPartita();
                                                  }
                                              });

        selezionaItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                caricaInfoPartita();
            }
        });

        annullaItem.addActionListener(new

                                              ActionListener() {

                                                  @Override
                                                  public void actionPerformed(ActionEvent e) {
                                                      partiteTable.clearSelection();
                                                  }
                                              });

        selezionaButton.addActionListener(new

                                                  ActionListener() {
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

    private boolean creaMilitanza() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        if (checkMilCampi()) {
            LocalDate ldI = dataInizioPicker.getSelectedDate();
            LocalDate ldF = dataFinePicker.getSelectedDate();

            if (militanza == null) {
                militanza = new Militanza(-1, dtf.format(ldI), dtf.format(ldF), new Squadra(nomeSquadraText.getSelectedItem().toString()));
                militanza.setPartite(new ArrayList<Partita>());
                controller.getGiocatoreCercato().addMilitanza(militanza);
                return controller.caricaMilitanza(controller.getGiocatoreCercato().getId(), militanza);
            } else {
                militanza.setDataInizio(dtf.format(ldI));
                militanza.setDataFine(dtf.format(ldF));
                militanza.setSquadra(new Squadra(nomeSquadraText.getSelectedItem().toString()));
                return controller.upgrateMilitanza(militanza);
            }
        }
        return false;
    }

    private Statistiche getStatistiche() {
        return new Statistiche(Integer.parseInt(goalText.getText()), Integer.parseInt(assistText.getText()), Integer.parseInt(rossiText.getText()),
                Integer.parseInt(gialliText.getText()), Integer.parseInt(rigoriSegnatiText.getText()), Integer.parseInt(goalSubitiText.getText()),
                Integer.parseInt(numeroParateText.getText()));
    }

    private void caricaInfoPartita() {
        int index = partiteTable.getSelectedRow();

        if (index >= 0) {
            Partita p = militanza.getPartite().get(index);

            String avversario = p.getSquadraCasa().equals(militanza.getSquadra().getNome()) ? p.getSquadraTrasferta() : p.getSquadraCasa();
            nomeAvversarioText.setSelectedItem(avversario);
            dataPartitaPicker.setSelectedDate(p.getData());
            int tipo = p.getSquadraCasa().equals(militanza.getSquadra().getNome()) ? 0 : 1;
            tipoPartitaBox.setSelectedIndex(tipo);

            goalCasaText.setText(String.valueOf(p.getGoalCasa()));
            goalTrafertText.setText(String.valueOf(p.getGoalTrasferta()));
            goalText.setText(String.valueOf(p.getStat().getGoal()));
            assistText.setText(String.valueOf(p.getStat().getAssist()));
            rossiText.setText(String.valueOf(p.getStat().getCartelliniRossi()));
            gialliText.setText(String.valueOf(p.getStat().getCartelliniGialli()));
            rigoriSegnatiText.setText(String.valueOf(p.getStat().getRigoriSegnati()));
            numeroParateText.setText(String.valueOf(p.getStat().getNumParate()));
            goalSubitiText.setText(String.valueOf(p.getStat().getGoalSubiti()));

            modificaButton.setEnabled(true);
        }

    }

    private boolean checkAllCampi() {

        if (nomeSquadraText.getSelectedItem() == null || !dataInizioPicker.isDateSelected() || !dataFinePicker.isDateSelected() ||
                !dataPartitaPicker.isDateSelected() || nomeAvversarioText.getSelectedItem() == null) {
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

    private void eliminaPartita() {
        int index = partiteTable.getSelectedRow();
        controller.deletePartita(militanza.getPartite().get(index));
        militanza.getPartite().remove(index);
        partiteTable.clearSelection();
        aggiornaTable();
        resetInfoPartita();

        eliminaButton.setEnabled(false);
        modificaButton.setEnabled(false);
        selezionaButton.setEnabled(false);
        aggiungiPartitaButton.setEnabled(true);
    }

    private void aggiornaTable() {
        DefaultTableModel model = (DefaultTableModel) partiteTable.getModel();

        model.setRowCount(0);
        String avversario = null;

        for (Partita partita : militanza.getPartite()) {
            avversario = militanza.getSquadra().getNome().equals(partita.getSquadraCasa()) ? partita.getSquadraCasa() : partita.getSquadraTrasferta();

            model.addRow(new Object[]{avversario, partita.getData()});
        }
    }


}
