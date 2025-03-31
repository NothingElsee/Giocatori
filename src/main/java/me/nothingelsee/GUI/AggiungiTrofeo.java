package me.nothingelsee.GUI;

import me.nothingelsee.Aesthetics.Estetica;
import me.nothingelsee.Controller.Controller;
import me.nothingelsee.Model.Militanza;
import me.nothingelsee.Model.Trofeo;
import raven.datetime.DatePicker;
import raven.datetime.DateSelectionAble;

import javax.swing.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * The type Aggiungi trofeo.
 */
public class AggiungiTrofeo {

    /**
     * The Frame.
     */
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
    private JButton modificaButton;
    private DatePicker dataPicker;
    private Trofeo trofeo;

    /**
     * Instantiates a new Aggiungi trofeo.
     *
     * @param frameChiamante the frame chiamante
     * @param controller     the controller
     */
    public AggiungiTrofeo(JFrame frameChiamante, Controller controller) {
        inizializzaComponenti(frameChiamante, controller);
        impostaEsteica();
        implementaListeners();
    }

    /**
     * Inizializza componenti.
     *
     * @param frameChiamante the frame chiamante
     * @param controller     the controller
     */
    public void inizializzaComponenti(JFrame frameChiamante, Controller controller) {
        this.frameChiamante = frameChiamante;
        this.controller = controller;
        trofeo = controller.getTrofeoCercato();

        frame = new JFrame("Trofeo");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setSize(300, 400);

        for (Militanza militanza : controller.getGiocatoreCercato().getMilitanze()) {
            squadraBox.addItem(militanza.getSquadra().getNome());
        }

        ArrayList<String> nomiTrofeo = new ArrayList<>();
        controller.getTrofeiNome(nomiTrofeo);
        for (String trofeo : nomiTrofeo) {
            trofeoBox.addItem(trofeo);
        }

        dataPicker = new DatePicker();
        dataPicker.setEditor(dataText);
        dataPicker.setDateSelectionMode(DatePicker.DateSelectionMode.SINGLE_DATE_SELECTED);

        if (trofeo != null) {
            caricaDati();
            modificaButton.setEnabled(true);
            aggiungiButton.setEnabled(false);
        }

        frame.setVisible(true);
    }

    /**
     * Imposta esteica.
     */
    public void impostaEsteica() {
        Estetica.setButtonColor(aggiungiButton);
        Estetica.setButtonColor(modificaButton);
        Estetica.setButtonColor(annullaButton);
    }

    /**
     * Implementa listeners.
     */
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

        tipoTrofeoBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (tipoTrofeoBox.getSelectedItem().equals("SQUADRA")) {
                    squadraBox.setVisible(true);
                    squadraLabel.setVisible(true);
                } else if (tipoTrofeoBox.getSelectedItem().equals("INDIVIDUALE")) {
                    squadraBox.setVisible(false);
                    squadraLabel.setVisible(false);
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
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                if (checkParametri()) {
                    trofeo = new Trofeo(trofeoBox.getSelectedItem().toString(), formatter.format(dataPicker.getSelectedDate()), tipoTrofeoBox.getSelectedItem().toString(), squadraBox.getSelectedItem().toString());
                    aggiungiTrofeo();
                }
            }
        });

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                super.windowClosed(e);
                frameChiamante.setVisible(true);
            }
        });

        dataPicker.setDateSelectionAble(new DateSelectionAble() {
            @Override
            public boolean isDateSelectedAble(LocalDate localDate) {
                return !localDate.isAfter(LocalDate.now());
            }
        });

        modificaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modificaTrofeo();
            }
        });
    }

    private boolean checkParametri() {
        if (trofeoBox.getSelectedItem() == null || !dataPicker.isDateSelected() && tipoTrofeoBox.getSelectedItem() == "INDIVIDUALE") {
            return false;
        }
        if (trofeoBox.getSelectedItem() == null || !dataPicker.isDateSelected() || squadraBox.getSelectedItem() == null && tipoTrofeoBox.getSelectedItem() == "SQUADRA") {
            return false;
        }
        return true;
    }

    private void aggiungiTrofeo() {
        if (trofeo.getTipo().equals("INDIVIDUALE")) {
            controller.caricaVittoriaIndividuale(controller.getGiocatoreCercato().getId(), trofeo);
        } else {
            controller.caricaVittoriaSquadra(controller.getGiocatoreCercato().getId(), trofeo);
        }

        controller.getGiocatoreCercato().getTrofei().add(trofeo);
        modificaButton.setEnabled(true);
        aggiungiButton.setEnabled(false);
    }

    private void modificaTrofeo() {
        trofeo.setNome(trofeoBox.getSelectedItem().toString());
        trofeo.setData(dataPicker.getSelectedDate());
        trofeo.setTipo(tipoTrofeoBox.getSelectedItem().toString());
        if (trofeo.getTipo().equals("SQUADRA")) {
            trofeo.setSquadra(squadraBox.getSelectedItem().toString());
            controller.deleteVittoria(trofeo);
            controller.caricaVittoriaSquadra(controller.getGiocatoreCercato().getId(), trofeo);
            frame.dispose();
            frameChiamante.setVisible(true);
            return;
        }
        controller.deleteVittoria(trofeo);
        controller.updateTrofeo(trofeo);
        controller.caricaVittoriaIndividuale(controller.getGiocatoreCercato().getId(), trofeo);
        frame.dispose();
        frameChiamante.setVisible(true);
    }

    private void caricaDati() {

        trofeoBox.addItem(trofeo.getNome());
        trofeoBox.setSelectedItem(trofeo.getNome());
        dataPicker.setSelectedDate(trofeo.getData());
        tipoTrofeoBox.setSelectedItem(trofeo.getTipo());

        if (trofeo.getTipo().equals("SQUADRA")) {
            squadraBox.setVisible(true);
            squadraBox.addItem(trofeo.getSquadra());
            squadraBox.setSelectedItem(trofeo.getSquadra());
        }
    }
}
