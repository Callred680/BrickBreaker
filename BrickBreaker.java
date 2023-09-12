/*
 *
 * DRIVER PROGRAM
 * 
 */
import java.awt.EventQueue;
import java.util.Scanner;

import javax.swing.JFrame;  // Creates window for JPanel to put on and other GUI components

public class BrickBreaker extends JFrame implements DEFAULTS{   // Extension of base class since nothing new is added/changed method wise
    private static int rows, columns;

    BrickBreaker(Difficulty difficulty){
        BrickBreaker.rows = difficulty.getBricks() / COLUMNS;
        BrickBreaker.columns = COLUMNS;
        CreateFrame(difficulty);
    }

    private void CreateFrame(Difficulty difficulty){
        // Establish JFrame parameters
        add(new Board(difficulty, rows));   // Add JPanel onto frame with dimensions
        setTitle("Brick Breaker");  // Sets title of frame
        setResizable(false);    // User is not able to resize window
        setDefaultCloseOperation(EXIT_ON_CLOSE);    // Program ends once exit button is clicked
        setLocationRelativeTo(null);    // Placed in center of screen
        pack(); // Sets window to preferred size
    }

    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        /*
         * TODO: Create method for obtaining user input
         *  - Also establish User login/signup before starting game
         */

        // Create frame for JPanel to be placed in/on
        System.out.println("Select difficulty:\n1. EASY\n2. MEDIUM\n3. HARD\n4. IMPOSSIBLE");
        //DEFAULTS.Difficulty dif = Difficulty.valueOf(input.nextLine());
        
        EventQueue.invokeLater(() -> {
            BrickBreaker BBGame = new BrickBreaker(Difficulty.HARD);
            BBGame.setVisible(true);
        });
    
        input.close();
    }
    
    public static int getRows(){
        return rows;
    }
    public static int getColumns(){
        return columns;
    }
}