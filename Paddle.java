/*
 *
 * Class for creating and accessing/manipulating the paddle on the board
 * 
 */

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.RoundRectangle2D;

public class Paddle extends KeyAdapter{
    private int WIDTH, HEIGHT, xPos, Move;

    Paddle(int WIDTH, int HEIGHT, int xPos, int yPos){
        this.WIDTH = HEIGHT/(WIDTH/2);  // Width of the paddle
        this.HEIGHT = HEIGHT/(WIDTH/4); // Height (thickness) of the paddle
        this.xPos = xPos;   // Current position of the paddle
        Move = 0;   // Amount paddle will move
    }

    public void move(){
        // Update position of paddle
        xPos += Move;

        // Check if paddle exceeds borders
        if(xPos <= 0 ){
            xPos = 0;
        }
        if(xPos >= User.BOARD_WIDTH - WIDTH){
            xPos = User.BOARD_WIDTH - WIDTH;
        }
        
    }

    public int getLeft(){
        return this.xPos;
    }
    public int getRight(){
        return this.xPos + this.WIDTH;
    }
    public int getWidth(){
        return this.WIDTH;
    }

    public RoundRectangle2D getPaddle(){
        return new RoundRectangle2D.Double(50.0, 50.0, HEIGHT, WIDTH, xPos, User.BOARD_HEIGHT-(HEIGHT/2));
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
            Move = -1;
        }
        if(key == KeyEvent.VK_RIGHT){
            Move = 1;
        }
    }
}
