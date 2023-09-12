/*
 *
 * Class for creating and accessing/manipulating the bricks on the board
 * 
 */

import java.awt.Rectangle;

public class Brick implements DEFAULTS{
    
    private boolean Destroyed;
    private int x, y;
    
    Brick(int x, int y){
        this.x = x;
        this.y = y;
        Destroyed = false;  // Represents not being destroyed
    }

    public static void setBricks(Brick[] Bricks, int ROWS){

        for(int i = 0; i < Bricks.length; i++){
            // Each brick will be offsetted by a factor based on the Frames height and width at the given time
            Bricks[i] = new Brick((BRICK_WIDTH*(i%COLUMNS)) + BRICK_WIDTH, (BRICK_HEIGHT*(i/COLUMNS)) + BRICK_HEIGHT);  // Add brick widht/height for space between bricks and borders
            /*
             * i%12 = Multiplies the x position by a factor of [0,11]
             * i/12 = Multiplies the y position by a factor of [0,2]
             */
        }
    }

    public boolean checkStatus(){
        return this.Destroyed;
    }
    public void setStatus(){
        this.Destroyed = true;
    }
    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }

    public Rectangle getRectangle(){
        return new Rectangle(x, y, BRICK_WIDTH, BRICK_HEIGHT);
    }
}
