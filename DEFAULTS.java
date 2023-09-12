/*
 *
 * Class for containing default values for the game
 *  - Also includes variables for changing gameplay/difficulties
 * 
 */

import java.awt.Color;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public interface DEFAULTS {
    enum Difficulty{
        EASY(36, 2, 1),
        MEDIUM(48, 1, 2),
        HARD(60, 2, 3),
        IMPOSSIBLE(72, 2, 4);

        private final int bricks, paddleSpeed, ballSpeed;

        private Difficulty(int bricks, int paddleSpeed, int ballSpeed){
            this.bricks = bricks;
            this.paddleSpeed = paddleSpeed;
            this.ballSpeed = ballSpeed;
        }

        public int getBricks(){
            return bricks;
        }
        public int getPaddleSpeed(){
            return paddleSpeed;
        }
        public int getBallSpeed(){
            return ballSpeed;
        }

    }
    // Default svariables for setting up and running brick breaker game
    static int COLUMNS = 12,
               BOARD_WIDTH = 500, 
               BOARD_HEIGHT = 600,
               BRICK_WIDTH = BOARD_WIDTH/(COLUMNS+2),
               BRICK_HEIGHT = BOARD_HEIGHT/(COLUMNS+2), 
               PADDLE_WIDTH = 90,
               PADDLE_HEIGHT = 15,
               BALL_WIDTH = 10,
               BALL_HEIGHT = 10;

    // Default colors for respective components
    static Color BRICK_COLOR = Color.RED,
                 BALL_COLOR = Color.BLACK,
                 PADDLE_COLOR = Color.GRAY,
                 TEXT_COLOR = Color.BLACK;

    // Default sound bits for respective interactions
    static String BALL_PADDLE_SOUND = "sounds\\shortBeep.aiff",
                  BALL_BRICK_SOUND = "sounds\\bloop.aiff";

    // Default class for sound caused by interactions of ball and paddle/brick
    public class Sound{
        private Clip clip; 

        private AudioInputStream input;

        public void PLAY(String sound) throws UnsupportedAudioFileException, IOException, LineUnavailableException{
            input = AudioSystem.getAudioInputStream(new File(sound));
            clip = AudioSystem.getClip();
            clip.open(input);
            clip.start();
        }
    }
}
