/*
 *
 * Class for creating and accessing/manipulating the ball(s) on the board
 *  - Future versions include possibility of multiple balls so individual class needed for simplicity
 * 
 */

import java.awt.geom.RoundRectangle2D;

public class Ball{
    private int WIDTH, HEIGHT, xPos, yPos;
    public int MoveX, MoveY;

    // Start ball initially falling at an angle, otherwise it will just bounce vertically
    Ball(int WIDTH, int HEIGHT){
        MoveX = 1;
        MoveY = -1;
        this.WIDTH = WIDTH/User.NUM_BRICKS;
        this.HEIGHT = HEIGHT/User.NUM_BRICKS; 
    }

    public void move(){
        // Update position of ball
        xPos += MoveX;
        yPos += MoveY;
        // Check if paddle exceeds borders
        if(xPos <= 0 ){
            MoveX = 1;
        }
        if(xPos >= User.BOARD_WIDTH - WIDTH){
            MoveX = -1;
        }
        if(yPos <= 0){
            MoveY = 1;
        }
    }

    // Returns center of ball for use in collision detection/response
    public int getX(){
        return this.xPos - ((this.WIDTH - this.xPos) / 2);
    }

    public RoundRectangle2D getBall(){
        return new RoundRectangle2D.Double(50.0, 50.0, HEIGHT, WIDTH, xPos, yPos);
    }
}
