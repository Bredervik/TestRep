import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.TargetDataLine;
import java.io.ByteArrayOutputStream;


/**
 * Created by anders on 03.07.16.
 */
public class InputHandler {
    private TargetDataLine mic;
    private SourceDataLine speakers;
    private AudioFormat format = new AudioFormat(8000.0f, 16, 1, true, true);
    private int numberOfRecordingBits = 1000;

    public InputHandler(TargetDataLine microphone,SourceDataLine loudSpeaker){
        this.mic = microphone;
        this.speakers = loudSpeaker;
    }
    public void record(){
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int numberOfBytesRead;
        int CHUNk_SIZE = 1024;
        byte[] data = new byte[mic.getBufferSize() / 5];
        int bytesRead = 0;
        int lastRead = -1;


        try {
            mic.open(format);
            speakers.open(format);
            speakers.start();
            while(bytesRead < numberOfRecordingBits) {
                numberOfBytesRead = mic.read(data, 0, CHUNk_SIZE);
                bytesRead += numberOfBytesRead;
                //
                out.write(data,0,numberOfBytesRead);
                //
                speakers.write(data, 0, numberOfBytesRead);
                if(bytesRead != lastRead){
                    System.out.println(bytesRead);
                    lastRead = bytesRead;
                }
            }
            System.out.print("Nå skal ting skje snart!");
            speakers.drain();
            speakers.close();
            mic.close();
            System.out.println("Ferdig");
        }
        catch (LineUnavailableException e){
            e.printStackTrace();
        }

    }


}
