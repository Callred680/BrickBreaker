/*
 * Class for creating board and calculating collisions/interactions
 *  - powerups
 *  - score
 *  - drawing components
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.Timer;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GamePlay extends JPanel implements DEFAULTS{
    
    // Must be ConcurrentHashMap as objects will be remove and checked concurrently
    private LinkedList<Brick> Bricks;  // Create set with thread-safe hash map by changing key set once initialized

    // private Brick[] Bricks;
    private static Paddle paddle;
    private static Ball ball;
    private static Timer timer;
    private static Difficulty difficulty;
    private static Sound Ball_Border_Sound, Ball_Brick_Sound, Ball_Paddle_Sound, Win_Sound, Lose_Sound;
    private static Menu menu;
    private static int Score;
    private boolean Running = true;
    private String message = "You won!";

    // Set width and height for game window
    GamePlay(Difficulty diff, int rows, Menu m){
        difficulty = diff;
        menu = m;
        Bricks = new LinkedList<Brick>();
        // Create hash set with capacity set to max brick count and load factor of 1 to avoid wasted time
        Brick.setBricks(Bricks, rows, difficulty.getBricks());
        paddle = new Paddle(difficulty);
        ball = new Ball(difficulty);

        // Create seperate sound objects for playing sound bits
        Ball_Border_Sound = new Sound(Sound.BALL_BORDER_SOUND);
        Ball_Paddle_Sound = new Sound(Sound.BALL_PADDLE_SOUND);
        Ball_Brick_Sound = new Sound(Sound.BALL_BRICK_SOUND);
        Win_Sound = new Sound(Sound.YOU_WIN_SOUND);
        Lose_Sound = new Sound(Sound.YOU_LOSE_SOUND);
        
        Score = 0;
        addKeyListener(paddle);   // Paddle class set to handle key inputs
        setFocusable(true);
        setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));   // Set JPanel to that of the JFrame size accordingly

        timer = new Timer(15, new Game());
        timer.start();
    }

    private class Game implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e){
            // Check game status and update accordingly
            updateGame();   
        }
    }

    private void updateGame(){
            try {
                ball.move(Ball_Border_Sound);
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            paddle.move();
        StatusCheck();
        repaint();
        if(!Running){
            int UserChoice = JOptionPane.showConfirmDialog(null, "Return to Menu?", "", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null);
            if(UserChoice == 0){
                BrickBreaker.BB.setVisible(false);
                BrickBreaker.BB.dispose();
                menu.setVisible(true);
            }else{
                // End program if user selects to not return to menu
                System.exit(0);
            }
        }
    }

    private void StatusCheck(){
        // If the ball reaches the bottom of the board, end game
        if(ball.getBall().getMaxY() >= BOARD_HEIGHT){

            Lose_Sound.Play_Sound();

            // Change final message to be displayed on game over screen
            message = "Game Over! You Lost!";
            Running = false;
            timer.stop();
        }

        // Check if every brick has been destroyed
        if(Bricks.isEmpty()){
                
            Win_Sound.Play_Sound();

            // Stop thread for gameplay/graphics 
            Running = false;
            timer.stop();
        }

        // Check if the ball has collided with the paddle for ball to ricochet approprietly
        if(ball.getBall().intersects(paddle.getPaddle())){
            // Reset sound if already played
            Ball_Paddle_Sound.Rewind_Sound();

            // Play respective sound bit for collision
            Ball_Paddle_Sound.Play_Sound();

            // Set ball to bouce off paddle based on current direction of JUST the paddle
            if(paddle.getMoveX() < 0){   
                ball.setMoveX(-difficulty.getBallSpeed());
            }else if( paddle.getMoveX() > 0){
                ball.setMoveX(difficulty.getBallSpeed());
            }
            // Set ball to bounce off paddle vertically no matter what direction it is moving in
            ball.setMoveY(-difficulty.getBallSpeed());
        }
        
        // Check if ball is anywhere in brick area
        for(Brick b : Bricks){
            if(ball.getBall().intersects(b.getRectangle())){
                // Reset sound if already played
                Ball_Brick_Sound.Rewind_Sound();

                // Increase score and play respective sound bit
                Score++;
                Ball_Brick_Sound.Play_Sound();

                if(b.getRectangle().contains(ball.getXPos()-1, ball.getYPos())){
                    ball.setMoveX(difficulty.getBallSpeed());
                }else if(b.getRectangle().contains(ball.getXPos()+ball.getWIDTH()+1, ball.getYPos())){
                    ball.setMoveX(-difficulty.getBallSpeed());
                }
                if(b.getRectangle().contains(ball.getXPos(), ball.getYPos()-1)){
                    ball.setMoveY(difficulty.getBallSpeed());
                }else if(b.getRectangle().contains(ball.getXPos(), ball.getYPos()+ball.getHEIGHT()+1)){
                    ball.setMoveY(-difficulty.getBallSpeed());
                }
                
                // Remove brick from HashSet to avoid being drawn again
                Bricks.remove(b);
                break;  // Assume only one brick can be hit at a time before checking again
            }
        }
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_SPEED);
        g2.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);

        if(Running){
            drawComponents(g2);
        }else{
            GameOver(g2);
        }

        Toolkit.getDefaultToolkit().sync();
    }

    private void drawComponents(Graphics2D g2){
        // Set Color of ball to users selection before drawing
        g2.setColor(BALL_COLOR);
        g2.fillRoundRect(ball.getXPos(), ball.getYPos(), ball.getWIDTH(), ball.getHEIGHT(), 100, 100);
        g2.setColor(TEXT_COLOR);
        g2.drawRoundRect(ball.getXPos(), ball.getYPos(), ball.getWIDTH(), ball.getHEIGHT(), 100, 100);

        // Set color of paddle to users selection before drawing
        g2.setColor(PADDLE_COLOR);
        g2.fillRoundRect(paddle.getXPos(), paddle.getYPos(), paddle.getWIDTH(), paddle.getHEIGHT(), 10, 10);
        g2.setColor(TEXT_COLOR);
        g2.drawRoundRect(paddle.getXPos(), paddle.getYPos(), paddle.getWIDTH(), paddle.getHEIGHT(), 10, 10);

        // Draw bricks not yet destroyed
        for(Brick b : Bricks){
            // Set color of bricks to users selection before drawing
            g2.setColor(BRICK_COLOR);
            g2.fillRect(b.getX(), b.getY(), BRICK_WIDTH, BRICK_HEIGHT);

            g2.setColor(TEXT_COLOR);
            g2.drawRect(b.getX(), b.getY(), BRICK_WIDTH, BRICK_HEIGHT);
        }

        // Update score
        g2.setColor(TEXT_COLOR);
        g2.setFont(new Font(TEXT_FONT, Font.BOLD, 30));
        FontMetrics fm = g2.getFontMetrics(g2.getFont());
        g2.drawString(Integer.toString(Score), BOARD_WIDTH - BRICK_WIDTH, fm.getAscent());
    }

    private void GameOver(Graphics2D g2){
        Font font = new Font(TEXT_FONT, Font.BOLD, 30);
        FontMetrics fontMetrics = this.getFontMetrics(font);

        g2.setColor(Color.BLACK);
        g2.setFont(font);
        g2.drawString(message,
                (BOARD_WIDTH - fontMetrics.stringWidth(message)) / 2, BOARD_HEIGHT / 2);
    }
}
