package me.nothingelsee.GUI;

import me.nothingelsee.Aesthetics.Estetica;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home {

    private static JFrame frame;
    private JPanel mainPanel;
    private JPanel middlePanel;
    private JButton guestButton;
    private JButton adminButton;
    private JPanel topPanel;
    private JPanel buttonPanel;
    private JPanel selectPanel;

    public Home() {
        impostaEstetica();
        implementazioneListeners();
    }

    private static void inizializzaComponenti() {

        frame = new JFrame("UNINA- Player Search");
        frame.setContentPane(new Home().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setSize(400,450);
    }

    private void impostaEstetica() {

        mainPanel.setBackground(Estetica.mainBackgroundColor);
        middlePanel.setBackground(Estetica.bluPetrolio);
        topPanel.setBackground(Estetica.corallo);

        Estetica.setButtonColor(adminButton);
        Estetica.setButtonColor(guestButton);
    }

    private void implementazioneListeners() {

        guestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){

                RicercaGiocatori giocatori = new RicercaGiocatori(frame, false);
                frame.setVisible(false);
            }
        });

        adminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                Login login = new Login(frame);
            }
        });
    }

    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch(Exception ignored){}

        inizializzaComponenti();
    }

}