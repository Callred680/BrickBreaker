/*
 *
 * Class for creating and accessing/manipulating the ball(s) on the board
 *  - Future versions include possibility of multiple balls so individual class needed for simplicity
 * 
 */

import java.awt.Rectangle;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Ball implements DEFAULTS{
    private static int WIDTH, HEIGHT, MoveX, MoveY;
    private Difficulty difficulty;
    private static Rectangle BallShape;    // Ball image with position coordinates built in

    // Start ball initially falling at an angle, otherwise it will just bounce vertically
    Ball(Difficulty difficulty){
        this.difficulty = difficulty;
        MoveX = difficulty.getBallSpeed();
        MoveY = -(difficulty.getBallSpeed());
        WIDTH = BALL_WIDTH;
        HEIGHT = BALL_HEIGHT; 
        BallShape = new Rectangle(BOARD_WIDTH / 2,  BOARD_HEIGHT / 2, WIDTH, HEIGHT);
    }

    public void move(Sound Ball_Border_Sound) throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        // Reset sound if already played
        Ball_Border_Sound.Rewind_Sound();

        // Update position of ball
        BallShape.x += MoveX;
        BallShape.y += MoveY;

        // Check if paddle exceeds borders
        if(BallShape.x <= 0 ){
            MoveX = difficulty.getBallSpeed();
            Ball_Border_Sound.Play_Sound();
        }else if(BallShape.x >= BOARD_WIDTH - WIDTH){
            MoveX = -(difficulty.getBallSpeed());
            Ball_Border_Sound.Play_Sound();
        }else if(BallShape.y <= 0){
            MoveY = difficulty.getBallSpeed();
            Ball_Border_Sound.Play_Sound();
        }
    }
    
    // Returns ball as object to be drawn
    public Rectangle getBall(){
        return BallShape;
    }

    // Returns center of ball for use in collision detection/response
    public int getXPos(){
        return BallShape.x;
    }
    public int getYPos(){
        return BallShape.y;
    }
    public int getWIDTH(){
        return WIDTH;
    }
    public int getHEIGHT(){
        return HEIGHT;
    }
    public int getMoveX(){
        return MoveX;
    }
    public int getMoveY(){
        return MoveY;
    }

    public void setMoveX(int NewMoveX){
        MoveX = NewMoveX;
    }
    public void setMoveY(int NewMoveY){
        MoveY = NewMoveY;
    }
}
