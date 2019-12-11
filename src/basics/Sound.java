package basics;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound {
	private Clip myClip;
    public Sound(String fileName) {
            try {
                File file = new File(fileName);
                if (file.exists()) {
                    myClip = AudioSystem.getClip();
                    AudioInputStream ais = AudioSystem.getAudioInputStream(file.toURI().toURL());
                    myClip.open(ais);
                }
                else {
                    throw new RuntimeException("Sound: file not found: " + fileName);
                }
            }
            catch (MalformedURLException e) {
                throw new RuntimeException("Sound: Malformed URL: " + e);
            }
            catch (UnsupportedAudioFileException e) {
                throw new RuntimeException("Sound: Unsupported Audio File: " + e);
            }
            catch (IOException e) {
                throw new RuntimeException("Sound: Input/Output Error: " + e);
            }
            catch (LineUnavailableException e) {
                throw new RuntimeException("Sound: Line Unavailable: " + e);
            }
    }
    public void play(){
        myClip.setFramePosition(10000);  // Must always rewind!
        myClip.loop(0);
        myClip.start();
    }
    public void loop(){
        myClip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop(){
        myClip.stop();
    }
    
    public float getVolume() {
        FloatControl gainControl = (FloatControl) myClip.getControl(FloatControl.Type.MASTER_GAIN);        
        return (float) Math.pow(10f, gainControl.getValue() / 20f);
    }

    public void setVolume(float volume) {
        if (volume < 0f || volume > 1f)
            throw new IllegalArgumentException("Volume not valid: " + volume);
        FloatControl gainControl = (FloatControl) myClip.getControl(FloatControl.Type.MASTER_GAIN);        
        gainControl.setValue(20f * (float) Math.log10(volume));
    }
    
}
