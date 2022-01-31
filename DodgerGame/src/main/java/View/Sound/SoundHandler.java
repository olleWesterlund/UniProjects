package View.Sound;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.File;

/**
 * @Author Viktor Sundberg (viktor.sundberg@icloud.com)
 */

public class SoundHandler {

    private double volume = 0.01;

    //Plays backgroundmusic on loop
    public void musicPlayer(String musicFilepath) {

        try {
            File soundPath = new File(musicFilepath);

            if(soundPath.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(soundPath);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                calculateDbVolume(volume, clip);
                clip.start();
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            }

        } catch(Exception e) {
            e.printStackTrace();
            System.out.println("Background music file missing");
        }
    }

    //For all singular instances of sound effects (collisions etc.)
    public void soundFx(String fxFilePath, double vol) {

        try {
            File fxPath = new File(fxFilePath);

            if(fxPath.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(fxPath);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                calculateDbVolume(vol, clip);
                clip.start();
            }

        } catch(Exception e) {
            e.printStackTrace();
            System.out.println("Fx file missing");
        }
    }

    //Calculates a double to decibel range and sets volume
    private static void calculateDbVolume(double vol, Clip clip){
        FloatControl gain = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
        float decibel = (float) (Math.log(vol) / Math.log(10) * 20);
        gain.setValue(decibel);
    }

    // Plays a sound
    public void playSound(String className) {
        String soundFilepath = null;

        if (className.equals("Asteroid")) {
            soundFilepath = GameObjectsSounds.getAsteroidSound();
        } else if (className.equals("ShieldPowerUp")) {
            soundFilepath = GameObjectsSounds.getShieldSound();
        } else if (className.equals("HealthPowerUp")) {
            soundFilepath = GameObjectsSounds.getHealthSound();
        } else if (className.equals("SlowDebuff")) {
            soundFilepath = GameObjectsSounds.getSlowDebuffSound();
        } else if(className.equals("LaserBeam")) {
            soundFilepath = GameObjectsSounds.getLaserBeamSound();
        }

        if(soundFilepath != null) {
            soundFx(soundFilepath, volume);
        }
    }
}