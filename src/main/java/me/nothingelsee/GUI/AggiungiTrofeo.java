package me.nothingelsee.GUI;

import me.nothingelsee.Aesthetics.Estetica;
import me.nothingelsee.Controller.Controller;
import me.nothingelsee.Model.Trofeo;
import raven.datetime.DatePicker;

import javax.swing.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
    private JFormattedTextField dataText;
    private JLabel squadraLabel;
    private JComboBox trofeoBox;
    private DatePicker dataPicker;
    private Trofeo trofeo;

    public AggiungiTrofeo(JFrame frameChiamante, Controller controller) {
        inizializzaComponenti(frameChiamante, controller);
        impostaEsteica();
        implementaListeners();
    }

    public void inizializzaComponenti(JFrame frameChiamante, Controller controller) {
        this.frameChiamante = frameChiamante;
        this.controller = controller;
        trofeo = controller.getTrofeoCercato();

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
        dataPicker = new DatePicker();
        dataPicker.setEditor(dataText);

        if(trofeo != null){
            caricaDati();
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
        if(trofeoBox.getSelectedItem().equals("") || !dataPicker.isDateSelected() && tipoTrofeoBox.getSelectedItem() == "INDIVIDUALE" ){}
        if(trofeoBox.getSelectedItem().equals("") || !dataPicker.isDateSelected() || squadraBox.getSelectedItem().equals("") && tipoTrofeoBox.getSelectedItem() == "SQUADRA" ){}
        else aggiungiButton.setEnabled(true);
    }

    public void aggiungiTrofeo() {

        controller.getGiocatoreCercato().getTrofei().add(trofeo);
        aggiungiButton.setEnabled(false);

        frame.setVisible(false);
        frameChiamante.setVisible(true);
    }

    private void caricaDati() {

        trofeoBox.addItem(trofeo.getNome());
        trofeoBox.setSelectedItem(trofeo.getNome());
        dataPicker.setSelectedDate(LocalDate.parse(trofeo.getData()));
        tipoTrofeoBox.setSelectedItem(trofeo.getTipo());

        if(trofeo.getTipo().equals("SQUADRA")){
            squadraBox.setVisible(true);
            squadraBox.addItem(trofeo.getSquadra());
            squadraBox.setSelectedItem(trofeo.getSquadra());

        } else if (trofeo.getTipo().equals("INDIVIDUALE")) {
            squadraBox.setVisible(false);
        }

    }
}
