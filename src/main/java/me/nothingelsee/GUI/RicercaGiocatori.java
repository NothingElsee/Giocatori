package me.nothingelsee.GUI;

import me.nothingelsee.Controller.Controller;
import me.nothingelsee.Model.Giocatore;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.ArrayList;

public class RicercaGiocatori {

    private static JFrame frame;
    private JTextField nomeGiocatore;
    private JButton cercaButton;
    private JTable giocatoriTable;
    private JPanel panel;
    private JButton visionaButton;
    private Controller controller;
    private JPopupMenu popup;
    private JMenuItem visionaPopup;
    private JMenuItem annullaPopup;

    public RicercaGiocatori() {
        controller = new Controller();

        popup = new JPopupMenu();
        visionaPopup = new JMenuItem("Visualizza");
        annullaPopup = new JMenuItem("Annulla");
        popup.add(visionaPopup);
        popup.add(annullaPopup);
        frame.add(popup);


        giocatoriTable.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{
                        "Nome", "Cognome", "Data di Nascita"
                }
        ));

        DefaultTableModel model = (DefaultTableModel) giocatoriTable.getModel();

        cercaButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed (ActionEvent e) {

                    controller.getGiocatori().clear();
                    model.setRowCount(0);
                    String nome = capitalizeFirstLetter(nomeGiocatore.getText());
                    controller.getGiocatoriByName(nome);
                    if(!controller.getGiocatori().isEmpty()) {
                        for (int i = 0; i < controller.getGiocatori().size(); i++){
                            model.addRow(new Object[]{controller.getGiocatori().get(i).getNome(), controller.getGiocatori().get(i).getCognome(), controller.getGiocatori().get(i).getDataNascita()});
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Non sono presenti giocatori con questo nome!");
                    }

            }
        });

        giocatoriTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e){

                super.mouseClicked(e);

                if (e.getButton() == MouseEvent.BUTTON1) {

                    int row = giocatoriTable.getSelectedRow();
                    controller.setGiocatoreCercato(controller.getGiocatori().get(row));

                } else if (e.getButton() == MouseEvent.BUTTON3) {


                    popup.show(giocatoriTable, e.getX(), e.getY());

                    int r = giocatoriTable.rowAtPoint(e.getPoint());
                    if(r >= 0 && r < giocatoriTable.getRowCount()){
                        giocatoriTable.setRowSelectionInterval(r, r);
                        int row = giocatoriTable.getSelectedRow();
                        controller.setGiocatoreCercato(controller.getGiocatori().get(row));
                    } else {
                        giocatoriTable.clearSelection();
                    }

                }
            }
        });

        visionaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                visualizzaGiocatore();
            }
        });

        visionaPopup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    visualizzaGiocatore();
            }
        });

        annullaPopup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                popup.hide();
            }
        });
    }

    public static void main(String[] args) {
        frame = new JFrame("Cerca Giocatori");
        frame.setContentPane(new RicercaGiocatori().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null);


    }

    public String capitalizeFirstLetter(String s){
        if(s.equals("")) return "";
        return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
    }

    private void visualizzaGiocatore(){

        LeggiGiocatore giocatoreVis = new LeggiGiocatore(controller, frame);
        giocatoreVis.frame.setVisible(true);
    }
}
