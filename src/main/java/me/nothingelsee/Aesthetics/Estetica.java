package me.nothingelsee.Aesthetics;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class Estetica {

    //Colori Personalizzati
    public static final Color mainBackgroundColor = new Color(245, 245, 245); //Grigio Chiaro
    public static final Color corallo = new Color(255, 90, 80);
    public static final Color bluPetrolio = new Color(0, 100, 120);
    public static final Color filterBackgorundColor = new Color(230, 240, 250); //Azzurro
    public static final Color buttonBackgroundColor = new Color(70, 130, 180); // Blu
    public static final Color buttonForegroundColor = Color.WHITE;
    public static final Color tableHeaderBackgroundColor = new Color(70, 130, 180); // Blu
    public static final Color tableHeaderForegroundColor = Color.WHITE;

    public static void setButtonColor(JButton button) {

        button.setBackground(buttonBackgroundColor);
        button.setForeground(buttonForegroundColor);
        button.setFocusPainted(false);
    }

    public static void setMenuItemColor(JMenuItem menuItem) {

        menuItem.setBackground(buttonBackgroundColor);
        menuItem.setForeground(buttonForegroundColor);
    }

    public static void setHeaderTable (JTable table) {
        //Intestazione tabella
        JTableHeader header = table.getTableHeader();
        header.setBackground(tableHeaderBackgroundColor);
        header.setForeground(tableHeaderForegroundColor);
        header.setFont(new Font("Arial", Font.BOLD, 12));
    }
}
