package me.nothingelsee.GUI;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Login {

    static JFrame frame;
    private JPanel mainPanel;
    private JPanel middlePanel;
    private JTextField userField;
    private JPasswordField passField;
    private JPanel buttonPanel;
    private JButton accediButton;

    public Login() {
        implementazioneListeners();
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
