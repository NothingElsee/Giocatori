package me.nothingelsee.GUI;

import me.nothingelsee.Aesthetics.Estetica;
import me.nothingelsee.Controller.Controller;
import me.nothingelsee.InterfacceDAO.TrofeoDAO;
import me.nothingelsee.Model.Trofeo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LeggiTrofei {

    JFrame frame;
    private Controller controller;
    private JPanel mainPanel;
    private JPanel buttonPanel;
    private JButton chiudiButton;
    private JTable trofeiTable;
    private JScrollPane trofeiScrollPane;

    public LeggiTrofei (Controller controller,JFrame frameChiamante){

        inizializzaComponenti(controller, frameChiamante);
        impostaEstetica();
        caricaDati();
        implementaListeners();

    }

    private void inizializzaComponenti (Controller controller, JFrame frameChiamante){
        this.controller = controller;
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
    }
}
