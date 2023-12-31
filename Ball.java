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
    private int WIDTH, HEIGHT, xPos, yPos, MoveX, MoveY;
    private Difficulty difficulty;

    // Start ball initially falling at an angle, otherwise it will just bounce vertically
    Ball(Difficulty difficulty){
        this.difficulty = difficulty;
        MoveX = difficulty.getBallSpeed();
        MoveY = -(difficulty.getBallSpeed());
        this.WIDTH = BALL_WIDTH;
        this.HEIGHT = BALL_HEIGHT; 
        xPos = BOARD_WIDTH / 2;
        yPos = BOARD_HEIGHT / 2;
    }

    public void move(Sound GameSounds) throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        // Update position of ball
        xPos += MoveX;
        yPos += MoveY;

        // Check if paddle exceeds borders
        if(xPos <= 0 ){
            MoveX = difficulty.getBallSpeed();
            GameSounds.HIT_sound(BALL_BORDER_SOUND);
        }
        if(xPos >= BOARD_WIDTH - WIDTH){
            MoveX = -(difficulty.getBallSpeed());
            GameSounds.HIT_sound(BALL_BORDER_SOUND);
        }
        if(yPos <= 0){
            MoveY = difficulty.getBallSpeed();
            GameSounds.HIT_sound(BALL_BORDER_SOUND);
        }
    }
    
    // Returns ball as object to be drawn
    public Rectangle getBall(){
        return new Rectangle(xPos, yPos, WIDTH, HEIGHT);
    }

    // Returns center of ball for use in collision detection/response
    public int getXPos(){
        return this.xPos;
    }
    public int getYPos(){
        return this.yPos;
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

    public void setMoveX(int MoveX){
        this.MoveX = MoveX;
    }
    public void setMoveY(int MoveY){
        this.MoveY = MoveY;
    }
}
