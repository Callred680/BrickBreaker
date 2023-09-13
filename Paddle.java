/*
 *
 * Class for creating and accessing/manipulating the paddle on the board
 * 
 */

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import java.awt.Rectangle;

public class Paddle extends KeyAdapter implements DEFAULTS{
    private int WIDTH, HEIGHT, xPos, yPos, MoveX;
    private Difficulty difficulty;

    Paddle(Difficulty difficulty){
        this.difficulty = difficulty;
        this.WIDTH = PADDLE_WIDTH;  // Width of the paddle
        this.HEIGHT = PADDLE_HEIGHT; // Height (thickness) of the paddle
        this.xPos = (BOARD_WIDTH/2) - (PADDLE_WIDTH/2);   // Start position of the paddle
        this.yPos = BOARD_HEIGHT - PADDLE_HEIGHT;;   // Y position which won't change
        MoveX = 0;   // Amount paddle will move
    }

    public void move(Sound GameSounds) throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        // Update position of paddle
        xPos += MoveX;

        // Check if paddle exceeds borders
        if(xPos <= 0 ){
            xPos = 0;
            //GameSounds.HIT_sound(BALL_BORDER_SOUND);
        }
        if(xPos >= BOARD_WIDTH - WIDTH){
            xPos = BOARD_WIDTH - WIDTH;
            //GameSounds.HIT_sound(BALL_BORDER_SOUND);
        }
        
    }

    // Returns ball as object to be drawn
    public Rectangle getPaddle(){
        return new Rectangle(xPos, yPos, WIDTH, HEIGHT);
    }
    
    // Return the left and right sides for the paddle
    public int getLeft(){
        return this.xPos;
    }
    public int getRight(){
        return this.xPos + this.WIDTH;
    }

    // Return set width for paddle
    public int getWIDTH(){
        return this.WIDTH;
    }
    public int getHEIGHT(){
        return this.HEIGHT;
    }
    public int getXPos(){
        return this.xPos;
    }
    public int getYPos(){
        return this.yPos;
    }
    public int getMoveX(){
        return MoveX;
    }

    @Override
    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_LEFT){
            MoveX = 0;
        }
        if(key == KeyEvent.VK_RIGHT){
            MoveX = 0;
        }
    }
    
    @Override
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_LEFT){
            MoveX = -(difficulty.getPaddleSpeed());
        }
        if(key == KeyEvent.VK_RIGHT){
            MoveX = difficulty.getPaddleSpeed();
        }
    }
}
