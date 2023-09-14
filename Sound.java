import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound{
    Sound(String SoundType){
        // Get sound clip and prepare to play for first call (rewind instead of destroying thread)
        try {
            input = AudioSystem.getAudioInputStream(new File(SoundType));
            clip = AudioSystem.getClip();
            clip.open(input);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static String BALL_PADDLE_SOUND = "sounds\\paddleHit.aiff",
                          BALL_BRICK_SOUND = "sounds\\brickHit.aiff",
                          BALL_BORDER_SOUND = "sounds\\woodHit.aiff",
                          YOU_LOSE_SOUND = "sounds\\YouLose.wav",
                          YOU_WIN_SOUND = "sounds\\YouWin.wav";
    private Clip clip; 
    private AudioInputStream input;

    public void Play_Sound() {
        clip.start();
    }
    public void Rewind_Sound(){
        clip.setFramePosition(0);
    }
    public boolean getClipStatus(){
        // Return status of clip [true = clip is running currently, false = it is not and can be rewinded]
        if(clip.isRunning()){
            return true;
        }
        return false;
    }
}