import javax.sound.sampled.*;

/**
 * Created by anders on 04.07.16.
 */
public class MicHandler {
    private TargetDataLine microphone;
    private AudioFormat format = new AudioFormat(8000.0f, 16, 1, true, true);


    public MicHandler(){

        try {
            System.out.println("Åpner mikrofonen slik at en kan ta verdier fra den.");
            DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
            this.microphone = (TargetDataLine) AudioSystem.getLine(info);
        }
        catch(LineUnavailableException e){
            //Errorhandling
            e.printStackTrace();
        }
    }

    public TargetDataLine getMicrophone() {
        return microphone;
    }
}
