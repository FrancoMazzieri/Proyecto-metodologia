package Graphics;

import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

/**
 *
 * @author Soledad Mineo
 */
public class Sound {
    private Clip clip;
    private FloatControl volumen;
    
    public Sound(Clip clip){
        this.clip = clip;
        volumen = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
    }
    //para iniciar el sonido en el juego
    public void play(){
        clip.setFramePosition(0);
        clip.start();
    }
    
    public void loop(){
        clip.setFramePosition(0);
        clip.loop(Clip.LOOP_CONTINUOUSLY); //se reproduce el sonido en forma infinita
    }
    //para detener el sonido 
    public void stop(){
        clip.stop();
    }
    
    public int getFramePosition(){
        return clip.getFramePosition();
    }
    
    public void changeVolumen(float value){
        volumen.setValue(value);
    }
}
