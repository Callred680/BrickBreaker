/*
 *
 * Class for creating and accessing/manipulating the paddle on the board
 * 
 */

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.awt.Rectangle;

public class Paddle extends KeyAdapter implements DEFAULTS{
    private static int WIDTH, HEIGHT, MoveX;
    private Difficulty difficulty;
    private static Rectangle PaddleShape;

    Paddle(Difficulty difficulty){
        this.difficulty = difficulty;
        WIDTH = PADDLE_WIDTH;  // Width of the paddle
        HEIGHT = PADDLE_HEIGHT; // Height (thickness) of the paddle
        MoveX = 0;   // Amount paddle will move
        PaddleShape = new Rectangle((BOARD_WIDTH/2) - (PADDLE_WIDTH/2), BOARD_HEIGHT - PADDLE_HEIGHT, WIDTH, HEIGHT);
    }

    public void move(){
        // Update position of paddle
        PaddleShape.x += MoveX;

        // Check if paddle exceeds borders
        if(PaddleShape.x <= 0 ){
            PaddleShape.x = 0;
            //GameSounds.HIT_sound(BALL_BORDER_SOUND);
        }
        if(PaddleShape.x >= BOARD_WIDTH - WIDTH){
            PaddleShape.x = BOARD_WIDTH - WIDTH;
            //GameSounds.HIT_sound(BALL_BORDER_SOUND);
        }
        
    }

    // Returns ball as object to be drawn
    public Rectangle getPaddle(){
        return PaddleShape;
    }
    
    // Return the left and right sides for the paddle
    public int getLeft(){
        return PaddleShape.x;
    }
    public int getRight(){
        return PaddleShape.x + WIDTH;
    }

    // Return set width for paddle
    public int getWIDTH(){
        return WIDTH;
    }
    public int getHEIGHT(){
        return HEIGHT;
    }
    public int getXPos(){
        return PaddleShape.x;
    }
    public int getYPos(){
        return PaddleShape.y;
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
