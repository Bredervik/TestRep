import javax.sound.sampled.*;
import java.io.ByteArrayOutputStream;

/**
 * Created by anders on 03.07.16.
 */
public class Runner {



    public static void main(String [] args){
        TargetDataLine microphone;
        int lastRead = -1;
        SourceDataLine speakers;
        AudioFormat format = new AudioFormat(44100,8,1,true,true);


        try {
            // Åpner mikrofonen slik at en kan ta verdier fra den.
            System.out.println("Åpner mikrofonen slik at en kan ta verdier fra den.");
            microphone = AudioSystem.getTargetDataLine(format);
            DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
            microphone = (TargetDataLine) AudioSystem.getLine(info);
            microphone.open(format);

            // Gjør klart til innlesing
            System.out.println("Gjør klart til innlesing");
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int numberOfBytesRead;
            int CHUNk_SIZE = 1024;
            byte[] data = new byte[microphone.getBufferSize() / 5];

            //Skriver til stream
            System.out.println("Skriver til stream");
            int bytesRead = 0;
            DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class,format);
            speakers = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
            speakers.open(format);
            speakers.start();
            while(bytesRead < 500) {
                numberOfBytesRead = microphone.read(data, 0, CHUNk_SIZE);
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
            microphone.close();
            System.out.println("Ferdig");
        }
        catch (LineUnavailableException e){
            System.out.println("Ting gikk til piss");
            e.printStackTrace();
        }

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
