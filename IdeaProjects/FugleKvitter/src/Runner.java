import javax.sound.sampled.*;
import java.io.*;

/**
 * Created by anders on 03.07.16.
 */
public class Runner {



    public static void main(String [] args){
        AudioFormat format = new AudioFormat(44100, 8, 2, false, false);
        System.out.println("Audio format used is: "+format.toString());
        SpeakerHandler aSpeakerHandler = new SpeakerHandler();
        SourceDataLine aSpeaker = aSpeakerHandler.getSpeakers();

        File file = new File("sample2.wav");
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[705];

        try {
            FileInputStream fis = new FileInputStream(file);
            for(int readNum; (readNum = fis.read(buf)) != -1;){
                bos.write(buf, 0, readNum);
            }

        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        byte[] bytes = bos.toByteArray();
        for(int i = 0; i < 15; i++){
            System.out.print(bytes[i]+" ");
        }
        aSpeakerHandler.playByteArray(bytes);



    /*

        MicHandler aMicHandler = new MicHandler();
        TargetDataLine aMic = aMicHandler.getMicrophone();

        SpeakerHandler aSpeakerHandler = new SpeakerHandler();
        SourceDataLine aSpeaker = aSpeakerHandler.getSpeakers();

        InputHandler inputHandler = new InputHandler(aMic,aSpeaker);

        inputHandler.record();
        */

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
