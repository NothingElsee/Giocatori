package me.nothingelsee.GUI;

import me.nothingelsee.Controller.Controller;
import me.nothingelsee.Model.Giocatore;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class TestGetGiocatori {

    private static JFrame frame;
    private JTextField nomeGiocatore;
    private JButton cercaButton;
    private JTable giocatoriTable;
    private JPanel panel;
    private JButton visionaButton;
    private Giocatore giocatoreCerca = null;
    private Controller controller;
    private JPopupMenu popup;
    private JMenuItem visionaPopup;
    private JMenuItem annullaPopup;

    public TestGetGiocatori() {
        controller = new Controller();

        popup = new JPopupMenu();
        visionaPopup = new JMenuItem("Visualizza");
        annullaPopup = new JMenuItem("Annulla");
        popup.add(visionaPopup);
        popup.add(annullaPopup);
        frame.add(popup);

        giocatoriTable.setModel(new DefaultTableModel(
                new Object[][] {
                },
                new String[] {
                        "Nome", "Cognome", "Data di Nascita'"
                }
        ));

        DefaultTableModel model = (DefaultTableModel) giocatoriTable.getModel();
        ArrayList<Giocatore> giocatoriAr = new ArrayList<>();

        cercaButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed (ActionEvent e) {
                if(nomeGiocatore.getText()!=null){
                    giocatoriAr.clear();
                    model.setRowCount(0);
                    String nome = capitalizeFirstLetter(nomeGiocatore.getText());
                    controller.getGiocatoriByName(nome, giocatoriAr);
                    if(!giocatoriAr.isEmpty()) {
                        for (int i = 0; i < giocatoriAr.size(); i++){
                            model.addRow(new Object[]{giocatoriAr.get(i).getNome(), giocatoriAr.get(i).getCognome(), giocatoriAr.get(i).getDataNascita()});
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Inserisci un giocatore prima!!");
                    }
                }
            }
        });

        giocatoriTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e){

                super.mouseClicked(e);

                if (e.getButton() == MouseEvent.BUTTON1) {

                    int row = giocatoriTable.getSelectedRow();
                    giocatoreCerca = giocatoriAr.get(row);

                } else if (e.getButton() == MouseEvent.BUTTON3) {


                    popup.show(giocatoriTable, e.getX(), e.getY());

                    int r = giocatoriTable.rowAtPoint(e.getPoint());
                    if(r >= 0 && r < giocatoriTable.getRowCount()){
                        giocatoriTable.setRowSelectionInterval(r, r);
                        int row = giocatoriTable.getSelectedRow();
                        giocatoreCerca = giocatoriAr.get(row);
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
        frame.setContentPane(new TestGetGiocatori().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null);


    }

    public String capitalizeFirstLetter(String s){
        return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
    }

    private void visualizzaGiocatore(){
        if(giocatoreCerca!=null) {
            TRy giocatoreVis = new TRy(controller, frame, giocatoreCerca);
            giocatoreVis.frame.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Seleziona un giocatore prima!!");
        }
    }
}
