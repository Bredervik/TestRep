import javax.sound.sampled.*;
import java.io.ByteArrayOutputStream;

/**
 * Created by anders on 03.07.16.
 */
public class Runner {



    public static void main(String [] args){

        MicHandler aMicHandler = new MicHandler();
        TargetDataLine aMic = aMicHandler.getMicrophone();

        SpeakerHandler aSpeakerHandler = new SpeakerHandler();
        SourceDataLine aSpeaker = aSpeakerHandler.getSpeakers();

        InputHandler inputHandler = new InputHandler(aMic,aSpeaker);

        inputHandler.record();

    }



    private AudioFormat getFormat(){
        float sampleRate = 44100;
        int sampleSizeInBits = 8;
        int channels = 1;
        boolean signed = true;
        boolean bigEndian = true;
        return new AudioFormat(sampleRate,sampleSizeInBits,channels,signed,bigEndian);
    }
}
