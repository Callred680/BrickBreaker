/*
 *
 * Class for creating and accessing/manipulating the bricks on the board
 * 
 */

import java.awt.Rectangle;
import java.util.LinkedList;

public class Brick implements DEFAULTS{
    
    private Rectangle BrickShape;
    
    Brick(int x, int y){
        BrickShape = new Rectangle(x, y, BRICK_WIDTH, BRICK_HEIGHT);
    }

    public static LinkedList<Brick> setBricks(LinkedList<Brick> Bricks, int ROWS, int BRICK_COUNT){

        for(int i = 0; i < BRICK_COUNT; i++){
            // Each brick will be offsetted by a factor based on the Frames height and width at the given time
            Bricks.add(new Brick((BRICK_WIDTH*(i%COLUMNS)) + BRICK_WIDTH, (BRICK_HEIGHT*(i/COLUMNS)) + BRICK_HEIGHT));  // Add brick widht/height for space between bricks and borders
            /*
             * i%12 = Multiplies the x position by a factor of [0,11]
             * i/12 = Multiplies the y position by a factor of [0,2]
             */
        }
        return Bricks;
    }

    public int getX(){
        return this.BrickShape.x;
    }
    public int getY(){
        return this.BrickShape.y;
    }

    public Rectangle getRectangle(){
        return BrickShape;
    }
}
