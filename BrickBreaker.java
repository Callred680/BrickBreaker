/*
 *
 * Starting point for running program
 * 
 */

import java.awt.EventQueue;

import javax.swing.JFrame;  // Creates window for JPanel to put on and other GUI components

public class BrickBreaker extends JFrame implements DEFAULTS{   // Extension of base class since nothing new is added/changed method wise
    private static int rows, columns;
    private static Menu menu;
    public static BrickBreaker BB;

    BrickBreaker(Difficulty difficulty){
        BrickBreaker.rows = difficulty.getBricks() / COLUMNS;
        BrickBreaker.columns = COLUMNS;
        CreateFrame(difficulty);
    }

    private void CreateFrame(Difficulty difficulty){
        // Establish JFrame parameters
        add(new GamePlay(difficulty, rows, menu));   // Add JPanel onto frame with dimensions
        setTitle("Brick Breaker");  // Sets title of frame
        setResizable(false);    // User is not able to resize window
        setDefaultCloseOperation(EXIT_ON_CLOSE);    // Program ends once exit button is clicked
        setLocationRelativeTo(null);    // Placed in center of screen
        pack(); // Sets window to preferred size
    }

    public static void main(String[] args){
        EventQueue.invokeLater(() -> {
            menu = new Menu();
            menu.setVisible(true);
        });
    }
    
    public static int getRows(){
        return rows;
    }
    public static int getColumns(){
        return columns;
    }
}