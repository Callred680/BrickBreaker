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
    private int WIDTH, HEIGHT, xPos, yPos, Move;
    private Difficulty difficulty;

    Paddle(Difficulty difficulty){
        this.difficulty = difficulty;
        this.WIDTH = PADDLE_WIDTH;  // Width of the paddle
        this.HEIGHT = PADDLE_HEIGHT; // Height (thickness) of the paddle
        this.xPos = (BOARD_WIDTH/2) - (PADDLE_WIDTH/2);   // Start position of the paddle
        this.yPos = BOARD_HEIGHT - PADDLE_HEIGHT;;   // Y position which won't change
        Move = 0;   // Amount paddle will move
    }

    public void move(Sound GameSounds) throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        // Update position of paddle
        xPos += Move;

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

    //Return the left and right sides for the paddle
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
    public int getMove(){
        return Move;
    }

    public Rectangle getPaddle(){
        return new Rectangle(xPos, yPos, WIDTH, HEIGHT);
    }

    @Override
    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_LEFT){
            Move = 0;
        }
        if(key == KeyEvent.VK_RIGHT){
            Move = 0;
        }
    }
    
    @Override
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_LEFT){
            Move = -(difficulty.getPaddleSpeed());
        }
        if(key == KeyEvent.VK_RIGHT){
            Move = difficulty.getPaddleSpeed();
        }
    }
}
