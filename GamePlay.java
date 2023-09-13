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
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.Timer;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GamePlay extends JPanel implements DEFAULTS{
    
    private Brick[] Bricks;
    private Paddle paddle;
    private Ball ball;
    private Timer timer;
    private Difficulty difficulty;
    private Sound GameSounds;
    private Menu menu;
    private int Score;
    private boolean Running = true;
    private String message = "You won!";

    // Set width and height for game window
    GamePlay(Difficulty difficulty, int rows, Menu menu){
        this.difficulty = difficulty;
        this.menu = menu;

        Bricks = new Brick[difficulty.getBricks()];
        Brick.setBricks(Bricks, rows);
        paddle = new Paddle(difficulty);
        ball = new Ball(difficulty);
        GameSounds = new Sound();
        Score = 0;
        addKeyListener(paddle);   // Paddle class set to handle key inputs
        setFocusable(true);
        setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));   // Set JPanel to that of the JFrame size accordingly

        timer = new Timer(0, new Game());
        timer.start();
    }

    private class Game implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e){
            updateGame();   // Check game status and update accordingly
        }
    }

    private void updateGame(){
        try{
            ball.move(GameSounds);
            paddle.move(GameSounds);
        }catch(UnsupportedAudioFileException | IOException | LineUnavailableException e){
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        StatusCheck();
        repaint();
        if(!Running){
            int UserChoice = JOptionPane.showConfirmDialog(null, "Return to Menu?", "", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null);
            if(UserChoice == 0){
                this.setVisible(false);
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
            try {
                GameSounds.GAME_OVER_sound(YOU_LOSE_SOUND);
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            message = "Game Over! You Lost!";
            Running = false;
            timer.stop();
        }

        // Check status of each brick
        boolean empty = true;
        for(int i = 0; i < Bricks.length; i++){
            if(!Bricks[i].checkStatus()){
                empty = false;
                break;
            }
        }
        if(empty){
            try {
                GameSounds.GAME_OVER_sound(YOU_WIN_SOUND);
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            Running = false;
            timer.stop();
        }

        // Check if the ball has collided with the paddle for ball to ricochet approprietly
        if(ball.getBall().intersects(paddle.getPaddle())){
            try {
                GameSounds.HIT_sound(BALL_PADDLE_SOUND);
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            int p = (int) paddle.getPaddle().getMinX();
            int b = (int) ball.getBall().getMinX();

            if(b < p+(paddle.getWIDTH()/3)){
                ball.setMoveX(-(difficulty.getBallSpeed()));
                ball.setMoveY(-(difficulty.getBallSpeed()));
            }else if(b >= p+(paddle.getWIDTH()/3) && b < p+((2*paddle.getWIDTH())/3)){
                ball.setMoveX(0);
                ball.setMoveY(-(difficulty.getBallSpeed()));
            }else{
                ball.setMoveX(difficulty.getBallSpeed());
                ball.setMoveY(-(difficulty.getBallSpeed()));
            }
        }

        for(int i = 0; i < Bricks.length; i++){
            if(ball.getBall().intersects(Bricks[i].getRectangle())){
                if(!Bricks[i].checkStatus()){
                    Score++;
                    try {
                        GameSounds.HIT_sound(BALL_BRICK_SOUND);
                    } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    if(Bricks[i].getRectangle().contains(ball.getXPos()-1, ball.getYPos())){
                        ball.setMoveX(difficulty.getBallSpeed());
                    }else if(Bricks[i].getRectangle().contains(ball.getXPos()+ball.getWIDTH()+1, ball.getYPos())){
                        ball.setMoveX(-difficulty.getBallSpeed());
                    }

                    if(Bricks[i].getRectangle().contains(ball.getXPos(), ball.getYPos()-1)){
                        ball.setMoveY(difficulty.getBallSpeed());
                    }else if(Bricks[i].getRectangle().contains(ball.getXPos(), ball.getYPos()+ball.getHEIGHT()+1)){
                        ball.setMoveY(-difficulty.getBallSpeed());
                    }
                }
                Bricks[i].setStatus();
            }
        }

    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        
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
        for(int i = 0; i < Bricks.length; i++){
            if(!Bricks[i].checkStatus()){
                // Set color of bricks to users selection before drawing
                g2.setColor(BRICK_COLOR);
                g2.fillRect(Bricks[i].getX(), Bricks[i].getY(), BRICK_WIDTH, BRICK_HEIGHT);

                g2.setColor(TEXT_COLOR);
                g2.drawRect(Bricks[i].getX(), Bricks[i].getY(), BRICK_WIDTH, BRICK_HEIGHT);
            }
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