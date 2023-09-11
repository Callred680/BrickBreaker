/*
 *
 * DRIVER PROGRAM
 * 
 */
import javax.swing.JFrame;  // Creates window for JPanel to put on and other GUI components

public class BrickBreaker extends JFrame{   // Extension of base class since nothing new is added/changed method wise
    public static int FRAME_WIDTH = 400, FRAME_HEIGHT = 400;

    private static int rows, columns;

    BrickBreaker(int rows, int columns){
        BrickBreaker.rows = rows;
        BrickBreaker.columns = columns;
        CreateFrame();
    }

    private void CreateFrame(){
        // Establish JFrame parameters
        FRAME_HEIGHT = User.BOARD_HEIGHT;     // Sets frame height for usage in other classes
        FRAME_WIDTH = User.BOARD_WIDTH;       // Sets frame weight for usage in other classes
        add(new Board(User.BOARD_WIDTH, User.BOARD_HEIGHT));   // Add JPanel onto frame with dimensions
        setTitle("Brick Breaker");  // Sets title of frame
        setResizable(false);    // User is not able to resize window
        setDefaultCloseOperation(EXIT_ON_CLOSE);    // Program ends once exit button is clicked
        setLocationRelativeTo(null);    // Placed in center of screen
        pack(); // Sets window to preferred size
    }

    public static void main(String[] args){

        /*
         * TODO: Create method for obtaining user input
         *  - Also establish User login/signup before starting game
         */

        // Create frame for JPanel to be placed in/on
        BrickBreaker BBGame = new BrickBreaker(3, 12);
        BBGame.setVisible(true);
    }
    
    public static int getRows(){
        return rows;
    }
    public static int getColumns(){
        return columns;
    }
}