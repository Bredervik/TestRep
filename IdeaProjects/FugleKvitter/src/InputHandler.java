import javax.sound.sampled.*;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Line.Info;
/**
 * Created by anders on 03.07.16.
 */
public class InputHandler {

    private final AudioFormat format = getFormat(); // Denne er grei
    private DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);

    if (!AudioSystem.isLineSupported((Line.Info) info) {
        // Handle the error ...

    }

    private final TargetDataLine line = (TargetDataLine) AudioSystem.getLine();
    line.open(format);
    line.start();


    private AudioFormat getFormat(){
        float sampleRate = 44100;
        int sampleSizeInBits = 8;
        int channels = 1;
        boolean signed = true;
        boolean bigEndian = true;
        return new AudioFormat(sampleRate,sampleSizeInBits,channels,signed,bigEndian);
    }

// Obtain and open the line.
    try {
        line = (TargetDataLine) AudioSystem.getLine(info);
        line.open(format);
    } catch (LineUnavailableException ex) {
        // Handle the error ...
    }
}
