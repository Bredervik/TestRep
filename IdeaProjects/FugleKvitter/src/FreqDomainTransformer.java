import com.sun.media.sound.FFT;

import static sun.awt.X11.XConstants.Complex;

/**
 * Created by anders on 05.07.16.
 */
public class FreqDomainTransformer {
    private int CHUNK_SIZE = 4096;

    public  FreqDomainTransformer() {

    }

    public Complex[][] freqTransformer(byte[] timeDomainBytes){
        int amountPossible = timeDomainBytes.length/CHUNK_SIZE;

        Complex[][] results = new Complex[amountPossible][];

        for(int times = 0; times < amountPossible; times++) {
            Complex[] complex = new Complex[CHUNK_SIZE];
            for (int i = 0; i < CHUNK_SIZE; i++) {
                complex[i] = new Complex(timeDomainBytes[(times * CHUNK_SIZE) + i], 0);
            }
            results[times] = FFT.fft(complex);
        }

    }
}
