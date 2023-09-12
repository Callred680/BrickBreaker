/*
 *
 * Class for creating and accessing/manipulating the ball(s) on the board
 *  - Future versions include possibility of multiple balls so individual class needed for simplicity
 * 
 */

import java.awt.Rectangle;

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

    public void move(){
        // Update position of ball
        xPos += MoveX;
        yPos += MoveY;

        // Check if paddle exceeds borders
        if(xPos <= 0 ){
            MoveX = difficulty.getBallSpeed();
        }
        if(xPos >= BOARD_WIDTH - WIDTH){
            MoveX = -(difficulty.getBallSpeed());
        }
        if(yPos <= 0){
            MoveY = difficulty.getBallSpeed();
        }
    }
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

    public void setMoveX(int MoveX){
        this.MoveX = MoveX;
    }
    public void setMoveY(int MoveY){
        this.MoveY = MoveY;
    }
}
