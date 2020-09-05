package objects;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class AudioOutputOverHead {

    public static void playAudio(String filename){
        try{
            File audioPath = new File(filename);
            if(audioPath.exists()){
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audioPath);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

