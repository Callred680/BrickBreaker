/*
 * Class for creating board and calculating collisions/interactions
 *  - powerups
 *  - score
 *  - drawing components
 */

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

import javax.swing.JPanel;

public class Board extends JPanel{
    
    private int WIDTH, HEIGHT;
    private Brick[] Bricks;
    private Paddle paddle;
    private Ball ball;
    private Timer timer;

    // Set width and height for game window
    Board(int WIDTH, int HEIGHT){
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        Brick.setBricks(Bricks);
        paddle = new Paddle(WIDTH, HEIGHT, WIDTH/3, 0);
        ball = new Ball(WIDTH, HEIGHT);

        addKeyListener(paddle);   // Private class that overrides 
        setFocusable(true);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));   // Set JPanel to that of the JFrame size accordingly

        timer = new Timer(10, new Game());
        timer.start();

        //Game g = new Game(); // Starts game
    }

    private class Game implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e){
            updateGame();   // Check game status and update accordingly
        }
    }

    private void updateGame(){
        ball.move();
        paddle.move();
        StatusCheck();
        repaint();
    }

    private void StatusCheck(){
        // If the ball reaches the bottom of the board, end game
        if(ball.getBall().getMaxY() >= User.BOARD_HEIGHT){
            GameOver("You Lost!");
        }

        // Check status of each brick
        boolean empty = true;
        for(int i = 0; i < User.NUM_BRICKS; i++){
            if(Bricks[i].checkStatus()){
                empty = false;
                break;
            }
        }
        if(empty){
            GameOver("Congratulations! You won!");
        }

        // Check if the ball as collided with the paddle for ball to ricochet approprietly
        if(ball.getBall().intersects(paddle.getPaddle().getX(), paddle.getPaddle().getY(), paddle.getPaddle().getWidth(), paddle.getPaddle().getHeight())){
            int left = paddle.getLeft() + (paddle.getWidth()/3);
            int right = paddle.getRight() - (paddle.getWidth()/3);
            if(ball.getX() < left){
                ball.MoveX = -1;
                ball.MoveY = -1;
            }else if(ball.getX() >= left && ball.getX() < right){
                ball.MoveX = 0;
                ball.MoveY = -1;
            }else{
                ball.MoveX = 1;
                ball.MoveY = -1;
            }
        }

    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        
        
        drawComponents(g2);

        Toolkit.getDefaultToolkit().sync();
    }

    private void drawComponents(Graphics2D g2){
        g2.draw(ball.getBall());
        g2.draw(paddle.getPaddle());

        // Draw bricks not yet destroyed
        for(int i = 0; i < Bricks.length; i++){
            if(!Bricks[i].checkStatus()){
                g2.draw(Bricks[i].getRectangle());
            }
        }
    }

    private void GameOver(String message){

    }
}
