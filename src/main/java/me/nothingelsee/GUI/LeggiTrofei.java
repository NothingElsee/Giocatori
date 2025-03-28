package me.nothingelsee.GUI;

import me.nothingelsee.Aesthetics.Estetica;
import me.nothingelsee.Controller.Controller;
import me.nothingelsee.Model.Trofeo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class LeggiTrofei {

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
    private boolean canModify = false;

    public LeggiTrofei(Controller controller, JFrame frameChiamante, boolean canModify){

        inizializzaComponenti(controller, frameChiamante, canModify);
        impostaEstetica();
        caricaDati();
        implementaListeners();
    }

    private void inizializzaComponenti (Controller controller, JFrame frameChiamante, boolean canModify){
        this.controller = controller;
        this.canModify = canModify;
        this.frameChiamante = frameChiamante;
        frame = new JFrame("Trofei");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setContentPane(mainPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);



        trofeiTable.setModel(new DefaultTableModel(
                new Object [][]{},
                new String [] {"Nome", "Data", "Tipo", "Squadra"}
        ));
    }

    private void impostaEstetica() {

        mainPanel.setBackground(Estetica.mainBackgroundColor);
        trofeiScrollPane.setBackground(Estetica.filterBackgorundColor);
        Estetica.setButtonColor(chiudiButton);
        Estetica.setHeaderTable(trofeiTable);
    }

    private void caricaDati() {

        DefaultTableModel model = (DefaultTableModel) trofeiTable.getModel();
        ArrayList<Trofeo> trofei = controller.getGiocatoreCercato().getTrofei();

        for(Trofeo t : trofei){
            model.addRow(new Object[]{t.getNome(), t.getData(), t.getTipo(), t.getSquadra()});
        }
    }

    private void implementaListeners() {
        chiudiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        aggiungiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AggiungiTrofeo aggiungiTrofeo = new AggiungiTrofeo(frame, controller);
                frame.setVisible(false);
                frameChiamante.setVisible(false);
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
}
