package me.nothingelsee.GUI;

import me.nothingelsee.Controller.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Login {

    private Controller controller;
    static JFrame frame;
    private JPanel mainPanel;
    private JPanel middlePanel;
    private JTextField userField;
    private JPasswordField passField;
    private JPanel buttonPanel;
    private JButton accediButton;
    private String username;
    private String password;

    public Login() {
        inizializzazioneComponenti();
        implementazioneListeners();
    }

    private void inizializzazioneComponenti() {
        controller = new Controller();
    }

    private void implementazioneListeners() {

        userField.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e){

                userField.setText("");
            }
        });

        passField.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e){
                passField.setText("");
            }
        });

        accediButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){

                username = userField.getText();
                password = passField.getText();

                if(controller.isIn(username, password)){

                } else {
                    JOptionPane.showMessageDialog(frame, "Utente: " + username + " non trovato", "Errore", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public static void main(String[] args) {
        frame = new JFrame("Login");
        frame.setContentPane(new Login().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

}
