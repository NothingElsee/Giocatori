package me.nothingelsee.GUI;

import me.nothingelsee.Aesthetics.Estetica;
import me.nothingelsee.Controller.Controller;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class AggiungiTrofeo {

    JFrame frame;
    private JFrame frameChiamante;
    private Controller controller;
    private JPanel mainPanel;
    private JPanel centerPanel;
    private JComboBox tipoTrofeoBox;
    private JComboBox squadraBox;
    private JPanel buttonPanel;
    private JButton aggiungiButton;
    private JButton annullaButton;
    private JTextField dataText;
    private JLabel squadraLabel;
    private JComboBox trofeoBox;

    public AggiungiTrofeo(JFrame frameChiamante, Controller controller) {
        inizializzaComponenti(frameChiamante, controller);
        impostaEsteica();
        implementaListeners();
    }

    public void inizializzaComponenti(JFrame frameChiamante, Controller controller) {
        this.frameChiamante = frameChiamante;
        this.controller = controller;

        frame = new JFrame("Aggiungi Trofeo");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        ArrayList<String> nomiSquadre = controller.getSquadreNomi();
        for(String squadra : nomiSquadre){
            squadraBox.addItem(squadra);
        }

        ArrayList<String> nomiTrofeo = new ArrayList<>();
        controller.getTrofeiNome(nomiTrofeo);
        for(String trofeo : nomiTrofeo){
            trofeoBox.addItem(trofeo);
        }
    }

    public void impostaEsteica() {
        Estetica.setButtonColor(aggiungiButton);
        Estetica.setButtonColor(annullaButton);
    }

    public void implementaListeners() {
        annullaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                frameChiamante.setVisible(true);
            }
        });

        trofeoBox.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                checkParametri();
            }
        });

        tipoTrofeoBox.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);

                if(tipoTrofeoBox.getSelectedItem().equals("SQUADRA")){
                    squadraBox.setVisible(true);
                    squadraLabel.setVisible(true);
                }
                checkParametri();
            }
        });

        squadraBox.addFocusListener(new FocusAdapter() {
           @Override
           public void focusLost(FocusEvent e) {
               super.focusLost(e);
                checkParametri();
           }
        });

        dataText.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                checkParametri();
            }
        });

        aggiungiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aggiungiTrofeo();
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

    private void checkParametri() {
        if(trofeoBox.getSelectedItem().equals("") || dataText.getText().isEmpty() && tipoTrofeoBox.getSelectedItem() == "INDIVIDUALE" ){}
        if(trofeoBox.getSelectedItem().equals("") || dataText.getText().isEmpty() || squadraBox.getSelectedItem().equals("") && tipoTrofeoBox.getSelectedItem() == "SQUADRA" ){}
        else aggiungiButton.setEnabled(true);
    }

    public void aggiungiTrofeo() {
        int id;
        id = controller.addTrofeo(trofeoBox.getSelectedItem().toString(), (String) tipoTrofeoBox.getSelectedItem());

        if(tipoTrofeoBox.getSelectedItem() == "SQUADRA"){
            id = controller.addVittoriaSquadra(id, squadraBox.getSelectedItem().toString(), dataText.getText());
            System.out.println("Trofeo Aggiunto Individuale!");
        } else if (tipoTrofeoBox.getSelectedItem() == "INDIVIDUALE") {
            id = controller.addVittoriaIndividuale(id, controller.getGiocatoreCercato().getId(), dataText.getText());
            System.out.println("Trofeo Aggiunto Individuale!");
        }

        if(id!=0) JOptionPane.showMessageDialog(frame, "Trofeo aggiunto con successo!");
        aggiungiButton.setEnabled(false);
    }
}
