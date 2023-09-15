/*
 * 
 * Class for tracking game stats
 * 
 */
public class GameStats {
    private boolean GameStatus; // Track if game ended in win or loss
    private int Score;
    private long TimeToBeat;
    private int Date;   // TODO: Add function to track date when game occurred
    private DEFAULTS.Difficulty difficulty;

    GameStats(int Score, DEFAULTS.Difficulty difficulty, long TimeToBeat, boolean GameStatus){
        this.Score = Score;
        this.difficulty = difficulty;
        this.TimeToBeat = TimeToBeat;
        this.GameStatus = GameStatus;
    }

    public int getScore(){
        return this.Score;
    }
    public long getTimeToBeat(){
        return this.TimeToBeat;
    }
    public int getDate(){
        return this.Date;
    }
    public DEFAULTS.Difficulty getDifficulty(){
        return this.difficulty;
    }
    public boolean getGameStatus(){
        return this.GameStatus;
    }
}
