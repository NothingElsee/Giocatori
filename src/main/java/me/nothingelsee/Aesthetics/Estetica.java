package me.nothingelsee.Aesthetics;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;

/**
 * The type Estetica.
 */
public class Estetica {

    /**
     * The constant corallo.
     */
//Colori Personalizzati
    public static final Color corallo = new Color(255, 90, 80);
    /**
     * The constant bluPetrolio.
     */
    public static final Color bluPetrolio = new Color(0, 100, 120);
    /**
     * The constant bluAcqua.
     */
    public static final Color bluAcqua = new Color(30, 150, 170);
    /**
     * The constant mainBackgroundColor.
     */
    public static final Color mainBackgroundColor = corallo; //new Color(245, 245, 245); //Grigio Chiaro
    /**
     * The constant filterBackgorundColor.
     */
    public static final Color filterBackgorundColor = bluAcqua; //new Color(230, 240, 250); //Azzurro
    /**
     * The constant buttonBackgroundColor.
     */
    public static final Color buttonBackgroundColor = new Color(70, 130, 180); // Blu
    /**
     * The constant buttonForegroundColor.
     */
    public static final Color buttonForegroundColor = Color.WHITE;
    /**
     * The constant tableHeaderBackgroundColor.
     */
    public static final Color tableHeaderBackgroundColor = new Color(70, 130, 180); // Blu
    /**
     * The constant tableHeaderForegroundColor.
     */
    public static final Color tableHeaderForegroundColor = new Color(255, 160, 122);

    /**
     * Sets button color.
     *
     * @param button the button
     */
    public static void setButtonColor(JButton button) {

        button.setBackground(buttonBackgroundColor);
        button.setForeground(buttonForegroundColor);
        button.setFocusPainted(false);
    }

    /**
     * Sets menu item color.
     *
     * @param menuItem the menu item
     */
    public static void setMenuItemColor(JMenuItem menuItem) {

        menuItem.setBackground(Color.BLACK);
        menuItem.setForeground(buttonForegroundColor);
    }

    /**
     * Sets header table.
     *
     * @param table the table
     */
    public static void setHeaderTable(JTable table) {
        //Intestazione tabella
        JTableHeader header = table.getTableHeader();
        header.setBackground(tableHeaderBackgroundColor);
        header.setForeground(tableHeaderForegroundColor);
        header.setFont(new Font("Arial", Font.BOLD, 12));
    }
}
