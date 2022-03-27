package cesak.matur;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Objects;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import javax.sound.sampled.*;

public class SoundManager
{
    // region Singleton

    private static final SoundManager soundManager = new SoundManager();

    private SoundManager()
    {

    }

    public static SoundManager getInstance()
    {
        return soundManager;
    }

    // endregion

    public void playSound(String fileName)
    {
        try
        {
            File file = new File(Objects.requireNonNull(getClass().getClassLoader().getResource("sounds/" + fileName + ".wav")).getFile());
            AudioInputStream stream;
            AudioFormat format;
            DataLine.Info info;
            Clip clip;

            stream = AudioSystem.getAudioInputStream(file);
            format = stream.getFormat();
            info = new DataLine.Info(Clip.class, format);
            clip = (Clip) AudioSystem.getLine(info);
            clip.open(stream);
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-5.0f);
            clip.start();
        } catch (Exception ignored)
        {
        }
    }
}
