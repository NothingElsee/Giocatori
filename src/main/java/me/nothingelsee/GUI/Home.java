package me.nothingelsee.GUI;

import javax.swing.*;

public class Home {

    private static JFrame frame;
    private JPanel panel1;
    private JTable playerTable;
    private JTextField playerName;
    private JTextField playerSurname;
    private JButton searchButton;

    public static void main(String[] args) {
        frame = new JFrame("Home");
        frame.setContentPane(new Home().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}