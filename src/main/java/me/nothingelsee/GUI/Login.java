package me.nothingelsee.GUI;

import me.nothingelsee.Aesthetics.Estetica;
import me.nothingelsee.Controller.Controller;

import javax.swing.*;
import java.awt.event.*;

/**
 * The type Login.
 */
public class Login {

    private Controller controller;
    /**
     * The Frame.
     */
    static JFrame frame;
    private JFrame frameChiamante;
    private JPanel mainPanel;
    private JPanel middlePanel;
    private JTextField userField;
    private JPasswordField passField;
    private JPanel buttonPanel;
    private JButton accediButton;
    private String username;
    private String password;

    /**
     * Instantiates a new Login.
     *
     * @param frameChiamante the frame chiamante
     */
    public Login(JFrame frameChiamante) {
        inizializzazioneComponenti(frameChiamante);
        impostaEstetica();
        implementazioneListeners();
    }

    private void inizializzazioneComponenti(JFrame frameChiamante) {
        frame = new JFrame("Login");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        this.frameChiamante = frameChiamante;
        controller = new Controller();
    }

    private void impostaEstetica() {

        Estetica.setButtonColor(accediButton);
    }

    private void implementazioneListeners() {

        userField.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                userField.setText("");
            }
        });

        passField.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                passField.setText("");
            }
        });


        accediButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                accediButtonFun();
            }
        });

        userField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    accediButtonFun();
                }
                if (e.getKeyCode() == KeyEvent.VK_TAB) {
                    passField.setText("");
                }
            }
        });

        passField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    accediButtonFun();
                }
            }
        });
    }

    private void accediButtonFun() {
        username = userField.getText();
        password = passField.getText();

        if (controller.isIn(username, password)) {
            JOptionPane.showMessageDialog(frame, "Accesso eseguito con successo!");
            frameChiamante.setVisible(false);
            frame.dispose();
            RicercaGiocatori ricercaGiocatori = new RicercaGiocatori(frameChiamante, true);
        } else {
            JOptionPane.showMessageDialog(frame, "Utente: " + username + " non trovato", "Errore", JOptionPane.ERROR_MESSAGE);
        }
    }
}

