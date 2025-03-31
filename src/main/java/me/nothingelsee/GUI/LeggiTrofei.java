package me.nothingelsee.GUI;

import me.nothingelsee.Aesthetics.Estetica;
import me.nothingelsee.Controller.Controller;
import me.nothingelsee.Model.Trofeo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * The type Leggi trofei.
 */
public class LeggiTrofei {

    /**
     * The Frame.
     */
    JFrame frame;
    private JFrame frameChiamante;
    private Controller controller;
    private JPanel mainPanel;
    private JPanel buttonPanel;
    private JButton chiudiButton;
    private JTable trofeiTable;
    private JScrollPane trofeiScrollPane;
    private JButton aggiungiButton;
    private JButton modificaButton;
    private JButton eliminaButton;
    private JPanel trofeiPanel;
    private JButton reloadTable;
    private JPopupMenu trofeiPopupMenu;
    private JMenuItem eliminaMenuItem;
    private JMenuItem modificaMenuItem;
    private JMenuItem annullaMenuItem;
    private boolean canModify = false;

    /**
     * Instantiates a new Leggi trofei.
     *
     * @param controller     the controller
     * @param frameChiamante the frame chiamante
     * @param canModify      the can modify
     */
    public LeggiTrofei(Controller controller, JFrame frameChiamante, boolean canModify) {

        inizializzaComponenti(controller, frameChiamante, canModify);
        impostaEstetica();
        implementaListeners();

    }

    private void inizializzaComponenti(Controller controller, JFrame frameChiamante, boolean canModify) {
        this.controller = controller;
        this.canModify = canModify;
        this.frameChiamante = frameChiamante;
        frame = new JFrame("Trofei");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setContentPane(mainPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setSize(400, 700);

        trofeiPopupMenu = new JPopupMenu();

        if (canModify) {
            eliminaButton.setVisible(true);
            aggiungiButton.setVisible(true);
            modificaButton.setVisible(true);

            eliminaMenuItem = new JMenuItem("Elimina");
            modificaMenuItem = new JMenuItem("Modifica");
            annullaMenuItem = new JMenuItem("Annulla");

            trofeiPopupMenu.add(eliminaMenuItem);
            trofeiPopupMenu.add(modificaMenuItem);
            trofeiPopupMenu.addSeparator();
            trofeiPopupMenu.add(annullaMenuItem);
        }

        trofeiTable.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{"Nome", "Data", "Tipo", "Squadra"}
        ));

        caricaDati();

        frame.setVisible(true);
    }

    private void impostaEstetica() {

        mainPanel.setBackground(Estetica.mainBackgroundColor);
        trofeiScrollPane.setBackground(Estetica.filterBackgorundColor);
        Estetica.setButtonColor(aggiungiButton);
        if (canModify) {
            Estetica.setButtonColor(modificaButton);
            eliminaButton.setBackground(Color.RED);
            Estetica.setButtonColor(chiudiButton);
            eliminaMenuItem.setBackground(Color.RED);
            Estetica.setMenuItemColor(modificaMenuItem);
            Estetica.setMenuItemColor(annullaMenuItem);
        }
        Estetica.setHeaderTable(trofeiTable);
    }

    private void implementaListeners() {
        chiudiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        if (canModify) {
            aggiungiButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    controller.setTrofeoCercato(null);
                    AggiungiTrofeo aggiungiTrofeo = new AggiungiTrofeo(frame, controller);
                    frame.setVisible(false);
                }
            });

            eliminaButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    eliminaTrofeo();
                    modificaButton.setEnabled(false);
                    eliminaButton.setEnabled(false);
                    trofeiTable.clearSelection();
                    caricaDati();
                }
            });
            eliminaMenuItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    eliminaTrofeo();
                    modificaButton.setEnabled(false);
                    eliminaButton.setEnabled(false);
                    aggiungiButton.setEnabled(true);
                    trofeiTable.clearSelection();
                    caricaDati();
                }
            });

            modificaButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    modificaTrofeo();
                    modificaButton.setEnabled(false);
                    eliminaButton.setEnabled(false);
                    aggiungiButton.setEnabled(true);
                    trofeiTable.clearSelection();
                }
            });
            modificaMenuItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    modificaTrofeo();
                    modificaButton.setEnabled(false);
                    eliminaButton.setEnabled(false);
                    trofeiTable.clearSelection();
                }
            });

            annullaMenuItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    trofeiTable.clearSelection();
                    modificaButton.setEnabled(false);
                    eliminaButton.setEnabled(false);
                    aggiungiButton.setEnabled(true);
                }
            });
        }

        trofeiTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                if (e.getButton() == MouseEvent.BUTTON1) {
                    if (trofeiTable.getSelectedRow() >= 0 && trofeiTable.getSelectedRow() < trofeiTable.getRowCount()) {
                        if (canModify) {
                            eliminaButton.setEnabled(true);
                            modificaButton.setEnabled(true);
                            aggiungiButton.setEnabled(false);
                        }
                    }
                } else if (e.getButton() == MouseEvent.BUTTON3) {
                    int row;

                    row = trofeiTable.rowAtPoint(e.getPoint());
                    if (row >= 0 && row < trofeiTable.getRowCount()) {
                        trofeiPopupMenu.show(trofeiTable, e.getX(), e.getY());

                        trofeiTable.setRowSelectionInterval(row, row);
                        if (canModify) {
                            eliminaButton.setEnabled(true);
                            modificaButton.setEnabled(true);
                            aggiungiButton.setEnabled(false);
                        }
                    }
                }
            }
        });

        reloadTable.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                caricaDati();
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

    private void caricaDati() {

        DefaultTableModel model = (DefaultTableModel) trofeiTable.getModel();
        model.setRowCount(0);
        controller.getTrofei(controller.getGiocatoreCercato());
        ArrayList<Trofeo> trofei = controller.getGiocatoreCercato().getTrofei();
        for (Trofeo t : trofei) {
            model.addRow(new Object[]{t.getNome(), t.getData(), t.getTipo(), t.getSquadra()});
        }
    }

    private void eliminaTrofeo() {
        int row = trofeiTable.getSelectedRow();
        controller.deleteVittoria(controller.getGiocatoreCercato().getTrofei().get(row));
        controller.getGiocatoreCercato().getTrofei().remove(row);
        trofeiTable.clearSelection();
        eliminaButton.setEnabled(false);
        modificaButton.setEnabled(false);
        caricaDati();
    }

    private void modificaTrofeo() {

        int row = trofeiTable.getSelectedRow();

        controller.setTrofeoCercato(controller.getGiocatoreCercato().getTrofei().get(row));
        AggiungiTrofeo aggiungiTrofeo = new AggiungiTrofeo(frame, controller);
        frame.setVisible(false);

        eliminaButton.setEnabled(false);
        modificaButton.setEnabled(false);
    }
}
