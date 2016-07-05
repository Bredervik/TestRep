import javax.sound.sampled.*;

/**
 * Created by anders on 04.07.16.
 */
public class SpeakerHandler {
    private SourceDataLine speakers;
    private AudioFormat format = new AudioFormat(8000.0f, 16, 1, true, true);

    public SpeakerHandler(){
        try {
            System.out.println("Preparing speakers");
            DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class, format);
            this.speakers = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
        }
        catch (LineUnavailableException e){
            //Error handling
            e.printStackTrace();
        }
    }
    public SourceDataLine getSpeakers(){
        return speakers;
    }
    public void playByteArray(byte[] aSound){
        try {
            System.out.println("Sound will now be played.");
            speakers.open(format);
            speakers.start();
            speakers.write(aSound, 0, aSound.length);
            speakers.drain();
            speakers.close();
        }
        catch (LineUnavailableException e){
            e.printStackTrace();
        }
    }
}
