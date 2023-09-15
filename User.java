/*
 * Class for containing user preferences [i.e. board size, color, scores, etc.]
 */

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class User {

    // ID can be any configuration
    private String ID;
    private int User_BOARD_WIDTH, User_BOARD_HEIGHT;

    private ArrayList<GameStats> Best_Games = new ArrayList<>();
    private ArrayList<GameStats> Recent_Games = new ArrayList<>();

    static String PADDLE_COLOR = "RED",
                  BALL_COLOR = "BLACK",
                  BRICK_COLOR = "GREEN";

    User(){
        // Set max size for game history
        Best_Games = new ArrayList<>(5);
        Recent_Games = new ArrayList<>(5);
        CreateUser();
    }

    User(String ID){
        // Get game records based on inputted ID
        getGameRecords(Best_Games, Recent_Games, ID);
    }
    
    // Add game into recent most recent games and check if game belongs in best game list
    public void AddGame(GameStats new_game){
        // 
    }
    // Get game records when user signs in
    public void getGameRecords(ArrayList<GameStats> Best_Games, ArrayList<GameStats> RecentGames, String ID){

    }
    // Write games into game records
    public void WriteToFile(){

    }
    // Create user ID if no User is inputted
    public void CreateUser(){
        this.ID = JOptionPane.showInputDialog(null, "Please input desired ID", "");
    }

    public ArrayList<GameStats> GetBestGames(){
        return Best_Games;
    }
    public ArrayList<GameStats> GetRecentGames(){
        return Recent_Games;
    }
}
