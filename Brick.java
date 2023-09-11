/*
 *
 * Class for creating and accessing/manipulating the bricks on the board
 * 
 */

import java.awt.Rectangle;

public class Brick {
    
    private boolean Status;
    private int x, y;
    private static int width, height;
    
    Brick(int x, int y){
        this.x = x;
        this.y = y;
        Status = true;  // Represents not being destroyed
    }

    public static void setBricks(Brick[] Bricks){
        Bricks = new Brick[User.NUM_BRICKS];

        // Allows space to be around bricks and adjust to the set frame size
        int space = BrickBreaker.FRAME_WIDTH / BrickBreaker.FRAME_HEIGHT;
        // Brick sizes given based on frame width / number of columns, frame height / number of columns*2 (only want bricks in the first half of frame)
        int xPos = BrickBreaker.FRAME_WIDTH / BrickBreaker.getColumns(), yPos = BrickBreaker.FRAME_HEIGHT / (BrickBreaker.getColumns()*2);   
        
        width = xPos;
        height = yPos;

        for(int i = 1; i <= User.NUM_BRICKS; i++){
            // Each brick will be offsetted by a factor based on the Frames height and width at the given time
            Bricks[i-1] = new Brick(xPos*(i%12) + space, yPos*(i/12) + space);
            /*
             * i%12 = Multiplies the xPos by a factor of [1,11]
             * i/12 = Multiplies the yPos by a factor of [1,3]
             */
        }
    }

    public boolean checkStatus(){
        return this.Status;
    }
    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }

    public Rectangle getRectangle(){
        return new Rectangle(x, y, width, height);
    }
}
